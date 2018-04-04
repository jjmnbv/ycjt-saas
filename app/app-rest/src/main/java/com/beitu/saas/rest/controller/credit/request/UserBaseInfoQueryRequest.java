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