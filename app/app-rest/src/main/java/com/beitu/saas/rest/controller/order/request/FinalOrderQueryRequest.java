package com.beitu.saas.rest.controller.order.request;

import com.beitu.saas.channel.consts.ChannelConsts;
import com.beitu.saas.order.consts.OrderConsts;
import com.beitu.saas.order.enums.OrderStatusEnum;
import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author linanjun
 * @create 2018/3/27 下午3:26
 * @description
 */
@ApiModel(description = "复审列表查询参数")
public class FinalOrderQueryRequest extends ParamsObject {

    @ApiModelProperty(value = "处理状态(1为待处理，2为已领取)")
    @NotNull(message = "处理状态不能为空")
    private Integer dealStatus;

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

    public Integer getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(Integer dealStatus) {
        this.dealStatus = dealStatus;
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
        if (!ChannelConsts.DEFAULT_ALL_CHANNEL_CODE.equals(channelCode)) {
            this.channelCode = channelCode;
        }
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        if (!OrderConsts.DEFAULT_ALL_ORDER_STATUS_CODE.equals(orderStatus)) {
            this.orderStatus = orderStatus;
        }
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

    @Override
    public void validate() {

    }

}