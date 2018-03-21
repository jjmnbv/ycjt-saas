package com.fqgj.youjie.auth.domain;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/14
 * Time: 下午4:35
 */
public class AdminRole {

    private Long adminId;

    private Long roleId;

    public AdminRole(Long adminId, Long roleId) {
        this.adminId = adminId;
        this.roleId = roleId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public Long getRoleId() {
        return roleId;
    }
}
