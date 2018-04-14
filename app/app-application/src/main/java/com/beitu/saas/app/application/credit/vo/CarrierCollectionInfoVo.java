package com.beitu.saas.app.application.credit.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/4/4 下午5:30
 * @description
 */
public class CarrierCollectionInfoVo {

    @ApiModelProperty(value = "近30个自然日被催收次数")
    private Integer collectionIn30Number;

    @ApiModelProperty(value = "近30-60个自然日被催收次数")
    private Integer collectionBetween30And60Number;

    @ApiModelProperty(value = "催收号码个数")
    private Integer collectionPhoneNumber;

    @ApiModelProperty(value = "呼入次数")
    private Integer callNumber;

    @ApiModelProperty(value = "呼出次数")
    private Integer calledNumber;

    @ApiModelProperty(value = "被叫时间")
    private Integer calledTime;

    @ApiModelProperty(value = "主叫时间")
    private Integer callTime;

    public Integer getCollectionIn30Number() {
        return collectionIn30Number;
    }

    public void setCollectionIn30Number(Integer collectionIn30Number) {
        this.collectionIn30Number = collectionIn30Number;
    }

    public Integer getCollectionBetween30And60Number() {
        return collectionBetween30And60Number;
    }

    public void setCollectionBetween30And60Number(Integer collectionBetween30And60Number) {
        this.collectionBetween30And60Number = collectionBetween30And60Number;
    }

    public Integer getCollectionPhoneNumber() {
        return collectionPhoneNumber;
    }

    public void setCollectionPhoneNumber(Integer collectionPhoneNumber) {
        this.collectionPhoneNumber = collectionPhoneNumber;
    }

    public Integer getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(Integer callNumber) {
        this.callNumber = callNumber;
    }

    public Integer getCalledNumber() {
        return calledNumber;
    }

    public void setCalledNumber(Integer calledNumber) {
        this.calledNumber = calledNumber;
    }

    public Integer getCalledTime() {
        return calledTime;
    }

    public void setCalledTime(Integer calledTime) {
        this.calledTime = calledTime;
    }

    public Integer getCallTime() {
        return callTime;
    }

    public void setCallTime(Integer callTime) {
        this.callTime = callTime;
    }

}