package com.beitu.saas.common.utils.excel.enums;

public enum ExportFileInfoTypeEnum {

    CARRIER_INFO(0, "运营商通话记录"),
    CONTACT_INFO(1, "通讯录信息");

    private Integer code;

    private String text;

    ExportFileInfoTypeEnum(Integer code, String text){
        this.code = code;
        this.text = text;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
