package com.beitu.saas.app.enums;

import com.fqgj.common.api.enums.MsgCodeEnum;

/**
 * @author linanjun
 * @create 2018/3/31 下午3:09
 * @description
 */
public enum H5OrderDetailButtonTypeEnum implements MsgCodeEnum {

    CONFIRM_RECEIPT_BUTTON_TYPE(1, "查看并签署电子借款合同", "确认收款页面按钮"),
    CONFIRM_EXTEND_BUTTON_TYPE(2, "查看并签署电子展期合同", "确认展期页面按钮");

    H5OrderDetailButtonTypeEnum(Integer code, String msg, String desc) {
        this.code = code;
        this.msg = msg;
        this.desc = desc;
    }

    private Integer code;

    private String msg;

    private String desc;


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


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static H5OrderDetailButtonTypeEnum getByCode(Integer code) {
        for (H5OrderDetailButtonTypeEnum typeEnum : H5OrderDetailButtonTypeEnum.values()) {
            if (typeEnum.getCode().equals(code)) {
                return typeEnum;
            }
        }
        return null;
    }

}