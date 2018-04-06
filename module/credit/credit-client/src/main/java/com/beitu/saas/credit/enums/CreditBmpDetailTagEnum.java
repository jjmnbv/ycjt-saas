package com.beitu.saas.credit.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/14
 * Time: 下午7:49
 */
public enum CreditBmpDetailTagEnum {
    BANK(1, "银行"),
    INSURANCE(2, "保险"),
    STOCK(3, "证券"),
    INTERNET_FINANCE(4, "互联网金融"),
    LOAN(5, "贷款"),
    FINANCIAL_MANAGEMENT(6, "理财"),
    GAMBLING(7, "赌博"),
    PAWN(8, "典当"),
    COURT(10, "法院检察院"),
    BUREAU(11, "公安局派出所"),
    LAWYER(12, "律师"),
    ALTERNATE(16, "小号"),
    CREDIT_CARD(17, "信用卡热线"),
    CASH_CREDIT(20, "现金贷"),
    OVERALL_CONSUMER_FINANCE(21, "综合消费金融"),
    DECORATION_INSTALLMENT(22, "装修分期"),
    RENT_INSTALLMENT(23, "租房分期"),
    TRAVEL_INSTALLMENT(24, "旅游分期"),
    EDUCATION_INSTALLMENT(25, "教育分期"),
    EB_CONSUMER_FINANCE(26, "电商消费金融"),
    SCHOOL_INSTALLMENT(27, "校园分期"),
    SCHOOL_LOAN(28, "校园贷款"),
    COSMETIC_SURGERY_INSTALLMENT(29, "医美分期"),
    AUTO_FINANCE(30, "汽车金融"),
    WEDDING_SERVICE_INSTALLMENT(31, "婚庆分期"),
    CORPORATE_LOAN(32, "企业贷款");
    
    private Integer type;
    
    private String desc;
    
    CreditBmpDetailTagEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
    
    public Integer getType() {
        return type;
    }
    
    public CreditBmpDetailTagEnum setType(Integer type) {
        this.type = type;
        return this;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public CreditBmpDetailTagEnum setDesc(String desc) {
        this.desc = desc;
        return this;
    }
    
    public static CreditBmpDetailTagEnum getEnumByType(Integer type) {
        CreditBmpDetailTagEnum creditBmpDetailTagEnum = null;
        for (CreditBmpDetailTagEnum e : CreditBmpDetailTagEnum.values()) {
            if (e.getType().equals(type)) {
                creditBmpDetailTagEnum = e;
            }
        }
        return creditBmpDetailTagEnum;
    }
    
    public static List<CreditBmpDetailTagEnum> getConcernTags() {
        List<CreditBmpDetailTagEnum> tagEnumList = new ArrayList<>();
        tagEnumList.add(BANK);
        tagEnumList.add(INTERNET_FINANCE);
        tagEnumList.add(LOAN);
        tagEnumList.add(GAMBLING);
        tagEnumList.add(PAWN);
        tagEnumList.add(COURT);
        tagEnumList.add(BUREAU);
        tagEnumList.add(LAWYER);
        tagEnumList.add(CREDIT_CARD);
        return tagEnumList;
    }
    
}
