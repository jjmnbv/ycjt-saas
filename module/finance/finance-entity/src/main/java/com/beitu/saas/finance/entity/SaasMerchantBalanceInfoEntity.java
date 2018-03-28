package com.beitu.saas.finance.entity;

import com.fqgj.common.entity.BaseEntity;

import java.math.BigDecimal;

/**
* User: fenqiguanjia
* Date: 2018-03-28
* Time: 11:41:55.194
* TableDesc:机构账户余额表
*/
public class SaasMerchantBalanceInfoEntity extends BaseEntity{
    /**
    *机构码
    */
    private String merchantCode;
    /**
    *用户账户余额
    */
    private BigDecimal value;
    /**
    *用户账户余额加密结果
    */
    private String encryptValue;
    /**
    *
    */
    private Long version;


    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  SaasMerchantBalanceInfoEntity setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
        return this;
    }

    public  BigDecimal getValue(){
        return this.value;
    }

    public  SaasMerchantBalanceInfoEntity setValue(BigDecimal value){
        this.value = value;
        return this;
    }

    public  String getEncryptValue(){
        return this.encryptValue;
    }

    public  SaasMerchantBalanceInfoEntity setEncryptValue(String encryptValue){
        this.encryptValue = encryptValue;
        return this;
    }

    public  Long getVersion(){
        return this.version;
    }

    public  SaasMerchantBalanceInfoEntity setVersion(Long version){
        this.version = version;
        return this;
    }
}
