package com.beitu.saas.borrower.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: jungle
* Date: 2018-04-03
* Time: 15:11:35.409
* TableDesc:SAAS借款人登录日志表
*/
public class SaasBorrowerLoginLog extends BaseEntity{
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
    *手机操作系统
    */
    private String phoneSystem;
    /**
    *登录IP
    */
    private String loginIp;
    /**
    *登录详细地址
    */
    private String loginIpAddress;


    public  String getBorrowerCode(){
        return this.borrowerCode;
    }

    public  SaasBorrowerLoginLog setBorrowerCode(String borrowerCode){
        this.borrowerCode = borrowerCode;
        return this;
    }

    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  SaasBorrowerLoginLog setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
        return this;
    }

    public  String getChannelCode(){
        return this.channelCode;
    }

    public  SaasBorrowerLoginLog setChannelCode(String channelCode){
        this.channelCode = channelCode;
        return this;
    }

    public  String getPhoneSystem(){
        return this.phoneSystem;
    }

    public  SaasBorrowerLoginLog setPhoneSystem(String phoneSystem){
        this.phoneSystem = phoneSystem;
        return this;
    }

    public  String getLoginIp(){
        return this.loginIp;
    }

    public  SaasBorrowerLoginLog setLoginIp(String loginIp){
        this.loginIp = loginIp;
        return this;
    }

    public  String getLoginIpAddress(){
        return this.loginIpAddress;
    }

    public  SaasBorrowerLoginLog setLoginIpAddress(String loginIpAddress){
        this.loginIpAddress = loginIpAddress;
        return this;
    }
}
