package com.beitu.saas.app.application.openapi.vo;

import java.io.Serializable;
import java.util.List;

public class OrderPushUserDunningInfoVo implements Serializable {
    
    private Long carrierId;
    
    private String sid;
    
    private String mobile;
    
    private Integer totalNum;
    
    private Integer effectiveNum;
    
    private String url;
    
    private List<OrderPushUserDunningDetailVo> details;
    
    public Long getCarrierId() {
        return carrierId;
    }
    
    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }
    
    public String getSid() {
        return sid;
    }
    
    public void setSid(String sid) {
        this.sid = sid;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public Integer getTotalNum() {
        return totalNum;
    }
    
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }
    
    public Integer getEffectiveNum() {
        return effectiveNum;
    }
    
    public void setEffectiveNum(Integer effectiveNum) {
        this.effectiveNum = effectiveNum;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public List<OrderPushUserDunningDetailVo> getDetails() {
        return details;
    }
    
    public void setDetails(List<OrderPushUserDunningDetailVo> details) {
        this.details = details;
    }
}
