package com.beitu.saas.borrower.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: jungle
* Date: 2018-03-22
* Time: 20:25:59.830
* TableDesc:SAAS借款人表
*/
public class SaasBorrower extends BaseEntity{
    /**
    *借款人CODE
    */
    private String borrowerCode;
    /**
    *机构CODE
    */
    private String merchantCode;
    /**
    *渠道CODE
    */
    private String channelCode;
    /**
    *手机号
    */
    private String mobile;


    public  String getBorrowerCode(){
        return this.borrowerCode;
    }

    public  SaasBorrower setBorrowerCode(String borrowerCode){
        this.borrowerCode = borrowerCode;
        return this;
    }

    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  SaasBorrower setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
        return this;
    }

    public  String getChannelCode(){
        return this.channelCode;
    }

    public  SaasBorrower setChannelCode(String channelCode){
        this.channelCode = channelCode;
        return this;
    }

    public  String getMobile(){
        return this.mobile;
    }

    public  SaasBorrower setMobile(String mobile){
        this.mobile = mobile;
        return this;
    }
}
