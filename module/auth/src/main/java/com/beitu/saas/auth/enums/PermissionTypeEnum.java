package com.beitu.saas.auth.enums;

/**
 * @author xiaochong
 * @create 2018/3/23 下午4:09
 * @description
 */
public enum PermissionTypeEnum {

    MENU_PERMISSION(1,"菜单权限"),
    BUTTON_PERMISSION(2,"按钮权限");
    private Integer key;
    private String value;

    PermissionTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
