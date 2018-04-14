package com.beitu.saas.borrower;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/4/12
 * Time: 下午3:15
 */
public class BorrowerInfoParam {
    private String name;
    private String mobile;
    private String identityCode;
    private String channelCode;
    private String merchantCode;

    public String getName() {
        return name;
    }

    public BorrowerInfoParam setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public BorrowerInfoParam setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public BorrowerInfoParam setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
        return this;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public BorrowerInfoParam setChannelCode(String channelCode) {
        this.channelCode = channelCode;
        return this;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public BorrowerInfoParam setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
        return this;
    }
}
