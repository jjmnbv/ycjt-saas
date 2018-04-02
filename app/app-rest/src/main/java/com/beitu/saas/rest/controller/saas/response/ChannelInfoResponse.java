package com.beitu.saas.rest.controller.saas.response;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/29 下午6:28
 * @description
 */
@ApiModel(value = "渠道展示信息")
public class ChannelInfoResponse implements ResponseData {

    @ApiModelProperty(value = "头部提示")
    private String headerTitle;

    @ApiModelProperty(value = "图片提示")
    private String picTitle;

    public ChannelInfoResponse(String headerTitle, String picTitle) {
        this.headerTitle = headerTitle;
        this.picTitle = picTitle;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public String getPicTitle() {
        return picTitle;
    }

    public void setPicTitle(String picTitle) {
        this.picTitle = picTitle;
    }

}
