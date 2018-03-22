package com.beitu.saas.auth.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:16.640
* TableDesc:
*/
public class SaasAdminRole extends BaseEntity {
    /**
    *管理员code
    */
    private Integer adminCode;
    /**
    *角色id
    */
    private Integer roleId;


    public  Integer getAdminCode(){
        return this.adminCode;
    }

    public  SaasAdminRole setAdminCode(Integer adminCode){
        this.adminCode = adminCode;
        return this;
    }

    public  Integer getRoleId(){
        return this.roleId;
    }

    public  SaasAdminRole setRoleId(Integer roleId){
        this.roleId = roleId;
        return this;
    }
}
