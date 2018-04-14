package com.beitu.saas.common.handle.carrier.domain;

import java.io.Serializable;

//通话记录
public class CarriersPhoneCallVo implements Serializable {
    
    private static final long serialVersionUID = -719075044542247879L;
    private String peercarrier;//运营商
    
    private String peernumber;//通话号码
    private String location;//通话地址
    private String dialtype;//主叫、被叫
    private String createtime;//通话时间
    private String callTime;//时长
    private String locationType;//通话类型
    
    private Integer calling = 0;//主叫次数
    private String callingTime;//主叫时长
    
    private Integer called = 0;//被叫次数
    private String calledTime;//被叫时长
    
    
    public String getLocationType() {
        return locationType;
    }
    
    public CarriersPhoneCallVo setLocationType(String locationType) {
        this.locationType = locationType;
        return this;
    }
    
    public String getPeernumber() {
        return peernumber;
    }
    
    public CarriersPhoneCallVo setPeernumber(String peernumber) {
        this.peernumber = peernumber;
        return this;
    }
    
    public String getLocation() {
        return location;
    }
    
    public CarriersPhoneCallVo setLocation(String location) {
        this.location = location;
        return this;
    }
    
    public String getDialtype() {
        return dialtype;
    }
    
    public CarriersPhoneCallVo setDialtype(String dialtype) {
        this.dialtype = dialtype;
        return this;
    }
    
    public String getCreatetime() {
        return createtime;
    }
    
    public CarriersPhoneCallVo setCreatetime(String createtime) {
        this.createtime = createtime;
        return this;
    }
    
    public String getCallTime() {
        return callTime;
    }
    
    public CarriersPhoneCallVo setCallTime(String callTime) {
        this.callTime = callTime;
        return this;
    }
    
    public String getPeercarrier() {
        return peercarrier;
    }
    
    public CarriersPhoneCallVo setPeercarrier(String peercarrier) {
        this.peercarrier = peercarrier;
        return this;
    }
    
    public Integer getCalling() {
        return calling;
    }
    
    public CarriersPhoneCallVo setCalling(Integer calling) {
        this.calling = calling;
        return this;
    }
    
    public String getCallingTime() {
        return callingTime;
    }
    
    public CarriersPhoneCallVo setCallingTime(String callingTime) {
        this.callingTime = callingTime;
        return this;
    }
    
    public Integer getCalled() {
        return called;
    }
    
    public CarriersPhoneCallVo setCalled(Integer called) {
        this.called = called;
        return this;
    }
    
    public String getCalledTime() {
        return calledTime;
    }
    
    public CarriersPhoneCallVo setCalledTime(String calledTime) {
        this.calledTime = calledTime;
        return this;
    }
    
}
