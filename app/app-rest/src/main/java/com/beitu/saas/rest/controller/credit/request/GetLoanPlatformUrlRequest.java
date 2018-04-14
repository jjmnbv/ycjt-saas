package com.beitu.saas.rest.controller.credit.request;

import com.beitu.saas.app.enums.SaasLoanPlatformEnum;
import com.beitu.saas.borrower.enums.BorrowerErrorCodeEnum;
import com.fqgj.common.api.ParamsObject;
import com.fqgj.common.api.exception.ApiIllegalArgumentException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author linanjun
 * @create 2018/3/30 下午4:36
 * @description
 */
@ApiModel(description = "得到多平台借贷链接请求参数")
public class GetLoanPlatformUrlRequest extends ParamsObject {

    @ApiModelProperty(value = "借贷平台类型")
    @NotNull(message = "借贷平台类型不能为空")
    private Integer loanPlatformType;

    @ApiModelProperty(value = "浏览器类型(2为微信)")
    private Integer type;

    public Integer getLoanPlatformType() {
        return loanPlatformType;
    }

    public void setLoanPlatformType(Integer loanPlatformType) {
        this.loanPlatformType = loanPlatformType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public void validate() {
        if (SaasLoanPlatformEnum.getByCode(loanPlatformType) == null) {
            throw new ApiIllegalArgumentException(BorrowerErrorCodeEnum.ILLEGAL_LOAN_PLATFORM_TYPE);
        }
    }
}
