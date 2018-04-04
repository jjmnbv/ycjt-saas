package com.beitu.saas.rest.controller.contract.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author linanjun
 * @create 2018/4/2 下午5:03
 * @description
 */
@ApiModel(value = "授权协议查看请求参数")
public class UserLicenseContractInfoRequest extends ParamsObject {

    @ApiModelProperty(value = "TOKEN")
    @NotBlank(message = "TOKEN不能为空")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public void validate() {

    }
}
