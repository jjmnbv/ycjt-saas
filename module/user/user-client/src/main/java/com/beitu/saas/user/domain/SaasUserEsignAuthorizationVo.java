package com.beitu.saas.user.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;
import java.util.Date;

/**
 * User: jungle
 * Date: 2018-04-02
 * Time: 14:51:43.296
 */
public class SaasUserEsignAuthorizationVo implements ResponseData, Serializable {

    private Long saasUserEsignAuthorizationId;

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

    public Long getSaasUserEsignAuthorizationId() {
        return saasUserEsignAuthorizationId;
    }

    public void setSaasUserEsignAuthorizationId(Long saasUserEsignAuthorizationId) {
        this.saasUserEsignAuthorizationId = saasUserEsignAuthorizationId;
    }


    public String getUserCode() {
        return this.userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getSealUrl() {
        return this.sealUrl;
    }

    public void setSealUrl(String sealUrl) {
        this.sealUrl = sealUrl;
    }

    public String getAuthorizationUrl() {
        return this.authorizationUrl;
    }

    public void setAuthorizationUrl(String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
    }

    public Date getAuthorizationTime() {
        return this.authorizationTime;
    }

    public void setAuthorizationTime(Date authorizationTime) {
        this.authorizationTime = authorizationTime;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
