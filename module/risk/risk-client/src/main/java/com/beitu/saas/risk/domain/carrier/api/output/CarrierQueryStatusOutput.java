package com.beitu.saas.risk.domain.carrier.api.output;

import com.beitu.saas.risk.domain.carrier.api.enums.CarrierCrawlStatusEnum;
import com.beitu.saas.risk.domain.platform.TripleServiceBaseOutput;
import com.beitu.saas.risk.domain.carrier.api.enums.CarrierCrawlTypeEnum;

public class CarrierQueryStatusOutput extends TripleServiceBaseOutput {
    private CarrierCrawlStatusEnum crawlStatus;
    private String description;
    /**
     * 查询最终数据的标识
     */
    private String phoneId;
    private String taskId;
    private String imgBase64Val;
    private CarrierCrawlTypeEnum carrierType;

    public CarrierCrawlStatusEnum getCrawlStatus() {
        return crawlStatus;
    }

    public CarrierQueryStatusOutput setCrawlStatus(CarrierCrawlStatusEnum crawlStatus) {
        this.crawlStatus = crawlStatus;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CarrierQueryStatusOutput setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public CarrierQueryStatusOutput setPhoneId(String phoneId) {
        this.phoneId = phoneId;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public CarrierQueryStatusOutput setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getImgBase64Val() {
        return imgBase64Val;
    }

    public CarrierQueryStatusOutput setImgBase64Val(String imgBase64Val) {
        this.imgBase64Val = imgBase64Val;
        return this;
    }

    public CarrierCrawlTypeEnum getCarrierType() {
        return carrierType;
    }

    public CarrierQueryStatusOutput setCarrierType(CarrierCrawlTypeEnum carrierType) {
        this.carrierType = carrierType;
        return this;
    }
}
