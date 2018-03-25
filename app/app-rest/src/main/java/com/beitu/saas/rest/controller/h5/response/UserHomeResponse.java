package com.beitu.saas.rest.controller.h5.response;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/22 上午11:43
 * @description
 */
@ApiModel(value = "用户首页信息")
public class UserHomeResponse implements ResponseData {

    @ApiModelProperty(value = "申请状态")
    private Integer applyType;

    @ApiModelProperty(value = "头部提示")
    private String headerTitle;

    @ApiModelProperty(value = "图片提示")
    private String picTitle;

    public UserHomeResponse(Integer applyType, String headerTitle, String picTitle) {
        this.applyType = applyType;
        this.headerTitle = headerTitle;
        this.picTitle = picTitle;
    }

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
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