package com.beitu.saas.rest.controller.h5.response;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/22 下午4:32
 * @description
 */
@ApiModel(value = "风控模块申请信息")
public class CreditApplyInfoResponse implements ResponseData {

    @ApiModelProperty(value = "申请状态")
    private String applyType;

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

}