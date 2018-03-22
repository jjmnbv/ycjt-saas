package com.beitu.saas.channel.domain;

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
     *渠道名称
     */
    private String channelName;
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

    public String getMerchantCode() {
        return merchantCode;
    }

    public SaasChannelVo setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
        return this;
    }

    public String getChannelName() {
        return channelName;
    }

    public SaasChannelVo setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public SaasChannelVo setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        return this;
    }

    public String getChargePerson() {
        return chargePerson;
    }

    public SaasChannelVo setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
        return this;
    }

    public String getCreator() {
        return creator;
    }

    public SaasChannelVo setCreator(String creator) {
        this.creator = creator;
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
