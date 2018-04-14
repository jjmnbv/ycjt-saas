package com.beitu.saas.risk.handler.carrier.h5.vo;


import com.beitu.saas.risk.domain.carrier.h5.enums.CarrierH5TypeEnum;

import java.io.Serializable;

/**
 * Created by zwh @yuntu-inc.com
 *
 * @description
 * @create 2017/10/31 0031 下午 3:28
 */
public class CarrierH5ResultVo implements Serializable{
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

    public CarrierH5ResultVo setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public CarrierH5ResultVo setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
        return this;
    }

    public CarrierH5TypeEnum getCarrierH5TypeEnum() {
        return carrierH5TypeEnum;
    }

    public CarrierH5ResultVo setCarrierH5TypeEnum(CarrierH5TypeEnum carrierH5TypeEnum) {
        this.carrierH5TypeEnum = carrierH5TypeEnum;
        return this;
    }
}
