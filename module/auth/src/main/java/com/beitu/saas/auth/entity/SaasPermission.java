package com.beitu.saas.auth.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:16.659
* TableDesc:
*/
public class SaasPermission extends BaseEntity {
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


    public  String getName(){
        return this.name;
    }

    public  SaasPermission setName(String name){
        this.name = name;
        return this;
    }

    public  String getPermission(){
        return this.permission;
    }

    public  SaasPermission setPermission(String permission){
        this.permission = permission;
        return this;
    }

    public  Integer getRelationId(){
        return this.relationId;
    }

    public  SaasPermission setRelationId(Integer relationId){
        this.relationId = relationId;
        return this;
    }

    public  Integer getType(){
        return this.type;
    }

    public  SaasPermission setType(Integer type){
        this.type = type;
        return this;
    }
}
