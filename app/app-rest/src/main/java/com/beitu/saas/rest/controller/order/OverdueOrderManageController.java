package com.beitu.saas.rest.controller.order;

import com.beitu.saas.app.api.ApiResponse;
import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.api.ModuleApiResponse;
import com.beitu.saas.app.application.order.OrderApplication;
import com.beitu.saas.app.application.order.OrderBillDetailApplication;
import com.beitu.saas.app.application.order.vo.QueryOrderBillDetailVo;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.collection.client.SaasCollectionOrderService;
import com.beitu.saas.order.enums.OrderStatusEnum;
import com.beitu.saas.rest.controller.order.request.*;
import com.beitu.saas.rest.controller.order.response.OverdueOrderDetailResponse;
import com.beitu.saas.rest.controller.order.response.OverdueOrderListResponse;
import com.fqgj.common.api.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @author linanjun
 * @create 2018/3/27 下午9:44
 * @description
 */
@Controller
@RequestMapping("/order/overdue")
@Api(description = "逾期管理模块")
public class OverdueOrderManageController {

    @Autowired
    private OrderApplication orderApplication;

    @Autowired
    private OrderBillDetailApplication orderBillDetailApplication;

    @Autowired
    private SaasCollectionOrderService saasCollectionOrderService;

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "逾期管理订单查询", response = OverdueOrderListResponse.class)
    public ModuleApiResponse<OverdueOrderListResponse> query(@RequestBody @Valid OverdueOrderQueryRequest req, Page page) {
        SaasAdmin saasAdmin = RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
        QueryOrderBillDetailVo queryOrderBillDetailVo = new QueryOrderBillDetailVo();
        queryOrderBillDetailVo.setMerchantCode(saasAdmin.getMerchantCode());
        BeanUtils.copyProperties(req, queryOrderBillDetailVo);
        return new ModuleApiResponse(new OverdueOrderListResponse(orderBillDetailApplication.listOverdueOrder(queryOrderBillDetailVo, page)), page);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "逾期管理订单详情查看", response = OverdueOrderDetailResponse.class)
    public DataApiResponse<OverdueOrderDetailResponse> detail(@RequestBody @Valid OverdueManagerOperateOrderRequest req) {
        String adminCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode();
        OverdueOrderDetailResponse response = new OverdueOrderDetailResponse();
        response.setOrderNumb(req.getOrderNumb());
        return new DataApiResponse<>(response);
    }

    @RequestMapping(value = "/collect/entrust", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "委托催收", response = ApiResponse.class)
    public ApiResponse entrustedCollection(@RequestBody @Valid OverdueManagerOperateOrderRequest req) {
        String adminCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode();
        saasCollectionOrderService.createCollectionOrder(req.getOrderNumb());
        return new ApiResponse("委托催收成功");
    }

}
