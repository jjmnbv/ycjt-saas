package com.beitu.saas.auth.enums;

/**
 * @author xiaochong
 * @create 2018/3/26 下午4:45
 * @description
 */
public enum ContractConfigTypeEnum {
    PERSONAL_CONTRACT(21, "个人合同"),
    COMPANY_CONTRACT(22, "公司合同");
    private Integer key;
    private String value;

    ContractConfigTypeEnum(int key, String value) {
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
