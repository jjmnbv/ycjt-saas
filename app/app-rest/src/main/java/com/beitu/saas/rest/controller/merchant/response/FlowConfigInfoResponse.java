package com.beitu.saas.rest.controller.merchant.response;

import com.beitu.saas.merchant.client.enums.MerchantFlowNumEnum;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xiaochong
 * @create 2018/4/9 下午12:23
 * @description
 */
@ApiModel
public class FlowConfigInfoResponse implements ResponseData {

    @ApiModelProperty("抢购方式1.共享抢单2.买断抢单")
    private Integer flowType=1;

    @ApiModelProperty("芝麻分要求1.610以下 2.610以上")
    private Integer zmScore=1;

    @ApiModelProperty("每日推送最大量")
    private Integer flowMaxNum=200;

    @ApiModelProperty("流量开关")
    private Boolean flowOpen=true;

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
        this.flowMaxNum = MerchantFlowNumEnum.getKeyByValue(flowMaxNum.longValue()).intValue();
    }

    public Boolean getFlowOpen() {
        return flowOpen;
    }

    public void setFlowOpen(Boolean flowOpen) {
        this.flowOpen = flowOpen;
    }
}
