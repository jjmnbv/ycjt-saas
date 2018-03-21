package com.fqgj.youjie.auth.enums;

/**
 * @ClassName:
 * @Description:
 * @Author yuyanqi
 * @Create 2017/3/21 0021 下午 2:44
 */
public enum RoleKeyEnum {

    SUPERADMIN("SUPERADMIN","超级管理员"),
    COLLECT_QS("COLLECT_QS", "前手"),
    COLLECT_MS("COLLECT_MS", "中手"),
    COLLECT_HS("COLLECT_HS", "后手"),
    AUDITOR("AUDITOR", "信审人员");

    private String type;

    private String desc;

    RoleKeyEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public RoleKeyEnum setType(String type) {
        this.type = type;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public RoleKeyEnum setDesc(String desc) {
        this.desc = desc;
        return this;
    }


    public static RoleKeyEnum getEnumByType(String type) {
        RoleKeyEnum RoleKeyEnum = null;
        for (RoleKeyEnum e : RoleKeyEnum.values()) {
            if (e.getType().equals(type)) {
                RoleKeyEnum = e;
            }
        }

        return RoleKeyEnum;
    }
}
