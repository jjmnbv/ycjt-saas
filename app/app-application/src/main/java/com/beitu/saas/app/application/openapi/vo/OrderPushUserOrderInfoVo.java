package com.beitu.saas.app.application.openapi.vo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderPushUserOrderInfoVo {
    
    private BigDecimal realCapital;
    
    private BigDecimal totalInterestRatio;
    
    private BigDecimal totalInterestFee;
    
    private BigDecimal lateInterestRatio;
    
    private String borrowPurpose;
    
    private Date repaymentDt;
    
    private Date createdDt;
    
    private Date expireDate;
    
    public BigDecimal getRealCapital() {
        return realCapital;
    }
    
    public void setRealCapital(BigDecimal realCapital) {
        this.realCapital = realCapital;
    }
    
    public BigDecimal getTotalInterestRatio() {
        return totalInterestRatio;
    }
    
    public void setTotalInterestRatio(BigDecimal totalInterestRatio) {
        this.totalInterestRatio = totalInterestRatio;
    }
    
    public BigDecimal getTotalInterestFee() {
        return totalInterestFee;
    }
    
    public void setTotalInterestFee(BigDecimal totalInterestFee) {
        this.totalInterestFee = totalInterestFee;
    }
    
    public BigDecimal getLateInterestRatio() {
        return lateInterestRatio;
    }
    
    public void setLateInterestRatio(BigDecimal lateInterestRatio) {
        this.lateInterestRatio = lateInterestRatio;
    }
    
    public String getBorrowPurpose() {
        return borrowPurpose;
    }
    
    public void setBorrowPurpose(String borrowPurpose) {
        this.borrowPurpose = borrowPurpose;
    }
    
    public Date getRepaymentDt() {
        return repaymentDt;
    }
    
    public void setRepaymentDt(Date repaymentDt) {
        this.repaymentDt = repaymentDt;
    }
    
    public Date getCreatedDt() {
        return createdDt;
    }
    
    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }
    
    public Date getExpireDate() {
        return expireDate;
    }
    
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
