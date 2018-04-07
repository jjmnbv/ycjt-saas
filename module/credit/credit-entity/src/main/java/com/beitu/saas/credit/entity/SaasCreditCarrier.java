package com.beitu.saas.credit.entity;

import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.626
 * TableDesc:运营商报告查询表
 */
public class SaasCreditCarrier extends BaseEntity {
    /**
     * 机构码
     */
    private String merchantCode;
    /**
     * 用户码
     */
    private String borrowerCode;
    /**
     * 运营商记录获取来源 1 : 51运营商 2 : 同盾运营商
     */
    private Integer type;
    /**
     * 运营商数据存储地址
     */
    private String url;
    /**
     * 是否查询成功
     */
    private Boolean success;


    public String getMerchantCode() {
        return this.merchantCode;
    }

    public SaasCreditCarrier setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
        return this;
    }

    public String getBorrowerCode() {
        return this.borrowerCode;
    }

    public SaasCreditCarrier setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
        return this;
    }

    public Integer getType() {
        return this.type;
    }

    public SaasCreditCarrier setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getUrl() {
        return this.url;
    }

    public SaasCreditCarrier setUrl(String url) {
        this.url = url;
        return this;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public SaasCreditCarrier setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
}
