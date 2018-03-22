package com.beitu.saas.auth.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.664
*/
public class SaasMerchantConfigVo implements ResponseData,Serializable{

    private Long saasMerchantConfigId;

    /**
    *机构id
    */
    private String merchantCode;
    /**
    *设置内容的枚举
    */
    private Integer configEnum;
    /**
    *设置类型1.合同2.短信3.支付密码
    */
    private Long configType;

    public Long getSaasMerchantConfigId() {
        return saasMerchantConfigId;
    }

    public void setSaasMerchantConfigId(Long saasMerchantConfigId) {
        this.saasMerchantConfigId = saasMerchantConfigId;
    }



    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  void setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
    }

    public  Integer getConfigEnum(){
        return this.configEnum;
    }

    public  void setConfigEnum(Integer configEnum){
        this.configEnum = configEnum;
    }

    public  Long getConfigType(){
        return this.configType;
    }

    public  void setConfigType(Long configType){
        this.configType = configType;
    }
}
