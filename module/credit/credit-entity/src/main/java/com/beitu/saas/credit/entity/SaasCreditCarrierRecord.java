package com.beitu.saas.credit.entity;

import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.657
 * TableDesc:运营商报告通话详情表
 */
public class SaasCreditCarrierRecord extends BaseEntity {
    /**
     * 运营商报告查询表ID
     */
    private Long recordId;
    /**
     * 号码
     */
    private String phone;
    /**
     * 商户名
     */
    private String merchant;
    /**
     * 地址
     */
    private String location;
    /**
     * 呼出次数
     */
    private Integer callingTime;
    /**
     * 呼入次数
     */
    private Integer calledTime;
    /**
     * 呼出时长（秒）
     */
    private Integer callingDuration;
    /**
     * 呼入时长（秒）
     */
    private Integer calledDuration;
    /**
     * 同通话时长（秒）
     */
    private Integer totalDuration;
    /**
     * 记录类型，1：高频联系人记录，2：商户通话记录，3：通话活跃记录
     */
    private Integer type;


    public Long getRecordId() {
        return this.recordId;
    }

    public SaasCreditCarrierRecord setRecordId(Long recordId) {
        this.recordId = recordId;
        return this;
    }

    public String getPhone() {
        return this.phone;
    }

    public SaasCreditCarrierRecord setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getMerchant() {
        return this.merchant;
    }

    public SaasCreditCarrierRecord setMerchant(String merchant) {
        this.merchant = merchant;
        return this;
    }

    public String getLocation() {
        return this.location;
    }

    public SaasCreditCarrierRecord setLocation(String location) {
        this.location = location;
        return this;
    }

    public Integer getCallingTime() {
        return this.callingTime;
    }

    public SaasCreditCarrierRecord setCallingTime(Integer callingTime) {
        this.callingTime = callingTime;
        return this;
    }

    public Integer getCalledTime() {
        return this.calledTime;
    }

    public SaasCreditCarrierRecord setCalledTime(Integer calledTime) {
        this.calledTime = calledTime;
        return this;
    }

    public Integer getCallingDuration() {
        return this.callingDuration;
    }

    public SaasCreditCarrierRecord setCallingDuration(Integer callingDuration) {
        this.callingDuration = callingDuration;
        return this;
    }

    public Integer getCalledDuration() {
        return this.calledDuration;
    }

    public SaasCreditCarrierRecord setCalledDuration(Integer calledDuration) {
        this.calledDuration = calledDuration;
        return this;
    }

    public Integer getTotalDuration() {
        return this.totalDuration;
    }

    public SaasCreditCarrierRecord setTotalDuration(Integer totalDuration) {
        this.totalDuration = totalDuration;
        return this;
    }

    public Integer getType() {
        return this.type;
    }

    public SaasCreditCarrierRecord setType(Integer type) {
        this.type = type;
        return this;
    }
}
