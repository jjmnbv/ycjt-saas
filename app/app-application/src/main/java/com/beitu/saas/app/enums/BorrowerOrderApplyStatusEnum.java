package com.beitu.saas.app.enums;

/**
 * @author linanjun
 * @create 2018/3/22 下午8:35
 * @description
 */
public enum BorrowerOrderApplyStatusEnum {

    NO_SUBMIT(1, "未提交"),
    REVIEWING(2, "审核中"),
    REFUSE(3, "驳回"),
    FINISHED(4, "完成借款");

    BorrowerOrderApplyStatusEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

    private Integer type;

    private String value;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
