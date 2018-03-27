package com.beitu.saas.rest.controller.order;

import com.beitu.saas.app.api.ApiResponse;
import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.api.ModuleApiResponse;
import com.beitu.saas.app.application.order.OrderApplication;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.client.SaasBorrowerService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.common.utils.identityNumber.vo.IdcardInfoExtractor;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.domain.SaasOrderVo;
import com.beitu.saas.order.enums.OrderStatusEnum;
import com.beitu.saas.rest.controller.order.request.*;
import com.beitu.saas.rest.controller.order.response.PreliminaryOrderDetailResponse;
import com.beitu.saas.rest.controller.order.response.PreliminaryOrderListResponse;
import com.beitu.saas.risk.helpers.CollectionUtils;
import com.fqgj.common.api.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
    private SaasOrderService saasOrderService;

    @Autowired
    private OrderApplication orderApplication;

    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;

    @Autowired
    private SaasBorrowerService saasBorrowerService;

    @Autowired
    private SaasChannelService saasChannelService;

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "初审订单查询", response = PreliminaryOrderQueryRequest.class)
    public ModuleApiResponse<PreliminaryOrderListResponse> query(@RequestBody @Valid PreliminaryOrderQueryRequest req, Page page) {
        SaasAdmin saasAdmin = RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
        List<SaasOrderVo> saasOrderVoList = saasOrderService.listPreliminaryReviewOrder(saasAdmin.getMerchantCode(), saasAdmin.getCode(), page);
        if (CollectionUtils.isEmpty(saasOrderVoList)) {
            return new ModuleApiResponse();
        }
        List<PreliminaryOrderListResponse.PreliminaryOrderListVo> orderListVoList = new ArrayList<>(saasOrderVoList.size());
        saasOrderVoList.forEach(saasOrderVo -> {
            PreliminaryOrderListResponse.PreliminaryOrderListVo orderListVo = new PreliminaryOrderListResponse.PreliminaryOrderListVo();
            orderListVo.setOrderNumb(saasOrderVo.getOrderNumb());
            orderListVo.setApplyDate(DateUtil.getDate(saasOrderVo.getCreatedDt()));
            orderListVo.setCapital(saasOrderVo.getRealCapital().toString());
            orderListVo.setOrderStatus(OrderStatusEnum.getByCode(saasOrderVo.getOrderStatus()).getMsg());
            orderListVo.setRemark(saasOrderVo.getRemark());
            orderListVo.setBorrowingDuration(DateUtil.countDay(saasOrderVo.getRepaymentDt(), saasOrderVo.getCreatedDt()) + "天");
            SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(saasOrderVo.getBorrowerCode());
            if (saasBorrowerRealInfoVo != null) {
                orderListVo.setBorrowerName(saasBorrowerRealInfoVo.getName());
                IdcardInfoExtractor idcardInfoExtractor = new IdcardInfoExtractor(saasBorrowerRealInfoVo.getIdentityCode());
                orderListVo.setBorrowerAge(idcardInfoExtractor.getAge());
                orderListVo.setBorrowerGender(idcardInfoExtractor.getGender());
            }
            SaasBorrowerVo saasBorrowerVo = saasBorrowerService.getByBorrowerCode(saasOrderVo.getBorrowerCode());
            orderListVo.setBorrowerMobile(saasBorrowerVo.getMobile());
            orderListVo.setChannelName(saasChannelService.getSaasChannelByChannelCode(saasOrderVo.getChannelCode()).getChannelName());
        });
        return new ModuleApiResponse(new PreliminaryOrderListResponse(orderListVoList), page);
    }

    @RequestMapping(value = "/remark/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "初审订单备注保存", response = ApiResponse.class)
    public ApiResponse saveRemark(@RequestBody @Valid PreliminaryOrderRemarkSaveRequest req) {
        // TODO
        return new ApiResponse();
    }

    @RequestMapping(value = "/collect", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "初审领单", response = ApiResponse.class)
    public ApiResponse collect(@RequestBody @Valid PreliminaryOrderCollectRequest req) {
        String adminCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode();
        orderApplication.updateOrderStatus(adminCode, req.getOrderNumb(), OrderStatusEnum.PRELIMINARY_REVIEWER_GET_ORDER, null);
        return new ApiResponse("操作成功");
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

    @RequestMapping(value = "/agree", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "通过初审", response = ApiResponse.class)
    public ApiResponse agree(@RequestBody @Valid PreliminaryProcessOrderRequest req) {
        String adminCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode();
        orderApplication.updateOrderStatus(adminCode, req.getOrderNumb(), OrderStatusEnum.SUBMIT_FINAL_REVIEW, null);
        return new ApiResponse("操作成功");
    }

    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "初审驳回", response = ApiResponse.class)
    public ApiResponse reject(@RequestBody @Valid PreliminaryProcessOrderRequest req) {
        String adminCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode();
        orderApplication.updateOrderStatus(adminCode, req.getOrderNumb(), OrderStatusEnum.PRELIMINARY_REVIEWER_REJECT, null);
        return new ApiResponse("操作成功");
    }

    @RequestMapping(value = "/refuse", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "初审拒绝", response = ApiResponse.class)
    public ApiResponse refuse(@RequestBody @Valid PreliminaryProcessOrderRequest req) {
        String adminCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode();
        orderApplication.updateOrderStatus(adminCode, req.getOrderNumb(), OrderStatusEnum.PRELIMINARY_REVIEWER_REFUSE, null);
        return new ApiResponse("操作成功");
    }

}
