package com.beitu.saas.risk.domain.carrier.h5.enums;

/**
 * Created by zwh @yuntu-inc.com
 *
 * @description
 * @create 2017/10/31 0031 下午 4:03
 */
public enum CarrierH5StatusEnum {
    DOING("DOING", "正在爬取中"),
    DONE_FAIL("DONE_FAIL", "爬取失败"),
    DONE_SUCCESS("DONE_SUCCESS", "爬取成功");

    private String name;
    private String desc;

    CarrierH5StatusEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public static CarrierH5StatusEnum getEnumByName(String name) {
        for (CarrierH5StatusEnum item: CarrierH5StatusEnum.values()) {
            if (item.name.equals(name)) {
                return item;
            }
        }
        return null;
    }
}
