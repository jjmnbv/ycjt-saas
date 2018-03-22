package com.beitu.saas.sms.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: jungle
* Date: 2018-02-28
* Time: 15:46:53.744
* TableDesc:触发场景信息表
*/
public class SmsBizFunction extends BaseEntity{
    /**
    *
    */
    private String bizCode;
    /**
    *app id
    */
    private Long appId;
    /**
    *功能名
    */
    private String bizName;
    /**
    *描述
    */
    private String descInfo;


    public  String getBizCode(){
        return this.bizCode;
    }

    public  SmsBizFunction setBizCode(String bizCode){
        this.bizCode = bizCode;
        return this;
    }

    public  Long getAppId(){
        return this.appId;
    }

    public  SmsBizFunction setAppId(Long appId){
        this.appId = appId;
        return this;
    }

    public  String getBizName(){
        return this.bizName;
    }

    public  SmsBizFunction setBizName(String bizName){
        this.bizName = bizName;
        return this;
    }

    public  String getDescInfo(){
        return this.descInfo;
    }

    public  SmsBizFunction setDescInfo(String descInfo){
        this.descInfo = descInfo;
        return this;
    }
}
