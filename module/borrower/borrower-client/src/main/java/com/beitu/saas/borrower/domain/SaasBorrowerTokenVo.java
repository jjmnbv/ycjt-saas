package com.beitu.saas.borrower.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;
import java.util.Date;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 20:25:59.899
 */
public class SaasBorrowerTokenVo implements ResponseData, Serializable {

    private Long saasBorrowerTokenId;

    /**
     * 借款人CODE
     */
    private String borrowerCode;
    /**
     * 机构CODE
     */
    private String merchantCode;
    /**
     * 授权令牌
     */
    private String token;
    /**
     * 过期时间
     */
    private Date expireDate;

    public Long getSaasBorrowerTokenId() {
        return saasBorrowerTokenId;
    }

    public void setSaasBorrowerTokenId(Long saasBorrowerTokenId) {
        this.saasBorrowerTokenId = saasBorrowerTokenId;
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

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpireDate() {
        return this.expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
