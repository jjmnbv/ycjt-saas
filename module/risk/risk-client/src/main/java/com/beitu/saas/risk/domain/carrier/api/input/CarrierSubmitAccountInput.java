package com.beitu.saas.risk.domain.carrier.api.input;


import com.beitu.saas.risk.domain.platform.TripleServiceBaseInput;

public class CarrierSubmitAccountInput extends TripleServiceBaseInput {
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 身份证号
     */
    private String identityCard;

    private String userCode;

    @Override
    public String getName() {
        return this.realName;
    }

    public String getMobile() {
        return mobile;
    }

    @Override
    public String getIdentityNo() {
        return this.identityCard;
    }

    public CarrierSubmitAccountInput setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CarrierSubmitAccountInput setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public CarrierSubmitAccountInput setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public CarrierSubmitAccountInput setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
        return this;
    }

    public String getUserCode() {
        return userCode;
    }

    public CarrierSubmitAccountInput setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
    }
}
