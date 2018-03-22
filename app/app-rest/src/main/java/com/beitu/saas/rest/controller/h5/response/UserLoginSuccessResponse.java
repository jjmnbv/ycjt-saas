package com.beitu.saas.rest.controller.h5.response;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/22 上午11:39
 * @description
 */
@ApiModel(value = "用户登录成功信息")
public class UserLoginSuccessResponse implements ResponseData {

    @ApiModelProperty(value = "用户TOKEN")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}