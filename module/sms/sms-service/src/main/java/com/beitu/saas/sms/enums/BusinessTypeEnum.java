package com.beitu.saas.sms.enums;

public enum BusinessTypeEnum {

    XJD(1, "现金贷"),
    HZD(2, "合作贷"),
    QLZD(3, "钱粒账单"),
    YCJT(4, "洋葱借条");

    private Integer code;
    private String desc;

    BusinessTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getBusinessTypeName(int value) {
        for (BusinessTypeEnum em : BusinessTypeEnum.values()) {
            if (em.getCode() == value) {
                return em.getDesc();
            }
        }
        return null;
    }

}
