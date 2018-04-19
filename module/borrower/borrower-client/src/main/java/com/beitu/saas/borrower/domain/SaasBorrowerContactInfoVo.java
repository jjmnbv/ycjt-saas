package com.beitu.saas.borrower.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
 * User: linchengyu
 * Date: 2018-04-19
 * Time: 16:43:06.907
 */
public class SaasBorrowerContactInfoVo implements ResponseData, Serializable {
    
    private Long saasBorrowerContactInfoId;
    
    /**
     * 机构CODE
     */
    private String merchantCode;
    /**
     * 借款人CODE
     */
    private String borrowerCode;
    /**
     * 通讯录数据存储地址
     */
    private String url;
    
    public SaasBorrowerContactInfoVo() {
    }
    
    public SaasBorrowerContactInfoVo(String merchantCode, String borrowerCode, String url) {
        this.merchantCode = merchantCode;
        this.borrowerCode = borrowerCode;
        this.url = url;
    }
    
    public Long getSaasBorrowerContactInfoId() {
        return saasBorrowerContactInfoId;
    }
    
    public void setSaasBorrowerContactInfoId(Long saasBorrowerContactInfoId) {
        this.saasBorrowerContactInfoId = saasBorrowerContactInfoId;
    }
    
    public String getMerchantCode() {
        return this.merchantCode;
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
    
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
}
