package com.beitu.saas.risk.domain.carrier.h5.input;

import com.beitu.saas.risk.domain.platform.TripleServiceBaseInput;

public class CarrierRequestUrlInput extends TripleServiceBaseInput{
    private String name;
    private String phone;
    private String idNumber;
    /**
     * 调用方生成的用户ID（调用方定义）
     */
    private String userCode;
    /**
     * H5重定向地址get
     */
    private String returnUrl;
    /**
     * 回调app端地址
     */
    private String appUrl;

    @Override
    public String getName() {
        return name;
    }

    public CarrierRequestUrlInput setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CarrierRequestUrlInput setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public CarrierRequestUrlInput setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getUserCode() {
        return userCode;
    }

    public CarrierRequestUrlInput setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public CarrierRequestUrlInput setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
        return this;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public CarrierRequestUrlInput setAppUrl(String appUrl) {
        this.appUrl = appUrl;
        return this;
    }

    @Override
    public String getMobile() {
        return getPhone();
    }

    @Override
    public String getIdentityNo() {
        return getIdNumber();
    }
}
