package com.beitu.saas.rest.controller.credit.response;

import com.beitu.saas.app.application.credit.vo.*;
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

    @ApiModelProperty(value = "个人信息")
    private UserPersonalInfoVo userPersonalInfoVo;

    @ApiModelProperty(value = "身份信息")
    private UserIdentityInfoVo userIdentityInfoVo;

    @ApiModelProperty(value = "工作信息")
    private UserWorkInfoVo userWorkInfoVo;

    @ApiModelProperty(value = "紧急联系人")
    private UserEmergentContactVo userEmergentContactVo;

    @ApiModelProperty(value = "生活区域")
    private UserLivingAreaVo userLivingAreaVo;

    public UserPersonalInfoVo getUserPersonalInfoVo() {
        return userPersonalInfoVo;
    }

    public void setUserPersonalInfoVo(UserPersonalInfoVo userPersonalInfoVo) {
        this.userPersonalInfoVo = userPersonalInfoVo;
    }

    public UserIdentityInfoVo getUserIdentityInfoVo() {
        return userIdentityInfoVo;
    }

    public void setUserIdentityInfoVo(UserIdentityInfoVo userIdentityInfoVo) {
        this.userIdentityInfoVo = userIdentityInfoVo;
    }

    public UserWorkInfoVo getUserWorkInfoVo() {
        return userWorkInfoVo;
    }

    public void setUserWorkInfoVo(UserWorkInfoVo userWorkInfoVo) {
        this.userWorkInfoVo = userWorkInfoVo;
    }

    public UserEmergentContactVo getUserEmergentContactVo() {
        return userEmergentContactVo;
    }

    public void setUserEmergentContactVo(UserEmergentContactVo userEmergentContactVo) {
        this.userEmergentContactVo = userEmergentContactVo;
    }

    public UserLivingAreaVo getUserLivingAreaVo() {
        return userLivingAreaVo;
    }

    public void setUserLivingAreaVo(UserLivingAreaVo userLivingAreaVo) {
        this.userLivingAreaVo = userLivingAreaVo;
    }

}
