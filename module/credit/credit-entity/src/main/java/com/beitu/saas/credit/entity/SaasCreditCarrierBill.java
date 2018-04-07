package com.beitu.saas.credit.entity;

import com.fqgj.common.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.646
 * TableDesc:运营商报告账单详情表
 */
public class SaasCreditCarrierBill extends BaseEntity {
    /**
     * 运营商报告查询表ID
     */
    private Long recordId;
    /**
     * 账单时间
     */
    private Date billDate;
    /**
     * 月基本费
     */
    private BigDecimal baseFee;
    /**
     * 月消费
     */
    private BigDecimal totalFee;
    /**
     * 呼出次数
     */
    private Integer callingTime;
    /**
     * 呼入次数
     */
    private Integer calledTime;
    /**
     * 呼出时长（分）
     */
    private Integer callingDuration;
    /**
     * 呼入时长（分）
     */
    private Integer calledDuration;


    public Long getRecordId() {
        return this.recordId;
    }

    public SaasCreditCarrierBill setRecordId(Long recordId) {
        this.recordId = recordId;
        return this;
    }

    public Date getBillDate() {
        return this.billDate;
    }

    public SaasCreditCarrierBill setBillDate(Date billDate) {
        this.billDate = billDate;
        return this;
    }

    public BigDecimal getBaseFee() {
        return this.baseFee;
    }

    public SaasCreditCarrierBill setBaseFee(BigDecimal baseFee) {
        this.baseFee = baseFee;
        return this;
    }

    public BigDecimal getTotalFee() {
        return this.totalFee;
    }

    public SaasCreditCarrierBill setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
        return this;
    }

    public Integer getCallingTime() {
        return this.callingTime;
    }

    public SaasCreditCarrierBill setCallingTime(Integer callingTime) {
        this.callingTime = callingTime;
        return this;
    }

    public Integer getCalledTime() {
        return this.calledTime;
    }

    public SaasCreditCarrierBill setCalledTime(Integer calledTime) {
        this.calledTime = calledTime;
        return this;
    }

    public Integer getCallingDuration() {
        return this.callingDuration;
    }

    public SaasCreditCarrierBill setCallingDuration(Integer callingDuration) {
        this.callingDuration = callingDuration;
        return this;
    }

    public Integer getCalledDuration() {
        return this.calledDuration;
    }

    public SaasCreditCarrierBill setCalledDuration(Integer calledDuration) {
        this.calledDuration = calledDuration;
        return this;
    }
}
