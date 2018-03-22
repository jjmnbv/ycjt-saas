package com.beitu.saas.risk.domain.carrier.api.output;

import com.beitu.saas.risk.domain.platform.TripleServiceBaseOutput;
import com.beitu.saas.risk.domain.carrier.api.enums.CarrierCrawlTypeEnum;

public class CarrierSubmitVerityOutput extends TripleServiceBaseOutput {
    private Boolean success;
    private CarrierCrawlTypeEnum carrierType;

    public CarrierCrawlTypeEnum getCarrierType() {
        return carrierType;
    }

    public CarrierSubmitVerityOutput setCarrierType(CarrierCrawlTypeEnum carrierType) {
        this.carrierType = carrierType;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public CarrierSubmitVerityOutput setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
}
