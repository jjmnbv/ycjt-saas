package com.beitu.saas.rest.controller.finance.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author xiaochong
 * @create 2018/3/28 下午8:46
 * @description
 */
@ApiModel
public class CreditHistoryRequest extends ParamsObject {

    @ApiModelProperty("开始时间")
    private Date startDate;

    @ApiModelProperty("结束时间")
    private Date endDate;

    @ApiModelProperty("0：支出，1：收入")
    private Integer opType;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    @Override
    public void validate() {

    }
}
