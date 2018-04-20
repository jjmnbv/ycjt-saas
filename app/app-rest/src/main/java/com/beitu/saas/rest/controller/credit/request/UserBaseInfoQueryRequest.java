package com.beitu.saas.rest.controller.credit.request;

import com.fqgj.common.api.ParamsObject;
import com.fqgj.common.api.exception.ApiIllegalArgumentException;
import com.fqgj.common.utils.StringUtils;
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

    @ApiModelProperty(value = "订单号")
    private String orderNumb;

    private String borrowerCode;

    public String getBorrowerCode() {
        return borrowerCode;
    }

    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }

    public String getOrderNumb() {
        return orderNumb;
    }

    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
    }

    @Override
    public void validate() {
        if (StringUtils.isEmpty(orderNumb) && StringUtils.isEmpty(borrowerCode)) {
            throw new ApiIllegalArgumentException("请求参数非法");
        }
    }

}