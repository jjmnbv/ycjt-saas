package com.beitu.saas.app.application;

import com.beitu.saas.app.enums.SaasSmsTypeEnum;
import com.beitu.saas.auth.domain.SaasMerchantVo;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.auth.entity.SaasMerchantConfig;
import com.beitu.saas.auth.service.SaasAdminService;
import com.beitu.saas.auth.service.SaasMerchantConfigService;
import com.beitu.saas.auth.service.SaasMerchantService;
import com.beitu.saas.borrower.client.SaasBorrowerService;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.beitu.saas.finance.client.SaasSmsHistoryService;
import com.beitu.saas.sms.client.SmsMsgService;
import com.beitu.saas.sms.enums.MessageSendErrorCodeEnum;
import com.beitu.saas.sms.enums.SmsTypeEnum;
import com.beitu.saas.sms.enums.VerifyCodeTypeEnum;
import com.beitu.saas.sms.ro.BatchSmsSendRquestRO;
import com.beitu.saas.sms.ro.Result;
import com.beitu.saas.sms.ro.SingleSmsSendRequestRO;
import com.beitu.saas.sms.ro.SmsMsgBatchContentRO;
import com.fqgj.exception.common.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author linanjun
 * @create 2018/3/22 上午10:34
 * @description
 */
@Service
public class SendApplication {

    @Autowired
    private SmsMsgService smsMsgService;

    @Autowired
    private SaasMerchantConfigService saasMerchantConfigService;

    @Autowired
    private SaasMerchantService saasMerchantService;

    @Autowired
    private SaasSmsHistoryService saasSmsHistoryService;

    @Autowired
    private SaasAdminService saasAdminService;

    @Autowired
    private SaasBorrowerService saasBorrowerService;

    @Autowired
    private SaasChannelService saasChannelService;


    /**
     * 发送验证码
     *
     * @param mobile             手机
     * @param code               验证码
     * @param verifyCodeTypeEnum 验证码内容
     */
    public void sendVerifyCode(String mobile, String code, VerifyCodeTypeEnum verifyCodeTypeEnum) {
        SingleSmsSendRequestRO ro = new SingleSmsSendRequestRO();
        ro.setPhone(mobile);
        ro.setBizCode(verifyCodeTypeEnum.getType());
        ro.setReplaceParam(new HashMap<String, String>(2) {{
            put("VERIFY_CODE", code);
            put("sign", "贝途科技");
        }});
        ro.setType(1);
        Result<Boolean> result = smsMsgService.send(ro);
        if (!result.isSuccess()) {
            throw new ApplicationException(MessageSendErrorCodeEnum.SEND_FAILED);
        }
    }

    public void sendNotifyMessageByChannelCode(String merchantCode, String channelCode, Map map, SaasSmsTypeEnum saasSmsTypeEnum) {
        SaasChannelEntity saasChannelEntity = saasChannelService.getSaasChannelByChannelCode(channelCode);
        SaasAdmin saasAdmin = saasAdminService.getSaasAdminByAdminCode(saasChannelEntity.getChargePersonCode());
        sendNotifyMessage(merchantCode, saasAdmin.getMobile(), map, saasSmsTypeEnum);
    }

    public void sendNotifyMessageByBorrowerCode(String merchantCode, String sendCode, Map map, SaasSmsTypeEnum saasSmsTypeEnum) {
        SaasBorrowerVo saasBorrowerVo = saasBorrowerService.getByBorrowerCodeAndMerchantCode(sendCode, merchantCode);
        sendNotifyMessage(merchantCode, saasBorrowerVo.getMobile(), map, saasSmsTypeEnum);
    }

    public void sendNotifyMessage(String merchantCode, String mobile, Map map, SaasSmsTypeEnum saasSmsTypeEnum) {
        if (saasMerchantConfigService.hasSmsConfig(merchantCode, saasSmsTypeEnum.getBizCode())) {
            return;
        }
        SaasMerchantVo saasMerchantVo = saasMerchantService.getByMerchantCode(merchantCode);
        if (map == null) {
            map = new HashMap(4);
        }
        map.put("sign", saasMerchantVo.getCompanyName());
        SingleSmsSendRequestRO ro = new SingleSmsSendRequestRO();
        ro.setPhone(mobile);
        ro.setBizCode(saasSmsTypeEnum.getBizCode());
        ro.setReplaceParam(map);
        ro.setType(0);
        Result<Boolean> result = smsMsgService.send(ro);
        if (!result.isSuccess()) {
            throw new ApplicationException(MessageSendErrorCodeEnum.SEND_FAILED);
        }
        saasSmsHistoryService.addExpenditureSmsHistory(merchantCode, saasSmsTypeEnum.getPrice(), mobile, saasSmsTypeEnum.getComment());
    }

    public void sendBatchNotifyMessage(String merchantCode, List<String> mobileList, Map map, SaasSmsTypeEnum saasSmsTypeEnum) {
        if (saasMerchantConfigService.hasSmsConfig(merchantCode, saasSmsTypeEnum.getBizCode())) {
            return;
        }
        SaasMerchantVo saasMerchantVo = saasMerchantService.getByMerchantCode(merchantCode);
        map.put("sign", saasMerchantVo.getCompanyName());
        BatchSmsSendRquestRO ro = new BatchSmsSendRquestRO();
        List<SmsMsgBatchContentRO> contentROS = new ArrayList<>();
        mobileList.parallelStream().forEach(mobile -> {
            SmsMsgBatchContentRO smsMsgBatchContentRO = new SmsMsgBatchContentRO();
            smsMsgBatchContentRO.setPhone(mobile);
            smsMsgBatchContentRO.setReplaceParam(map);
            contentROS.add(smsMsgBatchContentRO);
        });
        ro.setBizCode(saasSmsTypeEnum.getBizCode());
        ro.setContents(contentROS);
        Result<Boolean> result = smsMsgService.batchSend(ro);
        if (!result.isSuccess()) {
            throw new ApplicationException(MessageSendErrorCodeEnum.SEND_FAILED);
        }
        saasSmsHistoryService.addExpenditureSmsHistory(merchantCode, Long.valueOf(mobileList.size()) * saasSmsTypeEnum.getPrice(), null, saasSmsTypeEnum.getComment());
    }

}
