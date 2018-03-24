package com.beitu.saas.channel.domain;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/24
 * Time: 下午1:36
 */
public class CollectionOrderQueryVo {
    private String overdueStartDate;  //N天前
    private String overdueEndDate;  //当前天数
    private Integer overdueDaysType;
    private String mobile;
    private String name;
    private String identityCode;
    private String channelCode;

    public Integer getOverdueDaysType() {
        return overdueDaysType;
    }

    public CollectionOrderQueryVo setOverdueDaysType(Integer overdueDaysType) {
        this.overdueDaysType = overdueDaysType;
        return this;
    }

    public String getOverdueStartDate() {
        return overdueStartDate;
    }

    public CollectionOrderQueryVo setOverdueStartDate(String overdueStartDate) {
        this.overdueStartDate = overdueStartDate;
        return this;
    }

    public String getOverdueEndDate() {
        return overdueEndDate;
    }

    public CollectionOrderQueryVo setOverdueEndDate(String overdueEndDate) {
        this.overdueEndDate = overdueEndDate;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public CollectionOrderQueryVo setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getName() {
        return name;
    }

    public CollectionOrderQueryVo setName(String name) {
        this.name = name;
        return this;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public CollectionOrderQueryVo setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
        return this;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public CollectionOrderQueryVo setChannelCode(String channelCode) {
        this.channelCode = channelCode;
        return this;
    }
}
