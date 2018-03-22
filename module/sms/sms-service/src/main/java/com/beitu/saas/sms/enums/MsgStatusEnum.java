package com.beitu.saas.sms.enums;

public enum MsgStatusEnum {

    WAIT_FOR_SEND(0, "待发送"),
    SENDED(1, "已发送"),
    SEND_FAIL(2, "发送失败"),
    SUCCESS(3, "发送成功并已确认(push已读)");

    private Integer code;

    private String message;

    MsgStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String getMessageByCode(Integer code) {
        MsgStatusEnum[] msgStatusEnums = MsgStatusEnum.values();
        for (MsgStatusEnum msgStatusEnum : msgStatusEnums) {
            if (msgStatusEnum.getCode() == code) {
                return msgStatusEnum.getMessage();
            }
        }
        return null;
    }
}
