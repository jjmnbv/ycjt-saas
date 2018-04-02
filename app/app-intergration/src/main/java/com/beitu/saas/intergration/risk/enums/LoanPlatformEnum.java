package com.beitu.saas.intergration.risk.enums;

public enum LoanPlatformEnum {

    JIN_JIE_DAO(1, "jinjiedao", "今借到"),
    MI_FANG(2, "mifang", "米房"),
    JIE_DAI_BAO(3, "jiedaibao", "借贷宝"),
    WU_YOU_JIE_TIAO(4, "wuyoujietiao", "无忧借条");

    private Integer code;

    private String website;

    private String desc;

    LoanPlatformEnum(Integer code, String website, String desc) {
        this.code = code;
        this.website = website;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getWebsite() {
        return website;
    }

    public String getDesc() {
        return desc;
    }

    public static LoanPlatformEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (LoanPlatformEnum platformEnum : LoanPlatformEnum.values()) {
            if (platformEnum.getCode().equals(code)) {
                return platformEnum;
            }
        }
        return null;
    }

}
