package com.beitu.saas.collection.vo;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/23
 * Time: 上午11:35
 */

public class CollectionOrderInfoDetailVo {


    private String orderNo;
    private String name;
    private String mobile;
    private BigDecimal realCapital;
    private BigDecimal shouldRepayCapital;
    private String createdDt;
    private String repaymentDt;
    private String extend;
    private Integer overdueDuration;
    private String channelName;

    public String getOrderNo() {
        return orderNo;
    }

    public CollectionOrderInfoDetailVo setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public String getName() {
        return name;
    }

    public CollectionOrderInfoDetailVo setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public CollectionOrderInfoDetailVo setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public BigDecimal getRealCapital() {
        return realCapital;
    }

    public CollectionOrderInfoDetailVo setRealCapital(BigDecimal realCapital) {
        this.realCapital = realCapital;
        return this;
    }

    public BigDecimal getShouldRepayCapital() {
        return shouldRepayCapital;
    }

    public CollectionOrderInfoDetailVo setShouldRepayCapital(BigDecimal shouldRepayCapital) {
        this.shouldRepayCapital = shouldRepayCapital;
        return this;
    }

    public String getCreatedDt() {
        return createdDt;
    }

    public CollectionOrderInfoDetailVo setCreatedDt(String createdDt) {
        this.createdDt = createdDt;
        return this;
    }

    public String getRepaymentDt() {
        return repaymentDt;
    }

    public CollectionOrderInfoDetailVo setRepaymentDt(String repaymentDt) {
        this.repaymentDt = repaymentDt;
        return this;
    }

    public String getExtend() {
        return extend;
    }

    public CollectionOrderInfoDetailVo setExtend(String extend) {
        this.extend = extend;
        return this;
    }

    public Integer getOverdueDuration() {
        return overdueDuration;
    }

    public CollectionOrderInfoDetailVo setOverdueDuration(Integer overdueDuration) {
        this.overdueDuration = overdueDuration;
        return this;
    }

    public String getChannelName() {
        return channelName;
    }

    public CollectionOrderInfoDetailVo setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }
}
