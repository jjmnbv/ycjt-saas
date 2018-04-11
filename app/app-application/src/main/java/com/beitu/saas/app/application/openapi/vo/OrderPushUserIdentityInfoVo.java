package com.beitu.saas.app.application.openapi.vo;

import java.io.Serializable;

public class OrderPushUserIdentityInfoVo implements Serializable {
    
    private String frontUrl;
    
    private String backUrl;
    
    private String holdUrl;
    
    public String getFrontUrl() {
        return frontUrl;
    }
    
    public void setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
    }
    
    public String getBackUrl() {
        return backUrl;
    }
    
    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }
    
    public String getHoldUrl() {
        return holdUrl;
    }
    
    public void setHoldUrl(String holdUrl) {
        this.holdUrl = holdUrl;
    }
}
