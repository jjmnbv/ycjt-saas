package com.beitu.saas.rest.controller.order.request;

import com.beitu.saas.channel.consts.ChannelConsts;
import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/27 下午3:26
 * @description
 */
@ApiModel(description = "逾期管理列表查询参数")
public class OverdueOrderQueryRequest extends ParamsObject {

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "客户姓名")
    private String userName;

    @ApiModelProperty(value = "身份证号")
    private String identityCode;

    @ApiModelProperty(value = "渠道")
    private String channelCode;

    @ApiModelProperty(value = "应还日期枚举")
    private Integer queryRepaymentDtKey;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        if (!ChannelConsts.DEFAULT_ALL_CHANNEL_CODE.equals(channelCode)) {
            this.channelCode = channelCode;
        }
    }

    public Integer getQueryRepaymentDtKey() {
        return queryRepaymentDtKey;
    }

    public void setQueryRepaymentDtKey(Integer queryRepaymentDtKey) {
        this.queryRepaymentDtKey = queryRepaymentDtKey;
    }

    @Override
    public void validate() {

    }

}