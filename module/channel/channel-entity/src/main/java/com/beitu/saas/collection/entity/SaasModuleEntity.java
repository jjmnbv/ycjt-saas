package com.beitu.saas.collection.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: fenqiguanjia
* Date: 2018-03-21
* Time: 20:58:19.380
* TableDesc:风控模块信息表
*/
public class SaasModuleEntity extends BaseEntity{
    /**
    *模块号
    */
    private String moduleCode;
    /**
    *模块名称
    */
    private String moduleDesc;


    public  String getModuleCode(){
        return this.moduleCode;
    }

    public  SaasModuleEntity setModuleCode(String moduleCode){
        this.moduleCode = moduleCode;
        return this;
    }

    public  String getModuleDesc(){
        return this.moduleDesc;
    }

    public  SaasModuleEntity setModuleDesc(String moduleDesc){
        this.moduleDesc = moduleDesc;
        return this;
    }
}
