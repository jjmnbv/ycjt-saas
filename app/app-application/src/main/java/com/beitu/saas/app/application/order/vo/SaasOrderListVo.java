package com.beitu.saas.app.application.order.vo;

import com.fqgj.common.api.ResponseData;

/**
 * @author linanjun
 * @create 2018/3/27 下午3:04
 * @description
 */
public class SaasOrderListVo implements ResponseData {

    private String orderNumb;

    private String borrowerName;

    private String borrowerMobile;

    private String borrowerGender;

    private Integer borrowerAge;

    private String capital;

    private String borrowingDuration;

    private String applyDate;

    private String channelName;

    private String orderStatus;

    private Integer zmCreditScore;

    private String remark;

    private String preliminaryReviewer;

    private String finalReviewer;

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

    public String getBorrowerGender() {
        return borrowerGender;
    }

    public void setBorrowerGender(String borrowerGender) {
        this.borrowerGender = borrowerGender;
    }

    public Integer getBorrowerAge() {
        return borrowerAge;
    }

    public void setBorrowerAge(Integer borrowerAge) {
        this.borrowerAge = borrowerAge;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getBorrowingDuration() {
        return borrowingDuration;
    }

    public void setBorrowingDuration(String borrowingDuration) {
        this.borrowingDuration = borrowingDuration;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
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

    public Integer getZmCreditScore() {
        return zmCreditScore;
    }

    public void setZmCreditScore(Integer zmCreditScore) {
        this.zmCreditScore = zmCreditScore;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPreliminaryReviewer() {
        return preliminaryReviewer;
    }

    public void setPreliminaryReviewer(String preliminaryReviewer) {
        this.preliminaryReviewer = preliminaryReviewer;
    }

    public String getFinalReviewer() {
        return finalReviewer;
    }

    public void setFinalReviewer(String finalReviewer) {
        this.finalReviewer = finalReviewer;
    }
}
