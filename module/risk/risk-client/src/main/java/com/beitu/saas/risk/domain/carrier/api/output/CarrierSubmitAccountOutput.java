package com.beitu.saas.risk.domain.carrier.api.output;

import com.beitu.saas.risk.domain.carrier.api.enums.CarrierCrawlTypeEnum;
import com.beitu.saas.risk.domain.platform.TripleServiceBaseOutput;

public class CarrierSubmitAccountOutput extends TripleServiceBaseOutput {
    private String taskId;
    private CarrierCrawlTypeEnum carrierType;

    public String getTaskId() {
        return taskId;
    }

    public CarrierSubmitAccountOutput setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public CarrierCrawlTypeEnum getCarrierType() {
        return carrierType;
    }

    public CarrierSubmitAccountOutput setCarrierType(CarrierCrawlTypeEnum carrierType) {
        this.carrierType = carrierType;
        return this;
    }
}
