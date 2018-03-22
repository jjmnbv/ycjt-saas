package com.beitu.saas.auth.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.693
*/
public class SaasRolePermissionVo implements ResponseData,Serializable{

    private Long saasRolePermissionId;

    /**
    *角色id
    */
    private Integer roleId;
    /**
    *权限key
    */
    private Integer permissionKey;
    /**
    *1.菜单2.按钮
    */
    private Long permissionType;

    public Long getSaasRolePermissionId() {
        return saasRolePermissionId;
    }

    public void setSaasRolePermissionId(Long saasRolePermissionId) {
        this.saasRolePermissionId = saasRolePermissionId;
    }



    public  Integer getRoleId(){
        return this.roleId;
    }

    public  void setRoleId(Integer roleId){
        this.roleId = roleId;
    }

    public  Integer getPermissionKey(){
        return this.permissionKey;
    }

    public  void setPermissionKey(Integer permissionKey){
        this.permissionKey = permissionKey;
    }

    public  Long getPermissionType(){
        return this.permissionType;
    }

    public  void setPermissionType(Long permissionType){
        this.permissionType = permissionType;
    }
}
