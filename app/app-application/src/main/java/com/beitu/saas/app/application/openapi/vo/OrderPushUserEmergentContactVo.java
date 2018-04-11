package com.beitu.saas.app.application.openapi.vo;

import java.io.Serializable;

public class OrderPushUserEmergentContactVo implements Serializable {
    
    private String familyType;
    
    private String familyName;
    
    private String familyMobile;
    
    private String friendType;
    
    private String friendName;
    
    private String friendMobile;
    
    public String getFamilyType() {
        return familyType;
    }
    
    public void setFamilyType(String familyType) {
        this.familyType = familyType;
    }
    
    public String getFamilyName() {
        return familyName;
    }
    
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
    
    public String getFamilyMobile() {
        return familyMobile;
    }
    
    public void setFamilyMobile(String familyMobile) {
        this.familyMobile = familyMobile;
    }
    
    public String getFriendType() {
        return friendType;
    }
    
    public void setFriendType(String friendType) {
        this.friendType = friendType;
    }
    
    public String getFriendName() {
        return friendName;
    }
    
    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }
    
    public String getFriendMobile() {
        return friendMobile;
    }
    
    public void setFriendMobile(String friendMobile) {
        this.friendMobile = friendMobile;
    }
}
