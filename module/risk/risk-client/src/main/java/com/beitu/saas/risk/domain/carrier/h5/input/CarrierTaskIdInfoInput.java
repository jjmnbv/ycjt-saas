package com.beitu.saas.risk.domain.carrier.h5.input;

import com.beitu.saas.risk.domain.platform.TripleServiceBaseInput;

public class CarrierTaskIdInfoInput extends TripleServiceBaseInput {
    private String taskId;
    private String mobile;


    public CarrierTaskIdInfoInput setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public CarrierTaskIdInfoInput setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getMobile() {
        return this.mobile;
    }

    @Override
    public String getIdentityNo() {
        return null;
    }
}
