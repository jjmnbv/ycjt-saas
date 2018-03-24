package com.beitu.saas.rest.controller.channel.response;

import com.beitu.saas.collection.domain.SaasChannelVo;
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
@ApiModel(value = "渠道列表")
public class SaasChannelListResponse implements ResponseData {

    private List<SaasChannelVo> channelVos = new ArrayList<>();

    public SaasChannelListResponse(List<SaasChannelVo> channelVos) {
        this.channelVos = channelVos;
    }

    public List<SaasChannelVo> getChannelVos() {
        return channelVos;
    }

    public SaasChannelListResponse setChannelVos(List<SaasChannelVo> channelVos) {
        this.channelVos = channelVos;
        return this;
    }
}
