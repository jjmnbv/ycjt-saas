package com.beitu.saas.rest.controller.order.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author linanjun
 * @create 2018/3/27 上午11:33
 * @description
 */
@ApiModel(description = "贷后管理订单展期操作")
public class AfterLendManagerDestroyOrderRequest extends ParamsObject {

    @ApiModelProperty(value = "订单号", required = true)
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