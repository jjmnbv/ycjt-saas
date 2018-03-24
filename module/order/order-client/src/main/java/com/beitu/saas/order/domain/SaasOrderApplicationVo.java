package com.beitu.saas.order.domain;

import com.beitu.saas.order.entity.SaasOrderApplication;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: jungle
 * Date: 2018-03-24
 * Time: 14:59:56.228
 */
public class SaasOrderApplicationVo implements ResponseData, Serializable {

    private Long saasOrderApplicationId;

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
