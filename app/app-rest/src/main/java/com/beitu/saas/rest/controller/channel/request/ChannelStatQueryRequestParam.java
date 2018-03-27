package com.beitu.saas.rest.controller.channel.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/24
 * Time: 下午1:36
 */
@ApiModel(description = "渠道统计列表查询请求参数")
public class ChannelStatQueryRequestParam extends ParamsObject {
    @ApiModelProperty(value = "渠道创建开始日期")
    private String createStartDate;
    @ApiModelProperty(value = "渠道创建结束日期")
    private String createEndDate;  //当前天数
    @ApiModelProperty(value = "渠道号")
    private String merchantCode;

    public String getCreateStartDate() {
        return createStartDate;
    }

    public ChannelStatQueryRequestParam setCreateStartDate(String createStartDate) {
        this.createStartDate = createStartDate;
        return this;
    }

    public String getCreateEndDate() {
        return createEndDate;
    }

    public ChannelStatQueryRequestParam setCreateEndDate(String createEndDate) {
        this.createEndDate = createEndDate;
        return this;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public ChannelStatQueryRequestParam setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
        return this;
    }

    @Override
    public void validate() {

    }
}
