package com.beitu.saas.app.application.openapi.vo;

import java.math.BigDecimal;

public class OrderPushUserDunningDetailVo {
    
    private Integer callTelTotalNums;
    
    private Integer callTotalTimes;
    
    private Integer callOutTimes;
    
    private Integer callInTimes;
    
    private Integer callTotalDuration;
    
    private BigDecimal callAvgDuration;
    
    private Integer callOutDuration;
    
    private Integer callInDuration;
    
    private Integer callDurationBelow15;
    
    private Integer callDurationBetween15and30;
    
    private Integer callDurationAbove60;
    
    private String firstCallTime;
    
    private String lastCallTime;
    
    private Integer type;
    
    public Integer getCallTelTotalNums() {
        return callTelTotalNums;
    }
    
    public void setCallTelTotalNums(Integer callTelTotalNums) {
        this.callTelTotalNums = callTelTotalNums;
    }
    
    public Integer getCallTotalTimes() {
        return callTotalTimes;
    }
    
    public void setCallTotalTimes(Integer callTotalTimes) {
        this.callTotalTimes = callTotalTimes;
    }
    
    public Integer getCallOutTimes() {
        return callOutTimes;
    }
    
    public void setCallOutTimes(Integer callOutTimes) {
        this.callOutTimes = callOutTimes;
    }
    
    public Integer getCallInTimes() {
        return callInTimes;
    }
    
    public void setCallInTimes(Integer callInTimes) {
        this.callInTimes = callInTimes;
    }
    
    public Integer getCallTotalDuration() {
        return callTotalDuration;
    }
    
    public void setCallTotalDuration(Integer callTotalDuration) {
        this.callTotalDuration = callTotalDuration;
    }
    
    public BigDecimal getCallAvgDuration() {
        return callAvgDuration;
    }
    
    public void setCallAvgDuration(BigDecimal callAvgDuration) {
        this.callAvgDuration = callAvgDuration;
    }
    
    public Integer getCallOutDuration() {
        return callOutDuration;
    }
    
    public void setCallOutDuration(Integer callOutDuration) {
        this.callOutDuration = callOutDuration;
    }
    
    public Integer getCallInDuration() {
        return callInDuration;
    }
    
    public void setCallInDuration(Integer callInDuration) {
        this.callInDuration = callInDuration;
    }
    
    public Integer getCallDurationBelow15() {
        return callDurationBelow15;
    }
    
    public void setCallDurationBelow15(Integer callDurationBelow15) {
        this.callDurationBelow15 = callDurationBelow15;
    }
    
    public Integer getCallDurationBetween15and30() {
        return callDurationBetween15and30;
    }
    
    public void setCallDurationBetween15and30(Integer callDurationBetween15and30) {
        this.callDurationBetween15and30 = callDurationBetween15and30;
    }
    
    public Integer getCallDurationAbove60() {
        return callDurationAbove60;
    }
    
    public void setCallDurationAbove60(Integer callDurationAbove60) {
        this.callDurationAbove60 = callDurationAbove60;
    }
    
    public String getFirstCallTime() {
        return firstCallTime;
    }
    
    public void setFirstCallTime(String firstCallTime) {
        this.firstCallTime = firstCallTime;
    }
    
    public String getLastCallTime() {
        return lastCallTime;
    }
    
    public void setLastCallTime(String lastCallTime) {
        this.lastCallTime = lastCallTime;
    }
    
    public Integer getType() {
        return type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
}
