package com.beitu.saas.app.application;

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

/**
 * @author linanjun
 * @create 2018/3/22 上午10:34
 * @description
 */
@Service
public class SendApplication {

    @Autowired
    private SmsMsgService smsMsgService;

    /**
     * 发送验证码
     *
     * @param mobile             手机
     * @param code               验证码
     * @param verifyCodeTypeEnum 验证码内容
     */
    public void sendCodeAndNotifyMessage(String mobile, String code, VerifyCodeTypeEnum verifyCodeTypeEnum) {
        SingleSmsSendRequestRO ro = new SingleSmsSendRequestRO();
        ro.setPhone(mobile);
        ro.setBizCode(verifyCodeTypeEnum.getType());
        ro.setReplaceParam(new HashMap<String, String>() {{
            put("VERIFY_CODE", code);
        }});
        ro.setType(1);
        Result<Boolean> result = smsMsgService.send(ro);
        if (!result.isSuccess()) {
            throw new ApplicationException(MessageSendErrorCodeEnum.SEND_FAILED);
        }
    }

}
