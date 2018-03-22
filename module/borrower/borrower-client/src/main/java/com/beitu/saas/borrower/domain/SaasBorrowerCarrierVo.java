package com.beitu.saas.borrower.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;
import java.util.Date;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 20:25:59.850
 */
public class SaasBorrowerCarrierVo implements ResponseData, Serializable {

    private Long saasBorrowerCarrierId;

    /**
     * 借款人CODE
     */
    private String borrowerCode;
    /**
     * 运营商数据存储地址
     */
    private String url;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 过期时间
     */
    private Date expireDate;
    /**
     * 是否成功
     */
    private Boolean success;

    public Long getSaasBorrowerCarrierId() {
        return saasBorrowerCarrierId;
    }

    public void setSaasBorrowerCarrierId(Long saasBorrowerCarrierId) {
        this.saasBorrowerCarrierId = saasBorrowerCarrierId;
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

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getExpireDate() {
        return this.expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
