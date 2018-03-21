package com.fqgj.youjie.auth.domain;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/13
 * Time: 下午4:28
 */
public class Menu {
    private Long menuId;

    private Boolean isParent=false;

    private Long parentId;

    private String key;

    private String value;

    public Menu() {
    }

    public Menu(Long menuId, String key, String value) {
        this.menuId = menuId;
        this.key = key;
        this.value = value;
    }

    public Menu(Long menuId, Boolean isParent, String key, String value) {
        this.menuId = menuId;
        this.isParent = isParent;
        this.key = key;
        this.value = value;
    }

    public Menu setMenuId(Long menuId) {
        this.menuId = menuId;
        return this;
    }

    public Menu setParent(Boolean parent) {
        isParent = parent;
        return this;
    }

    public Menu setKey(String key) {
        this.key = key;
        return this;
    }

    public Menu setValue(String value) {
        this.value = value;
        return this;
    }


    public Long getMenuId() {
        return menuId;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Boolean getParent() {
        return isParent;
    }

    public Long getParentId() {
        return parentId;
    }

    public Menu setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }


}
