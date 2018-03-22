package com.beitu.saas.finance.entity;

import com.fqgj.common.entity.BaseEntity;

import java.util.Date;

/**
* User: fenqiguanjia
* Date: 2018-03-21
* Time: 20:58:19.368
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
    private Integer consumeCredit;
    /**
    *消耗点券
    */
    private Integer consumeMsg;
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

    public  Integer getConsumeCredit(){
        return this.consumeCredit;
    }

    public  SaasConsumeDayStatEntity setConsumeCredit(Integer consumeCredit){
        this.consumeCredit = consumeCredit;
        return this;
    }

    public  Integer getConsumeMsg(){
        return this.consumeMsg;
    }

    public  SaasConsumeDayStatEntity setConsumeMsg(Integer consumeMsg){
        this.consumeMsg = consumeMsg;
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
