package com.beitu.saas.order.enums;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/28
 * Time: 下午2:44
 */
public enum DashboarTypeEnum {
    NO_REPAY(0, "待还款"),
    OVERDUE(1, "逾期");

    private Integer type;

    private String desc;

    DashboarTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public DashboarTypeEnum setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public DashboarTypeEnum setDesc(String desc) {
        this.desc = desc;
        return this;
    }
}
