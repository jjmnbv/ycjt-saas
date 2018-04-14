package com.beitu.saas.rest.controller.borrow.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author linanjun
 * @create 2018/3/21 下午2:48
 * @description
 */
@ApiModel(description = "借款人订单提交信息")
public class BorrowSubmitOrderRequest extends ParamsObject {

    @ApiModelProperty(value = "用户CODE", required = true)
    @NotBlank(message = "用户CODE不能为空")
    private String userCode;

    @ApiModelProperty(value = "订单CODE", required = true)
    @NotBlank(message = "订单CODE不能为空")
    private String orderCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    @Override
    public void validate() {

    }

}