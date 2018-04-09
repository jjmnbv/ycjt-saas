package com.beitu.saas.rest.controller.credit.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author linanjun
 * @create 2018/4/9 下午6:16
 * @description
 */
@ApiModel(description = "SAAS得到多平台借贷链接请求参数")
public class SaasGetLoanPlatformUrlRequest extends ParamsObject {

    @ApiModelProperty(value = "借贷平台类型")
    @NotNull(message = "借贷平台类型不能为空")
    private Integer loanPlatformType;

    @ApiModelProperty(value = "用户CODE")
    @NotBlank(message = "用户码不能为空")
    private String borrowerCode;

    public Integer getLoanPlatformType() {
        return loanPlatformType;
    }

    public void setLoanPlatformType(Integer loanPlatformType) {
        this.loanPlatformType = loanPlatformType;
    }

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
