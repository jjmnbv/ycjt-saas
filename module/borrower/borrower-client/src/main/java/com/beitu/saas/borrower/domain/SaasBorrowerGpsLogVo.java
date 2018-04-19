package com.beitu.saas.borrower.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;
import java.util.Date;

/**
 * User: linchengyu
 * Date: 2018-04-19
 * Time: 14:04:03.583
 */
public class SaasBorrowerGpsLogVo implements ResponseData, Serializable {
    
    private Long saasBorrowerGpsLogId;
    
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
    
    public Long getSaasBorrowerGpsLogId() {
        return saasBorrowerGpsLogId;
    }
    
    public void setSaasBorrowerGpsLogId(Long saasBorrowerGpsLogId) {
        this.saasBorrowerGpsLogId = saasBorrowerGpsLogId;
    }
    
    public String getBorrowerCode() {
        return this.borrowerCode;
    }
    
    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }
    
    public String getLongitude() {
        return this.longitude;
    }
    
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    
    public String getLatitude() {
        return this.latitude;
    }
    
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public Date getLogTime() {
        return this.logTime;
    }
    
    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
}
