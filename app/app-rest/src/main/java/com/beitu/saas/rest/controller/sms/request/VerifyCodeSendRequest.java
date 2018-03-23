package com.beitu.saas.rest.controller.sms.request;

import com.beitu.saas.app.enums.VerifyCodeErrorCodeEnum;
import com.beitu.saas.common.utils.MobileUtil;
import com.beitu.saas.sms.enums.SmsErrorCodeEnum;
import com.beitu.saas.sms.enums.VerifyCodeTypeEnum;
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

    @NotBlank(message = "验证码类型不能为空")
    private String type;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void validate() {
        if (!MobileUtil.isMobile(mobile)) {
            throw new ApiIllegalArgumentException(VerifyCodeErrorCodeEnum.NOT_MOBILE);
        }
        if (!VerifyCodeTypeEnum.getNameList().contains(type)) {
            throw new ApiIllegalArgumentException(SmsErrorCodeEnum.VERIFYCODE_TYPE_NOT_EXIST);
        }
    }
}