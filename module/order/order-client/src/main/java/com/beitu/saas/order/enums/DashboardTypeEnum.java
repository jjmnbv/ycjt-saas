package com.beitu.saas.order.enums;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/28
 * Time: 下午2:44
 */
public enum DashboardTypeEnum {
    NO_REPAY(0, "待还款"),
    OVERDUE(1, "逾期");

    private Integer type;

    private String desc;

    DashboardTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public DashboardTypeEnum setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public DashboardTypeEnum setDesc(String desc) {
        this.desc = desc;
        return this;
    }
}
