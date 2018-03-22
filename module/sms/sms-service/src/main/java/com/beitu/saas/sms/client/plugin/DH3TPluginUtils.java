/**
 * yuntu-inc.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.beitu.saas.sms.client.plugin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.beitu.saas.sms.domain.SmsMsgLinkInfo;
import com.fqgj.common.utils.MD5;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.fqgj.log.util.TraceIdGenerator;
import com.beitu.saas.sms.domain.SmsMsgReport;
import com.beitu.saas.sms.util.HttpUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 大汉三通短信服务
 *
 * @author liting
 * @version $Id: DH3TPluginUtils.java, v 0.1 2017年9月25日 下午4:41:36 liting Exp $
 * @name 大汉三通短信服务
 */
public class DH3TPluginUtils {
    private static Log LOGGER = LogFactory.getLog(DH3TPluginUtils.class);
    private static final String CHAR_SET = "UTF-8";
    private static final String URL_ADDR_RPT = "/json/client/Report";
    private static final String VOICE_URL_ADDR_RPT = "/json/voiceSms/GetReport";

    /**
     * 单条短信
     *
     * @param linkInfo
     * @param phone
     * @param content
     * @return
     * @name 单条短信
     * @author liting
     * @date: 2017年9月25日 下午4:43:13
     */
    public static String send(SmsMsgLinkInfo linkInfo, String phone, String content, String appSign) {
        String url = linkInfo.getUrl();
        String msgId = doSend(linkInfo, phone, content, url, appSign);
        return msgId;
    }

    /**
     * 语音验证码
     * @return
     */
    public static Map<String, Object> sendVoiceVerify(SmsMsgLinkInfo linkInfo, String phone, String content){
        String account = linkInfo.getUserName();
        String password = linkInfo.getPassword();
        String url = linkInfo.getUrl();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        String msgId = TraceIdGenerator.generate();
        try {
            List<Map<String, Object>> dataList = Lists.newArrayList();
            Map<String, Object> data = Maps.newHashMap();
            data.put("msgid", msgId);//消息编号ID，必须唯一，不可为空，最大长度64位
            data.put("callee", phone);//被叫号码。只能有一个号码。
            data.put("text", content);//验证码内容，只允许4~8位的数字验证码
            data.put("playmode", 0);//放音模式： 0-只播放文本
            data.put("calltype", 1);//外呼类型：1-验证码呼叫
            dataList.add(data);

            Map<String, Object> params = Maps.newHashMap();
            params.put("account", account);
            params.put("password", MD5.md5(password));
            params.put("data", dataList);//支持批量
            LOGGER.info("sendVoiceMsgParams: {}", params);
            String result = HttpUtils.post(url, JSON.toJSONString(params), CHAR_SET);
            LOGGER.info("use DH3T send voice client msg phone:" + phone + ",content:" + content + " ,result:" + result);
            JSONObject object = JSON.parseObject(result);
            if ("DH:0000".equals(object.getString("result"))) { //呼叫成功
                dataMap.put("msgId", msgId);
            }
            //todo 暂不处理
//            if ("DH:1011".equals(object.getString("result"))){ //账户余额不足
//                dataMap.put("systemCode", "jkzj-openapi");//apollo上配置
//                dataMap.put("message", "【大汉三通】语音验证码发送失败：".concat(object.getString("desc")).concat(",account：").concat(account).concat(",password：").concat(password));
//            }
        } catch (Exception e) {
            LOGGER.info("use DH3T send voice client msg phone:" + phone + ",content:" + content, e);
        }
        return dataMap;
    }

    private static String doSend(SmsMsgLinkInfo linkInfo, String phones, String content,
                                 String url, String appSign) {
        String thirdMsg = null;
        String account = linkInfo.getUserName();
        String password = linkInfo.getPassword();
        String sign = appSign;

        try {
            Map<String, String> params = Maps.newHashMap();
            params.put("account", account);
            params.put("password", MD5.md5(password));
            params.put("sign", sign);
            params.put("phones", phones);
            params.put("content", content);
            LOGGER.info("sendMsgParams: {}", params);
            String result = HttpUtils.post(url, JSON.toJSONString(params), CHAR_SET);
            LOGGER.info("use DH3T send client msg phone:" + phones + ",content:" + content + " ,result:" + result);
            JSONObject object = JSON.parseObject(result);
            if ("0".equals(object.getString("result"))) {
                thirdMsg = object.getString("msgid");
            }
        } catch (Exception e) {
            LOGGER.info("use DH3T send client msg phone:" + phones + ",content:" + content, e);
        }
        return thirdMsg;
    }

    /**
     * 语音验证码-获取状态报告
     * @param linkInfo
     * @return
     */
    public static List<SmsMsgReport> getVoiceReport(SmsMsgLinkInfo linkInfo) {
        long startTime = System.currentTimeMillis();
        List<SmsMsgReport> reports = Lists.newArrayList();
        String url = linkInfo.getUrl() + VOICE_URL_ADDR_RPT;
        String account = linkInfo.getUserName();
        String password = linkInfo.getPassword();
        try {
            Map<String, String> params = Maps.newHashMap();
            params.put("account", account);
            params.put("password", MD5.md5(password));
            String result = HttpUtils.post(url, JSON.toJSONString(params), CHAR_SET);
            LOGGER.info("use DH3T voice getReporz client msg ,result:" + result);
            if (null != result) {
                JSONObject obj = JSON.parseObject(result);
                String code = obj.getString("result");
                if (!"DH:0000".equals(code)) {
                    return null;
                }
                JSONArray rpts = obj.getJSONArray("data");
                if (null == rpts){
                    return null;
                }
                Iterator it = rpts.iterator();
                while (it.hasNext()) {
                    JSONObject json = (JSONObject) it.next();
                    SmsMsgReport report = new SmsMsgReport();
                    report.setThirdMsgId(json.getString("msgid"));
                    //状态DH:0004成功，其余均失败
                    report.setStatus("DH:0000".equals(json.getString("status")) ? true : false);
                    reports.add(report);
                }
                return reports;
            }
            long endTime = System.currentTimeMillis();
            LOGGER.info("DH3T voice getReport cost:" + (endTime - startTime));
        } catch (Exception e) {
            LOGGER.info("DH3T voice getReport client msg error,linkInfo:" + linkInfo.toString(), e);
        }
        return null;
    }

    /**
     * 短信验证码-获取状态报告
     *
     * @param linkInfo
     * @return
     * @name 获取状态报告
     * @author liting
     * @date: 2017年9月25日 下午5:43:30
     */
    public static List<SmsMsgReport> getReport(SmsMsgLinkInfo linkInfo) {
        long startTime = System.currentTimeMillis();
        List<SmsMsgReport> reports = Lists.newArrayList();
        String url = linkInfo.getUrl() + URL_ADDR_RPT;
        String account = linkInfo.getUserName();
        String password = linkInfo.getPassword();
        try {
            Map<String, String> params = Maps.newHashMap();
            params.put("account", account);
            params.put("password", MD5.md5(password));
            String result = HttpUtils.post(url, JSON.toJSONString(params), CHAR_SET);
            LOGGER.info("use DH3T getReport client msg ,result:" + result);
            if (null != result) {
                JSONObject obj = JSON.parseObject(result);
                String code = obj.getString("result");
                if (!"0".equals(code)) {
                    return null;
                }
                JSONArray rpts = obj.getJSONArray("reports");
                Iterator it = rpts.iterator();
                while (it.hasNext()) {
                    JSONObject json = (JSONObject) it.next();
                    SmsMsgReport report = new SmsMsgReport();
                    report.setThirdMsgId(json.getString("msgid"));
                    //状态10成功，11失败
                    report.setStatus("10".equals(json.getString("status")) ? true : false);
                    reports.add(report);
                }
                return reports;
            }
            long endTime = System.currentTimeMillis();
            LOGGER.info("MENGWANG getReport cost:" + (endTime - startTime));
        } catch (Exception e) {
            LOGGER.info("DH3T getReport client msg error,linkInfo:" + linkInfo.toString(), e);
        }
        return null;
    }


    public static void main(String[] args) {
        test();
    }

    public static List<SmsMsgReport> test() {
        SmsMsgLinkInfo linkInfo = new SmsMsgLinkInfo();
        linkInfo.setUserName("dh69021");
        linkInfo.setPassword("2sW9iQ0o");
//        linkInfo.setUrl("http://voice.3tong.net/json/voiceSms/SubmitVoc");
        linkInfo.setUrl("http://voice.3tong.net");
//        String appSign = "【金壹贷】";
//        send(linkInfo, "13067702413", "（到期提醒）您的2500.00元借款第4期账单已于今天到期，本期待还金额为479.17元，请及时还款以免逾期产生罚息。", appSign);
//        linkInfo.setUrl("http://www.dh3t.com");
//        getReport(linkInfo);

        return getVoiceReport(linkInfo);
//        if(CollectionUtils.isEmpty(smsMsgReports)){
//            return;
//        }
//        //成功的三方消息id
//        List<String> successThirdMsgIds = Lists.newArrayList();
//        //失败的三方消息id
//        List<String> failThirdMsgIds = Lists.newArrayList();
//
//        for(SmsMsgReport smsMsgReport:smsMsgReports){
//            if (smsMsgReport.getStatus()) {
//                successThirdMsgIds.add(smsMsgReport.getThirdMsgId());
//            }else{
//                failThirdMsgIds.add(smsMsgReport.getThirdMsgId());
//            }
//        }
//        if(!CollectionUtils.isEmpty(successThirdMsgIds)){
//            //更新发送成功状态
////            messageDao.updateMesageStatus(successThirdMsgIds, MsgStatusEnum.SUCCESS.getCode());
//            LOGGER.info("================成功================");
//        }
//        if(!CollectionUtils.isEmpty(failThirdMsgIds)){
//            //更新发送失败状态
////            messageDao.updateMesageStatus(failThirdMsgIds, MsgStatusEnum.SEND_FAIL.getCode());
//            LOGGER.info("================失败================");
//
//        }

    }

}
