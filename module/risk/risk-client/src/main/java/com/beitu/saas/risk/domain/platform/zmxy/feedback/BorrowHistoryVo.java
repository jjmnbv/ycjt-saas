package com.beitu.saas.risk.domain.platform.zmxy.feedback;

import java.io.Serializable;

/**
 * @Author 柳朋朋
 * @Create 2016-12-23 11:29
 */
public class BorrowHistoryVo implements Serializable {
    //历史记录
    private Long userId;
    private Long borrowId;
    private String mobile;
    private String realName;
    private String identityNo;
    private String borrowNo;
    private Float capital;
    private Float realCapital;
    private Integer duration;
    private Integer historyBillStatus;
    private Integer borrowBillStatus;
    private String credatedDate;
    private String credatedDt;

    //逾期记录
    private Float paidAmount;
    private Float deductAmount;
    private Float serviceFee;
    private Float lateFee;
    private Integer overdueDays;
    private String repaymentDate;


    public Float getRealCapital() {
        return realCapital;
    }

    public BorrowHistoryVo setRealCapital(Float realCapital) {
        this.realCapital = realCapital;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public BorrowHistoryVo setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getRepaymentDate() {
        return repaymentDate;
    }

    public BorrowHistoryVo setRepaymentDate(String repaymentDate) {
        this.repaymentDate = repaymentDate;
        return this;
    }

    public Float getPaidAmount() {
        return paidAmount;
    }

    public BorrowHistoryVo setPaidAmount(Float paidAmount) {
        this.paidAmount = paidAmount;
        return this;
    }

    public Float getDeductAmount() {
        return deductAmount;
    }

    public BorrowHistoryVo setDeductAmount(Float deductAmount) {
        this.deductAmount = deductAmount;
        return this;
    }

    public Float getServiceFee() {
        return serviceFee;
    }

    public BorrowHistoryVo setServiceFee(Float serviceFee) {
        this.serviceFee = serviceFee;
        return this;
    }

    public Float getLateFee() {
        return lateFee;
    }

    public BorrowHistoryVo setLateFee(Float lateFee) {
        this.lateFee = lateFee;
        return this;
    }

    public Integer getOverdueDays() {
        return overdueDays;
    }

    public BorrowHistoryVo setOverdueDays(Integer overdueDays) {
        this.overdueDays = overdueDays;
        return this;
    }

    public String getCredatedDt() {
        return credatedDt;
    }

    public BorrowHistoryVo setCredatedDt(String credatedDt) {
        this.credatedDt = credatedDt;
        return this;
    }

    public Integer getBorrowBillStatus() {
        return borrowBillStatus;
    }

    public BorrowHistoryVo setBorrowBillStatus(Integer borrowBillStatus) {
        this.borrowBillStatus = borrowBillStatus;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public BorrowHistoryVo setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getBorrowId() {
        return borrowId;
    }

    public BorrowHistoryVo setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public BorrowHistoryVo setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public BorrowHistoryVo setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
        return this;
    }

    public String getBorrowNo() {
        return borrowNo;
    }

    public BorrowHistoryVo setBorrowNo(String borrowNo) {
        this.borrowNo = borrowNo;
        return this;
    }

    public Float getCapital() {
        return capital;
    }

    public BorrowHistoryVo setCapital(Float capital) {
        this.capital = capital;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public BorrowHistoryVo setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public Integer getHistoryBillStatus() {
        return historyBillStatus;
    }

    public BorrowHistoryVo setHistoryBillStatus(Integer historyBillStatus) {
        this.historyBillStatus = historyBillStatus;
        return this;
    }

    public String getCredatedDate() {
        return credatedDate;
    }

    public BorrowHistoryVo setCredatedDate(String credatedDate) {
        this.credatedDate = credatedDate;
        return this;
    }
}
