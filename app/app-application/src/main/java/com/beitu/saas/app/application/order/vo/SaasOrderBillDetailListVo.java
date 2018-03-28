package com.beitu.saas.app.application.order.vo;

import com.fqgj.common.api.ResponseData;

/**
 * @author linanjun
 * @create 2018/3/28 上午11:38
 * @description
 */
public class SaasOrderBillDetailListVo implements ResponseData {

    private String orderNumb;

    private String borrowerName;

    private String borrowerMobile;

    private String capital;

    private String amount;

    private String createdDate;

    private String repaymentDate;

    private Boolean extend;

    private Integer overdueDuration;

    private String loanLendRemark;

    private String channelName;

    private String orderStatus;

    public String getOrderNumb() {
        return orderNumb;
    }

    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBorrowerMobile() {
        return borrowerMobile;
    }

    public void setBorrowerMobile(String borrowerMobile) {
        this.borrowerMobile = borrowerMobile;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(String repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public Boolean getExtend() {
        return extend;
    }

    public void setExtend(Boolean extend) {
        this.extend = extend;
    }

    public Integer getOverdueDuration() {
        return overdueDuration;
    }

    public void setOverdueDuration(Integer overdueDuration) {
        this.overdueDuration = overdueDuration;
    }

    public String getLoanLendRemark() {
        return loanLendRemark;
    }

    public void setLoanLendRemark(String loanLendRemark) {
        this.loanLendRemark = loanLendRemark;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
