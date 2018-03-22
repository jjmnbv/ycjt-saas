package com.beitu.saas.app.application;

import com.beitu.saas.sms.enums.SmsTypeEnum;
import org.springframework.stereotype.Service;

/**
 * @author linanjun
 * @create 2018/3/22 上午10:34
 * @description
 */
@Service
public class SendApplication {

    private String SPLIT_REGX = "##";

    /**
     * 发送验证码
     *
     * @param userId      用户ID
     * @param mobile      手机
     * @param code        验证码
     * @param smsTypeEnum 短信类型枚举
     */
    public void sendCodeAndNotifyMessage(Long userId, String mobile, String code, SmsTypeEnum smsTypeEnum) {


    }

}
