package com.beitu.saas.rest.controller.collection.request;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/24
 * Time: 下午1:36
 */
public class CollectionOrderQueryRequestParam {
    private Integer overdueDaysType;
    private String mobile;
    private String name;
    private String identityCode;
    private String channelCode;

    public Integer getOverdueDaysType() {
        return overdueDaysType;
    }

    public CollectionOrderQueryRequestParam setOverdueDaysType(Integer overdueDaysType) {
        this.overdueDaysType = overdueDaysType;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public CollectionOrderQueryRequestParam setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getName() {
        return name;
    }

    public CollectionOrderQueryRequestParam setName(String name) {
        this.name = name;
        return this;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public CollectionOrderQueryRequestParam setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
        return this;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public CollectionOrderQueryRequestParam setChannelCode(String channelCode) {
        this.channelCode = channelCode;
        return this;
    }
}
