package com.beitu.saas.app.application.order.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author linanjun
 * @create 2018/3/27 下午3:07
 * @description
 */
public class QueryOrderVo {

    @ApiModelProperty(value = "机构CODE")
    private String merchantCode;

    @ApiModelProperty(value = "审核员CODE")
    private String reviewerCode;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "客户姓名")
    private String userName;

    @ApiModelProperty(value = "身份证号")
    private String identityCode;

    @ApiModelProperty(value = "渠道")
    private String channelCode;

    @ApiModelProperty(value = "状态")
    private Integer orderStatus;

    @ApiModelProperty(value = "申请时长")
    private Integer applyDuration;

    @ApiModelProperty(value = "申请结束时间")
    private Date applyEndDate;

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getReviewerCode() {
        return reviewerCode;
    }

    public void setReviewerCode(String reviewerCode) {
        this.reviewerCode = reviewerCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getApplyDuration() {
        return applyDuration;
    }

    public void setApplyDuration(Integer applyDuration) {
        this.applyDuration = applyDuration;
    }

    public Date getApplyEndDate() {
        return applyEndDate;
    }

    public void setApplyEndDate(Date applyEndDate) {
        this.applyEndDate = applyEndDate;
    }
}
