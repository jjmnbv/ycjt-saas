package com.beitu.saas.sms.service.sms.impl;

import com.alibaba.fastjson.JSON;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.sms.client.plugin.*;
import com.beitu.saas.sms.domain.SmsMsgLinkInfo;
import com.beitu.saas.sms.model.BusinessRefInfo;
import com.beitu.saas.sms.ro.SmsMsgBatchContentRO;
import com.beitu.saas.sms.util.DataQueueUtils;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.beitu.saas.sms.dao.SmsMessageDao;
import com.beitu.saas.sms.model.MsgInfo;
import com.beitu.saas.sms.enums.Contans;
import com.beitu.saas.sms.enums.MsgTypeEnum;
import com.beitu.saas.sms.enums.ServicerEnum;
import com.beitu.saas.sms.enums.SmsTypeEnum;
import com.beitu.saas.sms.ro.BatchSmsSendRquestRO;
import com.beitu.saas.sms.ro.SingleSmsSendRequestRO;
import com.beitu.saas.sms.ro.StateCode;
import com.beitu.saas.sms.service.sms.SmsMsgDataService;
import com.beitu.saas.sms.util.CacheKey;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 短信服务逻辑处理
 */
@Service
public class SmsMsgDataServiceImpl implements SmsMsgDataService {

    private static Log LOGGER = LogFactory.getLog(SmsMsgDataService.class);

    private static final int PAGE_SIZE = 1000;

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private SmsMessageDao messageDao;

    @Autowired
    private RedisClient redisClient;

    /**
     * @see SmsMsgDataService#singleSend(SingleSmsSendRequestRO)
     */
    @Override
    public StateCode singleSend(SingleSmsSendRequestRO single) {
        //塞队列
        if (DataQueueUtils.getSmsInstance().add(single)) {
            return StateCode.SUCCESS;
        }
        LOGGER.info("client msg queue is oom,please check the service");
        return StateCode.QUEUE_OOM;
    }

    /**
     * @see SmsMsgDataService#batchSend(BatchSmsSendRquestRO)
     */
    @Override
    public StateCode batchSend(BatchSmsSendRquestRO batch) {
        List<SingleSmsSendRequestRO> singles = Lists.newArrayList();
        for (SmsMsgBatchContentRO contentRO : batch.getContents()) {
            SingleSmsSendRequestRO single = new SingleSmsSendRequestRO();
            single.setBizCode(batch.getBizCode());
            single.setPhone(contentRO.getPhone());
            single.setSendTime(batch.getSendTime());
            single.setType(SmsTypeEnum.NOTIFY.getCode());
            single.setReplaceParam(contentRO.getReplaceParam());
            singles.add(single);
        }
        //塞队列
        if (DataQueueUtils.getSmsInstance().addAll(singles)) {
            return StateCode.SUCCESS;
        }
        LOGGER.info("client msg queue is oom,please check the service");
        return StateCode.QUEUE_OOM;
    }

    @PostConstruct
    public void dealWithSms() {
        LOGGER.info("conusmer client msg start!");
        new Thread() {
            @SuppressWarnings("static-access")
            @Override
            public void run() {
                while (true) {
                    List<SingleSmsSendRequestRO> elements = DataQueueUtils.batchSmsGetElements(PAGE_SIZE);
                    if (CollectionUtils.isEmpty(elements)) {
                        try {
                            sleep(5000);
                        } catch (InterruptedException e) {
                            LOGGER.info("conusmer client msg main thread sleep 5 s error," + e);
                        }
                        continue;
                    }
                    for (SingleSmsSendRequestRO element : elements) {
                        try {
                            doSend(element.getPhone(), element.getBizCode(), element.getReplaceParam(), element.getType(), element.getSendTime());
                        } catch (Exception e) {

                        }
                    }
                }
            }
        }.start();
    }

    /**
     * @param phone
     * @param bizCode
     * @param replaceParam
     * @param type
     * @param sendTime
     * @return
     */
    @Override
    public StateCode doSend(String phone, String bizCode, Map<String, String> replaceParam, Integer type, Date sendTime) {
        String value = redisClient.get(CacheKey.getKeyOfMessageConfig(bizCode));
        List<BusinessRefInfo> configList = JSON.parseArray(value, BusinessRefInfo.class);
        if (CollectionUtils.isEmpty(configList)) {
            LOGGER.info("配置信息错误, biz_code = ：{}", bizCode);
        }
        configList.sort(Comparator.comparingInt(BusinessRefInfo::getRank));
        BusinessRefInfo businessRefInfo = configList.get(0);
        String key = CacheKey.getKeyOfSmsMessageSendRecord(phone, bizCode);
        //配置切换时间
        Integer messageSwitchTime = configUtil.getMessageSwitchTime();
        //判断是否在上次发送短信的时间缓存内
        if (!redisClient.setnx(key, String.valueOf(businessRefInfo.getAppServicerId()))) {
            //更换别的服务商，如果有的话
            if (configList.size() > 1) {
                businessRefInfo = configList.get(1);
            }
        }
        redisClient.expire(key, messageSwitchTime);
        MsgInfo msgInfo = packMsg(phone, bizCode, businessRefInfo, replaceParam, type, sendTime, businessRefInfo.getAppSign());
        String result;
        // 即时发送
        if (null == sendTime) {
            result = dealSingleSend2(bizCode, businessRefInfo, msgInfo, type);
            result = StringUtils.isEmpty(result) ? Contans.DEFAULT_THIRD_MSGID : result;
            msgInfo.setThirdMsgId(result);
            if (Contans.DEFAULT_THIRD_MSGID.equals(result)) {//发送失败
                msgInfo.setStatus(2);
            }
        }
        // 消息入库
        messageDao.saveMessage(msgInfo);
        return StateCode.SUCCESS;

    }

    private MsgInfo packMsg(String phone, String bizCode, BusinessRefInfo info,
                            Map<String, String> replaceParam, Integer type, Date sendTime, String appSign) {
        String thirdTmpId = null; // 第三方模板ID
        String replaceList = info.getReplaceList();
        String content = info.getContent();
        if ("1".equals(info.getTemplateType())) { // 本地模板
            if (!StringUtils.isEmpty(replaceList)) {
                List<String> replaces = Lists.newArrayList(replaceList.split(","));
                for (String replace : replaces) {
                    String replaceStr = replaceParam.get(replace.trim());
                    if (null != replaceStr) {
                        content = content.replaceAll("##" + replace + "##", replaceStr);
                    }
                }
            }
        }
        if ("2".equals(info.getTemplateType())) { // 第三模板
            thirdTmpId = info.getContent();
            if (type == 1) {
                content = replaceParam.get("VERIFY_CODE");
            } else {
                content = JSON.toJSONString(replaceParam);
            }

        }
        MsgInfo msgInfo = new MsgInfo();
        msgInfo.setMsgTmpId(Integer.parseInt(String.valueOf(info.getMessageTemplateId())));
        msgInfo.setThirdMsgId(thirdTmpId);
        msgInfo.setBizCode(bizCode);
        msgInfo.setServicerCode(info.getServicerCode());
        msgInfo.setReceiver(phone);
        msgInfo.setContent(content);
        msgInfo.setSendType(0); // 单发
        msgInfo.setMsgType(Integer.parseInt(info.getMsgType()));
        msgInfo.setAppSign(appSign);
        Date now = new Date();
        if (null == sendTime) {
            msgInfo.setAgeingType(0);
            msgInfo.setSendTime(now);
            msgInfo.setStatus(1);
        } else {
            msgInfo.setAgeingType(1);
            msgInfo.setStatus(0);
            msgInfo.setSendTime(sendTime);
            msgInfo.setThirdMsgId("0");
        }
        return msgInfo;
    }


    private String dealSingleSend2(String bizCode, BusinessRefInfo info,
                                   MsgInfo msgInfo, Integer type) {
        if (configUtil.isServerTest()) {
            String phones = configUtil.getTestWhiteList();
            if (StringUtils.isEmpty(phones) || !phones.contains(msgInfo.getReceiver())) {
                LOGGER.info("phone:" + msgInfo.getReceiver() + ",is not in test white list!");
                return Contans.DEFAULT_THIRD_MSGID;
            }
        }

        SmsMsgLinkInfo linkInfo = JSON.parseObject(info.getLinkInfo(), SmsMsgLinkInfo.class);
        linkInfo.setUrl(linkInfo.getUrl() + linkInfo.getInterfaceUrl());
        String thirdTmpId = msgInfo.getThirdMsgId();
        String content = msgInfo.getContent();
        String result = null;
        //服务商选择
        LOGGER.info("make decsion let" + info.getServicerCode() + " do the message send!,phone:" + msgInfo.getReceiver() + ",bizCode:" + bizCode);
        if (ServicerEnum.YEGOU.getCode().equals(info.getServicerCode())) {
            //野狗
            //验证码类型
            if (type == 1) {
                result = YeGouPluginUtils.sendVerifyCode(msgInfo.getReceiver(), content, linkInfo, thirdTmpId);
            } else {
                result = YeGouPluginUtils.sendContent(msgInfo.getReceiver(), linkInfo, content);
            }
        } else if (ServicerEnum.MENGWANG.getCode().equals(info.getServicerCode())) {
            //梦网
            result = MengWangPluginUtils.send(linkInfo, msgInfo.getReceiver(), content);
        } else if (ServicerEnum.JIANZHOU.getCode().equals(info.getServicerCode())) {
            //建州
            content = content + info.getAppSign();
            result = JianZhouPluginUtils.send(linkInfo, msgInfo.getReceiver(), content);
        } else if (ServicerEnum.DH3T.getCode().equals(info.getServicerCode())) {
            if (String.valueOf(MsgTypeEnum.VOICE_MSG.getCode()).equals(info.getMsgType())) {
                Map<String, Object> dataMap = DH3TPluginUtils.sendVoiceVerify(linkInfo, msgInfo.getReceiver(), content);
                if (null != dataMap.get("msgId")) {
                    result = dataMap.get("msgId").toString();
                }
            } else {
                result = DH3TPluginUtils.send(linkInfo, msgInfo.getReceiver(), content, info.getAppSign());
            }

        } else if (ServicerEnum.WEIWANG.getCode().equals(info.getServicerCode())) {
            content = info.getAppSign() + content;
            result = WeiwanPluginUtils.send(linkInfo, msgInfo.getReceiver(), content);
        } else if (ServicerEnum.DH3T_VOICE.getCode().equals(info.getServicerCode())) {
            Map<String, Object> dataMap = DH3TPluginUtils.sendVoiceVerify(linkInfo, msgInfo.getReceiver(), content);
            if (null != dataMap.get("msgId")) {
                result = dataMap.get("msgId").toString();
            }
        }
        return result;
    }

}
