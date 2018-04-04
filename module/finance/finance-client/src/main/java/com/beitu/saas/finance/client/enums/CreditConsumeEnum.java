package com.beitu.saas.finance.client.enums;


/**
 * @author xiaochong
 * @create 2018/4/2 下午11:07
 * @description
 */
public enum CreditConsumeEnum {

    FLOW_SHARED(1, "流量-共享抢单"),
    FLOW_ALONE(1, "流量-买断抢单"),
    RISK_CARRIER(1, "风控-运营商"),
    RISK_LOAN_BLACKLIST(1, "风控-网贷黑名单"),
    FLOW_MULTI_PLATFORM_LENDING(1, "风控-多平台借贷"),
    ESIGN(1, " 电子签签署");

    private Integer num;

    private String desc;

    CreditConsumeEnum(Integer num, String desc) {
        this.num = num;
        this.desc = desc;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
