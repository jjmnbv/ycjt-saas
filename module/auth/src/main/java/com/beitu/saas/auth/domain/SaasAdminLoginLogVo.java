package com.beitu.saas.auth.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:33:55.834
*/
public class SaasAdminLoginLogVo implements ResponseData,Serializable{

    private Long saasAdminLoginLogId;

    /**
    *管理员code
    */
    private String adminCode;
    /**
    *登录IP
    */
    private String loginIp;
    /**
    *登录详细地址
    */
    private String loginIpAddress;

    public Long getSaasAdminLoginLogId() {
        return saasAdminLoginLogId;
    }

    public void setSaasAdminLoginLogId(Long saasAdminLoginLogId) {
        this.saasAdminLoginLogId = saasAdminLoginLogId;
    }



    public  String getAdminCode(){
        return this.adminCode;
    }

    public  void setAdminCode(String adminCode){
        this.adminCode = adminCode;
    }

    public  String getLoginIp(){
        return this.loginIp;
    }

    public  void setLoginIp(String loginIp){
        this.loginIp = loginIp;
    }

    public  String getLoginIpAddress(){
        return this.loginIpAddress;
    }

    public  void setLoginIpAddress(String loginIpAddress){
        this.loginIpAddress = loginIpAddress;
    }
}
