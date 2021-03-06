package com.beitu.saas.order.entity;

import com.fqgj.common.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User: jungle
 * Date: 2018-04-18
 * Time: 12:11:44.397
 * TableDesc:SAAS订单表
 */
public class SaasOrder extends BaseEntity {
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
    private BigDecimal lateInterestRatio;
    /**
     * 借款意图
     */
    private String borrowPurpose;
    /**
     * 账单应还日
     */
    private Date repaymentDt;
    /**
     * 订单计息时间
     */
    private Date createdDt;
    /**
     * 订单过期时间
     */
    private Date expireDate;
    /**
     * 借款协议URL地址
     */
    private String termUrl;
    /**
     * 借款人是否授权签署借款合同
     */
    private Boolean borrowerAuthorizedSignLoan;
    /**
     * 展期关联订单ID
     */
    private Long relationOrderId;
    /**
     * 订单状态
     */
    private Integer orderStatus;
    /**
     * 初审员
     */
    private String preliminaryReviewerCode;
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
     * 手续费
     */
    private BigDecimal serviceFee;
    /**
     *
     */
    private Long version;


    public String getOrderNumb() {
        return this.orderNumb;
    }

    public SaasOrder setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
        return this;
    }

    public String getMerchantCode() {
        return this.merchantCode;
    }

    public SaasOrder setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
        return this;
    }

    public String getChannelCode() {
        return this.channelCode;
    }

    public SaasOrder setChannelCode(String channelCode) {
        this.channelCode = channelCode;
        return this;
    }

    public String getBorrowerCode() {
        return this.borrowerCode;
    }

    public SaasOrder setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
        return this;
    }

    public BigDecimal getRealCapital() {
        return this.realCapital;
    }

    public SaasOrder setRealCapital(BigDecimal realCapital) {
        this.realCapital = realCapital;
        return this;
    }

    public BigDecimal getTotalInterestRatio() {
        return this.totalInterestRatio;
    }

    public SaasOrder setTotalInterestRatio(BigDecimal totalInterestRatio) {
        this.totalInterestRatio = totalInterestRatio;
        return this;
    }

    public BigDecimal getTotalInterestFee() {
        return this.totalInterestFee;
    }

    public SaasOrder setTotalInterestFee(BigDecimal totalInterestFee) {
        this.totalInterestFee = totalInterestFee;
        return this;
    }

    public BigDecimal getLateInterestRatio() {
        return this.lateInterestRatio;
    }

    public SaasOrder setLateInterestRatio(BigDecimal lateInterestRatio) {
        this.lateInterestRatio = lateInterestRatio;
        return this;
    }

    public String getBorrowPurpose() {
        return this.borrowPurpose;
    }

    public SaasOrder setBorrowPurpose(String borrowPurpose) {
        this.borrowPurpose = borrowPurpose;
        return this;
    }

    public Date getRepaymentDt() {
        return this.repaymentDt;
    }

    public SaasOrder setRepaymentDt(Date repaymentDt) {
        this.repaymentDt = repaymentDt;
        return this;
    }

    public Date getCreatedDt() {
        return this.createdDt;
    }

    public SaasOrder setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
        return this;
    }

    public Date getExpireDate() {
        return this.expireDate;
    }

    public SaasOrder setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
        return this;
    }

    public String getTermUrl() {
        return this.termUrl;
    }

    public SaasOrder setTermUrl(String termUrl) {
        this.termUrl = termUrl;
        return this;
    }

    public Boolean getBorrowerAuthorizedSignLoan() {
        return this.borrowerAuthorizedSignLoan;
    }

    public SaasOrder setBorrowerAuthorizedSignLoan(Boolean borrowerAuthorizedSignLoan) {
        this.borrowerAuthorizedSignLoan = borrowerAuthorizedSignLoan;
        return this;
    }

    public Long getRelationOrderId() {
        return this.relationOrderId;
    }

    public SaasOrder setRelationOrderId(Long relationOrderId) {
        this.relationOrderId = relationOrderId;
        return this;
    }

    public Integer getOrderStatus() {
        return this.orderStatus;
    }

    public SaasOrder setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public String getPreliminaryReviewerCode() {
        return this.preliminaryReviewerCode;
    }

    public SaasOrder setPreliminaryReviewerCode(String preliminaryReviewerCode) {
        this.preliminaryReviewerCode = preliminaryReviewerCode;
        return this;
    }

    public String getFinalReviewerCode() {
        return this.finalReviewerCode;
    }

    public SaasOrder setFinalReviewerCode(String finalReviewerCode) {
        this.finalReviewerCode = finalReviewerCode;
        return this;
    }

    public String getLoanLenderCode() {
        return this.loanLenderCode;
    }

    public SaasOrder setLoanLenderCode(String loanLenderCode) {
        this.loanLenderCode = loanLenderCode;
        return this;
    }

    public String getRemark() {
        return this.remark;
    }

    public SaasOrder setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public BigDecimal getServiceFee() {
        return this.serviceFee;
    }

    public SaasOrder setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
        return this;
    }

    public Long getVersion() {
        return this.version;
    }

    public SaasOrder setVersion(Long version) {
        this.version = version;
        return this;
    }
}
