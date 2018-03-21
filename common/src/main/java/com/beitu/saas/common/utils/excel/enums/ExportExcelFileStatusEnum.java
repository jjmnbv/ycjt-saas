package com.beitu.saas.common.utils.excel.enums;

public enum ExportExcelFileStatusEnum {

    NO_PERMISSION("INFO", "没有权限进行此操作"),
    UNDER_DUE_ORDER("INFO", "订单未逾期"),
    RUNNING("DOING", "系统正在导出，请稍候"),
    EXPORT_ERROR("ERROR", "导出失败，请联系客服"),
    EXPORT_SUCCESS("SUCC", "导出成功"); // text 中将保存路径

    private String code;

    private String text;

    ExportExcelFileStatusEnum(String code, String text){
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static ExportExcelFileStatusEnum getEnumByCode(String code) {
        ExportExcelFileStatusEnum exportExcelFileStatusEnum = null;
        for (ExportExcelFileStatusEnum e : ExportExcelFileStatusEnum.values()) {
            if (e.getCode().equalsIgnoreCase(code)) {
                exportExcelFileStatusEnum = e;
            }
        }
        return exportExcelFileStatusEnum;
    }
}
