package com.beitu.saas.rest.controller.order;

import com.beitu.saas.app.api.ApiResponse;
import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.api.ModuleApiResponse;
import com.beitu.saas.app.application.order.OrderApplication;
import com.beitu.saas.app.application.order.vo.QueryOrderVo;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.order.enums.OrderStatusEnum;
import com.beitu.saas.rest.controller.order.request.PreliminaryOrderDetailRequest;
import com.beitu.saas.rest.controller.order.request.PreliminaryOrderQueryRequest;
import com.beitu.saas.rest.controller.order.request.PreliminaryOrderRemarkSaveRequest;
import com.beitu.saas.rest.controller.order.request.PreliminaryReviewerOperateOrderRequest;
import com.beitu.saas.rest.controller.order.response.FinalOrderListResponse;
import com.beitu.saas.rest.controller.order.response.PreliminaryOrderDetailResponse;
import com.beitu.saas.rest.controller.order.response.PreliminaryOrderListResponse;
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
 * @create 2018/3/21 下午3:24
 * @description
 */
@Controller
@RequestMapping("/order/preliminary/review")
@Api(description = "订单初审模块")
public class PreliminaryReviewOrderController {

    @Autowired
    private OrderApplication orderApplication;

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "初审订单查询", response = PreliminaryOrderQueryRequest.class)
    public ModuleApiResponse<PreliminaryOrderListResponse> query(@RequestBody @Valid PreliminaryOrderQueryRequest req, Page page) {
        SaasAdmin saasAdmin = RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
        QueryOrderVo queryOrderVo = new QueryOrderVo();
        queryOrderVo.setMerchantCode(saasAdmin.getMerchantCode());
        if (req.getDealStatus() == 2) {
            queryOrderVo.setReviewerCode(saasAdmin.getCode());
        }
        BeanUtils.copyProperties(req, queryOrderVo);
        return new ModuleApiResponse(new FinalOrderListResponse(orderApplication.listPreliminaryReviewOrder(queryOrderVo, page)), page);
    }

    @RequestMapping(value = "/remark/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "初审订单备注保存", response = ApiResponse.class)
    public ApiResponse saveRemark(@RequestBody @Valid PreliminaryOrderRemarkSaveRequest req) {
        // TODO
        return new ApiResponse();
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "初审订单详情查看", response = ApiResponse.class)
    public DataApiResponse<PreliminaryOrderDetailResponse> detail(@RequestBody @Valid PreliminaryOrderDetailRequest req) {
        String adminCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode();
        orderApplication.updateOrderStatus(adminCode, req.getOrderNumb(), OrderStatusEnum.IN_PRELIMINARY_REVIEWER, null);
        PreliminaryOrderDetailResponse response = new PreliminaryOrderDetailResponse();
        response.setOrderNumb(req.getOrderNumb());
        return new DataApiResponse<>(response);
    }

    @RequestMapping(value = "/order/get", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "初审领单", response = ApiResponse.class)
    public ApiResponse getOrder(@RequestBody @Valid PreliminaryReviewerOperateOrderRequest req) {
        String adminCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode();
        orderApplication.updateOrderStatus(adminCode, req.getOrderNumb(), OrderStatusEnum.PRELIMINARY_REVIEWER_GET_ORDER, null);
        return new ApiResponse("操作成功");
    }

    @RequestMapping(value = "/agree", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "通过初审", response = ApiResponse.class)
    public ApiResponse agree(@RequestBody @Valid PreliminaryReviewerOperateOrderRequest req) {
        String adminCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode();
        orderApplication.updateOrderStatus(adminCode, req.getOrderNumb(), OrderStatusEnum.SUBMIT_FINAL_REVIEW, null);
        return new ApiResponse("操作成功");
    }

    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "初审驳回", response = ApiResponse.class)
    public ApiResponse reject(@RequestBody @Valid PreliminaryReviewerOperateOrderRequest req) {
        String adminCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode();
        orderApplication.updateOrderStatus(adminCode, req.getOrderNumb(), OrderStatusEnum.PRELIMINARY_REVIEWER_REJECT, null);
        return new ApiResponse("操作成功");
    }

    @RequestMapping(value = "/refuse", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "初审拒绝", response = ApiResponse.class)
    public ApiResponse refuse(@RequestBody @Valid PreliminaryReviewerOperateOrderRequest req) {
        String adminCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode();
        orderApplication.updateOrderStatus(adminCode, req.getOrderNumb(), OrderStatusEnum.PRELIMINARY_REVIEWER_REFUSE, null);
        return new ApiResponse("操作成功");
    }

}