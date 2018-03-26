package com.beitu.saas.rest.controller.channel.response;

import com.beitu.saas.channel.domain.SaasChannelDetailVo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/26
 * Time: 上午11:15
 */
@ApiModel(value = "渠道详情")
public class SaasChannelDetailResponse implements ResponseData {
    private SaasChannelDetailVo saasChannelDetailVo;

    public SaasChannelDetailResponse(SaasChannelDetailVo saasChannelDetailVo) {
        this.saasChannelDetailVo = saasChannelDetailVo;
    }

    public SaasChannelDetailVo getSaasChannelDetailVo() {
        return saasChannelDetailVo;
    }

    public SaasChannelDetailResponse setSaasChannelDetailVo(SaasChannelDetailVo saasChannelDetailVo) {
        this.saasChannelDetailVo = saasChannelDetailVo;
        return this;
    }
}
