package com.beitu.saas.collection.enums;

/**
 * @ClassName:
 * @Description:逾期时长
 * @Author watson
 * @Create 2017/5/3 0003下午 2:20
 */
public enum OverdueTimeEnum {
    ALL(0, 0, 0, "全部"),
    TODAY(1, 0, 1, "今日到期"),
    THREE_DAY(2, 2, 3, "逾期3天"),
    SEVEN_DAY(3, 6, 7, "逾期7天"),
    ONE_TO_FIFTEEN(4, 10, 15, "逾期10-15天"),
    FIFTEEN_TO_THIRTY(5, 15, 30, "逾期15-30天"),
    THIRTY_TO_SIXTY(6, 30, 60, "逾期30-60天"),
    SIXTY_TO_NINETY(7, 60, 90, "逾期60-90天"),
    MORE_THAN_NINETY(8, 90, 10000, "逾期90天以上");

    private Integer type;

    private Integer start;

    private Integer end;

    private String desc;

    OverdueTimeEnum(Integer type, Integer start, Integer end, String desc) {
        this.type = type;
        this.start = start;
        this.end = end;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public static OverdueTimeEnum getEnum(Integer type) {
        for (OverdueTimeEnum item : OverdueTimeEnum.values()) {
            if (type == 0) {
                return null;
            } else if (item.type.equals(type)) {
                return item;
            }
        }
        return null;
    }
}
