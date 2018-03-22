package com.beitu.saas.rest.controller.channel.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 下午3:22
 */
@ApiModel(description = "渠道列表查询请求参数")
public class ChannelQueryRequestVo extends ParamsObject {
    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    @NotNull(message = "页码不得为空")
    private Integer pageNo;

    @ApiModelProperty(value = "机构号")
    private String merchantCode;

    @ApiModelProperty(value = "渠道名称")
    private String channelName;

    @ApiModelProperty(value = "渠道号")
    private String channelCode;

    @ApiModelProperty(value = "渠道状态(0-开启,1-关闭)")
    private Integer channelStatus;

    @ApiModelProperty(value = "负责人")
    private String chargePerson;

    public Integer getPageNo() {
        return pageNo;
    }

    public ChannelQueryRequestVo setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public ChannelQueryRequestVo setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
        return this;
    }

    public String getChannelName() {
        return channelName;
    }

    public ChannelQueryRequestVo setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public ChannelQueryRequestVo setChannelCode(String channelCode) {
        this.channelCode = channelCode;
        return this;
    }

    public Integer getChannelStatus() {
        return channelStatus;
    }

    public ChannelQueryRequestVo setChannelStatus(Integer channelStatus) {
        this.channelStatus = channelStatus;
        return this;
    }

    public String getChargePerson() {
        return chargePerson;
    }

    public ChannelQueryRequestVo setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
        return this;
    }

    @Override
    public void validate() {

    }
}
