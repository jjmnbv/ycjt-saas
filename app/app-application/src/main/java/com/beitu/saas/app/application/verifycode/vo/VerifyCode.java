package com.beitu.saas.app.application.verifycode.vo;

import com.beitu.saas.app.domain.Mobile;
import com.beitu.saas.sms.enums.SmsTypeEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by linchengyu on 17/6/16.
 */
public class VerifyCode {

    private String verifyCode;

    private Mobile mobile;

    private SmsTypeEnum type;

    public VerifyCode(String verifyCode, SmsTypeEnum type) {
        if (StringUtils.isEmpty(verifyCode) || type == null) {
            return;
        }
        this.verifyCode = verifyCode;
        this.type = type;
    }

    public VerifyCode(Mobile mobile, SmsTypeEnum type) {
        if (mobile == null || type == null) {
            return;
        }
        this.mobile = mobile;
        this.type = type;
    }

    public VerifyCode(String verifyCode, Mobile mobile, SmsTypeEnum type) {
        if (StringUtils.isEmpty(verifyCode) || mobile == null || type == null) {
            return;
        }
        this.verifyCode = verifyCode;
        this.mobile = mobile;
        this.type = type;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public Mobile getMobile() {
        return mobile;
    }

    public SmsTypeEnum getType() {
        return type;
    }
}
