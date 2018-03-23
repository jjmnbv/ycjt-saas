package com.beitu.saas.rest.controller.h5.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/23 下午2:39
 * @description
 */
@ApiModel(description = "借款人查询风控模版列表参数")
public class QueryCreditModuleListRequest extends ParamsObject {

    @ApiModelProperty(value = "订单号")
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
