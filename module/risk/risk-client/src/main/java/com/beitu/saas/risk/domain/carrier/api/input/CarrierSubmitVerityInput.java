package com.beitu.saas.risk.domain.carrier.api.input;


import com.beitu.saas.risk.domain.platform.TripleServiceBaseInput;

public class CarrierSubmitVerityInput extends TripleServiceBaseInput {
    private String smsCode;
    private String imgCode;
    private String taskId;
    private String userCode;
    private String mobile;

    public CarrierSubmitVerityInput setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public CarrierSubmitVerityInput setSmsCode(String smsCode) {
        this.smsCode = smsCode;
        return this;
    }

    public String getImgCode() {
        return imgCode;
    }

    public CarrierSubmitVerityInput setImgCode(String imgCode) {
        this.imgCode = imgCode;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public CarrierSubmitVerityInput setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getUserCode() {
        return userCode;
    }

    public CarrierSubmitVerityInput setUserCode(String userCode) {
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
