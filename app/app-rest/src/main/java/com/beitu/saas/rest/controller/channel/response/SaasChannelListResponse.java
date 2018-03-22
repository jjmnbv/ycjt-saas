package com.beitu.saas.rest.controller.channel.response;

import com.beitu.saas.channel.domain.SaasChannelVo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
    @ApiModelProperty(value = "是否有下一页", required = true)
    private Boolean hasNextPage;

    @ApiModelProperty(value = "当前页码")
    private Integer currentPage;
    private List<SaasChannelVo> channelVos = new ArrayList<>();

    public SaasChannelListResponse(Boolean hasNextPage, Integer currentPage, List<SaasChannelVo> channelVos) {
        this.hasNextPage = hasNextPage;
        this.currentPage = currentPage;
        this.channelVos = channelVos;
    }

    public Boolean getHasNextPage() {
        return hasNextPage;
    }

    public SaasChannelListResponse setHasNextPage(Boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
        return this;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public SaasChannelListResponse setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public List<SaasChannelVo> getChannelVos() {
        return channelVos;
    }

    public SaasChannelListResponse setChannelVos(List<SaasChannelVo> channelVos) {
        this.channelVos = channelVos;
        return this;
    }
}
