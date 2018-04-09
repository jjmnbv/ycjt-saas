package com.beitu.saas.merchant.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: xiaochong
* Date: 2018-04-09
* Time: 11:49:35.603
* TableDesc:机构流量配置表
*/
public class SaasMerchantFlowConfig extends BaseEntity {
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


    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public SaasMerchantFlowConfig setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
        return this;
    }

    public  Integer getFlowType(){
        return this.flowType;
    }

    public SaasMerchantFlowConfig setFlowType(Integer flowType){
        this.flowType = flowType;
        return this;
    }

    public  Integer getZmScore(){
        return this.zmScore;
    }

    public SaasMerchantFlowConfig setZmScore(Integer zmScore){
        this.zmScore = zmScore;
        return this;
    }

    public  Integer getFlowMaxNum(){
        return this.flowMaxNum;
    }

    public SaasMerchantFlowConfig setFlowMaxNum(Integer flowMaxNum){
        this.flowMaxNum = flowMaxNum;
        return this;
    }

    public  Boolean getFlowOpen(){
        return this.flowOpen;
    }

    public SaasMerchantFlowConfig setFlowOpen(Boolean flowOpen){
        this.flowOpen = flowOpen;
        return this;
    }
}
