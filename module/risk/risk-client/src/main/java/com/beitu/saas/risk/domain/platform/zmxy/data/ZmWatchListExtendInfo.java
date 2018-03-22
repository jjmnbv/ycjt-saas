package com.beitu.saas.risk.domain.platform.zmxy.data;

import java.io.Serializable;

/**
 * @Author 柳朋朋
 * @Create 2016-10-24 22:26
 */
public class ZmWatchListExtendInfo implements Serializable {
    //对应的字段名
    private String key;
    //对应的值
    private String value;
    //对于这个key的描述
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
