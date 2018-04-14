package com.beitu.saas.rest.controller.order.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


/**
 * @author linanjun
 * @create 2018/3/27 上午11:33
 * @description
 */
@ApiModel(description = "待放款订单操作")
public class LenderAgreeOrderRequest extends ParamsObject {

    @ApiModelProperty(value = "订单号", required = true)
    @NotBlank(message = "订单号不能为空")
    private String orderNumb;

    @ApiModelProperty(value = "放款备注", required = true)
    @NotNull(message = "放款备注不能为空")
    private Integer lendRemark;

    public String getOrderNumb() {
        return orderNumb;
    }

    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
    }

    public Integer getLendRemark() {
        return lendRemark;
    }

    public void setLendRemark(Integer lendRemark) {
        this.lendRemark = lendRemark;
    }

    @Override
    public void validate() {

    }

}