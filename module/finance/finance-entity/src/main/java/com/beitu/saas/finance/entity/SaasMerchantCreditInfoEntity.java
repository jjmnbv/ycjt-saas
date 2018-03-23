package com.beitu.saas.finance.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: fenqiguanjia
* Date: 2018-03-23
* Time: 21:24:28.685
* TableDesc:机构点券表
*/
public class SaasMerchantCreditInfoEntity extends BaseEntity{
    /**
    *机构码
    */
    private String merchantCode;
    /**
    *机构点券余额
    */
    private Long value;
    /**
    *机构点券加密结果
    */
    private String encryptValue;
    /**
    *
    */
    private Long version;


    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  SaasMerchantCreditInfoEntity setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
        return this;
    }

    public  Long getValue(){
        return this.value;
    }

    public  SaasMerchantCreditInfoEntity setValue(Long value){
        this.value = value;
        return this;
    }

    public  String getEncryptValue(){
        return this.encryptValue;
    }

    public  SaasMerchantCreditInfoEntity setEncryptValue(String encryptValue){
        this.encryptValue = encryptValue;
        return this;
    }

    public  Long getVersion(){
        return this.version;
    }

    public  SaasMerchantCreditInfoEntity setVersion(Long version){
        this.version = version;
        return this;
    }
}
