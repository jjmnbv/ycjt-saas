package com.beitu.saas.rest.controller.order.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * @author linanjun
 * @create 2018/3/27 下午3:26
 * @description
 */
@ApiModel(description = "贷后管理列表查询参数")
public class AfterLendOrderQueryRequest extends ParamsObject {

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

    @Override
    public void validate() {

    }

}