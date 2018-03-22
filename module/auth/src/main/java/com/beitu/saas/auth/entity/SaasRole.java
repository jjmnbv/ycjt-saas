package com.beitu.saas.auth.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:16.662
* TableDesc:
*/
public class SaasRole extends BaseEntity {
    /**
    *机构code
    */
    private String merchantCode;
    /**
    *角色名
    */
    private String name;
    /**
    *角色描述
    */
    private String description;
    /**
    *角色创建人
    */
    private String createName;
    /**
    *是否启用
    */
    private Boolean enabled;


    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  SaasRole setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
        return this;
    }

    public  String getName(){
        return this.name;
    }

    public  SaasRole setName(String name){
        this.name = name;
        return this;
    }

    public  String getDescription(){
        return this.description;
    }

    public  SaasRole setDescription(String description){
        this.description = description;
        return this;
    }

    public  String getCreateName(){
        return this.createName;
    }

    public  SaasRole setCreateName(String createName){
        this.createName = createName;
        return this;
    }

    public  Boolean getEnabled(){
        return this.enabled;
    }

    public  SaasRole setEnabled(Boolean enabled){
        this.enabled = enabled;
        return this;
    }
}
