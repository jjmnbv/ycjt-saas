package com.beitu.saas.credit.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/14
 * Time: 下午7:49
 */
public enum CreditCarrierRecordTypeEnum {

    HIGH_FREQ(10, "高频联系人记录"),
    MERCHANT(20, "商户通话记录"),
    ACTIVE_REGION(30, "通话活跃记录"),
    LONG_DURATION(40, "长时间联系人记录"),
    CONTACT_REGION(50, "联系人所在地记录"),
    FINANCIAL(60, "金融敏感标签信息");

    private Integer type;

    private String desc;

    CreditCarrierRecordTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public CreditCarrierRecordTypeEnum setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public CreditCarrierRecordTypeEnum setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public static CreditCarrierRecordTypeEnum getEnumByType(Integer type) {
        CreditCarrierRecordTypeEnum creditCarrierRecordTypeEnum = null;
        for (CreditCarrierRecordTypeEnum e : CreditCarrierRecordTypeEnum.values()) {
            if (e.getType().equals(type)) {
                creditCarrierRecordTypeEnum = e;
            }
        }
        return creditCarrierRecordTypeEnum;
    }
}
