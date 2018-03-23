package com.beitu.saas.channel.enums;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/23
 * Time: 上午11:45
 */
public enum  CollectionOrderStatusEnum {
    OPEN(0, "未核销"),
    CLOSE(1, "已核销");

    private Integer type;

    private String desc;

    CollectionOrderStatusEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public CollectionOrderStatusEnum setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public CollectionOrderStatusEnum setDesc(String desc) {
        this.desc = desc;
        return this;
    }
}
