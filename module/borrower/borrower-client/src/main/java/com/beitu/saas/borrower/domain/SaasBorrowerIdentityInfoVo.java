package com.beitu.saas.borrower.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 15:56:46.988
 */
public class SaasBorrowerIdentityInfoVo implements ResponseData, Serializable {

    private Long saasBorrowerIdentityInfoId;

    /**
     * 借款人CODE
     */
    private String borrowerCode;
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
    /**
     * 是否成功
     */
    private Boolean success;

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

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
