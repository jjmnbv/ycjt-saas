package com.beitu.saas.order.domain;

import com.beitu.saas.order.entity.SaasOrder;
import com.beitu.saas.order.entity.SaasOrderApplication;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: jungle
 * Date: 2018-03-25
 * Time: 21:55:45.840
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

    private Date gmtCreate;

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

    public BigDecimal getLateInterestRatio() {
        return this.lateInterestRatio;
    }

    public void setLateInterestRatio(BigDecimal lateInterestRatio) {
        this.lateInterestRatio = lateInterestRatio;
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

    public Date getExpireDate() {
        return this.expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getTermUrl() {
        return this.termUrl;
    }

    public void setTermUrl(String termUrl) {
        this.termUrl = termUrl;
    }

    public Boolean getBorrowerAuthorizedSignLoan() {
        return this.borrowerAuthorizedSignLoan;
    }

    public void setBorrowerAuthorizedSignLoan(Boolean borrowerAuthorizedSignLoan) {
        this.borrowerAuthorizedSignLoan = borrowerAuthorizedSignLoan;
    }

    public Long getRelationOrderId() {
        return this.relationOrderId;
    }

    public void setRelationOrderId(Long relationOrderId) {
        this.relationOrderId = relationOrderId;
    }

    public Integer getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPreliminaryReviewerCode() {
        return this.preliminaryReviewerCode;
    }

    public void setPreliminaryReviewerCode(String preliminaryReviewerCode) {
        this.preliminaryReviewerCode = preliminaryReviewerCode;
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

    public BigDecimal getServiceFee() {
        return this.serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public static SaasOrderVo convertEntityToVO(SaasOrder saasOrder) {
        if (saasOrder == null) {
            return null;
        }
        SaasOrderVo saasOrderVo = new SaasOrderVo();
        BeanUtils.copyProperties(saasOrder, saasOrderVo);
        saasOrderVo.setSaasOrderId(saasOrder.getId());
        return saasOrderVo;
    }

    public static SaasOrder convertVOToEntity(SaasOrderVo saasOrderVo) {
        if (saasOrderVo == null) {
            return null;
        }
        SaasOrder saasOrder = new SaasOrder();
        BeanUtils.copyProperties(saasOrderVo, saasOrder);
        saasOrder.setId(saasOrderVo.getSaasOrderId());
        return saasOrder;
    }

}
