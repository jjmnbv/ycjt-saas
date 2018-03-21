package com.fqgj.youjie.auth.domain;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/14
 * Time: 下午4:05
 */
public class Permission {

    private Long id;

    private Long menuId;

    private String name;

    private String permission;

    private String description;

    public Permission() {
    }

    public Permission(Long id, Long menuId, String permission) {
        this.id = id;
        this.menuId = menuId;
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public Permission setName(String name) {
        this.name = name;
        return this;
    }

    public Permission setId(Long id) {
        this.id = id;
        return this;
    }

    public Permission setMenuId(Long menuId) {
        this.menuId = menuId;
        return this;
    }

    public Permission setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public Permission setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Long getMenuId() {
        return menuId;
    }

    public String getPermission() {
        return permission;
    }

    public String getDescription() {
        return description;
    }
}
