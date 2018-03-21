package com.fqgj.youjie.auth.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/11
 * Time: 下午3:51
 */
public enum AdminTypeEnum {
    normal(0, "普通用户"),
    internal(1, "内部用户"),
    collectionAdmin(2, "催收方"),
    collectionPerson(3, "催收员");

    private Integer type;

    private String desc;

    AdminTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public AdminTypeEnum setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public AdminTypeEnum setDesc(String desc) {
        this.desc = desc;
        return this;
    }


    public static AdminTypeEnum getEnumByType(Integer type) {
        AdminTypeEnum adminTypeEnum = null;
        for (AdminTypeEnum e : AdminTypeEnum.values()) {
            if (e.getType().equals(type)) {
                adminTypeEnum = e;
            }
        }

        return adminTypeEnum;
    }

}
