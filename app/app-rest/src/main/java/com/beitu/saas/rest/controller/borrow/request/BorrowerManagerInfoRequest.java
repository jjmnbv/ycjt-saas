package com.beitu.saas.rest.controller.borrow.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author linanjun
 * @create 2018/3/21 下午2:31
 * @description
 */
@ApiModel(description = "用户信息")
public class BorrowerManagerInfoRequest extends ParamsObject {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "身份证")
    private String identityCode;

    @ApiModelProperty(value = "渠道号")
    private String channelCode;

    public String getName() {
        return name;
    }

    public BorrowerManagerInfoRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public BorrowerManagerInfoRequest setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public BorrowerManagerInfoRequest setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
        return this;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public BorrowerManagerInfoRequest setChannelCode(String channelCode) {
        this.channelCode = channelCode;
        return this;
    }

    @Override
    public void validate() {

    }

}