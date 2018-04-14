package com.beitu.saas.auth.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:33:55.843
*/
public class SaasAdminRoleVo implements ResponseData,Serializable{

    private Long saasAdminRoleId;

    /**
    *管理员code
    */
    private String adminCode;
    /**
    *角色id
    */
    private Long roleId;

    public Long getSaasAdminRoleId() {
        return saasAdminRoleId;
    }

    public void setSaasAdminRoleId(Long saasAdminRoleId) {
        this.saasAdminRoleId = saasAdminRoleId;
    }



    public  String getAdminCode(){
        return this.adminCode;
    }

    public  void setAdminCode(String adminCode){
        this.adminCode = adminCode;
    }

    public  Long getRoleId(){
        return this.roleId;
    }

    public  void setRoleId(Long roleId){
        this.roleId = roleId;
    }
}
