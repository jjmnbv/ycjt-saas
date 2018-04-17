package com.beitu.saas.intergration.user.enums;

public enum UserMultiDebitStatusEnum {

    EFFECTIVE(0, "有效"),
    DISABLED(1, "失效");

    private Integer type;

    private String msg;

    UserMultiDebitStatusEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public Integer getType() {
        return type;
    }

    public UserMultiDebitStatusEnum setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public UserMultiDebitStatusEnum setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
