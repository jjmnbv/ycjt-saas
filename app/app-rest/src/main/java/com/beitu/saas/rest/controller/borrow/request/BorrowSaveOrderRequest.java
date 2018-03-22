package com.beitu.saas.rest.controller.borrow.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author linanjun
 * @create 2018/3/21 下午2:37
 * @description
 */
@ApiModel(description = "借款人订单信息保存")
public class BorrowSaveOrderRequest extends ParamsObject {

    @ApiModelProperty(value = "用户码", required = true)
    @NotBlank(message = "用户码不能为空")
    private String userCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Override
    public void validate() {

    }

}