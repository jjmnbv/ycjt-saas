package com.beitu.saas.borrower.entity;

import com.beitu.saas.common.consts.TimeConsts;
import com.fqgj.common.entity.BaseEntity;
import com.fqgj.common.utils.MD5;
import com.fqgj.common.utils.TimeUtils;

import java.util.Date;
import java.util.UUID;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 20:25:59.895
 * TableDesc:SAAS借款人TOKEN表
 */
public class SaasBorrowerToken extends BaseEntity {
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


    public String getBorrowerCode() {
        return this.borrowerCode;
    }

    public SaasBorrowerToken setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
        return this;
    }

    public String getMerchantCode() {
        return this.merchantCode;
    }

    public SaasBorrowerToken setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
        return this;
    }

    public String getToken() {
        return this.token;
    }

    public SaasBorrowerToken setToken(String token) {
        this.token = token;
        return this;
    }

    public Date getExpireDate() {
        return this.expireDate;
    }

    public SaasBorrowerToken setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
        return this;
    }

    public String createToken() {
        return MD5.md5(UUID.randomUUID().toString());
    }

    public Date createExpireDate() {
        return TimeUtils.appointed(TimeConsts.HALF_AN_HOUR);
    }

}
