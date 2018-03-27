package com.beitu.saas.rest.controller.order.response;

import com.fqgj.common.api.ResponseData;

/**
 * @author linanjun
 * @create 2018/3/21 下午3:59
 * @description
 */

public class PreliminaryOrderDetailResponse implements ResponseData {

    private String orderNumb;

    public String getOrderNumb() {
        return orderNumb;
    }

    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
    }
}
