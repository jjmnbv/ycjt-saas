package com.beitu.saas.finance.entity;

import com.fqgj.common.entity.BaseEntity;

import java.util.Date;

/**
* User: fenqiguanjia
* Date: 2018-03-23
* Time: 20:49:30.610
* TableDesc:消费日统计表
*/
public class SaasConsumeDayStatEntity extends BaseEntity{
    /**
    *日期
    */
    private Date dt;
    /**
    *机构号
    */
    private String merchantCode;
    /**
    *消耗点券
    */
    private Long consumeCredit;
    /**
    *消耗点券
    */
    private Long consumeSms;
    /**
    *上次清算日期
    */
    private Date lastClearDt;


    public  Date getDt(){
        return this.dt;
    }

    public  SaasConsumeDayStatEntity setDt(Date dt){
        this.dt = dt;
        return this;
    }

    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  SaasConsumeDayStatEntity setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
        return this;
    }

    public  Long getConsumeCredit(){
        return this.consumeCredit;
    }

    public  SaasConsumeDayStatEntity setConsumeCredit(Long consumeCredit){
        this.consumeCredit = consumeCredit;
        return this;
    }

    public  Long getConsumeSms(){
        return this.consumeSms;
    }

    public  SaasConsumeDayStatEntity setConsumeSms(Long consumeSms){
        this.consumeSms = consumeSms;
        return this;
    }

    public  Date getLastClearDt(){
        return this.lastClearDt;
    }

    public  SaasConsumeDayStatEntity setLastClearDt(Date lastClearDt){
        this.lastClearDt = lastClearDt;
        return this;
    }
}
