package com.beitu.saas.rest.controller.auth.request;

import com.beitu.saas.common.utils.MobileUtil;
import com.fqgj.common.api.ParamsObject;
import com.fqgj.common.api.exception.ApiIllegalArgumentException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xiaochong
 * @create 2018/3/24 下午3:52
 * @description
 */
@ApiModel
public class AddAdminRequest extends ParamsObject {

    @ApiModelProperty("手机号码")
    private String mobile;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("职位")
    private String job;

    @ApiModelProperty("角色id")
    private Long roleId;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public void validate() {
        if (!MobileUtil.isMobile(mobile)){
            throw new ApiIllegalArgumentException("请输入正确号码");
        }

    }
}
