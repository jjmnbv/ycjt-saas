package com.beitu.saas.rest.controller.auth.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xiaochong
 * @create 2018/3/24 下午5:32
 * @description
 */
@ApiModel
public class ResetPasswordRequest extends ParamsObject {

    @ApiModelProperty("账户id")
    private Long adminId;

    @ApiModelProperty("密码")
    private String password;

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void validate() {

    }
}
