package com.beitu.saas.risk.domain.carrier.h5.output;

import com.beitu.saas.risk.domain.platform.TripleServiceBaseOutput;

public class CarrierTaskIdInfoOutput extends TripleServiceBaseOutput {

    private String userCode;
    private String mobile;
    private String realName;
    private String idCard;
    private String appUrl;

    public String getUserCode() {
        return userCode;
    }

    public CarrierTaskIdInfoOutput setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public CarrierTaskIdInfoOutput setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public CarrierTaskIdInfoOutput setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getIdCard() {
        return idCard;
    }

    public CarrierTaskIdInfoOutput setIdCard(String idCard) {
        this.idCard = idCard;
        return this;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public CarrierTaskIdInfoOutput setAppUrl(String appUrl) {
        this.appUrl = appUrl;
        return this;
    }
}
