package com.beitu.saas.app.enums;

import com.fqgj.common.api.enums.MsgCodeEnum;

/**
 * @author linanjun
 * @create 2018/4/10 下午10:32
 * @description
 */
public enum H5BrowserTypeEnum implements MsgCodeEnum {

    OTHER(1, "其他浏览器"),
    WEIXIN(2, "微信");

    H5BrowserTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;

    private String msg;

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static H5BrowserTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (H5BrowserTypeEnum browserTypeEnum : H5BrowserTypeEnum.values()) {
            if (browserTypeEnum.getCode().equals(code)) {
                return browserTypeEnum;
            }
        }
        return null;
    }
}
