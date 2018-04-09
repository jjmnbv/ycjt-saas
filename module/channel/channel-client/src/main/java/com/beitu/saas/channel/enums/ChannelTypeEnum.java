package com.beitu.saas.channel.enums;

import com.beitu.saas.channel.domain.DefaultChannelInfoVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 下午2:22
 */
public enum ChannelTypeEnum {
    USER_DEFINED(0, "自定义渠道"),
    SYSTEM_DEFINED(1, "后台新建渠道"),
    RECOMMEND_DEFINED(2, "推荐流量渠道");

    private Integer type;

    private String desc;

    ChannelTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public ChannelTypeEnum setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public ChannelTypeEnum setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public static List<DefaultChannelInfoVo> getDefaultChannelList() {
        List<DefaultChannelInfoVo> defaultChannelList = new ArrayList<>();
        for (ChannelTypeEnum e : ChannelTypeEnum.values()) {
            if (e.getType() > 0) {
                DefaultChannelInfoVo defaultChannelInfoParam = new DefaultChannelInfoVo()
                        .setChannelType(e.getType())
                        .setChanelName(e.name());
                defaultChannelList.add(defaultChannelInfoParam);
            }
        }
        return defaultChannelList;
    }
}
