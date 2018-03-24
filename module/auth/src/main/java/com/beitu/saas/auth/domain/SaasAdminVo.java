package com.beitu.saas.auth.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:33:55.747
*/
public class SaasAdminVo implements ResponseData,Serializable{

    private Long saasAdminId;

    /**
    *code
    */
    private String code;
    /**
    *机构code
    */
    private String merchantCode;
    /**
    *密码
    */
    private String password;
    /**
    *姓名
    */
    private String name;
    /**
    *手机号
    */
    private String mobile;
    /**
     *职位
     */
    private String job;
    /**
    *
    */
    private Boolean enable;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Long getSaasAdminId() {
        return saasAdminId;
    }

    public void setSaasAdminId(Long saasAdminId) {
        this.saasAdminId = saasAdminId;
    }



    public  String getCode(){
        return this.code;
    }

    public  void setCode(String code){
        this.code = code;
    }

    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  void setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
    }

    public  String getPassword(){
        return this.password;
    }

    public  void setPassword(String password){
        this.password = password;
    }

    public  String getName(){
        return this.name;
    }

    public  void setName(String name){
        this.name = name;
    }

    public  String getMobile(){
        return this.mobile;
    }

    public  void setMobile(String mobile){
        this.mobile = mobile;
    }

    public  Boolean getEnable(){
        return this.enable;
    }

    public  void setEnable(Boolean enable){
        this.enable = enable;
    }
}
