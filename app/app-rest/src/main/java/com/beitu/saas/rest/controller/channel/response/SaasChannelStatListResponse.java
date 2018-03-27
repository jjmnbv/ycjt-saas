package com.beitu.saas.rest.controller.channel.response;

import com.beitu.saas.channel.vo.ChannelStatVo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/2/1
 * Time: 上午10:08
 */
@ApiModel(value = "渠道统计列表")
public class SaasChannelStatListResponse implements ResponseData {

    private List<ChannelStatVo> channelVos = new ArrayList<>();

    public SaasChannelStatListResponse(List<ChannelStatVo> channelVos) {
        this.channelVos = channelVos;
    }

    public List<ChannelStatVo> getChannelVos() {
        return channelVos;
    }

    public SaasChannelStatListResponse setChannelVos(List<ChannelStatVo> channelVos) {
        this.channelVos = channelVos;
        return this;
    }
}
