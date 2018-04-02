package com.beitu.saas.rest.controller.contract.response;

import com.fqgj.common.api.ResponseData;

import java.math.BigDecimal;

/**
 * @author linanjun
 * @create 2018/4/2 下午5:12
 * @description
 */
public class OrderExtendContractInfoResponse implements ResponseData {
    /**
     * 主订单
     */
    private String firstOrderNo;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 出借人合同姓名
     */
    private String lenderUserName;
    /**
     * 出借人合同号
     */
    private String lenderIdentityNo;
    /**
     * 是否显示 出借人印章
     */
    private Boolean stamp;
    /**
     * 出借人印章URL
     */
    private String stampUrl;

    /**
     * 借款人姓名
     */
    private String borrowUserName;
    /**
     * 借款人身份证号
     */
    private String borrowIdentityNo;

    /**
     * 借款金额
     */
    private BigDecimal realCapital;
    /**
     * 订单生成日期
     */
    private String createdDt;
    /**
     * 账单应还日
     */
    private String repaymentDt;
    /**
     * 借款年利率
     */
    private String totalInterestRatio;
    /**
     * 大写金额
     */
    private String realCapitalCN;

    /**
     * 落款时间
     */
    private String inscribeDate;

    public String getFirstOrderNo() {
        return firstOrderNo;
    }

    public void setFirstOrderNo(String firstOrderNo) {
        this.firstOrderNo = firstOrderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getLenderUserName() {
        return lenderUserName;
    }

    public void setLenderUserName(String lenderUserName) {
        this.lenderUserName = lenderUserName;
    }

    public String getLenderIdentityNo() {
        return lenderIdentityNo;
    }

    public void setLenderIdentityNo(String lenderIdentityNo) {
        this.lenderIdentityNo = lenderIdentityNo;
    }

    public Boolean getStamp() {
        return stamp;
    }

    public void setStamp(Boolean stamp) {
        this.stamp = stamp;
    }

    public String getStampUrl() {
        return stampUrl;
    }

    public void setStampUrl(String stampUrl) {
        this.stampUrl = stampUrl;
    }

    public String getBorrowUserName() {
        return borrowUserName;
    }

    public void setBorrowUserName(String borrowUserName) {
        this.borrowUserName = borrowUserName;
    }

    public String getBorrowIdentityNo() {
        return borrowIdentityNo;
    }

    public void setBorrowIdentityNo(String borrowIdentityNo) {
        this.borrowIdentityNo = borrowIdentityNo;
    }

    public BigDecimal getRealCapital() {
        return realCapital;
    }

    public void setRealCapital(BigDecimal realCapital) {
        this.realCapital = realCapital;
    }

    public String getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(String createdDt) {
        this.createdDt = createdDt;
    }

    public String getRepaymentDt() {
        return repaymentDt;
    }

    public void setRepaymentDt(String repaymentDt) {
        this.repaymentDt = repaymentDt;
    }

    public String getTotalInterestRatio() {
        return totalInterestRatio;
    }

    public void setTotalInterestRatio(String totalInterestRatio) {
        this.totalInterestRatio = totalInterestRatio;
    }

    public String getRealCapitalCN() {
        return realCapitalCN;
    }

    public void setRealCapitalCN(String realCapitalCN) {
        this.realCapitalCN = realCapitalCN;
    }

    public String getInscribeDate() {
        return inscribeDate;
    }

    public void setInscribeDate(String inscribeDate) {
        this.inscribeDate = inscribeDate;
    }

}
