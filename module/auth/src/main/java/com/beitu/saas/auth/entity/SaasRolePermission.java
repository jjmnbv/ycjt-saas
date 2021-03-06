package com.beitu.saas.auth.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: xiaochong
* Date: 2018-03-23
* Time: 16:00:30.804
* TableDesc:
*/
public class SaasRolePermission extends BaseEntity {
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


    public  Integer getRoleId(){
        return this.roleId;
    }

    public SaasRolePermission setRoleId(Integer roleId){
        this.roleId = roleId;
        return this;
    }

    public  Integer getPermissionKey(){
        return this.permissionKey;
    }

    public SaasRolePermission setPermissionKey(Integer permissionKey){
        this.permissionKey = permissionKey;
        return this;
    }

    public  Long getPermissionType(){
        return this.permissionType;
    }

    public SaasRolePermission setPermissionType(Long permissionType){
        this.permissionType = permissionType;
        return this;
    }

    public  Integer getRelationId(){
        return this.relationId;
    }

    public SaasRolePermission setRelationId(Integer relationId){
        this.relationId = relationId;
        return this;
    }
}
