package com.beitu.saas.app.application.credit.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by linchengyu on 17/8/3.
 */
public class CreditBmpMatchVo {
    
    private String typeName;
    
    private Integer callingTime;
    
    private Integer calledTime;
    
    private Integer callingDuration;
    
    private Integer calledDuration;
    
    private Integer totalDuration;
    
    private Date lastTimeCall;
    
    private List<String> numberList;
    
    public CreditBmpMatchVo(String typeName) {
        this.typeName = typeName;
        this.callingTime = 0;
        this.calledTime = 0;
        this.callingDuration = 0;
        this.calledDuration = 0;
        this.totalDuration = 0;
        this.numberList = new ArrayList<>();
    }
    
    public String getTypeName() {
        return typeName;
    }
    
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    
    public Integer getCallingTime() {
        return callingTime;
    }
    
    public void setCallingTime(Integer callingTime) {
        this.callingTime = callingTime;
    }
    
    public Integer getCalledTime() {
        return calledTime;
    }
    
    public void setCalledTime(Integer calledTime) {
        this.calledTime = calledTime;
    }
    
    public Integer getCallingDuration() {
        return callingDuration;
    }
    
    public void setCallingDuration(Integer callingDuration) {
        this.callingDuration = callingDuration;
    }
    
    public Integer getCalledDuration() {
        return calledDuration;
    }
    
    public void setCalledDuration(Integer calledDuration) {
        this.calledDuration = calledDuration;
    }
    
    public Integer getTotalDuration() {
        return totalDuration;
    }
    
    public void setTotalDuration(Integer totalDuration) {
        this.totalDuration = totalDuration;
    }
    
    public Date getLastTimeCall() {
        return lastTimeCall;
    }
    
    public void setLastTimeCall(Date lastTimeCall) {
        this.lastTimeCall = lastTimeCall;
    }
    
    public List<String> getNumberList() {
        return numberList;
    }
    
    public void setNumberList(List<String> numberList) {
        this.numberList = numberList;
    }
}
