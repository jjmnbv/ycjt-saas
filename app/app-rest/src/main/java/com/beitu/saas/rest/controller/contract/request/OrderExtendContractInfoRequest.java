package com.beitu.saas.rest.controller.contract.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author linanjun
 * @create 2018/4/2 下午5:03
 * @description
 */
public class OrderExtendContractInfoRequest extends ParamsObject {

    @ApiModelProperty(value = "TOKEN")
    @NotBlank(message = "TOKEN不能为空")
    private String token;

    @ApiModelProperty(value = "订单号")
    private String orderNumb;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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
