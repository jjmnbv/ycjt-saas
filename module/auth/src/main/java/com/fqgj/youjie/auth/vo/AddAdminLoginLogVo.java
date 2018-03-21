package com.fqgj.youjie.auth.vo;

/**
 * @author linanjun
 * @create 2017/12/29 上午11:29
 * @description
 */
public class AddAdminLoginLogVo {

    /**
     * 管理员id
     */
    private Long adminId;
    /**
     * 登录IP
     */
    private String loginIp;
    /**
     * 登录详细地址
     */
    private String loginIpAddress;

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginIpAddress() {
        return loginIpAddress;
    }

    public void setLoginIpAddress(String loginIpAddress) {
        this.loginIpAddress = loginIpAddress;
    }

}
