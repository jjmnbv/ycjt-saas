package com.beitu.saas.risk.domain.carrier.api.input;

import com.beitu.saas.risk.domain.platform.TripleServiceBaseInput;

public class CarrierQueryReportInput extends TripleServiceBaseInput {
    private String phoneId;
    private String userCode;
    private String mobile;

    public CarrierQueryReportInput setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public CarrierQueryReportInput setPhoneId(String phoneId) {
        this.phoneId = phoneId;
        return this;
    }

    public String getUserCode() {
        return userCode;
    }

    public CarrierQueryReportInput setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getMobile() {
        return mobile;
    }

    @Override
    public String getIdentityNo() {
        return null;
    }
}
