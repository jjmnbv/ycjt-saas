package com.beitu.saas.rest.controller.saas.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author linanjun
 * @create 2018/3/22 下午2:12
 * @description
 */
@ApiModel(description = "保存风控模块申请表信息")
public class SaasCreditSubmitRequest extends ParamsObject {

    @ApiModelProperty(value = "借款人CODE")
    @NotBlank(message = "借款人CODE不能为空")
    private String borrowerCode;

    public String getBorrowerCode() {
        return borrowerCode;
    }

    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }

    @Override
    public void validate() {

    }

}