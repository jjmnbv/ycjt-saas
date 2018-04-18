package com.beitu.saas.rest.controller.order.request;

import com.fqgj.common.api.ParamsObject;
import com.fqgj.common.api.exception.ApiIllegalArgumentException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


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

    @ApiModelProperty(value = "手续费")
    private BigDecimal serviceFee;

    @ApiModelProperty(value = "下款凭证")
    private String[] lendCertificateArray;

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

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String[] getLendCertificateArray() {
        return lendCertificateArray;
    }

    public void setLendCertificateArray(String[] lendCertificateArray) {
        this.lendCertificateArray = lendCertificateArray;
    }

    @Override
    public void validate() {
        if (this.serviceFee != null && this.serviceFee.compareTo(this.serviceFee.setScale(2, BigDecimal.ROUND_HALF_UP)) != 0) {
            throw new ApiIllegalArgumentException("手续费请输入最多两位小数的数字");
        }
    }

}