package com.beitu.saas.app.application.openapi.vo;

public class OrderPushUserCarrierExtVo {
    
    private String activeRegion;
    
    private String registerRegion;
    
    private String mostContactRegion;
    
    private Integer interactionCount;
    
    private Integer totalDuration;
    
    private Integer nightDuration;
    
    public String getActiveRegion() {
        return activeRegion;
    }
    
    public void setActiveRegion(String activeRegion) {
        this.activeRegion = activeRegion;
    }
    
    public String getRegisterRegion() {
        return registerRegion;
    }
    
    public void setRegisterRegion(String registerRegion) {
        this.registerRegion = registerRegion;
    }
    
    public String getMostContactRegion() {
        return mostContactRegion;
    }
    
    public void setMostContactRegion(String mostContactRegion) {
        this.mostContactRegion = mostContactRegion;
    }
    
    public Integer getInteractionCount() {
        return interactionCount;
    }
    
    public void setInteractionCount(Integer interactionCount) {
        this.interactionCount = interactionCount;
    }
    
    public Integer getTotalDuration() {
        return totalDuration;
    }
    
    public void setTotalDuration(Integer totalDuration) {
        this.totalDuration = totalDuration;
    }
    
    public Integer getNightDuration() {
        return nightDuration;
    }
    
    public void setNightDuration(Integer nightDuration) {
        this.nightDuration = nightDuration;
    }
}
