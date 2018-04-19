package com.beitu.saas.borrower.entity;

import com.fqgj.common.entity.BaseEntity;

import java.util.Date;

/**
 * User: linchengyu
 * Date: 2018-04-19
 * Time: 14:04:03.569
 * TableDesc:借款人GPS信息表
 */
public class SaasBorrowerGpsLog extends BaseEntity {
    /**
     * 借款人CODE
     */
    private String borrowerCode;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 地理位置信息
     */
    private String location;
    /**
     * 地理位置记录时间
     */
    private Date logTime;
    
    public String getBorrowerCode() {
        return this.borrowerCode;
    }
    
    public SaasBorrowerGpsLog setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
        return this;
    }
    
    public String getLongitude() {
        return this.longitude;
    }
    
    public SaasBorrowerGpsLog setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }
    
    public String getLatitude() {
        return this.latitude;
    }
    
    public SaasBorrowerGpsLog setLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public SaasBorrowerGpsLog setLocation(String location) {
        this.location = location;
        return this;
    }
    
    public Date getLogTime() {
        return this.logTime;
    }
    
    public SaasBorrowerGpsLog setLogTime(Date logTime) {
        this.logTime = logTime;
        return this;
    }
}
