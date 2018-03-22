package com.beitu.saas.auth.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;
import java.util.Date;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.634
*/
public class SaasAdminTokenVo implements ResponseData,Serializable{

    private Long saasAdminTokenId;

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

    public Long getSaasAdminTokenId() {
        return saasAdminTokenId;
    }

    public void setSaasAdminTokenId(Long saasAdminTokenId) {
        this.saasAdminTokenId = saasAdminTokenId;
    }



    public  String getAccessToken(){
        return this.accessToken;
    }

    public  void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }

    public  String getAdminCode(){
        return this.adminCode;
    }

    public  void setAdminCode(String adminCode){
        this.adminCode = adminCode;
    }

    public  Date getExpireDate(){
        return this.expireDate;
    }

    public  void setExpireDate(Date expireDate){
        this.expireDate = expireDate;
    }
}
