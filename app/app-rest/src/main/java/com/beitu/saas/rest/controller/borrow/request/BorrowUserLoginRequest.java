package com.beitu.saas.rest.controller.borrow.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author linanjun
 * @create 2018/3/21 下午2:24
 * @description
 */

@ApiModel(description = "借款人用户登录信息")
public class BorrowUserLoginRequest extends ParamsObject {

    @ApiModelProperty(value = "手机号码", required = true)
    @NotBlank(message = "手机号码不能为空")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public void validate() {

    }

}
