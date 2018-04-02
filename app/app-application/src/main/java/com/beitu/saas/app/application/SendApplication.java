package com.beitu.saas.app.application;

import com.beitu.saas.app.enums.SaasSmsTypeEnum;
import com.beitu.saas.auth.entity.SaasMerchantConfig;
import com.beitu.saas.auth.service.SaasMerchantConfigService;
import com.beitu.saas.sms.client.SmsMsgService;
import com.beitu.saas.sms.enums.MessageSendErrorCodeEnum;
import com.beitu.saas.sms.enums.SmsTypeEnum;
import com.beitu.saas.sms.enums.VerifyCodeTypeEnum;
import com.beitu.saas.sms.ro.Result;
import com.beitu.saas.sms.ro.SingleSmsSendRequestRO;
import com.fqgj.exception.common.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
        ro.setReplaceParam(new HashMap<String, String>() {{
            put("VERIFY_CODE", code);
            put("sign", "贝途科技");
        }});
        ro.setType(1);
        Result<Boolean> result = smsMsgService.send(ro);
        if (!result.isSuccess()) {
            throw new ApplicationException(MessageSendErrorCodeEnum.SEND_FAILED);
        }
    }


    public void sendNotifyMessage(String merchantCode, String mobile, Map map, SaasSmsTypeEnum saasSmsTypeEnum) {
        if (saasMerchantConfigService.hasSmsConfig(merchantCode, saasSmsTypeEnum.getBizCode())) {
            return;
        }
        SingleSmsSendRequestRO ro = new SingleSmsSendRequestRO();
        ro.setPhone(mobile);
        ro.setBizCode(saasSmsTypeEnum.getBizCode());
        ro.setReplaceParam(map);
        ro.setType(0);
        Result<Boolean> result = smsMsgService.send(ro);
        if (!result.isSuccess()) {
            throw new ApplicationException(MessageSendErrorCodeEnum.SEND_FAILED);
        }
    }

}
