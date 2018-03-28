package com.beitu.saas.rest.controller.order;

import com.beitu.saas.app.api.ApiResponse;
import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.api.ModuleApiResponse;
import com.beitu.saas.app.application.order.OrderApplication;
import com.beitu.saas.app.application.order.vo.QueryOrderVo;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.order.enums.OrderStatusEnum;
import com.beitu.saas.rest.controller.order.request.FinalReviewerOperateOrderRequest;
import com.beitu.saas.rest.controller.order.request.LendingOrderDetailRequest;
import com.beitu.saas.rest.controller.order.request.LendingOrderQueryRequest;
import com.beitu.saas.rest.controller.order.request.LendingOrderRemarkSaveRequest;
import com.beitu.saas.rest.controller.order.response.LendingOrderDetailResponse;
import com.beitu.saas.rest.controller.order.response.LendingOrderListResponse;
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
 * @create 2018/3/27 下午8:18
 * @description
 */
@Controller
@RequestMapping("/order/for/lending")
@Api(description = "待放款订单模块")
public class ForLendingOrderController {

    @Autowired
    private OrderApplication orderApplication;

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "待放款订单查询", response = LendingOrderListResponse.class)
    public ModuleApiResponse<LendingOrderListResponse> query(@RequestBody @Valid LendingOrderQueryRequest req, Page page) {
        SaasAdmin saasAdmin = RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
        QueryOrderVo queryOrderVo = new QueryOrderVo();
        queryOrderVo.setMerchantCode(saasAdmin.getMerchantCode());
        BeanUtils.copyProperties(req, queryOrderVo);
        return new ModuleApiResponse(new LendingOrderListResponse(orderApplication.listForLendingOrder(queryOrderVo, page)), page);
    }

    @RequestMapping(value = "/remark/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "待放款订单备注保存", response = ApiResponse.class)
    public ApiResponse saveRemark(@RequestBody @Valid LendingOrderRemarkSaveRequest req) {
        // TODO
        return new ApiResponse();
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "待放款订单详情查看", response = LendingOrderDetailResponse.class)
    public DataApiResponse<LendingOrderDetailResponse> detail(@RequestBody @Valid LendingOrderDetailRequest req) {
        String adminCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode();
        orderApplication.updateOrderStatus(adminCode, req.getOrderNumb(), OrderStatusEnum.IN_FINAL_REVIEWER, null);
        LendingOrderDetailResponse response = new LendingOrderDetailResponse();
        response.setOrderNumb(req.getOrderNumb());
        return new DataApiResponse<>(response);
    }

    @RequestMapping(value = "/agree", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "放款", response = ApiResponse.class)
    public ApiResponse agree(@RequestBody @Valid FinalReviewerOperateOrderRequest req) {
        String adminCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode();
        orderApplication.updateOrderStatus(adminCode, req.getOrderNumb(), OrderStatusEnum.TO_CONFIRM_RECEIPT, null);
        return new ApiResponse("操作成功");
    }

    @RequestMapping(value = "/refuse", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "放款拒绝", response = ApiResponse.class)
    public ApiResponse refuse(@RequestBody @Valid FinalReviewerOperateOrderRequest req) {
        String adminCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode();
        orderApplication.updateOrderStatus(adminCode, req.getOrderNumb(), OrderStatusEnum.LOAN_LENDER_REFUSE, null);
        return new ApiResponse("操作成功");
    }

}
