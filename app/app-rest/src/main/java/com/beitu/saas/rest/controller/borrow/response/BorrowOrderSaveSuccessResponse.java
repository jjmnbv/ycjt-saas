package com.beitu.saas.rest.controller.borrow.response;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/21 下午3:08
 * @description
 */
@ApiModel(value = "借款人订单保存成功信息")
public class BorrowOrderSaveSuccessResponse implements ResponseData {

    @ApiModelProperty(value = "订单CODE")
    private String orderCode;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}