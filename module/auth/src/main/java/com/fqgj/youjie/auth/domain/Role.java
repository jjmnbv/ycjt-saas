package com.fqgj.youjie.auth.domain;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/14
 * Time: 下午1:17
 */
public class Role {
    private Long roleId;

    private String name;

    private String description;

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Role(Long roleId, String name, String description) {
        this.roleId = roleId;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getDescription() {
        return description;
    }
}
