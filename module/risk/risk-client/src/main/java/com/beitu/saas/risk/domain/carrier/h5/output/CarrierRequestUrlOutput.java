package com.beitu.saas.risk.domain.carrier.h5.output;

import com.beitu.saas.risk.domain.carrier.h5.enums.CarrierH5TypeEnum;
import com.beitu.saas.risk.domain.platform.TripleServiceBaseOutput;

public class CarrierRequestUrlOutput extends TripleServiceBaseOutput {
    /**
     * 任务流水号
     */
    private String taskId;
    private String redirectUrl;
    /**
     * 对上层交互的运营商类型枚举
     */
    private CarrierH5TypeEnum carrierH5TypeEnum;

    public String getTaskId() {
        return taskId;
    }

    public CarrierRequestUrlOutput setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public CarrierRequestUrlOutput setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
        return this;
    }

    public CarrierH5TypeEnum getCarrierH5TypeEnum() {
        return carrierH5TypeEnum;
    }

    public CarrierRequestUrlOutput setCarrierH5TypeEnum(CarrierH5TypeEnum carrierH5TypeEnum) {
        this.carrierH5TypeEnum = carrierH5TypeEnum;
        return this;
    }
}
