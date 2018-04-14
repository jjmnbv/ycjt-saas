package com.beitu.saas.auth.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.687
*/
public class SaasRoleVo implements ResponseData,Serializable{

    private Long saasRoleId;

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

    public Long getSaasRoleId() {
        return saasRoleId;
    }

    public void setSaasRoleId(Long saasRoleId) {
        this.saasRoleId = saasRoleId;
    }



    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  void setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
    }

    public  String getName(){
        return this.name;
    }

    public  void setName(String name){
        this.name = name;
    }

    public  String getDescription(){
        return this.description;
    }

    public  void setDescription(String description){
        this.description = description;
    }

    public  String getCreateName(){
        return this.createName;
    }

    public  void setCreateName(String createName){
        this.createName = createName;
    }

    public  Boolean getEnabled(){
        return this.enabled;
    }

    public  void setEnabled(Boolean enabled){
        this.enabled = enabled;
    }
}
