package com.beitu.saas.order.entity;

import com.fqgj.common.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User: jungle
 * Date: 2018-03-26
 * Time: 17:00:42.342
 * TableDesc:SAAS订单申请表
 */
public class SaasOrderApplication extends BaseEntity {
    /**
     * 机构CODE
     */
    private String merchantCode;
    /**
     * 渠道CODE
     */
    private String channelCode;
    /**
     * 借款人CODE
     */
    private String borrowerCode;
    /**
     * 借款金额
     */
    private BigDecimal realCapital;
    /**
     * 借款年利率
     */
    private BigDecimal totalInterestRatio;
    /**
     * 逾期利率
     */
    private BigDecimal lateInterestRatio;
    /**
     * 借款意图
     */
    private String borrowPurpose;
    /**
     * 账单应还日
     */
    private Date repaymentDt;


    public String getMerchantCode() {
        return this.merchantCode;
    }

    public SaasOrderApplication setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
        return this;
    }

    public String getChannelCode() {
        return this.channelCode;
    }

    public SaasOrderApplication setChannelCode(String channelCode) {
        this.channelCode = channelCode;
        return this;
    }

    public String getBorrowerCode() {
        return this.borrowerCode;
    }

    public SaasOrderApplication setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
        return this;
    }

    public BigDecimal getRealCapital() {
        return this.realCapital;
    }

    public SaasOrderApplication setRealCapital(BigDecimal realCapital) {
        this.realCapital = realCapital;
        return this;
    }

    public BigDecimal getTotalInterestRatio() {
        return this.totalInterestRatio;
    }

    public SaasOrderApplication setTotalInterestRatio(BigDecimal totalInterestRatio) {
        this.totalInterestRatio = totalInterestRatio;
        return this;
    }

    public BigDecimal getLateInterestRatio() {
        return this.lateInterestRatio;
    }

    public SaasOrderApplication setLateInterestRatio(BigDecimal lateInterestRatio) {
        this.lateInterestRatio = lateInterestRatio;
        return this;
    }

    public String getBorrowPurpose() {
        return this.borrowPurpose;
    }

    public SaasOrderApplication setBorrowPurpose(String borrowPurpose) {
        this.borrowPurpose = borrowPurpose;
        return this;
    }

    public Date getRepaymentDt() {
        return this.repaymentDt;
    }

    public SaasOrderApplication setRepaymentDt(Date repaymentDt) {
        this.repaymentDt = repaymentDt;
        return this;
    }
}
