package com.beitu.saas.rest.controller.h5.response;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/23 下午5:03
 * @description
 */
@ApiModel(value = "风控模块申请信息保存成功返回结果")
public class CreditApplyInfoSaveSuccessResponse implements ResponseData {

    @ApiModelProperty(value = "订单号")
    private String orderNumb;

    public CreditApplyInfoSaveSuccessResponse(String orderNumb) {
        this.orderNumb = orderNumb;
    }

    public String getOrderNumb() {
        return orderNumb;
    }

    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
    }

}