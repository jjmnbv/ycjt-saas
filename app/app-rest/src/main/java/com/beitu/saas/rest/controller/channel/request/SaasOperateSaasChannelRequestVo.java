package com.beitu.saas.rest.controller.channel.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/2/7
 * Time: 下午5:37
 */
@ApiModel(description = "操作代理功能入参")
public class SaasOperateSaasChannelRequestVo extends ParamsObject {

    @ApiModelProperty(value = "渠道号", required = true)
    @javax.validation.constraints.NotNull(message = "渠道号不能为空")
    private String channelCode;

    @NotBlank(message = "状态不能为空")
    @ApiModelProperty(value = "渠道状态: 0: 开启 1:关闭", required = true)
    private Integer status;

    public String getChannelCode() {
        return channelCode;
    }

    public SaasOperateSaasChannelRequestVo setChannelCode(String channelCode) {
        this.channelCode = channelCode;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public SaasOperateSaasChannelRequestVo setStatus(Integer status) {
        this.status = status;
        return this;
    }

    @Override
    public void validate() {

    }
}
