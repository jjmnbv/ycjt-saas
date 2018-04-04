package com.beitu.saas.collection.param;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/24
 * Time: 下午1:36
 */
public class CollectionOrderQueryParam {
    private String overdueStartDate;  //N天前
    private String overdueEndDate;  //当前天数
    private Integer overdueDaysType;
    private String mobile;
    private String name;
    private String identityCode;
    private String channelCode;

    public String getOverdueStartDate() {
        return overdueStartDate;
    }

    public CollectionOrderQueryParam setOverdueStartDate(String overdueStartDate) {
        this.overdueStartDate = overdueStartDate;
        return this;
    }

    public String getOverdueEndDate() {
        return overdueEndDate;
    }

    public CollectionOrderQueryParam setOverdueEndDate(String overdueEndDate) {
        this.overdueEndDate = overdueEndDate;
        return this;
    }

    public Integer getOverdueDaysType() {
        return overdueDaysType;
    }

    public CollectionOrderQueryParam setOverdueDaysType(Integer overdueDaysType) {
        this.overdueDaysType = overdueDaysType;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public CollectionOrderQueryParam setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getName() {
        return name;
    }

    public CollectionOrderQueryParam setName(String name) {
        this.name = name;
        return this;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public CollectionOrderQueryParam setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
        return this;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public CollectionOrderQueryParam setChannelCode(String channelCode) {
        this.channelCode = channelCode;
        return this;
    }
}
