package com.beitu.saas.rest.controller.order.response;

import com.beitu.saas.app.application.order.vo.SaasOrderBillDetailListVo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @author linanjun
 * @create 2018/3/27 上午11:21
 * @description
 */
@ApiModel(value = "初审列表信息")
public class OverdueOrderListResponse implements ResponseData {

    private List<SaasOrderBillDetailListVo> orderListVoList;

    public OverdueOrderListResponse(List<SaasOrderBillDetailListVo> orderListVoList) {
        this.orderListVoList = orderListVoList;
    }

    public List<SaasOrderBillDetailListVo> getOrderListVoList() {
        return orderListVoList;
    }

    public void setOrderListVoList(List<SaasOrderBillDetailListVo> orderListVoList) {
        this.orderListVoList = orderListVoList;
    }

}