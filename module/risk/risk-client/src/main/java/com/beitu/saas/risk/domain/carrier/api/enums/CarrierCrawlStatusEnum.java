package com.beitu.saas.risk.domain.carrier.api.enums;


/**
 * @Author poyangchen
 * @Create 2017-04-05 上午11:30
 * @Description 对外api的统一状态枚举
 */
public enum CarrierCrawlStatusEnum {

    WAIT_CODE("WAIT_CODE", "等待输入短信验证码"),
    WAIT_IMG("WAIT_IMG", "等待输入图片验证码"),
    WAIT_CODE_IMG("WAIT_CODE_IMG", "等待输入图片和短信验证码"),
    DOING("DOING", "正在爬取中"),
    DONE_FAIL("DONE_FAIL", "爬取失败"),
    DONE_SUCCESS("DONE_SUCCESS", "爬取成功");

    private String type;

    private String desc;

    CarrierCrawlStatusEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public CarrierCrawlStatusEnum setType(String type) {
        this.type = type;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public CarrierCrawlStatusEnum setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public static CarrierCrawlStatusEnum getEnum(String type) {
        for (CarrierCrawlStatusEnum item : CarrierCrawlStatusEnum.values()) {
            if (item.type.equals(type)) {
                return item;
            }
        }
        return null;
    }

}
