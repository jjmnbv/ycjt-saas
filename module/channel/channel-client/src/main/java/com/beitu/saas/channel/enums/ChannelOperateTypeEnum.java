package com.beitu.saas.channel.enums;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 下午2:22
 */
public enum ChannelOperateTypeEnum {
    ADD(0, "创建"),
    UPDATE(1, "编辑");

    private Integer type;

    private String desc;

    ChannelOperateTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public ChannelOperateTypeEnum setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public ChannelOperateTypeEnum setDesc(String desc) {
        this.desc = desc;
        return this;
    }
}
