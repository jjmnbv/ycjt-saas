package com.beitu.saas.channel.domain;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/4/9
 * Time: 下午9:29
 */
public class DefaultChannelInfoVo {
    private Integer channelType;
    private String chanelName;

    public Integer getChannelType() {
        return channelType;
    }

    public DefaultChannelInfoVo setChannelType(Integer channelType) {
        this.channelType = channelType;
        return this;
    }

    public String getChanelName() {
        return chanelName;
    }

    public DefaultChannelInfoVo setChanelName(String chanelName) {
        this.chanelName = chanelName;
        return this;
    }
}
