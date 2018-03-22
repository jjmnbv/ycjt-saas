package com.beitu.saas.risk.domain.platform.zmxy.feedback;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @Author 柳朋朋
 * @Create 2016-12-23 11:38
 */
public class FeedBackDetail implements Serializable {
    @JSONField(name = "user_credentials_type")
    private String userCredentialsType = "0";

    @JSONField(name = "user_credentials_no")
    private String userCredentialsNo = "";

    @JSONField(name = "user_name")
    private String userName = "";

    @JSONField(name = "order_no")
    private String orderNo = "";

    @JSONField(name = "biz_type")
    private String bizType = "1";

    @JSONField(name = "pay_month")
    private String payMonth = "";

    @JSONField(name = "gmt_ovd_date")
    private String gmtOvdDate = "";

    @JSONField(name = "order_status")
    private String orderStatus = "";

    @JSONField(name = "create_amt")
    private String createAmt = "";

    @JSONField(name = "overdue_days")
    private String overdueDays = "";

    @JSONField(name = "overdue_amt")
    private String overdueAmt = "";

    @JSONField(name = "gmt_pay")
    private String gmtPay = "";

    @JSONField(name = "memo")
    private String memo = "";

    public String getUserCredentialsType() {
        return userCredentialsType;
    }

    public FeedBackDetail setUserCredentialsType(String userCredentialsType) {
        this.userCredentialsType = userCredentialsType;
        return this;
    }

    public String getUserCredentialsNo() {
        return userCredentialsNo;
    }

    public FeedBackDetail setUserCredentialsNo(String userCredentialsNo) {
        this.userCredentialsNo = userCredentialsNo;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public FeedBackDetail setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public FeedBackDetail setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public String getBizType() {
        return bizType;
    }

    public FeedBackDetail setBizType(String bizType) {
        this.bizType = bizType;
        return this;
    }

    public String getPayMonth() {
        return payMonth;
    }

    public FeedBackDetail setPayMonth(String payMonth) {
        this.payMonth = payMonth;
        return this;
    }

    public String getGmtOvdDate() {
        return gmtOvdDate;
    }

    public FeedBackDetail setGmtOvdDate(String gmtOvdDate) {
        this.gmtOvdDate = gmtOvdDate;
        return this;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public FeedBackDetail setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public String getCreateAmt() {
        return createAmt;
    }

    public FeedBackDetail setCreateAmt(String createAmt) {
        this.createAmt = createAmt;
        return this;
    }

    public String getOverdueDays() {
        return overdueDays;
    }

    public FeedBackDetail setOverdueDays(String overdueDays) {
        this.overdueDays = overdueDays;
        return this;
    }

    public String getOverdueAmt() {
        return overdueAmt;
    }

    public FeedBackDetail setOverdueAmt(String overdueAmt) {
        this.overdueAmt = overdueAmt;
        return this;
    }

    public String getGmtPay() {
        return gmtPay;
    }

    public FeedBackDetail setGmtPay(String gmtPay) {
        this.gmtPay = gmtPay;
        return this;
    }

    public String getMemo() {
        return memo;
    }

    public FeedBackDetail setMemo(String memo) {
        this.memo = memo;
        return this;
    }
}
