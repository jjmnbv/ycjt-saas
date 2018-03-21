package com.beitu.saas.common.utils.excel.enums;


import com.fqgj.common.api.enums.MsgCodeEnum;

public enum ExportFileMsgCodeEnum implements MsgCodeEnum {

    NO_PERMISSION(0, "没有权限进行此操作"),
    UNDER_DUE_ORDER(1, "订单未逾期，不允许导出"),
    NO_CARRIER_DATA(2, "没有运营商信息可导出"),
    NO_CONTACT_DATA(3, "没有通讯录信息可导出"),
    IS_RUNNING(4, "系统正在导出，请稍候"),
    EXPORT_ERROR(5, "导出失败，请联系客服");

    private Integer code;

    private String msg;

    ExportFileMsgCodeEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

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

}
