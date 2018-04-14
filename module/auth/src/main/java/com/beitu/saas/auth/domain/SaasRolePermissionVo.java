package com.beitu.saas.auth.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
* User: xiaochong
* Date: 2018-03-23
* Time: 16:00:29.735
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
    /**
    *所属菜单或按钮
    */
    private Integer relationId;

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

    public  Integer getRelationId(){
        return this.relationId;
    }

    public  void setRelationId(Integer relationId){
        this.relationId = relationId;
    }
}
