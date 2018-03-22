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
    private String applyType;

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

}