package com.beitu.saas.risk.domain.carrier.api.input;

import com.beitu.saas.risk.domain.platform.TripleServiceBaseInput;

public class CarrierQueryStatusInput extends TripleServiceBaseInput {
    private String taskId;
    private String userCode;
    private String mobile;

    public CarrierQueryStatusInput setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public CarrierQueryStatusInput setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getUserCode() {
        return userCode;
    }

    public CarrierQueryStatusInput setUserCode(String userCode) {
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
