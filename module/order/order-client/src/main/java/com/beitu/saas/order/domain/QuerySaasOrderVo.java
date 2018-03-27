package com.beitu.saas.order.domain;

import java.util.Date;

/**
 * @author linanjun
 * @create 2018/3/27 下午5:14
 * @description
 */
public class QuerySaasOrderVo {
    /**
     * 借款人CODE
     */
    private String[] borrowerCodeArray;
    /**
     * 机构CODE
     */
    private String merchantCode;
    /**
     * 渠道CODE
     */
    private String channelCode;
    /**
     * 订单状态
     */
    private Integer[] orderStatusArray;
    /**
     * 申请查询开始时间
     */
    private Date createdBeginDt;
    /**
     * 申请查询结束时间
     */
    private Date createdEndDt;
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

    public String[] getBorrowerCodeArray() {
        return borrowerCodeArray;
    }

    public void setBorrowerCodeArray(String[] borrowerCodeArray) {
        this.borrowerCodeArray = borrowerCodeArray;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public Integer[] getOrderStatusArray() {
        return orderStatusArray;
    }

    public void setOrderStatusArray(Integer[] orderStatusArray) {
        this.orderStatusArray = orderStatusArray;
    }

    public Date getCreatedBeginDt() {
        return createdBeginDt;
    }

    public void setCreatedBeginDt(Date createdBeginDt) {
        this.createdBeginDt = createdBeginDt;
    }

    public Date getCreatedEndDt() {
        return createdEndDt;
    }

    public void setCreatedEndDt(Date createdEndDt) {
        this.createdEndDt = createdEndDt;
    }

    public String getPreliminaryReviewerCode() {
        return preliminaryReviewerCode;
    }

    public void setPreliminaryReviewerCode(String preliminaryReviewerCode) {
        this.preliminaryReviewerCode = preliminaryReviewerCode;
    }

    public String getFinalReviewerCode() {
        return finalReviewerCode;
    }

    public void setFinalReviewerCode(String finalReviewerCode) {
        this.finalReviewerCode = finalReviewerCode;
    }

    public String getLoanLenderCode() {
        return loanLenderCode;
    }

    public void setLoanLenderCode(String loanLenderCode) {
        this.loanLenderCode = loanLenderCode;
    }
}
