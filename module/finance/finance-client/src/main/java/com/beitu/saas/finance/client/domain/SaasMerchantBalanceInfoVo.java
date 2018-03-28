package com.beitu.saas.finance.client.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* User: fenqiguanjia
* Date: 2018-03-28
* Time: 11:41:55.181
*/
public class SaasMerchantBalanceInfoVo implements ResponseData,Serializable{

    private Long saasMerchantBalanceInfoId;

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

    public Long getSaasMerchantBalanceInfoId() {
        return saasMerchantBalanceInfoId;
    }

    public void setSaasMerchantBalanceInfoId(Long saasMerchantBalanceInfoId) {
        this.saasMerchantBalanceInfoId = saasMerchantBalanceInfoId;
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
