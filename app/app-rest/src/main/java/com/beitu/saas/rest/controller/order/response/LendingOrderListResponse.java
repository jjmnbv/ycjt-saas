package com.beitu.saas.rest.controller.order.response;

import com.beitu.saas.app.application.order.vo.SaasOrderListVo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @author linanjun
 * @create 2018/3/27 上午11:21
 * @description
 */
@ApiModel(value = "初审列表信息")
public class LendingOrderListResponse implements ResponseData {

    private List<SaasOrderListVo> orderListVoList;

    public LendingOrderListResponse(List<SaasOrderListVo> orderListVoList) {
        this.orderListVoList = orderListVoList;
    }

    public List<SaasOrderListVo> getOrderListVoList() {
        return orderListVoList;
    }

    public void setOrderListVoList(List<SaasOrderListVo> orderListVoList) {
        this.orderListVoList = orderListVoList;
    }

}