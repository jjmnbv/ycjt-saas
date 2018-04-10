package com.beitu.saas.order.enums;

import com.fqgj.common.utils.DateUtil;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/28
 * Time: 下午2:44
 */
public enum OverdueDateTypeEnum {
    TODAY(0, "今日", DateUtil.getDate(new Date(), "yyyy-MM-dd")),
    TOMORROW(1, "明日", DateUtil.getDate(DateUtil.addDate(new Date(), 1), "yyyy-MM-dd"));

    private Integer type;

    private String desc;

    private String overdueDate;

    OverdueDateTypeEnum(Integer type, String desc, String overdueDate) {
        this.type = type;
        this.desc = desc;
        this.overdueDate = overdueDate;
    }

    public Integer getType() {
        return type;
    }

    public OverdueDateTypeEnum setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public OverdueDateTypeEnum setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String getOverdueDate() {
        return overdueDate;
    }

    public OverdueDateTypeEnum setOverdueDate(String overdueDate) {
        this.overdueDate = overdueDate;
        return this;
    }

    public static String getOverdueDate(int type) {
        OverdueDateTypeEnum overdueDateTypeEnum = null;
        for (OverdueDateTypeEnum e : OverdueDateTypeEnum.values()) {
            if (type == e.getType()) {
                overdueDateTypeEnum = e;
                return overdueDateTypeEnum.getOverdueDate();
            }
        }
        return null;
    }
}
