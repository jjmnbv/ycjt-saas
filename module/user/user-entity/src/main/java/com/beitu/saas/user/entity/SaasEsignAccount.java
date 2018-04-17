package com.beitu.saas.user.entity;

import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-04-17
 * Time: 13:36:51.642
 * TableDesc:SAASe签宝账户信息表
 */
public class SaasEsignAccount extends BaseEntity {
    /**
     * 账户信息CODE
     */
    private String saasEsignCode;
    /**
     * 印章名字
     */
    private String name;
    /**
     * 身份标识码
     */
    private String code;
    /**
     * e签宝账户标识
     */
    private String accountId;
    /**
     * e签宝生成印章URL地址
     */
    private String sealUrl;


    public String getSaasEsignCode() {
        return this.saasEsignCode;
    }

    public SaasEsignAccount setSaasEsignCode(String saasEsignCode) {
        this.saasEsignCode = saasEsignCode;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public SaasEsignAccount setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return this.code;
    }

    public SaasEsignAccount setCode(String code) {
        this.code = code;
        return this;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public SaasEsignAccount setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public String getSealUrl() {
        return this.sealUrl;
    }

    public SaasEsignAccount setSealUrl(String sealUrl) {
        this.sealUrl = sealUrl;
        return this;
    }
}
