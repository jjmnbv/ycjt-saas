package com.beitu.saas.borrower.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 15:56:46.967
 */
public class SaasBorrowerCarrierExtVo implements ResponseData, Serializable {

    private Long saasBorrowerCarrierExtId;

    /**
     * 借款人CODE
     */
    private String borrowerCode;
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

    public Long getSaasBorrowerCarrierExtId() {
        return saasBorrowerCarrierExtId;
    }

    public void setSaasBorrowerCarrierExtId(Long saasBorrowerCarrierExtId) {
        this.saasBorrowerCarrierExtId = saasBorrowerCarrierExtId;
    }


    public String getBorrowerCode() {
        return this.borrowerCode;
    }

    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }

    public String getActiveRegion() {
        return this.activeRegion;
    }

    public void setActiveRegion(String activeRegion) {
        this.activeRegion = activeRegion;
    }

    public String getRegisterRegion() {
        return this.registerRegion;
    }

    public void setRegisterRegion(String registerRegion) {
        this.registerRegion = registerRegion;
    }

    public String getMostContactRegion() {
        return this.mostContactRegion;
    }

    public void setMostContactRegion(String mostContactRegion) {
        this.mostContactRegion = mostContactRegion;
    }

    public Integer getInteractionCount() {
        return this.interactionCount;
    }

    public void setInteractionCount(Integer interactionCount) {
        this.interactionCount = interactionCount;
    }

    public Integer getTotalDuration() {
        return this.totalDuration;
    }

    public void setTotalDuration(Integer totalDuration) {
        this.totalDuration = totalDuration;
    }

    public Integer getNightDuration() {
        return this.nightDuration;
    }

    public void setNightDuration(Integer nightDuration) {
        this.nightDuration = nightDuration;
    }
}
