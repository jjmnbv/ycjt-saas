package com.beitu.saas.app.application.openapi.vo;

import java.io.Serializable;

public class OrderPushUserBasicInfoVo implements Serializable {
    
    private String mobile;
    
    private String name;
    
    private String identityNo;
    
    private Integer zmScore;
    
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
    
    public Integer getZmScore() {
        return zmScore;
    }
    
    public void setZmScore(Integer zmScore) {
        this.zmScore = zmScore;
    }
}
