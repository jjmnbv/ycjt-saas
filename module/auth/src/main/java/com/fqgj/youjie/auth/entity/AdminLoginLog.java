package com.fqgj.youjie.auth.entity;
import com.fqgj.common.entity.BaseEntity;
import java.util.Date;
/**
* User: jungle
* Date: 2017-12-29
* Time: 11:41:07.508
* TableDesc:管理员登录日志表
*/
public class AdminLoginLog extends BaseEntity{
    /**
    *管理员id
    */
    private Long adminId;
    /**
    *登录IP
    */
    private String loginIp;
    /**
    *登录详细地址
    */
    private String loginIpAddress;
    /**
    *登录时间
    */
    private Date loginDate;
    /**
    *
    */
    private Integer isDeleted;


    public  Long getAdminId(){
        return this.adminId;
    }

    public  AdminLoginLog setAdminId(Long adminId){
        this.adminId = adminId;
        return this;
    }

    public  String getLoginIp(){
        return this.loginIp;
    }

    public  AdminLoginLog setLoginIp(String loginIp){
        this.loginIp = loginIp;
        return this;
    }

    public  String getLoginIpAddress(){
        return this.loginIpAddress;
    }

    public  AdminLoginLog setLoginIpAddress(String loginIpAddress){
        this.loginIpAddress = loginIpAddress;
        return this;
    }

    public  Date getLoginDate(){
        return this.loginDate;
    }

    public  AdminLoginLog setLoginDate(Date loginDate){
        this.loginDate = loginDate;
        return this;
    }

    public  Integer getIsDeleted(){
        return this.isDeleted;
    }

    public  AdminLoginLog setIsDeleted(Integer isDeleted){
        this.isDeleted = isDeleted;
        return this;
    }
}
