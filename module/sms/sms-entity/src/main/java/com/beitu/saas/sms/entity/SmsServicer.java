package com.beitu.saas.sms.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: jungle
* Date: 2018-02-28
* Time: 15:46:53.796
* TableDesc:服务商信息表
*/
public class SmsServicer extends BaseEntity{
    /**
    *服务商类型 1 短信 2 语音 3 邮件 4 站内push 多个用,号隔开(已弃用)
    */
    private String servicerType;
    /**
    *服务商码 拼音大写
    */
    private String servicerCode;
    /**
    *服务商名称
    */
    private String servicerName;
    /**
    *限制条件
    */
    private String limitInfo;
    /**
    *收费标准
    */
    private String fee;
    /**
    *描述
    */
    private String descInfo;


    public  String getServicerType(){
        return this.servicerType;
    }

    public  SmsServicer setServicerType(String servicerType){
        this.servicerType = servicerType;
        return this;
    }

    public  String getServicerCode(){
        return this.servicerCode;
    }

    public  SmsServicer setServicerCode(String servicerCode){
        this.servicerCode = servicerCode;
        return this;
    }

    public  String getServicerName(){
        return this.servicerName;
    }

    public  SmsServicer setServicerName(String servicerName){
        this.servicerName = servicerName;
        return this;
    }

    public  String getLimitInfo(){
        return this.limitInfo;
    }

    public  SmsServicer setLimitInfo(String limitInfo){
        this.limitInfo = limitInfo;
        return this;
    }

    public  String getFee(){
        return this.fee;
    }

    public  SmsServicer setFee(String fee){
        this.fee = fee;
        return this;
    }

    public  String getDescInfo(){
        return this.descInfo;
    }

    public  SmsServicer setDescInfo(String descInfo){
        this.descInfo = descInfo;
        return this;
    }
}
