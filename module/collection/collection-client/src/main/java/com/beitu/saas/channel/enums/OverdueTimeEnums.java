package com.beitu.saas.channel.enums;

/**
 * @ClassName:
 * @Description:逾期时长
 * @Author watson
 * @Create 2017/5/3 0003下午 2:20
 */
public enum OverdueTimeEnums {
    TODAY(1, 0, 1, "1"),
    THREE_DAY(2, 2, 3, "3天"),
    SEVEN_DAY(3, 6, 7, "7天"),
    ONE_TO_FIFTEEN(4, 0, 15, "0-15天"),
    FIFTEEN_TO_THIRTY(5, 15, 30, "15-30天"),
    THIRTY_TO_SIXTY(6, 30, 60, "30-60天"),
    SIXTY_TO_NINETY(7, 60, 90, "60-90天");

    private Integer type;

    private Integer start;

    private Integer end;

    private String desc;

    OverdueTimeEnums(Integer type, Integer start, Integer end, String desc) {
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


    public static OverdueTimeEnums getEnum(Integer type) {
        for (OverdueTimeEnums item : OverdueTimeEnums.values()) {
            if (item.type.equals(type)) {
                return item;
            }
        }
        return null;
    }
}
