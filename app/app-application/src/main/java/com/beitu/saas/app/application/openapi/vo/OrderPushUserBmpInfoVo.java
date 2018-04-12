package com.beitu.saas.app.application.openapi.vo;

import java.util.List;

public class OrderPushUserBmpInfoVo {
    
    private String sid;
    
    private String url;
    
    private List<OrderPushUserBmpDetailVo> details;
    
    public String getSid() {
        return sid;
    }
    
    public void setSid(String sid) {
        this.sid = sid;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public List<OrderPushUserBmpDetailVo> getDetails() {
        return details;
    }
    
    public void setDetails(List<OrderPushUserBmpDetailVo> details) {
        this.details = details;
    }
}
