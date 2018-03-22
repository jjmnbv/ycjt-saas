package com.beitu.saas.auth.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:16.637
* TableDesc:管理员登录日志表
*/
public class SaasAdminLoginLog extends BaseEntity {
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


    public  String getAdminCode(){
        return this.adminCode;
    }

    public  SaasAdminLoginLog setAdminCode(String adminCode){
        this.adminCode = adminCode;
        return this;
    }

    public  String getLoginIp(){
        return this.loginIp;
    }

    public  SaasAdminLoginLog setLoginIp(String loginIp){
        this.loginIp = loginIp;
        return this;
    }

    public  String getLoginIpAddress(){
        return this.loginIpAddress;
    }

    public  SaasAdminLoginLog setLoginIpAddress(String loginIpAddress){
        this.loginIpAddress = loginIpAddress;
        return this;
    }
}
