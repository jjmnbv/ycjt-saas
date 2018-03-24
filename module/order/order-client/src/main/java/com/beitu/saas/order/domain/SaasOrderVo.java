package com.beitu.saas.order.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: jungle
 * Date: 2018-03-24
 * Time: 14:59:56.215
 */
public class SaasOrderVo implements ResponseData, Serializable {

    private Long saasOrderId;

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
     * 借款年利率
     */
    private BigDecimal totalInterestRatio;
    /**
     * 借款总利息
     */
    private BigDecimal totalInterestFee;
    /**
     * 逾期利率
     */
    private BigDecimal lateFeeRatio;
    /**
     * 借款意图
     */
    private String borrowPurpose;
    /**
     * 账单应还日
     */
    private Date repaymentDt;
    /**
     * 订单申请时间
     */
    private Date createdDt;
    /**
     * 借款协议URL地址
     */
    private String termUrl;
    /**
     * 是否是展期订单
     */
    private Boolean isExtend;
    /**
     * 展期关联订单CODE
     */
    private String relationOrderCode;
    /**
     * 订单状态
     */
    private Integer orderStatus;
    /**
     * 初审员
     */
    private String primaryReviewerCode;
    /**
     * 复审员
     */
    private String finalReviewerCode;
    /**
     * 放款人
     */
    private String loanLenderCode;
    /**
     * 备注
     */
    private String remark;
    /**
     *
     */
    private Long version;

    public Long getSaasOrderId() {
        return saasOrderId;
    }

    public void setSaasOrderId(Long saasOrderId) {
        this.saasOrderId = saasOrderId;
    }


    public String getOrderNumb() {
        return this.orderNumb;
    }

    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
    }

    public String getMerchantCode() {
        return this.merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getChannelCode() {
        return this.channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getBorrowerCode() {
        return this.borrowerCode;
    }

    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }

    public BigDecimal getRealCapital() {
        return this.realCapital;
    }

    public void setRealCapital(BigDecimal realCapital) {
        this.realCapital = realCapital;
    }

    public BigDecimal getTotalInterestRatio() {
        return this.totalInterestRatio;
    }

    public void setTotalInterestRatio(BigDecimal totalInterestRatio) {
        this.totalInterestRatio = totalInterestRatio;
    }

    public BigDecimal getTotalInterestFee() {
        return this.totalInterestFee;
    }

    public void setTotalInterestFee(BigDecimal totalInterestFee) {
        this.totalInterestFee = totalInterestFee;
    }

    public BigDecimal getLateFeeRatio() {
        return this.lateFeeRatio;
    }

    public void setLateFeeRatio(BigDecimal lateFeeRatio) {
        this.lateFeeRatio = lateFeeRatio;
    }

    public String getBorrowPurpose() {
        return this.borrowPurpose;
    }

    public void setBorrowPurpose(String borrowPurpose) {
        this.borrowPurpose = borrowPurpose;
    }

    public Date getRepaymentDt() {
        return this.repaymentDt;
    }

    public void setRepaymentDt(Date repaymentDt) {
        this.repaymentDt = repaymentDt;
    }

    public Date getCreatedDt() {
        return this.createdDt;
    }

    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    public String getTermUrl() {
        return this.termUrl;
    }

    public void setTermUrl(String termUrl) {
        this.termUrl = termUrl;
    }

    public Boolean getIsExtend() {
        return this.isExtend;
    }

    public void setIsExtend(Boolean isExtend) {
        this.isExtend = isExtend;
    }

    public String getRelationOrderCode() {
        return this.relationOrderCode;
    }

    public void setRelationOrderCode(String relationOrderCode) {
        this.relationOrderCode = relationOrderCode;
    }

    public Integer getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPrimaryReviewerCode() {
        return this.primaryReviewerCode;
    }

    public void setPrimaryReviewerCode(String primaryReviewerCode) {
        this.primaryReviewerCode = primaryReviewerCode;
    }

    public String getFinalReviewerCode() {
        return this.finalReviewerCode;
    }

    public void setFinalReviewerCode(String finalReviewerCode) {
        this.finalReviewerCode = finalReviewerCode;
    }

    public String getLoanLenderCode() {
        return this.loanLenderCode;
    }

    public void setLoanLenderCode(String loanLenderCode) {
        this.loanLenderCode = loanLenderCode;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
