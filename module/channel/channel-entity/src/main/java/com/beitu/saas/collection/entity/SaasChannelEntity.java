package com.beitu.saas.collection.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: fenqiguanjia
* Date: 2018-03-22
* Time: 11:40:43.402
* TableDesc:渠道信息表
*/
public class SaasChannelEntity extends BaseEntity{
    /**
    *机构号
    */
    private String merchantCode;
    /**
    *渠道号
    */
    private String channelCode;
    /**
    *渠道名称
    */
    private String channelName;
    /**
    *渠道状态(0-开启,1-关闭)
    */
    private Integer channelStatus;
    /**
    *渠道连接
    */
    private String linkUrl;
    /**
    *负责人
    */
    private String chargePerson;
    /**
    *创建人
    */
    private String creator;
    /**
    *备注
    */
    private String remark;


    public  String getMerchantCode(){
        return this.merchantCode;
    }

    public  SaasChannelEntity setMerchantCode(String merchantCode){
        this.merchantCode = merchantCode;
        return this;
    }

    public  String getChannelCode(){
        return this.channelCode;
    }

    public  SaasChannelEntity setChannelCode(String channelCode){
        this.channelCode = channelCode;
        return this;
    }

    public  String getChannelName(){
        return this.channelName;
    }

    public  SaasChannelEntity setChannelName(String channelName){
        this.channelName = channelName;
        return this;
    }

    public  Integer getChannelStatus(){
        return this.channelStatus;
    }

    public  SaasChannelEntity setChannelStatus(Integer channelStatus){
        this.channelStatus = channelStatus;
        return this;
    }

    public  String getLinkUrl(){
        return this.linkUrl;
    }

    public  SaasChannelEntity setLinkUrl(String linkUrl){
        this.linkUrl = linkUrl;
        return this;
    }

    public  String getChargePerson(){
        return this.chargePerson;
    }

    public  SaasChannelEntity setChargePerson(String chargePerson){
        this.chargePerson = chargePerson;
        return this;
    }

    public  String getCreator(){
        return this.creator;
    }

    public  SaasChannelEntity setCreator(String creator){
        this.creator = creator;
        return this;
    }

    public  String getRemark(){
        return this.remark;
    }

    public  SaasChannelEntity setRemark(String remark){
        this.remark = remark;
        return this;
    }
}
