package com.beitu.saas.app.application.credit.vo;

import com.beitu.saas.common.utils.LocationUtil;
import com.beitu.saas.credit.domain.SaasCreditCarrierRecordVo;
import com.fqgj.common.utils.StringUtils;

import java.io.Serializable;

/**
 * Created by linchengyu on 17/6/27.
 */
public class CreditCarrierMerchantListVo implements Serializable {
    
    /**
     * 电话
     */
    private String mobile;
    
    /**
     * 归属地
     */
    private String location;
    
    /**
     * 呼入呼出
     */
    private String inAndOut;
    
    /**
     * 时长
     */
    private String duration;
    
    public CreditCarrierMerchantListVo(SaasCreditCarrierRecordVo saasCreditCarrierRecordVo) {
        this.mobile = saasCreditCarrierRecordVo.getPhone();
        if (StringUtils.isNotEmpty(saasCreditCarrierRecordVo.getMerchant())) {
            this.mobile = saasCreditCarrierRecordVo.getMerchant() + ":" + saasCreditCarrierRecordVo.getPhone();
        }
        this.location = LocationUtil.formatLocation(saasCreditCarrierRecordVo.getLocation());
        this.inAndOut = (saasCreditCarrierRecordVo.getCalledTime() == null ? 0 : saasCreditCarrierRecordVo.getCalledTime()) + "/" + (saasCreditCarrierRecordVo.getCallingTime() == null ? 0 : saasCreditCarrierRecordVo.getCallingTime());
        
        Integer totalDuration = ((saasCreditCarrierRecordVo.getCalledDuration() == null ? 0 : saasCreditCarrierRecordVo.getCalledDuration()) + (saasCreditCarrierRecordVo.getCallingDuration() == null ? 0 : saasCreditCarrierRecordVo.getCallingDuration()));
        this.duration = (totalDuration / 60 + ((totalDuration % 60 > 0) ? 1 : 0)) + "分";
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getInAndOut() {
        return inAndOut;
    }
    
    public void setInAndOut(String inAndOut) {
        this.inAndOut = inAndOut;
    }
    
    public String getDuration() {
        return duration;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
}
