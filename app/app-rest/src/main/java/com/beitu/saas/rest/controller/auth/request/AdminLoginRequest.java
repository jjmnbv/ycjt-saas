package com.beitu.saas.rest.controller.auth.request;

import com.beitu.saas.app.annotations.BeanFieldValidator;
import com.beitu.saas.common.enums.ValidateFieldEnum;
import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author xiaochong
 * @create 2018/3/22 下午4:03
 * @description
 */
@ApiModel
public class AdminLoginRequest extends ParamsObject {

    @ApiModelProperty("手机号码")
    @NotBlank(message = "手机号不能为空")
    @BeanFieldValidator(type = ValidateFieldEnum.MOBILE, message = "请输入正确手机号")
    private String mobile;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty("手机验证码")
    private String verifyCode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Override
    public void validate() {
    }
}
