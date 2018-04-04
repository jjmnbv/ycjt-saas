package com.beitu.saas.borrower.domain;

import com.beitu.saas.borrower.entity.SaasBorrowerIdentityInfo;
import com.beitu.saas.borrower.entity.SaasBorrowerLoginLog;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * User: jungle
 * Date: 2018-04-03
 * Time: 15:11:35.432
 */
public class SaasBorrowerLoginLogVo implements ResponseData, Serializable {

    private Long saasBorrowerLoginLogId;

    /**
     * 借款人CODE
     */
    private String borrowerCode;
    /**
     * 机构CODE
     */
    private String merchantCode;
    /**
     * 渠道CODE
     */
    private String channelCode;
    /**
     * 手机操作系统
     */
    private String phoneSystem;
    /**
     * 登录IP
     */
    private String loginIp;
    /**
     * 登录详细地址
     */
    private String loginIpAddress;

    private Date gmtCreate;

    public Long getSaasBorrowerLoginLogId() {
        return saasBorrowerLoginLogId;
    }

    public void setSaasBorrowerLoginLogId(Long saasBorrowerLoginLogId) {
        this.saasBorrowerLoginLogId = saasBorrowerLoginLogId;
    }


    public String getBorrowerCode() {
        return this.borrowerCode;
    }

    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }

    public String getMerchantCode() {
        return this.merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getChannelCode() {
        return this.channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getPhoneSystem() {
        return this.phoneSystem;
    }

    public void setPhoneSystem(String phoneSystem) {
        this.phoneSystem = phoneSystem;
    }

    public String getLoginIp() {
        return this.loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginIpAddress() {
        return this.loginIpAddress;
    }

    public void setLoginIpAddress(String loginIpAddress) {
        this.loginIpAddress = loginIpAddress;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public static SaasBorrowerLoginLogVo convertEntityToVO(SaasBorrowerLoginLog saasBorrowerLoginLog) {
        if (saasBorrowerLoginLog == null) {
            return null;
        }
        SaasBorrowerLoginLogVo saasBorrowerLoginLogVo = new SaasBorrowerLoginLogVo();
        BeanUtils.copyProperties(saasBorrowerLoginLog, saasBorrowerLoginLogVo);
        saasBorrowerLoginLogVo.setSaasBorrowerLoginLogId(saasBorrowerLoginLog.getId());
        return saasBorrowerLoginLogVo;
    }

    public static SaasBorrowerLoginLog convertVOToEntity(SaasBorrowerLoginLogVo saasBorrowerLoginLogVo) {
        if (saasBorrowerLoginLogVo == null) {
            return null;
        }
        SaasBorrowerLoginLog saasBorrowerLoginLog = new SaasBorrowerLoginLog();
        BeanUtils.copyProperties(saasBorrowerLoginLogVo, saasBorrowerLoginLog);
        saasBorrowerLoginLog.setId(saasBorrowerLoginLogVo.getSaasBorrowerLoginLogId());
        return saasBorrowerLoginLog;
    }

}