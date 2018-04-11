package com.beitu.saas.credit.entity;

import com.fqgj.common.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.667
 * TableDesc:用户催收数据详情表
 */
public class SaasCreditDunningDetail extends BaseEntity {
    /**
     * 用户催收数据查询表ID
     */
    private Long recordId;
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


    public Long getRecordId() {
        return this.recordId;
    }

    public SaasCreditDunningDetail setRecordId(Long recordId) {
        this.recordId = recordId;
        return this;
    }

    public Integer getCallTelTotalNums() {
        return this.callTelTotalNums;
    }

    public SaasCreditDunningDetail setCallTelTotalNums(Integer callTelTotalNums) {
        this.callTelTotalNums = callTelTotalNums;
        return this;
    }

    public Integer getCallTotalTimes() {
        return this.callTotalTimes;
    }

    public SaasCreditDunningDetail setCallTotalTimes(Integer callTotalTimes) {
        this.callTotalTimes = callTotalTimes;
        return this;
    }

    public Integer getCallOutTimes() {
        return this.callOutTimes;
    }

    public SaasCreditDunningDetail setCallOutTimes(Integer callOutTimes) {
        this.callOutTimes = callOutTimes;
        return this;
    }

    public Integer getCallInTimes() {
        return this.callInTimes;
    }

    public SaasCreditDunningDetail setCallInTimes(Integer callInTimes) {
        this.callInTimes = callInTimes;
        return this;
    }

    public Integer getCallTotalDuration() {
        return this.callTotalDuration;
    }

    public SaasCreditDunningDetail setCallTotalDuration(Integer callTotalDuration) {
        this.callTotalDuration = callTotalDuration;
        return this;
    }

    public BigDecimal getCallAvgDuration() {
        return this.callAvgDuration;
    }

    public SaasCreditDunningDetail setCallAvgDuration(BigDecimal callAvgDuration) {
        this.callAvgDuration = callAvgDuration;
        return this;
    }

    public Integer getCallOutDuration() {
        return this.callOutDuration;
    }

    public SaasCreditDunningDetail setCallOutDuration(Integer callOutDuration) {
        this.callOutDuration = callOutDuration;
        return this;
    }

    public Integer getCallInDuration() {
        return this.callInDuration;
    }

    public SaasCreditDunningDetail setCallInDuration(Integer callInDuration) {
        this.callInDuration = callInDuration;
        return this;
    }

    public Integer getCallDurationBelow15() {
        return this.callDurationBelow15;
    }

    public SaasCreditDunningDetail setCallDurationBelow15(Integer callDurationBelow15) {
        this.callDurationBelow15 = callDurationBelow15;
        return this;
    }

    public Integer getCallDurationBetween15and30() {
        return this.callDurationBetween15and30;
    }

    public SaasCreditDunningDetail setCallDurationBetween15and30(Integer callDurationBetween15and30) {
        this.callDurationBetween15and30 = callDurationBetween15and30;
        return this;
    }

    public Integer getCallDurationAbove60() {
        return this.callDurationAbove60;
    }

    public SaasCreditDunningDetail setCallDurationAbove60(Integer callDurationAbove60) {
        this.callDurationAbove60 = callDurationAbove60;
        return this;
    }

    public String getFirstCallTime() {
        return this.firstCallTime;
    }

    public SaasCreditDunningDetail setFirstCallTime(String firstCallTime) {
        this.firstCallTime = firstCallTime;
        return this;
    }

    public String getLastCallTime() {
        return this.lastCallTime;
    }

    public SaasCreditDunningDetail setLastCallTime(String lastCallTime) {
        this.lastCallTime = lastCallTime;
        return this;
    }

    public Integer getType() {
        return this.type;
    }

    public SaasCreditDunningDetail setType(Integer type) {
        this.type = type;
        return this;
    }
}
