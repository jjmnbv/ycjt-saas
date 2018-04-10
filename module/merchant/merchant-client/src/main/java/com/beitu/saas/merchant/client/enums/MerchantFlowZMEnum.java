package com.beitu.saas.merchant.client.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author xiaochong
 * @create 2018/4/9 下午1:26
 * @description
 */
public enum MerchantFlowZMEnum {
    ZM_610_DOWN(1L, "610分以下(8点券/单)"),
    ZM_610_UP(2L, "610(含)分以上(10点券/单)");
    private Long key;
    private String value;

    MerchantFlowZMEnum(Long key, String value) {
        this.key = key;
        this.value = value;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getValueByKey(Long value) {
        for (MerchantFlowZMEnum item : MerchantFlowZMEnum.values()) {
            if (item.value.equals(value)) {
                return item.getValue();
            }
        }
        return null;
    }

    public static Boolean hasKey(Long key) {
        for (MerchantFlowZMEnum item : MerchantFlowZMEnum.values()) {
            if (item.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public static List getEnumMap() {
        List list = new ArrayList();
        for (MerchantFlowZMEnum item : MerchantFlowZMEnum.values()) {
            list.add(new HashMap() {{
                put("key", item.getKey());
                put("value", item.getValue());
            }});
        }
        return list;
    }
}
