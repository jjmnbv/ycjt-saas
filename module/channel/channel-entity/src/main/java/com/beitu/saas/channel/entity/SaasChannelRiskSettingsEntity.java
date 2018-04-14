package com.beitu.saas.channel.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: fenqiguanjia
* Date: 2018-03-22
* Time: 11:13:06.167
* TableDesc:渠道风控配置信息表
*/
public class SaasChannelRiskSettingsEntity extends BaseEntity{
    /**
    *渠道号
    */
    private String channelCode;
    /**
    *模块号
    */
    private String moduleCode;
    /**
    *字段号
    */
    private String itemCode;
    /**
    *是否必填(0-否,1-是)
    */
    private Integer required;


    public  String getChannelCode(){
        return this.channelCode;
    }

    public  SaasChannelRiskSettingsEntity setChannelCode(String channelCode){
        this.channelCode = channelCode;
        return this;
    }

    public  String getModuleCode(){
        return this.moduleCode;
    }

    public  SaasChannelRiskSettingsEntity setModuleCode(String moduleCode){
        this.moduleCode = moduleCode;
        return this;
    }

    public  String getItemCode(){
        return this.itemCode;
    }

    public  SaasChannelRiskSettingsEntity setItemCode(String itemCode){
        this.itemCode = itemCode;
        return this;
    }

    public  Integer getRequired(){
        return this.required;
    }

    public  SaasChannelRiskSettingsEntity setRequired(Integer required){
        this.required = required;
        return this;
    }
}
