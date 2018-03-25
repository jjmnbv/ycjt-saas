package com.beitu.saas.rest.controller.h5.response;

import com.beitu.saas.app.application.order.vo.H5OrderListVo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author linanjun
 * @create 2018/3/25 下午2:08
 * @description
 */
@ApiModel(value = "用户账单列表信息")
public class H5OrderListResponse implements ResponseData {

    @ApiModelProperty(value = "账单列表")
    private List<H5OrderListVo> orderListVos;

    public H5OrderListResponse(List<H5OrderListVo> orderListVos) {
        this.orderListVos = orderListVos;
    }

    public List<H5OrderListVo> getOrderListVos() {
        return orderListVos;
    }

    public void setOrderListVos(List<H5OrderListVo> orderListVos) {
        this.orderListVos = orderListVos;
    }

}