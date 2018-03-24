package com.beitu.saas.finance.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: fenqiguanjia
* Date: 2018-03-23
* Time: 20:33:11.072
* TableDesc:账户短信条数流水表
*/
public class SaasSmsHistoryEntity extends BaseEntity{
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


    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  SaasSmsHistoryEntity setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
        return this;
    }

    public  Integer getOpType(){
        return this.opType;
    }

    public  SaasSmsHistoryEntity setOpType(Integer opType){
        this.opType = opType;
        return this;
    }

    public  Long getSms(){
        return this.sms;
    }

    public  SaasSmsHistoryEntity setSms(Long sms){
        this.sms = sms;
        return this;
    }

    public  Long getCurrentSms(){
        return this.currentSms;
    }

    public  SaasSmsHistoryEntity setCurrentSms(Long currentSms){
        this.currentSms = currentSms;
        return this;
    }

    public  String getComment(){
        return this.comment;
    }

    public  SaasSmsHistoryEntity setComment(String comment){
        this.comment = comment;
        return this;
    }

    public  String getSendPhone(){
        return this.sendPhone;
    }

    public  SaasSmsHistoryEntity setSendPhone(String sendPhone){
        this.sendPhone = sendPhone;
        return this;
    }
}
