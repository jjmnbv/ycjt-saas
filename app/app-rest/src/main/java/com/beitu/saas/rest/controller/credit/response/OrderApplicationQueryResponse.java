package com.beitu.saas.rest.controller.credit.response;

import com.beitu.saas.app.application.order.vo.OrderApplicationListVo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author linanjun
 * @create 2018/4/4 上午11:12
 * @description
 */
@ApiModel(value = "历史进件列表")
public class OrderApplicationQueryResponse implements ResponseData {

    @ApiModelProperty(value = "历史进件列表信息")
    private List<OrderApplicationListVo> orderApplicationListVoList;

    public OrderApplicationQueryResponse(List<OrderApplicationListVo> orderApplicationListVoList) {
        this.orderApplicationListVoList = orderApplicationListVoList;
    }

    public List<OrderApplicationListVo> getOrderApplicationListVoList() {
        return orderApplicationListVoList;
    }

    public void setOrderApplicationListVoList(List<OrderApplicationListVo> orderApplicationListVoList) {
        this.orderApplicationListVoList = orderApplicationListVoList;
    }
}