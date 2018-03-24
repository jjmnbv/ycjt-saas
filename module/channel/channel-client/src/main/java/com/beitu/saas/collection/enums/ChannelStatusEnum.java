package com.beitu.saas.collection.enums;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 下午2:22
 */
public enum ChannelStatusEnum {
    OPEN(0, "开启"),
    CLOSE(1, "关闭");

    private Integer type;

    private String desc;

    ChannelStatusEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public ChannelStatusEnum setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public ChannelStatusEnum setDesc(String desc) {
        this.desc = desc;
        return this;
    }
}
