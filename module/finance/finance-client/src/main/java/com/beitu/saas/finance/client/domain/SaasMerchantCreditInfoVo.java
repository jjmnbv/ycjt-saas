package com.beitu.saas.finance.client.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* User: fenqiguanjia
* Date: 2018-03-23
* Time: 20:33:11.048
*/
public class SaasMerchantCreditInfoVo implements ResponseData,Serializable{

    private Long saasMerchantCreditInfoId;

    /**
    *机构码
    */
    private String merchantCode;
    /**
    *机构点券余额
    */
    private BigDecimal value;
    /**
    *机构点券加密结果
    */
    private String encryptValue;
    /**
    *
    */
    private Long version;

    public Long getSaasMerchantCreditInfoId() {
        return saasMerchantCreditInfoId;
    }

    public void setSaasMerchantCreditInfoId(Long saasMerchantCreditInfoId) {
        this.saasMerchantCreditInfoId = saasMerchantCreditInfoId;
    }



    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  void setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
    }

    public  BigDecimal getValue(){
        return this.value;
    }

    public  void setValue(BigDecimal value){
        this.value = value;
    }

    public  String getEncryptValue(){
        return this.encryptValue;
    }

    public  void setEncryptValue(String encryptValue){
        this.encryptValue = encryptValue;
    }

    public  Long getVersion(){
        return this.version;
    }

    public  void setVersion(Long version){
        this.version = version;
    }
}
