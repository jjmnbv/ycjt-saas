package com.beitu.saas.risk.domain.platform.zmxy.rong.info;

/**
 * Created by ykpbean kangping.ying@yuntu-inc.com
 *
 * @description
 * @create 2017-06-12 下午8:39
 */


public class ZmxyDetailsExtendInfo {

    private String description;
    private String key;
    private String value;

    public String getDescription() {
        return description;
    }

    public ZmxyDetailsExtendInfo setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getKey() {
        return key;
    }

    public ZmxyDetailsExtendInfo setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public ZmxyDetailsExtendInfo setValue(String value) {
        this.value = value;
        return this;
    }
}
