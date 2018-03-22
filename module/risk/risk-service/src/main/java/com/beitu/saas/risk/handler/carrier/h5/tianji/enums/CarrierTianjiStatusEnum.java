package com.beitu.saas.risk.handler.carrier.h5.tianji.enums;

/**
 * Created by zwh @yuntu-inc.com
 *
 * @description
 * @create 2017/11/2 0002 下午 12:06
 */
public enum CarrierTianjiStatusEnum {
    INIT("init", "初始化"),
    LOGIN("login", "登录成功"),
    LOGIN_FAIL("login_fail", "登录失败"),
    CRAWL("crawl", "抓取成功"),
    CRAWL_FAIL("crawl_fail", "抓取失败"),
    REPORT("report", "生成报告成功"),
    REPORT_FAIL("report_fail", "生成报告失败");

    private String type;
    private String desc;

    CarrierTianjiStatusEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public CarrierTianjiStatusEnum setType(String type) {
        this.type = type;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public CarrierTianjiStatusEnum setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public static CarrierTianjiStatusEnum getEnumByType(String type) {
        for (CarrierTianjiStatusEnum item: CarrierTianjiStatusEnum.values()) {
            if (item.type.equals(type)) {
                return item;
            }
        }
        return null;
    }
}
