package com.beitu.saas.app.application.openapi.vo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderPushUserCarrierBillVo {
    
    private Date billDate;
    
    private BigDecimal baseFee;
    
    private BigDecimal totalFee;
    
    private Integer callingTime;
    
    private Integer calledTime;
    
    private Integer callingDuration;
    
    private Integer calledDuration;
    
    public Date getBillDate() {
        return billDate;
    }
    
    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }
    
    public BigDecimal getBaseFee() {
        return baseFee;
    }
    
    public void setBaseFee(BigDecimal baseFee) {
        this.baseFee = baseFee;
    }
    
    public BigDecimal getTotalFee() {
        return totalFee;
    }
    
    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }
    
    public Integer getCallingTime() {
        return callingTime;
    }
    
    public void setCallingTime(Integer callingTime) {
        this.callingTime = callingTime;
    }
    
    public Integer getCalledTime() {
        return calledTime;
    }
    
    public void setCalledTime(Integer calledTime) {
        this.calledTime = calledTime;
    }
    
    public Integer getCallingDuration() {
        return callingDuration;
    }
    
    public void setCallingDuration(Integer callingDuration) {
        this.callingDuration = callingDuration;
    }
    
    public Integer getCalledDuration() {
        return calledDuration;
    }
    
    public void setCalledDuration(Integer calledDuration) {
        this.calledDuration = calledDuration;
    }
}
