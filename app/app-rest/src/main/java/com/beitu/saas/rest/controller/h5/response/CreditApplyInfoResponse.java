package com.beitu.saas.rest.controller.h5.response;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author linanjun
 * @create 2018/3/22 下午4:32
 * @description
 */
@ApiModel(value = "风控模块申请信息")
public class CreditApplyInfoResponse implements ResponseData {

    @ApiModelProperty(value = "订单号")
    private String orderNumb;

    /**
     * 借款金额
     */
    @ApiModelProperty(value = "借款金额")
    private String realCapital;
    /**
     * 借款年利率
     */
    @ApiModelProperty(value = "借款年利率")
    private String totalInterestRatio;
    /**
     * 借款意图
     */
    @ApiModelProperty(value = "借款意图")
    private String borrowPurpose;
    /**
     * 借款天数
     */
    @ApiModelProperty(value = "借款天数")
    private Integer borrowingDuration;

    /**
     * 是否需要实名
     */
    @ApiModelProperty(value = "是否需要实名")
    private Boolean needRealName;

    public String getOrderNumb() {
        return orderNumb;
    }

    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
    }

    public String getRealCapital() {
        return realCapital;
    }

    public void setRealCapital(BigDecimal realCapital) {
        this.realCapital = realCapital.toString();
    }

    public String getTotalInterestRatio() {
        return totalInterestRatio;
    }

    public void setTotalInterestRatio(BigDecimal totalInterestRatio) {
        this.totalInterestRatio = totalInterestRatio.multiply(BigDecimal.valueOf(100L)).toString();
    }

    public String getBorrowPurpose() {
        return borrowPurpose;
    }

    public void setBorrowPurpose(String borrowPurpose) {
        this.borrowPurpose = borrowPurpose;
    }

    public Integer getBorrowingDuration() {
        return borrowingDuration;
    }

    public void setBorrowingDuration(Integer borrowingDuration) {
        this.borrowingDuration = borrowingDuration;
    }

    public Boolean getNeedRealName() {
        return needRealName;
    }

    public void setNeedRealName(Boolean needRealName) {
        this.needRealName = needRealName;
    }
}