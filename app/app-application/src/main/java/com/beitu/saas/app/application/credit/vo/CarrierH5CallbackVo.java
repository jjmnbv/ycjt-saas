package com.beitu.saas.app.application.credit.vo;

import com.beitu.saas.risk.domain.carrier.h5.enums.CarrierH5TypeEnum;

import java.io.Serializable;

/**
 * @author linanjun
 * @create 2018/3/22 下午6:05
 * @description
 */
public class CarrierH5CallbackVo implements Serializable {

    private String sign;

    private String status;

    private String taskId;

    private String timestamp;

    private String userCode;

    private String data;

    private CarrierH5TypeEnum carrierType;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public CarrierH5TypeEnum getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(CarrierH5TypeEnum carrierType) {
        this.carrierType = carrierType;
    }

}
