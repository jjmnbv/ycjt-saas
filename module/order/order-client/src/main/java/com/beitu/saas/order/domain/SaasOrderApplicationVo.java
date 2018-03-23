package com.beitu.saas.order.domain;

import com.beitu.saas.order.entity.SaasOrderApplication;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: jungle
 * Date: 2018-03-23
 * Time: 15:18:54.768
 */
public class SaasOrderApplicationVo implements ResponseData, Serializable {

    private Long saasOrderApplicationId;

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
    private Date repaymentDate;

    public Long getSaasOrderApplicationId() {
        return saasOrderApplicationId;
    }

    public void setSaasOrderApplicationId(Long saasOrderApplicationId) {
        this.saasOrderApplicationId = saasOrderApplicationId;
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

    public Date getRepaymentDate() {
        return this.repaymentDate;
    }

    public void setRepaymentDate(Date repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public static SaasOrderApplicationVo convertEntityToVO(SaasOrderApplication saasOrderApplication) {
        if (saasOrderApplication == null) {
            return null;
        }
        SaasOrderApplicationVo saasOrderApplicationVo = new SaasOrderApplicationVo();
        BeanUtils.copyProperties(saasOrderApplication, saasOrderApplicationVo);
        saasOrderApplicationVo.setSaasOrderApplicationId(saasOrderApplication.getId());
        return saasOrderApplicationVo;
    }

    public static SaasOrderApplication convertVOToEntity(SaasOrderApplicationVo saasOrderApplicationVo) {
        if (saasOrderApplicationVo == null) {
            return null;
        }
        SaasOrderApplication saasOrderApplication = new SaasOrderApplication();
        BeanUtils.copyProperties(saasOrderApplicationVo, saasOrderApplication);
        saasOrderApplication.setId(saasOrderApplicationVo.getSaasOrderApplicationId());
        return saasOrderApplication;
    }

}
