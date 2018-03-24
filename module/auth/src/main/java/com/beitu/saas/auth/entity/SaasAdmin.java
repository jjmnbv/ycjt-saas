package com.beitu.saas.auth.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:16.634
* TableDesc:saas用户表
*/
public class SaasAdmin extends BaseEntity {
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

    public  String getCode(){
        return this.code;
    }

    public  SaasAdmin setCode(String code){
        this.code = code;
        return this;
    }

    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  SaasAdmin setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
        return this;
    }

    public  String getPassword(){
        return this.password;
    }

    public  SaasAdmin setPassword(String password){
        this.password = password;
        return this;
    }

    public  String getName(){
        return this.name;
    }

    public  SaasAdmin setName(String name){
        this.name = name;
        return this;
    }

    public  String getMobile(){
        return this.mobile;
    }

    public  SaasAdmin setMobile(String mobile){
        this.mobile = mobile;
        return this;
    }

    public  Boolean getEnable(){
        return this.enable;
    }

    public  SaasAdmin setEnable(Boolean enable){
        this.enable = enable;
        return this;
    }
}
