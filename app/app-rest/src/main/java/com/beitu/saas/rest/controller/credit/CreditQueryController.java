package com.beitu.saas.rest.controller.credit;

import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.application.borrower.BorrowerApplication;
import com.beitu.saas.app.application.collection.CollectionApplication;
import com.beitu.saas.app.application.collection.vo.CollectionCommentListVo;
import com.beitu.saas.app.application.credit.BorrowerBaseInfoApplication;
import com.beitu.saas.app.application.credit.vo.*;
import com.beitu.saas.app.application.order.OrderApplication;
import com.beitu.saas.app.application.order.OrderApplyApplication;
import com.beitu.saas.app.application.order.OrderBillDetailApplication;
import com.beitu.saas.app.application.order.OrderStatusHistoryApplication;
import com.beitu.saas.app.application.order.vo.OrderApplicationListVo;
import com.beitu.saas.app.application.order.vo.SaasOrderDetailVo;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.domain.SaasOrderVo;
import com.beitu.saas.order.enums.OrderErrorCodeEnum;
import com.beitu.saas.rest.controller.credit.request.CreditQueryRequest;
import com.beitu.saas.rest.controller.credit.request.UserBaseInfoQueryRequest;
import com.beitu.saas.rest.controller.credit.response.*;
import com.beitu.saas.risk.helpers.CollectionUtils;
import com.fqgj.exception.common.ApplicationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @author linanjun
 * @create 2018/3/21 下午10:04
 * @description
 */
@Controller
@RequestMapping("/credit/query")
@Api(description = "借款人风控查询模块")
public class CreditQueryController {

    @Autowired
    private BorrowerBaseInfoApplication borrowerBaseInfoApplication;

    @Autowired
    private SaasOrderService saasOrderService;

    @Autowired
    private OrderStatusHistoryApplication orderStatusHistoryApplication;

    @Autowired
    private OrderApplyApplication orderApplyApplication;

    @Autowired
    private OrderBillDetailApplication orderBillDetailApplication;

    @Autowired
    private CollectionApplication collectionApplication;

    @RequestMapping(value = "/base", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "借款人用户基础信息", response = UserBaseInfoResponse.class)
    public DataApiResponse<UserBaseInfoResponse> queryBaseInfo(@RequestBody @Valid UserBaseInfoQueryRequest req) {
        String orderNumb = req.getOrderNumb();
        String borrowerCode = getBorrowerCodeByOrderNumb(orderNumb);

        OrderApplicationListVo orderApplicationListVo = null;
        List<OrderApplicationListVo> orderApplicationListVoList = orderApplyApplication.listOrderApplicationByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);
        if (CollectionUtils.isNotEmpty(orderApplicationListVoList)) {
            orderApplicationListVo = orderApplicationListVoList.get(0);
        }
        BorrowerPersonalInfoVo borrowerPersonalInfoVo = borrowerBaseInfoApplication.getUserPersonalInfoVo(borrowerCode, orderNumb);
        BorrowerIdentityInfoVo borrowerIdentityInfoVo = borrowerBaseInfoApplication.getUserIdentityInfoVo(borrowerCode, orderNumb);
        BorrowerWorkInfoVo borrowerWorkInfoVo = borrowerBaseInfoApplication.getUserWorkInfoVo(borrowerCode, orderNumb);
        BorrowerEmergentContactVo borrowerEmergentContactVo = borrowerBaseInfoApplication.getUserEmergentContactVo(borrowerCode, orderNumb);
        BorrowerLivingAreaVo borrowerLivingAreaVo = borrowerBaseInfoApplication.getUserLivingAreaVo(borrowerCode, orderNumb);

        return new DataApiResponse<>(new UserBaseInfoResponse(orderApplicationListVo, borrowerPersonalInfoVo, borrowerIdentityInfoVo, borrowerWorkInfoVo, borrowerEmergentContactVo, borrowerLivingAreaVo));
    }

    @RequestMapping(value = "/order/log", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "订单操作记录", response = OrderLogQueryResponse.class)
    public DataApiResponse<OrderLogQueryResponse> listOrderLog(@RequestBody @Valid CreditQueryRequest req) {
        String orderNumb = req.getOrderNumb();
        return new DataApiResponse<>(new OrderLogQueryResponse(orderStatusHistoryApplication.listOrderStatusHistory(orderNumb)));
    }

    @RequestMapping(value = "/order/application", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "订单进件记录", response = OrderApplicationQueryResponse.class)
    public DataApiResponse<OrderApplicationQueryResponse> listOrderApplication(@RequestBody @Valid CreditQueryRequest req) {
        String borrowerCode = getBorrowerCodeByOrderNumb(req.getOrderNumb());
        return new DataApiResponse<>(new OrderApplicationQueryResponse(orderApplyApplication.listOrderApplicationByBorrowerCodeAndOrderNumb(borrowerCode, null)));
    }

    @RequestMapping(value = "/order/detail", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "订单详情", response = OrderApplicationQueryResponse.class)
    public DataApiResponse<OrderDetailQueryResponse> getOrderDetail(@RequestBody @Valid CreditQueryRequest req) {
        String orderNumb = req.getOrderNumb();
        List<SaasOrderDetailVo> saasOrderDetailVoList = orderBillDetailApplication.getAllOrderBillDetailByOrderNumb(orderNumb);
        String viewContractUrl = "";
        String downloadContractUrl = "";
        return new DataApiResponse<>(new OrderDetailQueryResponse(saasOrderDetailVoList, viewContractUrl, downloadContractUrl));
    }

    @RequestMapping(value = "/collection/log", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "催收记录", response = OrderCollectionCommentQueryResponse.class)
    public DataApiResponse<OrderCollectionCommentQueryResponse> listCollectionLog(@RequestBody @Valid CreditQueryRequest req) {
        String orderNumb = req.getOrderNumb();
        List<CollectionCommentListVo> collectionCommentListVoList = collectionApplication.getAllCollectionCommentByOrderNumb(orderNumb);
        return new DataApiResponse<>(new OrderCollectionCommentQueryResponse(collectionCommentListVoList));
    }

    @RequestMapping(value = "/carrier/info", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "运营商信息", response = OrderCollectionCommentQueryResponse.class)
    public DataApiResponse<OrderCollectionCommentQueryResponse> getCarrierInfo(@RequestBody @Valid CreditQueryRequest req) {
        String orderNumb = req.getOrderNumb();
        List<CollectionCommentListVo> collectionCommentListVoList = collectionApplication.getAllCollectionCommentByOrderNumb(orderNumb);
        return new DataApiResponse<>(new OrderCollectionCommentQueryResponse(collectionCommentListVoList));
    }

    private String getBorrowerCodeByOrderNumb(String orderNumb) {
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumb(orderNumb);
        if (saasOrderVo == null) {
            throw new ApplicationException(OrderErrorCodeEnum.ERROR_ORDER_NUMB);
        }
        return saasOrderVo.getBorrowerCode();
    }

}