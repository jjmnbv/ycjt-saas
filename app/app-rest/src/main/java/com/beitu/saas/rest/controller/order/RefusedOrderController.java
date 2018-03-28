package com.beitu.saas.rest.controller.order;

import com.beitu.saas.app.api.ApiResponse;
import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.api.ModuleApiResponse;
import com.beitu.saas.app.application.order.OrderApplication;
import com.beitu.saas.app.application.order.vo.QueryOrderVo;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.order.enums.OrderStatusEnum;
import com.beitu.saas.rest.controller.order.request.RefusedOrderDetailRequest;
import com.beitu.saas.rest.controller.order.request.RefusedOrderQueryRequest;
import com.beitu.saas.rest.controller.order.request.RefusedOrderRemarkSaveRequest;
import com.beitu.saas.rest.controller.order.response.RefusedOrderDetailResponse;
import com.beitu.saas.rest.controller.order.response.RefusedOrderListResponse;
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
 * @create 2018/3/27 下午3:58
 * @description
 */
@Controller
@RequestMapping("/order/refused")
@Api(description = "已拒订单模块")
public class RefusedOrderController {

    @Autowired
    private OrderApplication orderApplication;

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "已拒订单查询", response = RefusedOrderListResponse.class)
    public ModuleApiResponse<RefusedOrderListResponse> query(@RequestBody @Valid RefusedOrderQueryRequest req, Page page) {
        SaasAdmin saasAdmin = RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
        QueryOrderVo queryOrderVo = new QueryOrderVo();
        queryOrderVo.setMerchantCode(saasAdmin.getMerchantCode());
        BeanUtils.copyProperties(req, queryOrderVo);
        return new ModuleApiResponse(new RefusedOrderListResponse(orderApplication.listRefusedOrder(queryOrderVo, page)), page);
    }

    @RequestMapping(value = "/remark/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "已拒订单备注保存", response = ApiResponse.class)
    public ApiResponse saveRemark(@RequestBody @Valid RefusedOrderRemarkSaveRequest req) {
        // TODO
        return new ApiResponse();
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "已拒订单详情查看", response = RefusedOrderDetailResponse.class)
    public DataApiResponse<RefusedOrderDetailResponse> detail(@RequestBody @Valid RefusedOrderDetailRequest req) {
        String adminCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode();
        orderApplication.updateOrderStatus(adminCode, req.getOrderNumb(), OrderStatusEnum.IN_FINAL_REVIEWER, null);
        RefusedOrderDetailResponse response = new RefusedOrderDetailResponse();
        response.setOrderNumb(req.getOrderNumb());
        return new DataApiResponse<>(response);
    }

}
