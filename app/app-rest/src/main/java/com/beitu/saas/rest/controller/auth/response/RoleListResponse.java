package com.beitu.saas.rest.controller.auth.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xiaochong
 * @create 2018/3/24 下午1:53
 * @description
 */
@ApiModel
public class RoleListResponse {

    @ApiModelProperty("角色Id")
    private Long roleId;

    @ApiModelProperty("角色名")
    private String name;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("角色创建人")
    private String createName;

    @ApiModelProperty("是否启用")
    private Boolean enabled;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
