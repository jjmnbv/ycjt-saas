package com.beitu.saas.merchant.client.enums;

import java.util.*;

/**
 * @author xiaochong
 * @create 2018/4/9 下午12:02
 * @description
 */
public enum MerchantFlowNumEnum {
    FLOW_100(1L, 100L),
    FLOW_200(2L, 200L),
    FLOW_300(3L, 300L),
    FLOW_400(4L, 400L),
    FLOW_500(5L, 500L),;
    private Long key;
    private Long value;

    MerchantFlowNumEnum(Long key, Long value) {
        this.key = key;
        this.value = value;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public static Long getKeyByValue(Long value) {
        for (MerchantFlowNumEnum item : MerchantFlowNumEnum.values()) {
            if (item.value.equals(value)) {
                return item.getKey();
            }
        }
        return null;
    }

    public static List getEnumMap() {
        List list = new ArrayList();
        for (MerchantFlowNumEnum item : MerchantFlowNumEnum.values()) {
            list.add(new HashMap() {{
                put("key", item.getKey());
                put("value", item.getValue());
            }});
        }
        return list;
    }

    public static Boolean hasValue(Long value) {
        for (MerchantFlowNumEnum item : MerchantFlowNumEnum.values()) {
            if (item.value.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
