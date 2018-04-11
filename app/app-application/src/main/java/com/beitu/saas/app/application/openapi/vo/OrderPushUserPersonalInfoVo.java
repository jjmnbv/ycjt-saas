package com.beitu.saas.app.application.openapi.vo;

import java.io.Serializable;

public class OrderPushUserPersonalInfoVo implements Serializable {
    
    private String wechat;
    
    private String education;
    
    private String address;
    
    private String liveDuration;
    
    private String maritalStatus;
    
    public String getWechat() {
        return wechat;
    }
    
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
    
    public String getEducation() {
        return education;
    }
    
    public void setEducation(String education) {
        this.education = education;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getLiveDuration() {
        return liveDuration;
    }
    
    public void setLiveDuration(String liveDuration) {
        this.liveDuration = liveDuration;
    }
    
    public String getMaritalStatus() {
        return maritalStatus;
    }
    
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
}
