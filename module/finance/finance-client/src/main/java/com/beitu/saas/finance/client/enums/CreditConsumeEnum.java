package com.beitu.saas.finance.client.enums;


/**
 * @author xiaochong
 * @create 2018/4/2 下午11:07
 * @description
 */
public enum CreditConsumeEnum {

    FLOW_SHARED_610_DOWN(6, "流量-共享&芝麻分610以下"),
    FLOW_SHARED_610_UP(8, "流量-共享&芝麻分610以上"),
    FLOW_ALONE_610_DOWN(16, "流量-买断&芝麻分610以下"),
    FLOW_ALONE_610_UP(20, "流量-买断&芝麻分610以上"),
    FLOW_REPORT(4, "流量-信用报告"),
    RISK_CARRIER(1, "风控-运营商"),
    RISK_LOAN_BLACKLIST(1, "风控-网贷黑名单"),
    RISK_MULTI_PLATFORM_LENDING(0, "风控-多平台借贷"),
    RISK_ESIGN(1, "风控-电子签签署"),
    RISK_DIANHUABANG(1, "风控-催收分"),
    RISK_REALNAME(1, "风控-实名认证"),;

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
