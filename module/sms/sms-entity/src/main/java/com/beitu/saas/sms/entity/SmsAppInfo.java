package com.beitu.saas.sms.entity;

import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-02-28
 * Time: 15:46:53.652
 * TableDesc:App信息表
 */
public class SmsAppInfo extends BaseEntity {
    /**
     * app 名
     */
    private String appName;
    /**
     * app Code
     */
    private String appCode;
    /**
     * 消息签名
     */
    private String sign;
    /**
     * 所属业务线
     */
    private Integer businessTypeId;


    public String getAppName() {
        return this.appName;
    }

    public SmsAppInfo setAppName(String appName) {
        this.appName = appName;
        return this;
    }

    public String getAppCode() {
        return this.appCode;
    }

    public SmsAppInfo setAppCode(String appCode) {
        this.appCode = appCode;
        return this;
    }

    public String getSign() {
        return this.sign;
    }

    public SmsAppInfo setSign(String sign) {
        this.sign = sign;
        return this;
    }

    public Integer getBusinessTypeId() {
        return this.businessTypeId;
    }

    public SmsAppInfo setBusinessTypeId(Integer businessTypeId) {
        this.businessTypeId = businessTypeId;
        return this;
    }
}
