package com.beitu.saas.rest.controller.credit;

import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.application.collection.CollectionApplication;
import com.beitu.saas.app.application.collection.vo.CollectionCommentListVo;
import com.beitu.saas.app.application.credit.BorrowerBaseInfoApplication;
import com.beitu.saas.app.application.credit.CarrierReportApplication;
import com.beitu.saas.app.application.credit.LoanPlatformApplication;
import com.beitu.saas.app.application.credit.TongdunReportApplication;
import com.beitu.saas.app.application.credit.vo.*;
import com.beitu.saas.app.application.order.OrderApplyApplication;
import com.beitu.saas.app.application.order.OrderBillDetailApplication;
import com.beitu.saas.app.application.order.OrderStatusHistoryApplication;
import com.beitu.saas.app.application.order.vo.OrderApplicationListVo;
import com.beitu.saas.app.application.order.vo.SaasOrderDetailVo;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.app.enums.SaasLoanPlatformEnum;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.consts.TermUrlConsts;
import com.beitu.saas.intergration.risk.pojo.LoanPlatformQueryPojo;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.domain.SaasOrderVo;
import com.beitu.saas.order.enums.OrderErrorCodeEnum;
import com.beitu.saas.rest.controller.credit.request.CreditLoanPlatformRequest;
import com.beitu.saas.rest.controller.credit.request.CreditQueryRequest;
import com.beitu.saas.rest.controller.credit.request.UserBaseInfoQueryRequest;
import com.beitu.saas.rest.controller.credit.response.*;
import com.beitu.saas.risk.helpers.StringUtils;
import com.fqgj.common.utils.CollectionUtils;
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

    @Autowired
    private LoanPlatformApplication loanPlatformApplication;

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private CarrierReportApplication carrierReportApplication;

    @Autowired
    private TongdunReportApplication tongdunReportApplication;

    @RequestMapping(value = "/base", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "借款人用户基础信息", response = UserBaseInfoResponse.class)
    public DataApiResponse<UserBaseInfoResponse> queryBaseInfo(@RequestBody @Valid UserBaseInfoQueryRequest req) {
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();

        String orderNumb = req.getOrderNumb();
        String borrowerCode = getBorrowerCodeByOrderNumbAndMerchantCode(orderNumb, merchantCode);

        OrderApplicationListVo orderApplicationListVo = null;
        List<OrderApplicationListVo> orderApplicationListVoList = orderApplyApplication.listOrderApplicationByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);
        if (CollectionUtils.isNotEmpty(orderApplicationListVoList)) {
            orderApplicationListVo = orderApplicationListVoList.get(0);
        }
        BorrowerPersonalInfoVo borrowerPersonalInfoVo = borrowerBaseInfoApplication.getUserPersonalInfoVo(merchantCode, borrowerCode, orderNumb);
        BorrowerIdentityInfoVo borrowerIdentityInfoVo = borrowerBaseInfoApplication.getUserIdentityInfoVo(borrowerCode, orderNumb);
        BorrowerWorkInfoVo borrowerWorkInfoVo = borrowerBaseInfoApplication.getUserWorkInfoVo(borrowerCode, orderNumb);
        BorrowerEmergentContactVo borrowerEmergentContactVo = borrowerBaseInfoApplication.getUserEmergentContactVo(merchantCode, borrowerCode, orderNumb);
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
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        String borrowerCode = getBorrowerCodeByOrderNumbAndMerchantCode(req.getOrderNumb(), merchantCode);
        return new DataApiResponse<>(new OrderApplicationQueryResponse(orderApplyApplication.listOrderApplicationByBorrowerCodeAndOrderNumb(borrowerCode, null)));
    }

    @RequestMapping(value = "/order/detail", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "订单详情", response = OrderDetailQueryResponse.class)
    public DataApiResponse<OrderDetailQueryResponse> getOrderDetail(@RequestBody @Valid CreditQueryRequest req) {
        String orderNumb = req.getOrderNumb();
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        List<SaasOrderDetailVo> saasOrderDetailVoList = orderBillDetailApplication.getAllOrderBillDetailByOrderNumb(merchantCode, orderNumb);
        String viewContractUrl = "";
        String downloadContractUrl = "";
        if (CollectionUtils.isNotEmpty(saasOrderDetailVoList)) {
            SaasOrderVo saasOrderVo = saasOrderService.getMainSaasOrderByOrderNumb(saasOrderDetailVoList.get(0).getOrderNumb());
            if (saasOrderVo != null && StringUtils.isNotEmpty(saasOrderVo.getTermUrl())) {
                viewContractUrl = configUtil.getAddressURLPrefix() + TermUrlConsts.pdfViewUrl + "?pdf=/" + saasOrderVo.getTermUrl();
                downloadContractUrl = configUtil.getAddressURLPrefix() + saasOrderVo.getTermUrl();
            }
        }
        return new DataApiResponse<>(new OrderDetailQueryResponse(saasOrderDetailVoList, viewContractUrl, downloadContractUrl));
    }

    @RequestMapping(value = "/collection/log", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "催收记录", response = OrderCollectionCommentQueryResponse.class)
    public DataApiResponse<OrderCollectionCommentQueryResponse> listCollectionLog(@RequestBody @Valid CreditQueryRequest req) {
        String orderNumb = req.getOrderNumb();
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        List<CollectionCommentListVo> collectionCommentListVoList = collectionApplication.getAllCollectionCommentByOrderNumb(merchantCode, orderNumb);
        return new DataApiResponse<>(new OrderCollectionCommentQueryResponse(collectionCommentListVoList));
    }

    @RequestMapping(value = "/platform/data", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "多平台借贷爬取结果", response = LoanPlatformDataInfoResponse.class)
    public DataApiResponse<LoanPlatformDataInfoResponse> getLoanPlatformData(@RequestBody @Valid CreditLoanPlatformRequest req) {
        String orderNumb = req.getOrderNumb();
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        String borrowerCode = getBorrowerCodeByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        SaasLoanPlatformEnum platform = SaasLoanPlatformEnum.getByCode(req.getPlatform());
        LoanPlatformQueryPojo pojo = loanPlatformApplication.getLoanPlatformData(borrowerCode, platform);
        return new DataApiResponse<>(new LoanPlatformDataInfoResponse(pojo));
    }

    @RequestMapping(value = "/carrier/data", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "运营商信息", response = CreditCarrierReportVo.class)
    public DataApiResponse<CreditCarrierReportVo> getCarrierData(@RequestBody @Valid CreditQueryRequest req) {
        String orderNumb = req.getOrderNumb();
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        String borrowerCode = getBorrowerCodeByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        return new DataApiResponse<>(carrierReportApplication.getCarrierReportByMerchantCodeAndBorrowerCode(merchantCode, borrowerCode));
    }

    @RequestMapping(value = "/black/data", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "网贷黑名单信息", response = TongdunReportVo.class)
    public DataApiResponse<TongdunReportVo> getBlackData(@RequestBody @Valid CreditQueryRequest req) {
        String orderNumb = req.getOrderNumb();
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        String borrowerCode = getBorrowerCodeByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        return new DataApiResponse<>(tongdunReportApplication.getTongdunReport(merchantCode, borrowerCode));
    }

    private String getBorrowerCodeByOrderNumbAndMerchantCode(String orderNumb, String merchantCode) {
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        if (saasOrderVo == null) {
            throw new ApplicationException(OrderErrorCodeEnum.ERROR_ORDER_NUMB);
        }
        return saasOrderVo.getBorrowerCode();
    }

}