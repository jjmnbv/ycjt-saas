package com.beitu.saas.common.handle.dianhua.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by linchengyu on 17/8/1.
 */
public class DunningDataVo {
    
    private String sid;
    
    private String uid;
    
    private String tel;
    
    private Long time;
    
    @JsonProperty("total_num")
    private Integer totalNum;
    
    @JsonProperty("effective_num")
    private Integer effectiveNum;
    
    private DunningPeriodVo overview;
    
    @JsonProperty("last_week")
    private DunningPeriodVo lastWeek;
    
    @JsonProperty("last_two_weeks")
    private DunningPeriodVo lastTwoWeeks;
    
    @JsonProperty("last_three_weeks")
    private DunningPeriodVo lastThreeWeeks;
    
    @JsonProperty("last_30_days")
    private DunningPeriodVo last30Days;
    
    @JsonProperty("last_30_and_60_days")
    private DunningPeriodVo last30And60Days;
    
    @JsonProperty("last_60_and_90_days")
    private DunningPeriodVo last60And90Days;
    
    public String getSid() {
        return sid;
    }
    
    public void setSid(String sid) {
        this.sid = sid;
    }
    
    public String getUid() {
        return uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }
    
    public String getTel() {
        return tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    public Long getTime() {
        return time;
    }
    
    public void setTime(Long time) {
        this.time = time;
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
    
    public DunningPeriodVo getOverview() {
        return overview;
    }
    
    public void setOverview(DunningPeriodVo overview) {
        this.overview = overview;
    }
    
    public DunningPeriodVo getLastWeek() {
        return lastWeek;
    }
    
    public void setLastWeek(DunningPeriodVo lastWeek) {
        this.lastWeek = lastWeek;
    }
    
    public DunningPeriodVo getLastTwoWeeks() {
        return lastTwoWeeks;
    }
    
    public void setLastTwoWeeks(DunningPeriodVo lastTwoWeeks) {
        this.lastTwoWeeks = lastTwoWeeks;
    }
    
    public DunningPeriodVo getLastThreeWeeks() {
        return lastThreeWeeks;
    }
    
    public void setLastThreeWeeks(DunningPeriodVo lastThreeWeeks) {
        this.lastThreeWeeks = lastThreeWeeks;
    }
    
    public DunningPeriodVo getLast30Days() {
        return last30Days;
    }
    
    public void setLast30Days(DunningPeriodVo last30Days) {
        this.last30Days = last30Days;
    }
    
    public DunningPeriodVo getLast30And60Days() {
        return last30And60Days;
    }
    
    public void setLast30And60Days(DunningPeriodVo last30And60Days) {
        this.last30And60Days = last30And60Days;
    }
    
    public DunningPeriodVo getLast60And90Days() {
        return last60And90Days;
    }
    
    public void setLast60And90Days(DunningPeriodVo last60And90Days) {
        this.last60And90Days = last60And90Days;
    }
}
