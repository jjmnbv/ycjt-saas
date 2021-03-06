package com.beitu.saas.rest.controller.credit.request;

import com.beitu.saas.app.enums.SaasLoanPlatformEnum;
import com.beitu.saas.borrower.enums.BorrowerErrorCodeEnum;
import com.fqgj.common.api.ParamsObject;
import com.fqgj.common.api.exception.ApiIllegalArgumentException;
import com.fqgj.common.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author linanjun
 * @create 2018/3/21 下午10:09
 * @description
 */
@ApiModel(description = "借款人多平台信息查询")
public class CreditLoanPlatformRequest extends ParamsObject {
    
    @ApiModelProperty(value = "订单号")
    private String orderNumb;
    
    @ApiModelProperty(value = "借款平台类型", required = true)
    @NotNull(message = "借款平台类型不能为空")
    private Integer platform;

    private String borrowerCode;

    public String getBorrowerCode() {
        return borrowerCode;
    }

    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }


    public String getOrderNumb() {
        return orderNumb;
    }
    
    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
    }
    
    public Integer getPlatform() {
        return platform;
    }
    
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }
    
    @Override
    public void validate() {
        if (SaasLoanPlatformEnum.getByCode(platform) == null) {
            throw new ApiIllegalArgumentException(BorrowerErrorCodeEnum.ILLEGAL_LOAN_PLATFORM_TYPE);
        }
        if (StringUtils.isEmpty(orderNumb)&&StringUtils.isEmpty(borrowerCode)){
            throw new ApiIllegalArgumentException("请求参数非法");
        }
    }
    
}