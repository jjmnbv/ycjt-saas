package com.beitu.saas.rest.controller.merchant.request;

import com.beitu.saas.merchant.client.enums.MerchantFlowNumEnum;
import com.beitu.saas.merchant.client.enums.MerchantFlowZMEnum;
import com.fqgj.common.api.ParamsObject;
import com.fqgj.common.api.exception.ApiIllegalArgumentException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xiaochong
 * @create 2018/4/9 下午1:50
 * @description
 */
@ApiModel
public class SaveConfigRequest extends ParamsObject {

    @ApiModelProperty("抢购方式1.共享抢单2.买断抢单")
    private Integer flowType;

    @ApiModelProperty("芝麻分要求1.610以下 2.610以上")
    private Integer zmScore;

    @ApiModelProperty("每日推送最大量传具体数字")
    private Integer flowMaxNum;

    @ApiModelProperty("流量开关")
    private Boolean flowOpen;

    public Integer getFlowType() {
        return flowType;
    }

    public void setFlowType(Integer flowType) {
        this.flowType = flowType;
    }

    public Integer getZmScore() {
        return zmScore;
    }

    public void setZmScore(Integer zmScore) {
        this.zmScore = zmScore;
    }

    public Integer getFlowMaxNum() {
        return flowMaxNum;
    }

    public void setFlowMaxNum(Integer flowMaxNum) {
        this.flowMaxNum = flowMaxNum;
    }

    public Boolean getFlowOpen() {
        return flowOpen;
    }

    public void setFlowOpen(Boolean flowOpen) {
        this.flowOpen = flowOpen;
    }

    @Override
    public void validate() {
        if (!MerchantFlowNumEnum.hasValue(flowMaxNum.longValue())) {
            throw new ApiIllegalArgumentException("每日推送最大量非法");
        }
        if (!MerchantFlowZMEnum.hasKey(zmScore.longValue())) {
            throw new ApiIllegalArgumentException("芝麻分非法");
        }
    }
}
