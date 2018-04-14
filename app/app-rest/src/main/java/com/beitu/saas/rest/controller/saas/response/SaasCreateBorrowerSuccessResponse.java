package com.beitu.saas.rest.controller.saas.response;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/22 上午11:39
 * @description
 */
@ApiModel(value = "SAAS创建借款人成功信息")
public class SaasCreateBorrowerSuccessResponse implements ResponseData {

    @ApiModelProperty(value = "借款人CODE")
    private String borrowerCode;

    public SaasCreateBorrowerSuccessResponse(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }

    public String getBorrowerCode() {
        return borrowerCode;
    }

    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }
}