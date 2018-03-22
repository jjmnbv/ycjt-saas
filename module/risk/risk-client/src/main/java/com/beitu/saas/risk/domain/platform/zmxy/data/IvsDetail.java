package com.beitu.saas.risk.domain.platform.zmxy.data;

import java.io.Serializable;

/**
 * @Author 柳朋朋
 * @Create 2016-10-24 22:21
 */
public class IvsDetail implements Serializable {
    //风险因素code
    private String code;
    //风险描述说明
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
