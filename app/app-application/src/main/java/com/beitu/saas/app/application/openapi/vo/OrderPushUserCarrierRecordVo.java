package com.beitu.saas.app.application.openapi.vo;

public class OrderPushUserCarrierRecordVo {
    
    private String phone;
    
    private String merchant;
    
    private String location;
    
    private Integer callingTime;
    
    private Integer calledTime;
    
    private Integer callingDuration;
    
    private Integer calledDuration;
    
    private Integer totalDuration;
    
    private Integer type;
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getMerchant() {
        return merchant;
    }
    
    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
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
    
    public Integer getTotalDuration() {
        return totalDuration;
    }
    
    public void setTotalDuration(Integer totalDuration) {
        this.totalDuration = totalDuration;
    }
    
    public Integer getType() {
        return type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
}
