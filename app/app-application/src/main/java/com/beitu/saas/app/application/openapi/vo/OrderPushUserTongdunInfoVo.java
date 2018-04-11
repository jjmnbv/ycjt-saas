package com.beitu.saas.app.application.openapi.vo;

import java.io.Serializable;

public class OrderPushUserTongdunInfoVo implements Serializable {
    
    private String mobile;
    
    private String identityCode;
    
    private String reportId;
    
    private OrderPushUserTongdunDetailVo detail;
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getIdentityCode() {
        return identityCode;
    }
    
    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }
    
    public String getReportId() {
        return reportId;
    }
    
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
    
    public OrderPushUserTongdunDetailVo getDetail() {
        return detail;
    }
    
    public void setDetail(OrderPushUserTongdunDetailVo detail) {
        this.detail = detail;
    }
}
