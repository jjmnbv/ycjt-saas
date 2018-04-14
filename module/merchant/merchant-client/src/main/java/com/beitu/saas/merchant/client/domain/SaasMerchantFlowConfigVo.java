package com.beitu.saas.merchant.client.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
* User: xiaochong
* Date: 2018-04-09
* Time: 11:49:34.690
*/
public class SaasMerchantFlowConfigVo implements ResponseData,Serializable{

    private Long saasMerchantFlowConfigId;

    /**
    *
    */
    private String merchantCode;
    /**
    *抢购方式1.共享抢单2.买断抢单
    */
    private Integer flowType;
    /**
    *芝麻分要求
    */
    private Integer zmScore;
    /**
    *每日推送最大量
    */
    private Integer flowMaxNum;
    /**
    *流量开关
    */
    private Boolean flowOpen;

    public Long getSaasMerchantFlowConfigId() {
        return saasMerchantFlowConfigId;
    }

    public void setSaasMerchantFlowConfigId(Long saasMerchantFlowConfigId) {
        this.saasMerchantFlowConfigId = saasMerchantFlowConfigId;
    }



    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  void setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
    }

    public  Integer getFlowType(){
        return this.flowType;
    }

    public  void setFlowType(Integer flowType){
        this.flowType = flowType;
    }

    public  Integer getZmScore(){
        return this.zmScore;
    }

    public  void setZmScore(Integer zmScore){
        this.zmScore = zmScore;
    }

    public  Integer getFlowMaxNum(){
        return this.flowMaxNum;
    }

    public  void setFlowMaxNum(Integer flowMaxNum){
        this.flowMaxNum = flowMaxNum;
    }

    public  Boolean getFlowOpen(){
        return this.flowOpen;
    }

    public  void setFlowOpen(Boolean flowOpen){
        this.flowOpen = flowOpen;
    }
}
