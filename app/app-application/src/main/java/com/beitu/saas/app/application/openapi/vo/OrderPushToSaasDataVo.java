package com.beitu.saas.app.application.openapi.vo;

public class OrderPushToSaasDataVo {
    
    private String mobile;
    
    private String identityNo;
    
    private Integer zmScore;
    
    private String data;
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
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
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
}
