package com.beitu.saas.finance.client.enums;

/**
 * @author xiaochong
 * @create 2018/3/29 下午8:38
 * @description
 */
public enum OpTypeEnum {

    EXPENDITURE(0, "支出"),
    INCOME(1, "收入");

    private Integer key;

    private String value;

    OpTypeEnum(Integer key, String value) {
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
