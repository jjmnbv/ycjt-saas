package com.beitu.saas.auth.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.678
*/
public class SaasPermissionVo implements ResponseData,Serializable{

    private Long saasPermissionId;

    /**
    *名称
    */
    private String name;
    /**
    *权限key
    */
    private String permission;
    /**
    *所属菜单或按钮
    */
    private Integer relationId;
    /**
    *1.菜单2.按钮
    */
    private Integer type;

    public Long getSaasPermissionId() {
        return saasPermissionId;
    }

    public void setSaasPermissionId(Long saasPermissionId) {
        this.saasPermissionId = saasPermissionId;
    }



    public  String getName(){
        return this.name;
    }

    public  void setName(String name){
        this.name = name;
    }

    public  String getPermission(){
        return this.permission;
    }

    public  void setPermission(String permission){
        this.permission = permission;
    }

    public  Integer getRelationId(){
        return this.relationId;
    }

    public  void setRelationId(Integer relationId){
        this.relationId = relationId;
    }

    public  Integer getType(){
        return this.type;
    }

    public  void setType(Integer type){
        this.type = type;
    }
}
