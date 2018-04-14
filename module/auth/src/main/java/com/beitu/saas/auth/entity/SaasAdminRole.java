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
    private String adminCode;
    /**
    *角色id
    */
    private Long roleId;


    public  String getAdminCode(){
        return this.adminCode;
    }

    public  SaasAdminRole setAdminCode(String adminCode){
        this.adminCode = adminCode;
        return this;
    }

    public  Long getRoleId(){
        return this.roleId;
    }

    public  SaasAdminRole setRoleId(Long roleId){
        this.roleId = roleId;
        return this;
    }
}
