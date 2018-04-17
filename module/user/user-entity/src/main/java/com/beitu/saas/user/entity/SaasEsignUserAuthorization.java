package com.beitu.saas.user.entity;

import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-04-17
 * Time: 13:36:51.658
 * TableDesc:SAAS用户e签宝授权信息表
 */
public class SaasEsignUserAuthorization extends BaseEntity {
    /**
     * 用户码
     */
    private String userCode;
    /**
     * SAAS账户信息CODE
     */
    private String saasEsignCode;
    /**
     * 签章授权协议URL地址
     */
    private String authorizationUrl;


    public String getUserCode() {
        return this.userCode;
    }

    public SaasEsignUserAuthorization setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public String getSaasEsignCode() {
        return this.saasEsignCode;
    }

    public SaasEsignUserAuthorization setSaasEsignCode(String saasEsignCode) {
        this.saasEsignCode = saasEsignCode;
        return this;
    }

    public String getAuthorizationUrl() {
        return this.authorizationUrl;
    }

    public SaasEsignUserAuthorization setAuthorizationUrl(String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
        return this;
    }
}
