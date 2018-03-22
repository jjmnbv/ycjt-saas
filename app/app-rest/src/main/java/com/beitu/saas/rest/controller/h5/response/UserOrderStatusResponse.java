package com.beitu.saas.rest.controller.h5.response;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/22 下午8:33
 * @description
 */
@ApiModel(value = "用户订单状态信息")
public class UserOrderStatusResponse implements ResponseData {

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
}
