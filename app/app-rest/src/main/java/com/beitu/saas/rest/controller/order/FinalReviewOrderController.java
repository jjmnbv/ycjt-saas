package com.beitu.saas.rest.controller.order;

import com.beitu.saas.app.api.ApiResponse;
import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.api.ModuleApiResponse;
import com.beitu.saas.app.application.order.OrderApplication;
import com.beitu.saas.app.application.order.vo.QueryOrderVo;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.order.enums.OrderStatusEnum;
import com.beitu.saas.rest.controller.order.request.FinalOrderDetailRequest;
import com.beitu.saas.rest.controller.order.request.FinalOrderQueryRequest;
import com.beitu.saas.rest.controller.order.request.FinalOrderRemarkSaveRequest;
import com.beitu.saas.rest.controller.order.request.FinalReviewerOperateOrderRequest;
import com.beitu.saas.rest.controller.order.response.FinalOrderDetailResponse;
import com.beitu.saas.rest.controller.order.response.FinalOrderListResponse;
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
 * @create 2018/3/27 下午2:51
 * @description
 */
@Controller
@RequestMapping("/order/final/review")
@Api(description = "订单复审模块")
public class FinalReviewOrderController {

    @Autowired
    private OrderApplication orderApplication;

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "复审订单查询", response = FinalOrderListResponse.class)
    public ModuleApiResponse<FinalOrderListResponse> query(@RequestBody @Valid FinalOrderQueryRequest req, Page page) {
        SaasAdmin saasAdmin = RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
        QueryOrderVo queryOrderVo = new QueryOrderVo();
        queryOrderVo.setMerchantCode(saasAdmin.getMerchantCode());
        if (req.getDealStatus() == 2) {
            queryOrderVo.setReviewerCode(saasAdmin.getCode());
        }
        BeanUtils.copyProperties(req, queryOrderVo);
        return new ModuleApiResponse(new FinalOrderListResponse(orderApplication.listFinalReviewOrder(queryOrderVo, page)), page);
    }

    @RequestMapping(value = "/remark/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "复审订单备注保存", response = ApiResponse.class)
    public ApiResponse saveRemark(@RequestBody @Valid FinalOrderRemarkSaveRequest req) {
        // TODO
        return new ApiResponse();
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "复审订单详情查看", response = FinalOrderDetailResponse.class)
    public DataApiResponse<FinalOrderDetailResponse> detail(@RequestBody @Valid FinalOrderDetailRequest req) {
        String adminCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode();
        orderApplication.updateOrderStatus(adminCode, req.getOrderNumb(), OrderStatusEnum.IN_FINAL_REVIEWER, null);
        FinalOrderDetailResponse response = new FinalOrderDetailResponse();
        response.setOrderNumb(req.getOrderNumb());
        return new DataApiResponse<>(response);
    }

    @RequestMapping(value = "/order/get", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "复审领单", response = ApiResponse.class)
    public ApiResponse getOrder(@RequestBody @Valid FinalReviewerOperateOrderRequest req) {
        String adminCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode();
        orderApplication.updateOrderStatus(adminCode, req.getOrderNumb(), OrderStatusEnum.FINAL_REVIEWER_GET_ORDER, null);
        return new ApiResponse("操作成功");
    }

    @RequestMapping(value = "/agree", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "通过复审", response = ApiResponse.class)
    public ApiResponse agree(@RequestBody @Valid FinalReviewerOperateOrderRequest req) {
        String adminCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode();
        orderApplication.updateOrderStatus(adminCode, req.getOrderNumb(), OrderStatusEnum.SUBMIT_LOAN_LENDER, null);
        return new ApiResponse("操作成功");
    }

    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "复审驳回", response = ApiResponse.class)
    public ApiResponse reject(@RequestBody @Valid FinalReviewerOperateOrderRequest req) {
        String adminCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode();
        orderApplication.updateOrderStatus(adminCode, req.getOrderNumb(), OrderStatusEnum.FINAL_REVIEWER_REJECT, null);
        return new ApiResponse("操作成功");
    }

    @RequestMapping(value = "/refuse", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "复审拒绝", response = ApiResponse.class)
    public ApiResponse refuse(@RequestBody @Valid FinalReviewerOperateOrderRequest req) {
        String adminCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode();
        orderApplication.updateOrderStatus(adminCode, req.getOrderNumb(), OrderStatusEnum.FINAL_REVIEWER_REFUSE, null);
        return new ApiResponse("操作成功");
    }

}
