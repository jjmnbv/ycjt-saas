package com.beitu.saas.order.entity;

import com.fqgj.common.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User: jungle
 * Date: 2018-03-27
 * Time: 14:22:21.109
 * TableDesc:SAAS账单表
 */
public class SaasOrderBillDetail extends BaseEntity {
    /**
     * 订单号
     */
    private String orderNumb;
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
     * 未结算利息
     */
    private BigDecimal needPayInterest;
    /**
     * 借款年利率
     */
    private BigDecimal totalInterestRatio;
    /**
     * 利息
     */
    private BigDecimal interest;
    /**
     * 账单生成日
     */
    private Date createdDt;
    /**
     * 账单应还日
     */
    private Date repaymentDt;
    /**
     * 逾期利率
     */
    private BigDecimal lateInterestRatio;
    /**
     * 逾期利息
     */
    private BigDecimal lateInterest;
    /**
     * 应还金额
     */
    private BigDecimal amount;
    /**
     * 展期关联账单ID
     */
    private Long relationOrderBillDetailId;
    /**
     * 是否可见
     */
    private Boolean visible;
    /**
     * 是否已核销
     */
    private Boolean destroy;
    /**
     * 核销时间
     */
    private Date actualDestroyDate;
    /**
     * 核销日期
     */
    private Date actualDestroyDt;


    public String getOrderNumb() {
        return this.orderNumb;
    }

    public SaasOrderBillDetail setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
        return this;
    }

    public String getMerchantCode() {
        return this.merchantCode;
    }

    public SaasOrderBillDetail setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
        return this;
    }

    public String getChannelCode() {
        return this.channelCode;
    }

    public SaasOrderBillDetail setChannelCode(String channelCode) {
        this.channelCode = channelCode;
        return this;
    }

    public String getBorrowerCode() {
        return this.borrowerCode;
    }

    public SaasOrderBillDetail setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
        return this;
    }

    public BigDecimal getRealCapital() {
        return this.realCapital;
    }

    public SaasOrderBillDetail setRealCapital(BigDecimal realCapital) {
        this.realCapital = realCapital;
        return this;
    }

    public BigDecimal getNeedPayInterest() {
        return this.needPayInterest;
    }

    public SaasOrderBillDetail setNeedPayInterest(BigDecimal needPayInterest) {
        this.needPayInterest = needPayInterest;
        return this;
    }

    public BigDecimal getTotalInterestRatio() {
        return this.totalInterestRatio;
    }

    public SaasOrderBillDetail setTotalInterestRatio(BigDecimal totalInterestRatio) {
        this.totalInterestRatio = totalInterestRatio;
        return this;
    }

    public BigDecimal getInterest() {
        return this.interest;
    }

    public SaasOrderBillDetail setInterest(BigDecimal interest) {
        this.interest = interest;
        return this;
    }

    public Date getCreatedDt() {
        return this.createdDt;
    }

    public SaasOrderBillDetail setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
        return this;
    }

    public Date getRepaymentDt() {
        return this.repaymentDt;
    }

    public SaasOrderBillDetail setRepaymentDt(Date repaymentDt) {
        this.repaymentDt = repaymentDt;
        return this;
    }

    public BigDecimal getLateInterestRatio() {
        return this.lateInterestRatio;
    }

    public SaasOrderBillDetail setLateInterestRatio(BigDecimal lateInterestRatio) {
        this.lateInterestRatio = lateInterestRatio;
        return this;
    }

    public BigDecimal getLateInterest() {
        return this.lateInterest;
    }

    public SaasOrderBillDetail setLateInterest(BigDecimal lateInterest) {
        this.lateInterest = lateInterest;
        return this;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public SaasOrderBillDetail setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Long getRelationOrderBillDetailId() {
        return this.relationOrderBillDetailId;
    }

    public SaasOrderBillDetail setRelationOrderBillDetailId(Long relationOrderBillDetailId) {
        this.relationOrderBillDetailId = relationOrderBillDetailId;
        return this;
    }

    public Boolean getVisible() {
        return this.visible;
    }

    public SaasOrderBillDetail setVisible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public Boolean getDestroy() {
        return this.destroy;
    }

    public SaasOrderBillDetail setDestroy(Boolean destroy) {
        this.destroy = destroy;
        return this;
    }

    public Date getActualDestroyDate() {
        return this.actualDestroyDate;
    }

    public SaasOrderBillDetail setActualDestroyDate(Date actualDestroyDate) {
        this.actualDestroyDate = actualDestroyDate;
        return this;
    }

    public Date getActualDestroyDt() {
        return this.actualDestroyDt;
    }

    public SaasOrderBillDetail setActualDestroyDt(Date actualDestroyDt) {
        this.actualDestroyDt = actualDestroyDt;
        return this;
    }
}
