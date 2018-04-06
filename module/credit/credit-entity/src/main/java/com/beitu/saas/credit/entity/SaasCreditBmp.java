package com.beitu.saas.credit.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: jungle
* Date: 2018-04-06
* Time: 18:11:44.598
* TableDesc:电话邦匹配数据查询表
*/
public class SaasCreditBmp extends BaseEntity{
    /**
    *机构码
    */
    private String merchantCode;
    /**
    *用户码
    */
    private String borrowerCode;
    /**
    *电话邦催收数据查询唯一标识
    */
    private String sid;
    /**
    *电话邦匹配数据存储地址
    */
    private String url;
    /**
    *是否查询成功
    */
    private Boolean success;


    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  SaasCreditBmp setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
        return this;
    }

    public  String getBorrowerCode(){
        return this.borrowerCode;
    }

    public  SaasCreditBmp setBorrowerCode(String borrowerCode){
        this.borrowerCode = borrowerCode;
        return this;
    }

    public  String getSid(){
        return this.sid;
    }

    public  SaasCreditBmp setSid(String sid){
        this.sid = sid;
        return this;
    }

    public  String getUrl(){
        return this.url;
    }

    public  SaasCreditBmp setUrl(String url){
        this.url = url;
        return this;
    }

    public  Boolean getSuccess(){
        return this.success;
    }

    public  SaasCreditBmp setSuccess(Boolean success){
        this.success = success;
        return this;
    }
}
