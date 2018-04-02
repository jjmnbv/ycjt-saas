package com.beitu.saas.rest.controller.auth.response;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xiaochong
 * @create 2018/3/31 下午7:08
 * @description
 */
@ApiModel
public class AdminDetailResponse implements ResponseData {

    @ApiModelProperty("用户id")
    private Long adminId;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("职位")
    private String job;

    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("角色名")
    private String roleName;

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
