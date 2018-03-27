package com.beitu.saas.app.enums;

/**
 * @author xiaochong
 * @create 2018/3/27 上午10:42
 * @description
 */
public enum ButtonPermissionEnum {

    SET_FLOW_CONDITIONS("0fbfccb158665dec", "设置流量条件"),
    CONFIGURE_GRAB_MODE("65b318b3e14c9203", "配置抢单模式"),
    RECOMMEND_GRAB("1c41fe92257a4391", "推荐借款抢单"),
    RECOMMEND_REJECT("3ea3d82389e321eb", "推荐借款拒绝"),

    FIRST_REVIEW_RECEIVE("65b318b3e14c9203", "初审领单"),
    FIRST_REVIEW_PASS("6ef62ed1a2e1990d", "通过初审"),
    FIRST_REVIEW_REBUT("b33ee3ff7b07ec29", "初审驳回"),
    FIRST_REVIEW_REJECT("1d5f845c6ab50db6", "初审拒绝"),
    FIRST_REVIEW_REMARKS("6794a13f529b2411", "初审修改备注"),

    SECOND_REVIEW_RECEIVE("6cc2a1ba1803375f", "复审领单"),
    SECOND_REVIEW_PASS("ad302d1e8e83e9f1", "通过复审"),
    SECOND_REVIEW_REBUT("cebe6217cf187f95", "复审驳回"),
    SECOND_REVIEW_REJECT("6ed77f1f1616105e", "复审拒绝"),
    SECOND_REVIEW_REMARKS("3df6d133c3d13d16", "复审修改备注"),

    LOAN("d65f90acd53638dc", "放款"),
    LOAN_REJECT("32ccce885767854b", "待放款拒绝"),
    LOAN_REMARKS("7cb256651ababa31", "待放款修改备注"),

    EXTEND("3e391493f0db0d23", "展期"),
    DESTROY("647cfa2931f5b1e7", "核销"),

    ENTRUSTED_COLLECTION("6536825c103e0c23", "委托催收"),

    add_collection_remarks("db66ddd9ce7f037f", "添加催收记录");
    private String key;
    private String value;

    ButtonPermissionEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
