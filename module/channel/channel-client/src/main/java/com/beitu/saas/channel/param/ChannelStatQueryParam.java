package com.beitu.saas.channel.param;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/24
 * Time: 下午1:36
 */
public class ChannelStatQueryParam {

    private String createStartDate;
    private String createEndDate;  //当前天数
    private String merchantCode;

    public String getCreateStartDate() {
        return createStartDate;
    }

    public ChannelStatQueryParam setCreateStartDate(String createStartDate) {
        this.createStartDate = createStartDate;
        return this;
    }

    public String getCreateEndDate() {
        return createEndDate;
    }

    public ChannelStatQueryParam setCreateEndDate(String createEndDate) {
        this.createEndDate = createEndDate;
        return this;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public ChannelStatQueryParam setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
        return this;
    }
}
