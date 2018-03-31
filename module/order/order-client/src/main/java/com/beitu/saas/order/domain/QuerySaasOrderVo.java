package com.beitu.saas.order.domain;

import java.util.Date;
import java.util.List;

/**
 * @author linanjun
 * @create 2018/3/27 下午5:14
 * @description
 */
public class QuerySaasOrderVo {
    /**
     * 借款人CODE
     */
    private List<String> borrowerCodeList;
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
    private List<Integer> orderStatusList;
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
    /**
     * 账单应还日
     */
    private Date repaymentDt;

    /**
     * 账单应还 查询开始日
     */
    private Date repaymentBeginDt;

    /**
     * 账单应还 查询结束日
     */
    private Date repaymentEndDt;

    public List<String> getBorrowerCodeList() {
        return borrowerCodeList;
    }

    public void setBorrowerCodeList(List<String> borrowerCodeList) {
        this.borrowerCodeList = borrowerCodeList;
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

    public List<Integer> getOrderStatusList() {
        return orderStatusList;
    }

    public void setOrderStatusList(List<Integer> orderStatusList) {
        this.orderStatusList = orderStatusList;
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

    public Date getRepaymentDt() {
        return repaymentDt;
    }

    public void setRepaymentDt(Date repaymentDt) {
        this.repaymentDt = repaymentDt;
    }

    public Date getRepaymentBeginDt() {
        return repaymentBeginDt;
    }

    public void setRepaymentBeginDt(Date repaymentBeginDt) {
        this.repaymentBeginDt = repaymentBeginDt;
    }

    public Date getRepaymentEndDt() {
        return repaymentEndDt;
    }

    public void setRepaymentEndDt(Date repaymentEndDt) {
        this.repaymentEndDt = repaymentEndDt;
    }
}
