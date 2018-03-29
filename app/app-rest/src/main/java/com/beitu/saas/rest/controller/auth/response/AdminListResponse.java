package com.beitu.saas.rest.controller.auth.response;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xiaochong
 * @create 2018/3/24 下午6:43
 * @description
 */
@ApiModel
public class AdminListResponse implements ResponseData {

    @ApiModelProperty("用户id")
    private Long adminId;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("职位")
    private String job;

    @ApiModelProperty("启用")
    private Boolean enable;

    @ApiModelProperty("角色名")
    private String roleName;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("角色创建人")
    private String createName;

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

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
}
