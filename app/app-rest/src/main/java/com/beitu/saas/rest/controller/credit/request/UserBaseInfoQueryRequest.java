package com.beitu.saas.rest.controller.credit.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author linanjun
 * @create 2018/3/21 下午10:09
 * @description
 */
@ApiModel(description = "借款人基本信息查询")
public class UserBaseInfoQueryRequest extends ParamsObject {

    @ApiModelProperty(value = "订单CODE", required = true)
    @NotBlank(message = "订单CODE不能为空")
    private String orderCode;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    @Override
    public void validate() {

    }

}