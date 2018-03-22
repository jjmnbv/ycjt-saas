package com.beitu.saas.rest.controller.borrow.response;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/21 下午2:26
 * @description
 */
@ApiModel(value = "借款人用户登录成功信息")
public class BorrowUserLoginSuccessResponse implements ResponseData {

    @ApiModelProperty(value = "列表信息")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
