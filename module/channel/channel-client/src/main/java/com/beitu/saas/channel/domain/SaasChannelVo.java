package com.beitu.saas.channel.domain;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 上午10:56
 */
public class SaasChannelVo {

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
     *渠道长连接
     */
    private String longLinkUrl;

    /**
     *渠道短连接
     */
    private String shortLinkUrl;
    /**
     *负责人code
     */
    private String chargePersonCode;
    /**
     *负责人
     */
    private String chargePersonName;
    /**
     *创建人code
     */
    private String creatorCode;
    /**
     *创建人
     */
    private String creatorName;
    /**
     *备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date gmtCreate;


    public String getChargePersonCode() {
        return chargePersonCode;
    }

    public SaasChannelVo setChargePersonCode(String chargePersonCode) {
        this.chargePersonCode = chargePersonCode;
        return this;
    }

    public String getCreatorCode() {
        return creatorCode;
    }

    public SaasChannelVo setCreatorCode(String creatorCode) {
        this.creatorCode = creatorCode;
        return this;
    }

    public String getLongLinkUrl() {
        return longLinkUrl;
    }

    public SaasChannelVo setLongLinkUrl(String longLinkUrl) {
        this.longLinkUrl = longLinkUrl;
        return this;
    }

    public String getShortLinkUrl() {
        return shortLinkUrl;
    }

    public SaasChannelVo setShortLinkUrl(String shortLinkUrl) {
        this.shortLinkUrl = shortLinkUrl;
        return this;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public SaasChannelVo setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public SaasChannelVo setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
        return this;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public SaasChannelVo setChannelCode(String channelCode) {
        this.channelCode = channelCode;
        return this;
    }

    public String getChannelName() {
        return channelName;
    }

    public SaasChannelVo setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }

    public Integer getChannelStatus() {
        return channelStatus;
    }

    public SaasChannelVo setChannelStatus(Integer channelStatus) {
        this.channelStatus = channelStatus;
        return this;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public SaasChannelVo setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        return this;
    }

    public String getChargePersonName() {
        return chargePersonName;
    }

    public SaasChannelVo setChargePersonName(String chargePersonName) {
        this.chargePersonName = chargePersonName;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public SaasChannelVo setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public SaasChannelVo setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
