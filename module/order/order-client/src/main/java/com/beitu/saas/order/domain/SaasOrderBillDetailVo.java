package com.beitu.saas.order.domain;

import com.beitu.saas.order.entity.SaasOrderBillDetail;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: jungle
 * Date: 2018-03-25
 * Time: 21:55:45.872
 */
public class SaasOrderBillDetailVo implements ResponseData, Serializable {

    private Long saasOrderBillDetailId;

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

    public Long getSaasOrderBillDetailId() {
        return saasOrderBillDetailId;
    }

    public void setSaasOrderBillDetailId(Long saasOrderBillDetailId) {
        this.saasOrderBillDetailId = saasOrderBillDetailId;
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

    public BigDecimal getNeedPayInterest() {
        return this.needPayInterest;
    }

    public void setNeedPayInterest(BigDecimal needPayInterest) {
        this.needPayInterest = needPayInterest;
    }

    public BigDecimal getTotalInterestRatio() {
        return this.totalInterestRatio;
    }

    public void setTotalInterestRatio(BigDecimal totalInterestRatio) {
        this.totalInterestRatio = totalInterestRatio;
    }

    public BigDecimal getInterest() {
        return this.interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public Date getCreatedDt() {
        return this.createdDt;
    }

    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    public Date getRepaymentDt() {
        return this.repaymentDt;
    }

    public void setRepaymentDt(Date repaymentDt) {
        this.repaymentDt = repaymentDt;
    }

    public BigDecimal getLateInterestRatio() {
        return this.lateInterestRatio;
    }

    public void setLateInterestRatio(BigDecimal lateInterestRatio) {
        this.lateInterestRatio = lateInterestRatio;
    }

    public BigDecimal getLateInterest() {
        return this.lateInterest;
    }

    public void setLateInterest(BigDecimal lateInterest) {
        this.lateInterest = lateInterest;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getRelationOrderBillDetailId() {
        return this.relationOrderBillDetailId;
    }

    public void setRelationOrderBillDetailId(Long relationOrderBillDetailId) {
        this.relationOrderBillDetailId = relationOrderBillDetailId;
    }

    public Boolean getVisible() {
        return this.visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getDestroy() {
        return this.destroy;
    }

    public void setDestroy(Boolean destroy) {
        this.destroy = destroy;
    }

    public Date getActualDestroyDate() {
        return this.actualDestroyDate;
    }

    public void setActualDestroyDate(Date actualDestroyDate) {
        this.actualDestroyDate = actualDestroyDate;
    }

    public Date getActualDestroyDt() {
        return this.actualDestroyDt;
    }

    public void setActualDestroyDt(Date actualDestroyDt) {
        this.actualDestroyDt = actualDestroyDt;
    }

    public static SaasOrderBillDetailVo convertEntityToVO(SaasOrderBillDetail saasOrderBillDetail) {
        if (saasOrderBillDetail == null) {
            return null;
        }
        SaasOrderBillDetailVo saasOrderBillDetailVo = new SaasOrderBillDetailVo();
        BeanUtils.copyProperties(saasOrderBillDetail, saasOrderBillDetailVo);
        saasOrderBillDetailVo.setSaasOrderBillDetailId(saasOrderBillDetail.getId());
        return saasOrderBillDetailVo;
    }

    public static SaasOrderBillDetail convertVOToEntity(SaasOrderBillDetailVo saasOrderBillDetailVo) {
        if (saasOrderBillDetailVo == null) {
            return null;
        }
        SaasOrderBillDetail saasOrderBillDetail = new SaasOrderBillDetail();
        BeanUtils.copyProperties(saasOrderBillDetailVo, saasOrderBillDetail);
        saasOrderBillDetail.setId(saasOrderBillDetailVo.getSaasOrderBillDetailId());
        return saasOrderBillDetail;
    }

}
