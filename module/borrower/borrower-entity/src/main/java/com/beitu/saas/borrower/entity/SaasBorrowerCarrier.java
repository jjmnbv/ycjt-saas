package com.beitu.saas.borrower.entity;

import com.fqgj.common.entity.BaseEntity;

import java.util.Date;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 15:56:46.954
 * TableDesc:SAAS借款人运营商报告信息表
 */
public class SaasBorrowerCarrier extends BaseEntity {
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
    private Integer mobile;
    /**
     * 过期时间
     */
    private Date expireDate;
    /**
     * 是否成功
     */
    private Boolean success;


    public String getBorrowerCode() {
        return this.borrowerCode;
    }

    public SaasBorrowerCarrier setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
        return this;
    }

    public String getUrl() {
        return this.url;
    }

    public SaasBorrowerCarrier setUrl(String url) {
        this.url = url;
        return this;
    }

    public Integer getMobile() {
        return this.mobile;
    }

    public SaasBorrowerCarrier setMobile(Integer mobile) {
        this.mobile = mobile;
        return this;
    }

    public Date getExpireDate() {
        return this.expireDate;
    }

    public SaasBorrowerCarrier setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
        return this;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public SaasBorrowerCarrier setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
}
