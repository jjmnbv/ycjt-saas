package com.beitu.saas.user.entity;

import com.fqgj.common.entity.BaseEntity;

import java.util.Date;

/**
 * User: jungle
 * Date: 2018-04-02
 * Time: 14:51:43.279
 * TableDesc:SAAS用户e签宝授权信息表
 */
public class SaasUserEsignAuthorization extends BaseEntity {
    /**
     * 用户码
     */
    private String userCode;
    /**
     * 用户e签宝账户标识
     */
    private String accountId;
    /**
     * e签宝生成印章URL地址
     */
    private String sealUrl;
    /**
     * 签章后授权协议URL地址
     */
    private String authorizationUrl;
    /**
     * 用户授权意愿时间
     */
    private Date authorizationTime;
    /**
     * 是否已成功授权
     */
    private Boolean success;


    public String getUserCode() {
        return this.userCode;
    }

    public SaasUserEsignAuthorization setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public SaasUserEsignAuthorization setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public String getSealUrl() {
        return this.sealUrl;
    }

    public SaasUserEsignAuthorization setSealUrl(String sealUrl) {
        this.sealUrl = sealUrl;
        return this;
    }

    public String getAuthorizationUrl() {
        return this.authorizationUrl;
    }

    public SaasUserEsignAuthorization setAuthorizationUrl(String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
        return this;
    }

    public Date getAuthorizationTime() {
        return this.authorizationTime;
    }

    public SaasUserEsignAuthorization setAuthorizationTime(Date authorizationTime) {
        this.authorizationTime = authorizationTime;
        return this;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public SaasUserEsignAuthorization setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
}
