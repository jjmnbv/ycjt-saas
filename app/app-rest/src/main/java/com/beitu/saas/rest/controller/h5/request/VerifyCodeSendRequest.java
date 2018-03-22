package com.beitu.saas.rest.controller.h5.request;

import com.beitu.saas.app.enums.VerifyCodeErrorCodeEnum;
import com.beitu.saas.common.utils.MobileUtil;
import com.fqgj.common.api.ParamsObject;
import com.fqgj.common.api.exception.ApiIllegalArgumentException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author linanjun
 * @create 2018/3/22 上午11:07
 * @description
 */
@ApiModel(description = "借款人短信发送")
public class VerifyCodeSendRequest extends ParamsObject {

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public void validate() {
        if (!MobileUtil.isMobile(mobile)) {
            throw new ApiIllegalArgumentException(VerifyCodeErrorCodeEnum.NOT_MOBILE);
        }
    }
}