package com.beitu.saas.credit.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/14
 * Time: 下午7:49
 */
public enum CreditContactRelationTypeEnum {

    RELATIVE_CONTACT(10, "亲戚"),
    RISK_CONTACT(20, "异常"),
    EMERGENCY_CONTACT(30, "紧急联系人");
    
    private Integer type;
    
    private String desc;
    
    CreditContactRelationTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
    
    public Integer getType() {
        return type;
    }
    
    public CreditContactRelationTypeEnum setType(Integer type) {
        this.type = type;
        return this;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public CreditContactRelationTypeEnum setDesc(String desc) {
        this.desc = desc;
        return this;
    }
    
    public static CreditContactRelationTypeEnum getEnumByType(Integer type) {
        CreditContactRelationTypeEnum creditCarrierRecordTypeEnum = null;
        for (CreditContactRelationTypeEnum e : CreditContactRelationTypeEnum.values()) {
            if (e.getType().equals(type)) {
                creditCarrierRecordTypeEnum = e;
            }
        }
        return creditCarrierRecordTypeEnum;
    }
}
