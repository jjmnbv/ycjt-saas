package com.beitu.saas.rest.controller.order;

import com.beitu.saas.app.annotations.HasPermission;
import com.beitu.saas.app.api.ApiResponse;
import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.api.ModuleApiResponse;
import com.beitu.saas.app.application.order.OrderApplication;
import com.beitu.saas.app.application.order.OrderCalculateApplication;
import com.beitu.saas.app.application.order.vo.QueryOrderVo;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.app.enums.SaasLendRemarkEnum;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.common.consts.ButtonPermissionConsts;
import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.domain.SaasOrderVo;
import com.beitu.saas.rest.controller.order.request.*;
import com.beitu.saas.rest.controller.order.response.LendingOrderDetailResponse;
import com.beitu.saas.rest.controller.order.response.LendingOrderListResponse;
import com.fqgj.common.api.Page;
import com.fqgj.common.api.exception.ApiIllegalArgumentException;
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
import java.util.Date;

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

    @Autowired
    private SaasOrderService saasOrderService;

    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;

    @Autowired
    private OrderCalculateApplication orderCalculateApplication;

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

    @HasPermission(permissionKey = ButtonPermissionConsts.LOAN_REMARKS)
    @RequestMapping(value = "/remark/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "待放款订单备注保存", response = ApiResponse.class)
    public ApiResponse saveRemark(@RequestBody @Valid LendingOrderRemarkSaveRequest req) {
        SaasAdmin saasAdmin = RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
        orderApplication.saveOrderRemark(saasAdmin.getMerchantCode(), saasAdmin.getCode(), req.getOrderNumb(), req.getRemark());
        return new ApiResponse("保存成功");
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "待放款订单详情查看", response = LendingOrderDetailResponse.class)
    public DataApiResponse<LendingOrderDetailResponse> detail(@RequestBody @Valid LendingOrderDetailRequest req) {
        SaasAdmin saasAdmin = RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
        LendingOrderDetailResponse response = new LendingOrderDetailResponse();
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(req.getOrderNumb(), saasAdmin.getMerchantCode());
        if (saasOrderVo != null) {
            response.setCapital(saasOrderVo.getRealCapital().toString());
            response.setTotalInterestRatio(orderCalculateApplication.getInterestRatio(saasOrderVo.getTotalInterestRatio()));
            response.setBorrowingDuration(DateUtil.countDay(saasOrderVo.getRepaymentDt(), new Date()) + "天");
            SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(saasOrderVo.getBorrowerCode());
            response.setBorrowerName(saasBorrowerRealInfoVo.getName());
        }
        response.setOrderNumb(req.getOrderNumb());
        return new DataApiResponse<>(response);
    }

    @HasPermission(permissionKey = ButtonPermissionConsts.LOAN)
    @RequestMapping(value = "/agree", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "放款", response = ApiResponse.class)
    public ApiResponse agree(@RequestBody @Valid LenderAgreeOrderRequest req) {
        SaasAdmin saasAdmin = RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
        SaasLendRemarkEnum saasLendRemarkEnum = SaasLendRemarkEnum.getByCode(req.getLendRemark());
        if (saasLendRemarkEnum == null) {
            throw new ApiIllegalArgumentException("下款途径不支持");
        }
        orderApplication.lenderAgree(saasAdmin.getMerchantCode(), saasAdmin.getCode(), req.getOrderNumb(), saasLendRemarkEnum.getMsg());
        return new ApiResponse("操作成功");
    }

    @HasPermission(permissionKey = ButtonPermissionConsts.LOAN_REFUSE)
    @RequestMapping(value = "/refuse", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "放款拒绝", response = ApiResponse.class)
    public ApiResponse refuse(@RequestBody @Valid LenderRefuseOrderRequest req) {
        SaasAdmin saasAdmin = RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
        orderApplication.lenderRefuse(saasAdmin.getMerchantCode(), saasAdmin.getCode(), req.getOrderNumb());
        return new ApiResponse("操作成功");
    }

}
