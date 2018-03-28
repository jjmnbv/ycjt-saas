package com.beitu.saas.app.application.order.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author linanjun
 * @create 2018/3/28 上午10:49
 * @description
 */
public class QueryOrderBillDetailVo {

    @ApiModelProperty(value = "机构CODE")
    private String merchantCode;

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

    @ApiModelProperty(value = "还款时长")
    private Integer repaymentDuration;

    @ApiModelProperty(value = "还款结束时间")
    private Date repaymentEndDate;

    @ApiModelProperty(value = "应还日期枚举")
    private Integer queryRepaymentDtKey;

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
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

    public Integer getRepaymentDuration() {
        return repaymentDuration;
    }

    public void setRepaymentDuration(Integer repaymentDuration) {
        this.repaymentDuration = repaymentDuration;
    }

    public Date getRepaymentEndDate() {
        return repaymentEndDate;
    }

    public void setRepaymentEndDate(Date repaymentEndDate) {
        this.repaymentEndDate = repaymentEndDate;
    }

    public Integer getQueryRepaymentDtKey() {
        return queryRepaymentDtKey;
    }

    public void setQueryRepaymentDtKey(Integer queryRepaymentDtKey) {
        this.queryRepaymentDtKey = queryRepaymentDtKey;
    }
}
