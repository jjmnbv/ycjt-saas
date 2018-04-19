package com.beitu.saas.borrower.domain;

import com.beitu.saas.borrower.entity.SaasBorrowerIdentityInfo;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 20:25:59.876
 */
public class SaasBorrowerIdentityInfoVo implements ResponseData, Serializable {
    
    private Long saasBorrowerIdentityInfoId;
    
    /**
     * 借款人CODE
     */
    private String borrowerCode;
    /**
     * 订单号
     */
    private String orderNumb;
    /**
     * 身份证正面面图片URL
     */
    private String frontUrl;
    /**
     * 身份证反面图片URL
     */
    private String backUrl;
    /**
     * 手持身份证图片URL
     */
    private String holdUrl;
    
    public Long getSaasBorrowerIdentityInfoId() {
        return saasBorrowerIdentityInfoId;
    }
    
    public void setSaasBorrowerIdentityInfoId(Long saasBorrowerIdentityInfoId) {
        this.saasBorrowerIdentityInfoId = saasBorrowerIdentityInfoId;
    }
    
    public String getBorrowerCode() {
        return this.borrowerCode;
    }
    
    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }
    
    public String getOrderNumb() {
        return this.orderNumb;
    }
    
    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
    }
    
    public String getFrontUrl() {
        return this.frontUrl;
    }
    
    public void setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
    }
    
    public String getBackUrl() {
        return this.backUrl;
    }
    
    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }
    
    public String getHoldUrl() {
        return this.holdUrl;
    }
    
    public void setHoldUrl(String holdUrl) {
        this.holdUrl = holdUrl;
    }
    
    public static SaasBorrowerIdentityInfoVo convertEntityToVO(SaasBorrowerIdentityInfo saasBorrowerIdentityInfo) {
        if (saasBorrowerIdentityInfo == null) {
            return null;
        }
        SaasBorrowerIdentityInfoVo saasBorrowerIdentityInfoVo = new SaasBorrowerIdentityInfoVo();
        BeanUtils.copyProperties(saasBorrowerIdentityInfo, saasBorrowerIdentityInfoVo);
        saasBorrowerIdentityInfoVo.setSaasBorrowerIdentityInfoId(saasBorrowerIdentityInfo.getId());
        return saasBorrowerIdentityInfoVo;
    }
    
    public static SaasBorrowerIdentityInfo convertVOToEntity(SaasBorrowerIdentityInfoVo saasBorrowerIdentityInfoVo) {
        if (saasBorrowerIdentityInfoVo == null) {
            return null;
        }
        SaasBorrowerIdentityInfo saasBorrowerIdentityInfo = new SaasBorrowerIdentityInfo();
        BeanUtils.copyProperties(saasBorrowerIdentityInfoVo, saasBorrowerIdentityInfo);
        saasBorrowerIdentityInfo.setId(saasBorrowerIdentityInfoVo.getSaasBorrowerIdentityInfoId());
        return saasBorrowerIdentityInfo;
    }
    
}
