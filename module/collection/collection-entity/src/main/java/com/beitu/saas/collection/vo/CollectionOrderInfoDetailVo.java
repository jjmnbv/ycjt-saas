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
    private String gender;
    private String identityCode;
    private BigDecimal realCapital;
    private BigDecimal totalRealCapital;
    private String applyDays;
    private String channelCode;
    private String remark;

    private String channelName;
    private Integer status;
    private Integer age;

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

    public String getGender() {
        return gender;
    }

    public CollectionOrderInfoDetailVo setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public CollectionOrderInfoDetailVo setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
        return this;
    }

    public BigDecimal getRealCapital() {
        return realCapital;
    }

    public CollectionOrderInfoDetailVo setRealCapital(BigDecimal realCapital) {
        this.realCapital = realCapital;
        return this;
    }

    public BigDecimal getTotalRealCapital() {
        return totalRealCapital;
    }

    public CollectionOrderInfoDetailVo setTotalRealCapital(BigDecimal totalRealCapital) {
        this.totalRealCapital = totalRealCapital;
        return this;
    }

    public String getApplyDays() {
        return applyDays;
    }

    public CollectionOrderInfoDetailVo setApplyDays(String applyDays) {
        this.applyDays = applyDays;
        return this;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public CollectionOrderInfoDetailVo setChannelCode(String channelCode) {
        this.channelCode = channelCode;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public CollectionOrderInfoDetailVo setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getChannelName() {
        return channelName;
    }

    public CollectionOrderInfoDetailVo setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public CollectionOrderInfoDetailVo setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public CollectionOrderInfoDetailVo setAge(Integer age) {
        this.age = age;
        return this;
    }
}
