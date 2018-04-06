package com.beitu.saas.credit.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/14
 * Time: 下午7:49
 */
public enum CreditDunningDetailTypeEnum {
    OVER_VIEW(10, "总览催收"),
    OVER_VIEW_NOT_SURE(11, "总览疑似催收"),
    LAST_WEEK(20, "近一周催收"),
    LAST_WEEK_NOT_SURE(21, "近一周疑似催收"),
    LAST_TWO_WEEK(30, "近两周催收"),
    LAST_TWO_WEEK_NOT_SURE(31, "近两周疑似催收"),
    LAST_THREE_WEEK(40, "近三周催收"),
    LAST_THREE_WEEK_NOT_SURE(41, "近三周疑似催收"),
    DAYS_30(50, "近30天催收"),
    DAYS_30_NOT_SURE(51, "近30天疑似催收"),
    DAYS_30_60(60, "近30至60天催收"),
    DAYS_30_60_NOT_SURE(61, "近30至60天疑似催收"),
    DAYS_60_90(70, "近60至90天催收"),
    DAYS_60_90_NOT_SURE(71, "近60至90天疑似催收");
    
    private Integer type;
    
    private String desc;
    
    CreditDunningDetailTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
    
    public Integer getType() {
        return type;
    }
    
    public CreditDunningDetailTypeEnum setType(Integer type) {
        this.type = type;
        return this;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public CreditDunningDetailTypeEnum setDesc(String desc) {
        this.desc = desc;
        return this;
    }
    
    public static CreditDunningDetailTypeEnum getEnumByType(Integer type) {
        CreditDunningDetailTypeEnum creditDunningDetailTypeEnum = null;
        for (CreditDunningDetailTypeEnum e : CreditDunningDetailTypeEnum.values()) {
            if (e.getType().equals(type)) {
                creditDunningDetailTypeEnum = e;
            }
        }
        return creditDunningDetailTypeEnum;
    }
}
