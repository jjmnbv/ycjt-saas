package com.beitu.saas.app.application.collection.vo;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;

import java.math.BigDecimal;

/**
 * @author linanjun
 * @create 2018/4/1 下午8:39
 * @description
 */
@ApiModel(value = "催收列表展示信息")
public class CollectionOrderListVo implements ResponseData {

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

    public CollectionOrderListVo setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public String getName() {
        return name;
    }

    public CollectionOrderListVo setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public CollectionOrderListVo setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public BigDecimal getRealCapital() {
        return realCapital;
    }

    public CollectionOrderListVo setRealCapital(BigDecimal realCapital) {
        this.realCapital = realCapital;
        return this;
    }

    public BigDecimal getShouldRepayCapital() {
        return shouldRepayCapital;
    }

    public CollectionOrderListVo setShouldRepayCapital(BigDecimal shouldRepayCapital) {
        this.shouldRepayCapital = shouldRepayCapital;
        return this;
    }

    public String getCreatedDt() {
        return createdDt;
    }

    public CollectionOrderListVo setCreatedDt(String createdDt) {
        this.createdDt = createdDt;
        return this;
    }

    public String getRepaymentDt() {
        return repaymentDt;
    }

    public CollectionOrderListVo setRepaymentDt(String repaymentDt) {
        this.repaymentDt = repaymentDt;
        return this;
    }

    public String getExtend() {
        return extend;
    }

    public CollectionOrderListVo setExtend(String extend) {
        this.extend = extend;
        return this;
    }

    public Integer getOverdueDuration() {
        return overdueDuration;
    }

    public CollectionOrderListVo setOverdueDuration(Integer overdueDuration) {
        this.overdueDuration = overdueDuration;
        return this;
    }

    public String getChannelName() {
        return channelName;
    }

    public CollectionOrderListVo setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }
}
