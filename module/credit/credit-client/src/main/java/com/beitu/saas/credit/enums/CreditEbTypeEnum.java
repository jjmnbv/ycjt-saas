package com.beitu.saas.credit.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/14
 * Time: 下午7:49
 */
public enum CreditEbTypeEnum {
    TAOBAO(0, "淘宝"),
    JD(2, "京东");
    
    private Integer type;
    
    private String desc;
    
    CreditEbTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
    
    public Integer getType() {
        return type;
    }
    
    public CreditEbTypeEnum setType(Integer type) {
        this.type = type;
        return this;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public CreditEbTypeEnum setDesc(String desc) {
        this.desc = desc;
        return this;
    }
    
    public static CreditEbTypeEnum getEnumByType(Integer type) {
        CreditEbTypeEnum creditEbTypeEnum = null;
        for (CreditEbTypeEnum e : CreditEbTypeEnum.values()) {
            if (e.getType().equals(type)) {
                creditEbTypeEnum = e;
            }
        }
        return creditEbTypeEnum;
    }
    
}
