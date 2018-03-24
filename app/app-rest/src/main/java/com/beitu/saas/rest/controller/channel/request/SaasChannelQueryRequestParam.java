package com.beitu.saas.rest.controller.channel.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 下午3:22
 */
@ApiModel(description = "渠道列表查询请求参数")
public class SaasChannelQueryRequestParam extends ParamsObject {

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

    public String getMerchantCode() {
        return merchantCode;
    }

    public SaasChannelQueryRequestParam setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
        return this;
    }

    public String getChannelName() {
        return channelName;
    }

    public SaasChannelQueryRequestParam setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public SaasChannelQueryRequestParam setChannelCode(String channelCode) {
        this.channelCode = channelCode;
        return this;
    }

    public Integer getChannelStatus() {
        return channelStatus;
    }

    public SaasChannelQueryRequestParam setChannelStatus(Integer channelStatus) {
        this.channelStatus = channelStatus;
        return this;
    }

    public String getChargePerson() {
        return chargePerson;
    }

    public SaasChannelQueryRequestParam setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
        return this;
    }

    @Override
    public void validate() {

    }
}
