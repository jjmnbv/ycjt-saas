package com.beitu.saas.rest.controller.credit.request;

import com.fqgj.common.api.ParamsObject;
import com.fqgj.common.api.exception.ApiIllegalArgumentException;
import com.fqgj.common.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author linanjun
 * @create 2018/4/4 上午11:10
 * @description
 */
@ApiModel(description = "风控信息查询")
public class CreditQueryRequest extends ParamsObject {

    @ApiModelProperty(value = "订单号")
    private String orderNumb;

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

    @Override
    public void validate() {
        if (StringUtils.isEmpty(orderNumb)&&StringUtils.isEmpty(borrowerCode)){
            throw new ApiIllegalArgumentException("请求参数非法");
        }
    }

}