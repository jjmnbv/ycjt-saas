package com.beitu.saas.auth.entity;

import com.fqgj.common.entity.BaseEntity;

import java.util.Date;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:16.644
* TableDesc:
*/
public class SaasAdminToken extends BaseEntity {
    /**
    *token
    */
    private String accessToken;
    /**
    *管理员code
    */
    private String adminCode;
    /**
    *过期时间
    */
    private Date expireDate;


    public  String getAccessToken(){
        return this.accessToken;
    }

    public  SaasAdminToken setAccessToken(String accessToken){
        this.accessToken = accessToken;
        return this;
    }

    public  String getAdminCode(){
        return this.adminCode;
    }

    public  SaasAdminToken setAdminCode(String adminCode){
        this.adminCode = adminCode;
        return this;
    }

    public  Date getExpireDate(){
        return this.expireDate;
    }

    public  SaasAdminToken setExpireDate(Date expireDate){
        this.expireDate = expireDate;
        return this;
    }
}
