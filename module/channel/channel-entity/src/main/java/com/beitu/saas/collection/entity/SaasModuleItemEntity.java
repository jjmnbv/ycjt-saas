package com.beitu.saas.collection.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: fenqiguanjia
* Date: 2018-03-21
* Time: 20:58:19.389
* TableDesc:模块字段信息表
*/
public class SaasModuleItemEntity extends BaseEntity{
    /**
    *模块号
    */
    private String moduleCode;
    /**
    *字段号
    */
    private String itemCode;
    /**
    *字段名称
    */
    private String itemDesc;


    public  String getModuleCode(){
        return this.moduleCode;
    }

    public  SaasModuleItemEntity setModuleCode(String moduleCode){
        this.moduleCode = moduleCode;
        return this;
    }

    public  String getItemCode(){
        return this.itemCode;
    }

    public  SaasModuleItemEntity setItemCode(String itemCode){
        this.itemCode = itemCode;
        return this;
    }

    public  String getItemDesc(){
        return this.itemDesc;
    }

    public  SaasModuleItemEntity setItemDesc(String itemDesc){
        this.itemDesc = itemDesc;
        return this;
    }
}
