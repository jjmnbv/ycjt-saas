package com.beitu.saas.credit.entity;

import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.652
 * TableDesc:运营商报告扩充资料表
 */
public class SaasCreditCarrierExt extends BaseEntity {
    /**
     * 运营商报告查询表ID
     */
    private Long recordId;
    /**
     * 活跃地区
     */
    private String activeRegion;
    /**
     * 入网地区
     */
    private String registerRegion;
    /**
     * 联系人最多地区
     */
    private String mostContactRegion;
    /**
     * 互通联系人数量
     */
    private Integer interactionCount;
    /**
     * 总通话时长
     */
    private Integer totalDuration;
    /**
     * 夜间通话时长
     */
    private Integer nightDuration;


    public Long getRecordId() {
        return this.recordId;
    }

    public SaasCreditCarrierExt setRecordId(Long recordId) {
        this.recordId = recordId;
        return this;
    }

    public String getActiveRegion() {
        return this.activeRegion;
    }

    public SaasCreditCarrierExt setActiveRegion(String activeRegion) {
        this.activeRegion = activeRegion;
        return this;
    }

    public String getRegisterRegion() {
        return this.registerRegion;
    }

    public SaasCreditCarrierExt setRegisterRegion(String registerRegion) {
        this.registerRegion = registerRegion;
        return this;
    }

    public String getMostContactRegion() {
        return this.mostContactRegion;
    }

    public SaasCreditCarrierExt setMostContactRegion(String mostContactRegion) {
        this.mostContactRegion = mostContactRegion;
        return this;
    }

    public Integer getInteractionCount() {
        return this.interactionCount;
    }

    public SaasCreditCarrierExt setInteractionCount(Integer interactionCount) {
        this.interactionCount = interactionCount;
        return this;
    }

    public Integer getTotalDuration() {
        return this.totalDuration;
    }

    public SaasCreditCarrierExt setTotalDuration(Integer totalDuration) {
        this.totalDuration = totalDuration;
        return this;
    }

    public Integer getNightDuration() {
        return this.nightDuration;
    }

    public SaasCreditCarrierExt setNightDuration(Integer nightDuration) {
        this.nightDuration = nightDuration;
        return this;
    }
}
