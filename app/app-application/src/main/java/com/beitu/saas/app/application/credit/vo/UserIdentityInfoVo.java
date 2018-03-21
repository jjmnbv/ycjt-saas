package com.beitu.saas.app.application.credit.vo;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/21 下午8:17
 * @description
 */
@ApiModel(value = "用户身份信息模块")
public class UserIdentityInfoVo implements ResponseData {

    @ApiModelProperty(value = "身份证正面")
    private String userFrontUrl;

    @ApiModelProperty(value = "身份证反面")
    private String userBackUrl;

    @ApiModelProperty(value = "手持身份证")
    private String userHoldUrl;

    public String getUserFrontUrl() {
        return userFrontUrl;
    }

    public void setUserFrontUrl(String userFrontUrl) {
        this.userFrontUrl = userFrontUrl;
    }

    public String getUserBackUrl() {
        return userBackUrl;
    }

    public void setUserBackUrl(String userBackUrl) {
        this.userBackUrl = userBackUrl;
    }

    public String getUserHoldUrl() {
        return userHoldUrl;
    }

    public void setUserHoldUrl(String userHoldUrl) {
        this.userHoldUrl = userHoldUrl;
    }
}
