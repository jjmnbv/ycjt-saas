package com.beitu.saas.borrower.domain;

import com.beitu.saas.borrower.entity.SaasBorrowerGpsLog;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

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
     * 机构CODE
     */
    private String merchantCode;
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
    
    public String getMerchantCode() {
        return merchantCode;
    }
    
    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
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
    
    public static SaasBorrowerGpsLog convertVO2Entity(SaasBorrowerGpsLogVo saasBorrowerGpsLogVo) {
        if (saasBorrowerGpsLogVo == null) {
            return null;
        }
        SaasBorrowerGpsLog saasBorrowerGpsLog = new SaasBorrowerGpsLog();
        BeanUtils.copyProperties(saasBorrowerGpsLogVo, saasBorrowerGpsLog);
        saasBorrowerGpsLog.setId(saasBorrowerGpsLogVo.getSaasBorrowerGpsLogId());
        return saasBorrowerGpsLog;
    }
    
    public static SaasBorrowerGpsLogVo convertEntity2VO(SaasBorrowerGpsLog saasBorrowerGpsLog) {
        if (saasBorrowerGpsLog == null) {
            return null;
        }
        SaasBorrowerGpsLogVo saasBorrowerGpsLogVo = new SaasBorrowerGpsLogVo();
        BeanUtils.copyProperties(saasBorrowerGpsLog, saasBorrowerGpsLogVo);
        saasBorrowerGpsLogVo.setSaasBorrowerGpsLogId(saasBorrowerGpsLog.getId());
        return saasBorrowerGpsLogVo;
    }
    
}
