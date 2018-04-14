package com.beitu.saas.rest.controller.auth.request;

import com.fqgj.common.api.ParamsObject;

/**
 * @author xiaochong
 * @create 2018/3/24 下午5:45
 * @description
 */
public class UpdateAdminRequest extends ParamsObject {

    private String job;

    private Long roleId;

    private String name;

    private Long adminId;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    @Override
    public void validate() {

    }
}
