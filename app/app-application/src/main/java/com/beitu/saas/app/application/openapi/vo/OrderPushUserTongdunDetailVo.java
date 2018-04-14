package com.beitu.saas.app.application.openapi.vo;

import java.util.Date;

public class OrderPushUserTongdunDetailVo {
    
    private String reportId;
    
    private String tongdunReport;
    
    private String finalScore;
    
    private String finalDecision;
    
    private String itemName;
    
    private Date reportTime;
    
    public String getReportId() {
        return reportId;
    }
    
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
    
    public String getTongdunReport() {
        return tongdunReport;
    }
    
    public void setTongdunReport(String tongdunReport) {
        this.tongdunReport = tongdunReport;
    }
    
    public String getFinalScore() {
        return finalScore;
    }
    
    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }
    
    public String getFinalDecision() {
        return finalDecision;
    }
    
    public void setFinalDecision(String finalDecision) {
        this.finalDecision = finalDecision;
    }
    
    public String getItemName() {
        return itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public Date getReportTime() {
        return reportTime;
    }
    
    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }
}
