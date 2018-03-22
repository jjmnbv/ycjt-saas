package com.beitu.saas.channel;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 下午2:58
 */
public class SaasChannelParam {
    /**
     * 机构号
     */
    private String merchantCode;
    /**
     * 渠道名称
     */
    private String channelName;
    /**
     * 渠道号
     */
    private String channelCode;
    /**
     * 渠道状态(0-开启,1-关闭)
     */
    private Integer channelStatus;
    /**
     * 负责人
     */
    private String chargePerson;

    public String getMerchantCode() {
        return merchantCode;
    }

    public SaasChannelParam setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
        return this;
    }

    public String getChannelName() {
        return channelName;
    }

    public SaasChannelParam setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public SaasChannelParam setChannelCode(String channelCode) {
        this.channelCode = channelCode;
        return this;
    }

    public Integer getChannelStatus() {
        return channelStatus;
    }

    public SaasChannelParam setChannelStatus(Integer channelStatus) {
        this.channelStatus = channelStatus;
        return this;
    }

    public String getChargePerson() {
        return chargePerson;
    }

    public SaasChannelParam setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
        return this;
    }
}
