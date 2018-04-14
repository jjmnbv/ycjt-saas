package com.beitu.saas.auth.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:16.653
* TableDesc:机构配置表
*/
public class SaasMerchantConfig extends BaseEntity {
    /**
    *机构id
    */
    private String merchantCode;
    /**
    *设置内容的枚举
    */
    private String configEnum;
    /**
    *设置类型1.合同2.短信3.支付密码
    */
    private Long configType;


    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  SaasMerchantConfig setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
        return this;
    }

    public  String getConfigEnum(){
        return this.configEnum;
    }

    public  SaasMerchantConfig setConfigEnum(String configEnum){
        this.configEnum = configEnum;
        return this;
    }

    public  Long getConfigType(){
        return this.configType;
    }

    public  SaasMerchantConfig setConfigType(Long configType){
        this.configType = configType;
        return this;
    }
}
