package com.beitu.saas.channel.domain;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/23
 * Time: 上午10:19
 */
public class SaasH5ChannelVo {
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



    public String getMerchantCode() {
        return merchantCode;
    }

    public SaasH5ChannelVo setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
        return this;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public SaasH5ChannelVo setChannelCode(String channelCode) {
        this.channelCode = channelCode;
        return this;
    }

    public String getChannelName() {
        return channelName;
    }

    public SaasH5ChannelVo setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }
}
