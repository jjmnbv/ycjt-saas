package com.beitu.saas.order.domain;

import com.beitu.saas.order.entity.SaasOrderApplication;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: jungle
 * Date: 2018-03-25
 * Time: 21:55:45.854
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
     * 借款协议URL地址
     */
    private String termUrl;

    private Date gmtCreate;

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

    public String getTermUrl() {
        return this.termUrl;
    }

    public void setTermUrl(String termUrl) {
        this.termUrl = termUrl;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
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
