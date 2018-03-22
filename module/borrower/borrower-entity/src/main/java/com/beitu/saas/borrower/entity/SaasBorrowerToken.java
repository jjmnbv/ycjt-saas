package com.beitu.saas.borrower.entity;
import com.fqgj.common.entity.BaseEntity;
import java.util.Date;
/**
* User: jungle
* Date: 2018-03-22
* Time: 15:56:47.011
* TableDesc:SAAS借款人TOKEN表
*/
public class SaasBorrowerToken extends BaseEntity{
    /**
    *借款人CODE
    */
    private String borrowerCode;
    /**
    *机构CODE
    */
    private String merchantCode;
    /**
    *授权令牌
    */
    private String token;
    /**
    *过期时间
    */
    private Date expireDate;


    public  String getBorrowerCode(){
        return this.borrowerCode;
    }

    public  SaasBorrowerToken setBorrowerCode(String borrowerCode){
        this.borrowerCode = borrowerCode;
        return this;
    }

    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  SaasBorrowerToken setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
        return this;
    }

    public  String getToken(){
        return this.token;
    }

    public  SaasBorrowerToken setToken(String token){
        this.token = token;
        return this;
    }

    public  Date getExpireDate(){
        return this.expireDate;
    }

    public  SaasBorrowerToken setExpireDate(Date expireDate){
        this.expireDate = expireDate;
        return this;
    }
}
