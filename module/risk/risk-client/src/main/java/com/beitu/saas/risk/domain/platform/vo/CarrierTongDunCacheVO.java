package com.beitu.saas.risk.domain.platform.vo;

import com.beitu.saas.risk.domain.carrier.api.input.CarrierSubmitAccountInput;

import java.io.Serializable;

/**
 * @Author poyangchen
 * @Create 2017-04-07 上午11:44
 * @Description
 */
public class CarrierTongDunCacheVO implements Serializable {

    private CarrierSubmitAccountInput carrierSubmitAccountInput;

    private String taskStage;

    private String taskId;

    private String smsCode;

    private String imgCode;

    public CarrierSubmitAccountInput getCarrierSubmitAccountInput() {
        return carrierSubmitAccountInput;
    }

    public CarrierTongDunCacheVO setCarrierSubmitAccountInput(CarrierSubmitAccountInput carrierSubmitAccountInput) {
        this.carrierSubmitAccountInput = carrierSubmitAccountInput;
        return this;
    }

    public String getTaskStage() {
        return taskStage;
    }

    public CarrierTongDunCacheVO setTaskStage(String taskStage) {
        this.taskStage = taskStage;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public CarrierTongDunCacheVO setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public CarrierTongDunCacheVO setSmsCode(String smsCode) {
        this.smsCode = smsCode;
        return this;
    }

    public String getImgCode() {
        return imgCode;
    }

    public CarrierTongDunCacheVO setImgCode(String imgCode) {
        this.imgCode = imgCode;
        return this;
    }
}
