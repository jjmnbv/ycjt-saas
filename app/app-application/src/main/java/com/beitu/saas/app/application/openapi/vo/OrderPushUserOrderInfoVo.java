package com.beitu.saas.app.application.openapi.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderPushUserOrderInfoVo implements Serializable {
    
    private BigDecimal realCapital;
    
    private BigDecimal totalInterestRatio;
    
    private BigDecimal lateInterestRatio;
    
    private String borrowPurpose;
    
    private Date repaymentDt;
    
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
}
