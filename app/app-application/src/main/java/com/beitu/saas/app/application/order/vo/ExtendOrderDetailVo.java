package com.beitu.saas.app.application.order.vo;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/25 下午2:33
 * @description
 */
public class ExtendOrderDetailVo implements ResponseData {

    @ApiModelProperty(value = "展期提示")
    private String extendTitle;

    @ApiModelProperty(value = "展期天数")
    private Integer extendDuration;

    @ApiModelProperty(value = "展期截止日期")
    private String repaymentDt;

    @ApiModelProperty(value = "展期利率")
    private String totalInterestRatio;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;

    @ApiModelProperty(value = "展期协议")
    private String extendTermUrl;

    public String getExtendTitle() {
        return extendTitle;
    }

    public void setExtendTitle(String extendTitle) {
        this.extendTitle = extendTitle;
    }

    public Integer getExtendDuration() {
        return extendDuration;
    }

    public void setExtendDuration(Integer extendDuration) {
        this.extendDuration = extendDuration;
    }

    public String getRepaymentDt() {
        return repaymentDt;
    }

    public void setRepaymentDt(String repaymentDt) {
        this.repaymentDt = repaymentDt;
    }

    public String getTotalInterestRatio() {
        return totalInterestRatio;
    }

    public void setTotalInterestRatio(String totalInterestRatio) {
        this.totalInterestRatio = totalInterestRatio;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getExtendTermUrl() {
        return extendTermUrl;
    }

    public void setExtendTermUrl(String extendTermUrl) {
        this.extendTermUrl = extendTermUrl;
    }
}
