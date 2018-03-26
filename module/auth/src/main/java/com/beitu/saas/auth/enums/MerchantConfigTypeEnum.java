package com.beitu.saas.auth.enums;

/**
 * @author xiaochong
 * @create 2018/3/26 下午4:58
 * @description
 */
public enum MerchantConfigTypeEnum {
    CONTRACT_CONFIG(1, "合同设置"),
    SMS_CONFIG(22, "短信设置"),
    PASSWORD_CONFIG(3, "密码设置");
    private Integer key;
    private String value;

    MerchantConfigTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
