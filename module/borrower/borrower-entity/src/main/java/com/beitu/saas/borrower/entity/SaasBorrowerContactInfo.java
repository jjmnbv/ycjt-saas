package com.beitu.saas.borrower.entity;

import com.fqgj.common.entity.BaseEntity;

/**
 * User: linchengyu
 * Date: 2018-04-19
 * Time: 16:43:06.878
 * TableDesc:SAAS借款人设备通讯录表
 */
public class SaasBorrowerContactInfo extends BaseEntity {
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
    
    
    public String getMerchantCode() {
        return this.merchantCode;
    }
    
    public SaasBorrowerContactInfo setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
        return this;
    }
    
    public String getBorrowerCode() {
        return this.borrowerCode;
    }
    
    public SaasBorrowerContactInfo setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public SaasBorrowerContactInfo setUrl(String url) {
        this.url = url;
        return this;
    }
}
