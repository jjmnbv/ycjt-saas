package com.beitu.saas.common.handle.dianhua.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by linchengyu on 17/8/1.
 */
public class DunningStatisticVo {
    
    @JsonProperty("call_tel_total_nums")
    private String callTelTotalNums;
    
    @JsonProperty("call_total_times")
    private String callTotalTimes;
    
    @JsonProperty("call_out_times")
    private String callOutTimes;
    
    @JsonProperty("call_in_times")
    private String callInTimes;
    
    @JsonProperty("call_total_duration")
    private String callTotalDuration;
    
    @JsonProperty("call_avg_duration")
    private String callAvgDuration;
    
    @JsonProperty("call_out_duration")
    private String callOutDuration;
    
    @JsonProperty("call_in_duration")
    private String callInDuration;
    
    @JsonProperty("call_duration_below15")
    private String callDurationBelow15;
    
    @JsonProperty("call_duration_between15_and_30")
    private String callDurationBetween15And30;
    
    @JsonProperty("call_duration_above60")
    private String callDurationAbove60;
    
    @JsonProperty("first_call_time")
    private String firstCallTime;
    
    @JsonProperty("last_call_time")
    private String lastCallTime;
    
    public String getCallTelTotalNums() {
        return callTelTotalNums;
    }
    
    public void setCallTelTotalNums(String callTelTotalNums) {
        this.callTelTotalNums = callTelTotalNums;
    }
    
    public String getCallTotalTimes() {
        return callTotalTimes;
    }
    
    public void setCallTotalTimes(String callTotalTimes) {
        this.callTotalTimes = callTotalTimes;
    }
    
    public String getCallOutTimes() {
        return callOutTimes;
    }
    
    public void setCallOutTimes(String callOutTimes) {
        this.callOutTimes = callOutTimes;
    }
    
    public String getCallInTimes() {
        return callInTimes;
    }
    
    public void setCallInTimes(String callInTimes) {
        this.callInTimes = callInTimes;
    }
    
    public String getCallTotalDuration() {
        return callTotalDuration;
    }
    
    public void setCallTotalDuration(String callTotalDuration) {
        this.callTotalDuration = callTotalDuration;
    }
    
    public String getCallAvgDuration() {
        return callAvgDuration;
    }
    
    public void setCallAvgDuration(String callAvgDuration) {
        this.callAvgDuration = callAvgDuration;
    }
    
    public String getCallOutDuration() {
        return callOutDuration;
    }
    
    public void setCallOutDuration(String callOutDuration) {
        this.callOutDuration = callOutDuration;
    }
    
    public String getCallInDuration() {
        return callInDuration;
    }
    
    public void setCallInDuration(String callInDuration) {
        this.callInDuration = callInDuration;
    }
    
    public String getCallDurationBelow15() {
        return callDurationBelow15;
    }
    
    public void setCallDurationBelow15(String callDurationBelow15) {
        this.callDurationBelow15 = callDurationBelow15;
    }
    
    public String getCallDurationBetween15And30() {
        return callDurationBetween15And30;
    }
    
    public void setCallDurationBetween15And30(String callDurationBetween15And30) {
        this.callDurationBetween15And30 = callDurationBetween15And30;
    }
    
    public String getCallDurationAbove60() {
        return callDurationAbove60;
    }
    
    public void setCallDurationAbove60(String callDurationAbove60) {
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
}
