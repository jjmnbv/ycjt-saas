package com.beitu.saas.credit.domain;

import com.beitu.saas.credit.entity.SaasCreditDunning;
import com.beitu.saas.credit.entity.SaasCreditDunningDetail;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.669
 */
public class SaasCreditDunningDetailVo implements ResponseData, Serializable {

    private Long saasCreditDunningDetailId;

    /**
     * 用户催收数据查询表ID
     */
    private Long recordId;
    /**
     * 电话邦催收数据查询唯一标识
     */
    private String sid;
    /**
     * 总通话号码数
     */
    private Integer callTelTotalNums;
    /**
     * 总通话次数
     */
    private Integer callTotalTimes;
    /**
     * 主叫次数
     */
    private Integer callOutTimes;
    /**
     * 被叫次数
     */
    private Integer callInTimes;
    /**
     * 通话总时长
     */
    private Integer callTotalDuration;
    /**
     * 通话平均时长
     */
    private BigDecimal callAvgDuration;
    /**
     * 主叫总时长
     */
    private Integer callOutDuration;
    /**
     * 被叫总时长
     */
    private Integer callInDuration;
    /**
     * 通话时长低于15秒的次数
     */
    private Integer callDurationBelow15;
    /**
     * 通话时长15-30秒的次数
     */
    private Integer callDurationBetween15and30;
    /**
     * 通话时长60秒以上的次数
     */
    private Integer callDurationAbove60;
    /**
     * 首次通话时间
     */
    private String firstCallTime;
    /**
     * 最近一次通话时间
     */
    private String lastCallTime;
    /**
     * 类型:10-总览催收,11-总览疑似催收,20-近一周催收,21-近一周疑似催收,30-近两周催收,31-近两周疑似催收,40-近三周催收,41-近三周疑似催收,50-近30天催收,51-近30天疑似催收,60-近30至60天催收,61-近30至60
     */
    private Integer type;

    public Long getSaasCreditDunningDetailId() {
        return saasCreditDunningDetailId;
    }

    public void setSaasCreditDunningDetailId(Long saasCreditDunningDetailId) {
        this.saasCreditDunningDetailId = saasCreditDunningDetailId;
    }


    public Long getRecordId() {
        return this.recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getSid() {
        return this.sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Integer getCallTelTotalNums() {
        return this.callTelTotalNums;
    }

    public void setCallTelTotalNums(Integer callTelTotalNums) {
        this.callTelTotalNums = callTelTotalNums;
    }

    public Integer getCallTotalTimes() {
        return this.callTotalTimes;
    }

    public void setCallTotalTimes(Integer callTotalTimes) {
        this.callTotalTimes = callTotalTimes;
    }

    public Integer getCallOutTimes() {
        return this.callOutTimes;
    }

    public void setCallOutTimes(Integer callOutTimes) {
        this.callOutTimes = callOutTimes;
    }

    public Integer getCallInTimes() {
        return this.callInTimes;
    }

    public void setCallInTimes(Integer callInTimes) {
        this.callInTimes = callInTimes;
    }

    public Integer getCallTotalDuration() {
        return this.callTotalDuration;
    }

    public void setCallTotalDuration(Integer callTotalDuration) {
        this.callTotalDuration = callTotalDuration;
    }

    public BigDecimal getCallAvgDuration() {
        return this.callAvgDuration;
    }

    public void setCallAvgDuration(BigDecimal callAvgDuration) {
        this.callAvgDuration = callAvgDuration;
    }

    public Integer getCallOutDuration() {
        return this.callOutDuration;
    }

    public void setCallOutDuration(Integer callOutDuration) {
        this.callOutDuration = callOutDuration;
    }

    public Integer getCallInDuration() {
        return this.callInDuration;
    }

    public void setCallInDuration(Integer callInDuration) {
        this.callInDuration = callInDuration;
    }

    public Integer getCallDurationBelow15() {
        return this.callDurationBelow15;
    }

    public void setCallDurationBelow15(Integer callDurationBelow15) {
        this.callDurationBelow15 = callDurationBelow15;
    }

    public Integer getCallDurationBetween15and30() {
        return this.callDurationBetween15and30;
    }

    public void setCallDurationBetween15and30(Integer callDurationBetween15and30) {
        this.callDurationBetween15and30 = callDurationBetween15and30;
    }

    public Integer getCallDurationAbove60() {
        return this.callDurationAbove60;
    }

    public void setCallDurationAbove60(Integer callDurationAbove60) {
        this.callDurationAbove60 = callDurationAbove60;
    }

    public String getFirstCallTime() {
        return this.firstCallTime;
    }

    public void setFirstCallTime(String firstCallTime) {
        this.firstCallTime = firstCallTime;
    }

    public String getLastCallTime() {
        return this.lastCallTime;
    }

    public void setLastCallTime(String lastCallTime) {
        this.lastCallTime = lastCallTime;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public static SaasCreditDunningDetailVo convertEntityToVO(SaasCreditDunningDetail saasCreditDunningDetail) {
        if (saasCreditDunningDetail == null) {
            return null;
        }
        SaasCreditDunningDetailVo saasCreditDunningDetailVo = new SaasCreditDunningDetailVo();
        BeanUtils.copyProperties(saasCreditDunningDetail, saasCreditDunningDetailVo);
        saasCreditDunningDetailVo.setSaasCreditDunningDetailId(saasCreditDunningDetail.getId());
        return saasCreditDunningDetailVo;
    }

    public static SaasCreditDunningDetail convertVOToEntity(SaasCreditDunningDetailVo saasCreditDunningDetailVo) {
        if (saasCreditDunningDetailVo == null) {
            return null;
        }
        SaasCreditDunningDetail saasCreditDunningDetail = new SaasCreditDunningDetail();
        BeanUtils.copyProperties(saasCreditDunningDetailVo, saasCreditDunningDetail);
        saasCreditDunningDetail.setId(saasCreditDunningDetailVo.getSaasCreditDunningDetailId());
        return saasCreditDunningDetail;
    }

}
