package com.beitu.saas.rest.controller.credit.response;

import com.beitu.saas.app.application.order.vo.OrderStatusHistoryListVo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author linanjun
 * @create 2018/4/4 上午11:12
 * @description
 */
@ApiModel(value = "操作记录列表")
public class OrderLogQueryResponse implements ResponseData {

    @ApiModelProperty(value = "操作记录列表信息")
    private List<OrderStatusHistoryListVo> orderStatusHistoryListVoList;

    public OrderLogQueryResponse(List<OrderStatusHistoryListVo> orderStatusHistoryListVoList) {
        this.orderStatusHistoryListVoList = orderStatusHistoryListVoList;
    }

    public List<OrderStatusHistoryListVo> getOrderStatusHistoryListVoList() {
        return orderStatusHistoryListVoList;
    }

    public void setOrderStatusHistoryListVoList(List<OrderStatusHistoryListVo> orderStatusHistoryListVoList) {
        this.orderStatusHistoryListVoList = orderStatusHistoryListVoList;
    }

}