package com.beitu.saas.credit.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/2/27
 * Time: 下午1:15
 * 实名认证类型
 */
public enum CreditAuthTypeEnum {
    ZM(1, "芝麻信用");

    private Integer type;

    private String desc;

    CreditAuthTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public CreditAuthTypeEnum setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public CreditAuthTypeEnum setDesc(String desc) {
        this.desc = desc;
        return this;
    }


    public static CreditAuthTypeEnum getEnumByType(Integer type) {
        CreditAuthTypeEnum creditAuthTypeEnum = null;
        for (CreditAuthTypeEnum authTypeEnum : CreditAuthTypeEnum.values()) {
            if (authTypeEnum.getType().equals(type)) {
                creditAuthTypeEnum = authTypeEnum;
            }
        }

        return creditAuthTypeEnum;
    }
}
