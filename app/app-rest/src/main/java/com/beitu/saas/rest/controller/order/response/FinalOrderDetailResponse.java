package com.beitu.saas.rest.controller.order.response;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;

/**
 * @author linanjun
 * @create 2018/3/21 下午3:59
 * @description
 */
@ApiModel(value = "订单风控信息")
public class FinalOrderDetailResponse implements ResponseData {

    private String orderNumb;

    private Integer orderStatus;

    public String getOrderNumb() {
        return orderNumb;
    }

    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

}
