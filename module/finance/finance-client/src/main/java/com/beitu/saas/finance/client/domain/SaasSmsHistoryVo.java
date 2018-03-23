package com.beitu.saas.finance.client.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
* User: fenqiguanjia
* Date: 2018-03-23
* Time: 20:33:11.070
*/
public class SaasSmsHistoryVo implements ResponseData,Serializable{

    private Long saasSmsHistoryId;

    /**
    *机构码
    */
    private String merchantCode;
    /**
    *余额操作类型，0：支出，1：收入
    */
    private Integer opType;
    /**
    *短信条数
    */
    private Long sms;
    /**
    *操作后短信条数
    */
    private Long currentSms;
    /**
    *备注信息
    */
    private String comment;
    /**
    *发送手机号码
    */
    private String sendPhone;

    public Long getSaasSmsHistoryId() {
        return saasSmsHistoryId;
    }

    public void setSaasSmsHistoryId(Long saasSmsHistoryId) {
        this.saasSmsHistoryId = saasSmsHistoryId;
    }



    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  void setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
    }

    public  Integer getOpType(){
        return this.opType;
    }

    public  void setOpType(Integer opType){
        this.opType = opType;
    }

    public  Long getSms(){
        return this.sms;
    }

    public  void setSms(Long sms){
        this.sms = sms;
    }

    public  Long getCurrentSms(){
        return this.currentSms;
    }

    public  void setCurrentSms(Long currentSms){
        this.currentSms = currentSms;
    }

    public  String getComment(){
        return this.comment;
    }

    public  void setComment(String comment){
        this.comment = comment;
    }

    public  String getSendPhone(){
        return this.sendPhone;
    }

    public  void setSendPhone(String sendPhone){
        this.sendPhone = sendPhone;
    }
}
