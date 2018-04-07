package com.beitu.saas.credit.domain;

import com.beitu.saas.credit.entity.SaasCreditCarrierExt;
import com.beitu.saas.credit.entity.SaasCreditDunning;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.653
 */
public class SaasCreditCarrierExtVo implements ResponseData, Serializable {

    private Long saasCreditCarrierExtId;

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

    public Long getSaasCreditCarrierExtId() {
        return saasCreditCarrierExtId;
    }

    public void setSaasCreditCarrierExtId(Long saasCreditCarrierExtId) {
        this.saasCreditCarrierExtId = saasCreditCarrierExtId;
    }


    public Long getRecordId() {
        return this.recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
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

    public static SaasCreditCarrierExtVo convertEntityToVO(SaasCreditCarrierExt saasCreditCarrierExt) {
        if (saasCreditCarrierExt == null) {
            return null;
        }
        SaasCreditCarrierExtVo saasCreditCarrierExtVo = new SaasCreditCarrierExtVo();
        BeanUtils.copyProperties(saasCreditCarrierExt, saasCreditCarrierExtVo);
        saasCreditCarrierExtVo.setSaasCreditCarrierExtId(saasCreditCarrierExt.getId());
        return saasCreditCarrierExtVo;
    }

    public static SaasCreditCarrierExt convertVOToEntity(SaasCreditCarrierExtVo saasCreditCarrierExtVo) {
        if (saasCreditCarrierExtVo == null) {
            return null;
        }
        SaasCreditCarrierExt saasCreditCarrierExt = new SaasCreditCarrierExt();
        BeanUtils.copyProperties(saasCreditCarrierExtVo, saasCreditCarrierExt);
        saasCreditCarrierExt.setId(saasCreditCarrierExtVo.getSaasCreditCarrierExtId());
        return saasCreditCarrierExt;
    }

}
