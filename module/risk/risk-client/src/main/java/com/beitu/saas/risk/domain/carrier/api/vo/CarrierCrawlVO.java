package com.beitu.saas.risk.domain.carrier.api.vo;



import com.beitu.saas.risk.domain.carrier.api.enums.CarrierCrawlStatusEnum;
import com.beitu.saas.risk.domain.carrier.api.enums.CarrierCrawlTypeEnum;

import java.io.Serializable;

/**
 * @Author poyangchen
 * @Create 2017-04-05 上午11:30
 * @Description
 */
public class CarrierCrawlVO implements Serializable {

    private CarrierCrawlStatusEnum crawlStatus;
    private String description;
    /**
     * 查询最终数据的标识
     */
    private String phoneId;
    private String taskId;
    private String imgBase64Val;
    private CarrierCrawlTypeEnum carrierType;

    public CarrierCrawlTypeEnum getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(CarrierCrawlTypeEnum carrierType) {
        this.carrierType = carrierType;
    }

    public String getImgBase64Val() {
        return imgBase64Val;
    }

    public void setImgBase64Val(String imgBase64Val) {
        this.imgBase64Val = imgBase64Val;
    }

    public CarrierCrawlStatusEnum getCrawlStatus() {
        return crawlStatus;
    }

    public void setCrawlStatus(CarrierCrawlStatusEnum crawlStatus) {
        this.crawlStatus = crawlStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

}
