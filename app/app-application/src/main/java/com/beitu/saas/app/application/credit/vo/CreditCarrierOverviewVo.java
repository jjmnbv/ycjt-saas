package com.beitu.saas.app.application.credit.vo;

import com.beitu.saas.common.consts.TimeConsts;
import com.beitu.saas.common.utils.LocationUtil;
import com.beitu.saas.credit.domain.SaasCreditCarrierExtVo;
import com.fqgj.common.api.ResponseData;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: linchengyu
 * Date: 2017-06-29
 * Time: 22:16:59.764
 */
public class CreditCarrierOverviewVo implements ResponseData, Serializable {

    private String activeRegionName;

    private String registerRegionName;

    private String mostContactRegionName;

    private Integer interactionCount;

    private String nightDuration;

    private String nightPercentage;

    public CreditCarrierOverviewVo(SaasCreditCarrierExtVo saasCreditCarrierExtVo) {
        if (saasCreditCarrierExtVo != null) {
            this.activeRegionName = LocationUtil.formatLocation(saasCreditCarrierExtVo.getActiveRegion());
            this.registerRegionName = LocationUtil.formatLocation(saasCreditCarrierExtVo.getRegisterRegion());
            this.mostContactRegionName = LocationUtil.formatLocation(saasCreditCarrierExtVo.getMostContactRegion());
            this.interactionCount = saasCreditCarrierExtVo.getInteractionCount();
            Integer duration = saasCreditCarrierExtVo.getNightDuration() == null ? 0 : saasCreditCarrierExtVo.getNightDuration();
            this.nightDuration = "时长" + (duration / TimeConsts.ONE_MINUTE + ((duration % TimeConsts.ONE_MINUTE > 0) ? 1 : 0)) + "分";
            this.nightPercentage = "占" + Math.round(100F * saasCreditCarrierExtVo.getNightDuration() / saasCreditCarrierExtVo.getTotalDuration()) + "%";
        }
    }

    public String getActiveRegionName() {
        return activeRegionName;
    }

    public void setActiveRegionName(String activeRegionName) {
        this.activeRegionName = activeRegionName;
    }

    public String getRegisterRegionName() {
        return registerRegionName;
    }

    public void setRegisterRegionName(String registerRegionName) {
        this.registerRegionName = registerRegionName;
    }

    public String getMostContactRegionName() {
        return mostContactRegionName;
    }

    public void setMostContactRegionName(String mostContactRegionName) {
        this.mostContactRegionName = mostContactRegionName;
    }

    public Integer getInteractionCount() {
        return interactionCount;
    }

    public void setInteractionCount(Integer interactionCount) {
        this.interactionCount = interactionCount;
    }

    public String getNightDuration() {
        return nightDuration;
    }

    public void setNightDuration(String nightDuration) {
        this.nightDuration = nightDuration;
    }

    public String getNightPercentage() {
        return nightPercentage;
    }

    public void setNightPercentage(String nightPercentage) {
        this.nightPercentage = nightPercentage;
    }

}
