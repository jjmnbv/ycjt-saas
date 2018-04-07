package com.beitu.saas.credit.entity;

import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.661
 * TableDesc:用户催收数据查询表
 */
public class SaasCreditDunning extends BaseEntity {
    /**
     * 机构码
     */
    private String merchantCode;
    /**
     * 用户码
     */
    private String borrowerCode;
    /**
     * 运营商数据ID
     */
    private Long carrierId;
    /**
     * 电话邦催收数据查询唯一标识
     */
    private String sid;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 总记录数
     */
    private Integer totalNum;
    /**
     * 有效数据数
     */
    private Integer effectiveNum;
    /**
     * 催收数据存储地址
     */
    private String url;
    /**
     * 是否查询成功
     */
    private Boolean success;


    public String getMerchantCode() {
        return this.merchantCode;
    }

    public SaasCreditDunning setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
        return this;
    }

    public String getBorrowerCode() {
        return this.borrowerCode;
    }

    public SaasCreditDunning setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
        return this;
    }

    public Long getCarrierId() {
        return this.carrierId;
    }

    public SaasCreditDunning setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
        return this;
    }

    public String getSid() {
        return this.sid;
    }

    public SaasCreditDunning setSid(String sid) {
        this.sid = sid;
        return this;
    }

    public String getMobile() {
        return this.mobile;
    }

    public SaasCreditDunning setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public Integer getTotalNum() {
        return this.totalNum;
    }

    public SaasCreditDunning setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
        return this;
    }

    public Integer getEffectiveNum() {
        return this.effectiveNum;
    }

    public SaasCreditDunning setEffectiveNum(Integer effectiveNum) {
        this.effectiveNum = effectiveNum;
        return this;
    }

    public String getUrl() {
        return this.url;
    }

    public SaasCreditDunning setUrl(String url) {
        this.url = url;
        return this;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public SaasCreditDunning setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
}
