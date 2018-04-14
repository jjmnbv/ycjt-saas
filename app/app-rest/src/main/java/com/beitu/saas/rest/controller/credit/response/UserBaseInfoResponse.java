package com.beitu.saas.rest.controller.credit.response;

import com.beitu.saas.app.application.credit.vo.*;
import com.beitu.saas.app.application.order.vo.OrderApplicationListVo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/21 下午10:05
 * @description
 */
@ApiModel(value = "借款人用户基本信息")
public class UserBaseInfoResponse implements ResponseData {

    @ApiModelProperty(value = "申请信息")
    private OrderApplicationListVo orderApplicationListVo;

    @ApiModelProperty(value = "个人信息")
    private BorrowerPersonalInfoVo borrowerPersonalInfoVo;

    @ApiModelProperty(value = "身份信息")
    private BorrowerIdentityInfoVo borrowerIdentityInfoVo;

    @ApiModelProperty(value = "工作信息")
    private BorrowerWorkInfoVo borrowerWorkInfoVo;

    @ApiModelProperty(value = "紧急联系人")
    private BorrowerEmergentContactVo userEmergentContactVo;

    @ApiModelProperty(value = "生活区域")
    private BorrowerLivingAreaVo borrowerLivingAreaVo;

    public UserBaseInfoResponse(OrderApplicationListVo orderApplicationListVo, BorrowerPersonalInfoVo borrowerPersonalInfoVo, BorrowerIdentityInfoVo borrowerIdentityInfoVo, BorrowerWorkInfoVo borrowerWorkInfoVo, BorrowerEmergentContactVo userEmergentContactVo, BorrowerLivingAreaVo borrowerLivingAreaVo) {
        this.orderApplicationListVo = orderApplicationListVo;
        this.borrowerPersonalInfoVo = borrowerPersonalInfoVo;
        this.borrowerIdentityInfoVo = borrowerIdentityInfoVo;
        this.borrowerWorkInfoVo = borrowerWorkInfoVo;
        this.userEmergentContactVo = userEmergentContactVo;
        this.borrowerLivingAreaVo = borrowerLivingAreaVo;
    }

    public OrderApplicationListVo getOrderApplicationListVo() {
        return orderApplicationListVo;
    }

    public void setOrderApplicationListVo(OrderApplicationListVo orderApplicationListVo) {
        this.orderApplicationListVo = orderApplicationListVo;
    }

    public BorrowerPersonalInfoVo getBorrowerPersonalInfoVo() {
        return borrowerPersonalInfoVo;
    }

    public void setBorrowerPersonalInfoVo(BorrowerPersonalInfoVo borrowerPersonalInfoVo) {
        this.borrowerPersonalInfoVo = borrowerPersonalInfoVo;
    }

    public BorrowerIdentityInfoVo getBorrowerIdentityInfoVo() {
        return borrowerIdentityInfoVo;
    }

    public void setBorrowerIdentityInfoVo(BorrowerIdentityInfoVo borrowerIdentityInfoVo) {
        this.borrowerIdentityInfoVo = borrowerIdentityInfoVo;
    }

    public BorrowerWorkInfoVo getBorrowerWorkInfoVo() {
        return borrowerWorkInfoVo;
    }

    public void setBorrowerWorkInfoVo(BorrowerWorkInfoVo borrowerWorkInfoVo) {
        this.borrowerWorkInfoVo = borrowerWorkInfoVo;
    }

    public BorrowerEmergentContactVo getUserEmergentContactVo() {
        return userEmergentContactVo;
    }

    public void setUserEmergentContactVo(BorrowerEmergentContactVo userEmergentContactVo) {
        this.userEmergentContactVo = userEmergentContactVo;
    }

    public BorrowerLivingAreaVo getBorrowerLivingAreaVo() {
        return borrowerLivingAreaVo;
    }

    public void setBorrowerLivingAreaVo(BorrowerLivingAreaVo borrowerLivingAreaVo) {
        this.borrowerLivingAreaVo = borrowerLivingAreaVo;
    }

}
