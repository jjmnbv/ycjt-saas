package com.beitu.saas.channel.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: fenqiguanjia
* Date: 2018-04-09
* Time: 20:43:57.460
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
    *渠道类型： 0-自定义渠道 1-系统默认渠道
    */
    private Integer channelType;
    /**
    *渠道连接
    */
    private String linkUrl;
    /**
    *负责人code
    */
    private String chargePersonCode;
    /**
    *创建人code
    */
    private String creatorCode;
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

    public  Integer getChannelType(){
        return this.channelType;
    }

    public  SaasChannelEntity setChannelType(Integer channelType){
        this.channelType = channelType;
        return this;
    }

    public  String getLinkUrl(){
        return this.linkUrl;
    }

    public  SaasChannelEntity setLinkUrl(String linkUrl){
        this.linkUrl = linkUrl;
        return this;
    }

    public  String getChargePersonCode(){
        return this.chargePersonCode;
    }

    public  SaasChannelEntity setChargePersonCode(String chargePersonCode){
        this.chargePersonCode = chargePersonCode;
        return this;
    }

    public  String getCreatorCode(){
        return this.creatorCode;
    }

    public  SaasChannelEntity setCreatorCode(String creatorCode){
        this.creatorCode = creatorCode;
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
