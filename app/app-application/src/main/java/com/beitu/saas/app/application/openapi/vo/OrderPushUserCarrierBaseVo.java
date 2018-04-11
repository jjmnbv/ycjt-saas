package com.beitu.saas.app.application.openapi.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderPushUserCarrierBaseVo implements Serializable {
    
    private String carrierType;
    
    private String mobile;
    
    private String name;
    
    private String identityNo;
    
    private BigDecimal balance;
    
    private String province;
    
    private String planName;
    
    private Date registerTime;
    
    public String getCarrierType() {
        return carrierType;
    }
    
    public void setCarrierType(String carrierType) {
        this.carrierType = carrierType;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getIdentityNo() {
        return identityNo;
    }
    
    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }
    
    public BigDecimal getBalance() {
        return balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    public String getProvince() {
        return province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    
    public String getPlanName() {
        return planName;
    }
    
    public void setPlanName(String planName) {
        this.planName = planName;
    }
    
    public Date getRegisterTime() {
        return registerTime;
    }
    
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}
