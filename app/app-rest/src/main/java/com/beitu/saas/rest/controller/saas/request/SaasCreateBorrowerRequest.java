package com.beitu.saas.rest.controller.saas.request;

import com.beitu.saas.app.enums.VerifyCodeErrorCodeEnum;
import com.beitu.saas.common.utils.MobileUtil;
import com.fqgj.common.api.ParamsObject;
import com.fqgj.common.api.exception.ApiIllegalArgumentException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author linanjun
 * @create 2018/3/22 上午11:37
 * @description
 */
@ApiModel(description = "SAAS 新增 借款人")
public class SaasCreateBorrowerRequest extends ParamsObject {

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @ApiModelProperty(value = "客户姓名")
    @NotBlank(message = "客户姓名不能为空")
    private String name;

    @ApiModelProperty(value = "客户身份证号")
    @NotBlank(message = "客户身份证号不能为空")
    private String identityCode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    @Override
    public void validate() {
        if (!MobileUtil.isMobile(mobile)) {
            throw new ApiIllegalArgumentException(VerifyCodeErrorCodeEnum.NOT_MOBILE);
        }
    }

}