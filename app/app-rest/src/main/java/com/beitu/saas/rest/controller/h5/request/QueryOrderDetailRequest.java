package com.beitu.saas.rest.controller.h5.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author linanjun
 * @create 2018/3/25 下午1:55
 * @description
 */
@ApiModel(description = "借款人查询订单详情参数")
public class QueryOrderDetailRequest extends ParamsObject {

    @ApiModelProperty(value = "订单号")
    @NotBlank(message = "订单号不能为空")
    private String orderNumb;

    public String getOrderNumb() {
        return orderNumb;
    }

    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
    }

    @Override
    public void validate() {

    }
}
