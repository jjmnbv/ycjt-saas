package com.beitu.saas.risk.domain.carrier.api.output;

import com.beitu.saas.risk.domain.platform.TripleServiceBaseOutput;
import com.beitu.saas.risk.domain.carrier.api.enums.CarrierCrawlTypeEnum;

public class CarrierQueryReportOutput extends TripleServiceBaseOutput {
    private String report;
    private CarrierCrawlTypeEnum carrierType;

    public CarrierCrawlTypeEnum getCarrierType() {
        return carrierType;
    }

    public CarrierQueryReportOutput setCarrierType(CarrierCrawlTypeEnum carrierType) {
        this.carrierType = carrierType;
        return this;
    }

    public String getReport() {
        return report;
    }

    public CarrierQueryReportOutput setReport(String report) {
        this.report = report;
        return this;
    }
}
