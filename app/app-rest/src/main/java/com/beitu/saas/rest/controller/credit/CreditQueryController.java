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
import com.beitu.saas.app.common.RequestBasicInfo;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.app.enums.SaasLoanPlatformEnum;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.client.SaasBorrowerService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.consts.TermUrlConsts;
import com.beitu.saas.common.handle.oss.OSSService;
import com.beitu.saas.common.utils.ShortUrlUtil;
import com.beitu.saas.credit.client.SaasGxbEbService;
import com.beitu.saas.credit.domain.SaasGxbEbVo;
import com.beitu.saas.credit.enums.CreditErrorCodeEnum;
import com.beitu.saas.intergration.risk.RiskEcommerceService;
import com.beitu.saas.intergration.risk.param.GXBEcommerceCrawlingParam;
import com.beitu.saas.intergration.risk.pojo.LoanPlatformQueryPojo;
import com.beitu.saas.order.client.SaasOrderLendRemarkService;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.domain.SaasOrderVo;
import com.beitu.saas.order.enums.OrderErrorCodeEnum;
import com.beitu.saas.rest.controller.credit.request.CreditLoanPlatformRequest;
import com.beitu.saas.rest.controller.credit.request.CreditQueryRequest;
import com.beitu.saas.rest.controller.credit.request.UserBaseInfoQueryRequest;
import com.beitu.saas.rest.controller.credit.response.*;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.common.api.Response;
import com.fqgj.common.api.annotations.ParamsValidate;
import com.fqgj.common.api.exception.ApiErrorException;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.JSONUtils;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.exception.common.ApplicationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @Autowired
    private SaasGxbEbService saasGxbEbService;

    @Autowired
    private OSSService ossService;

    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private RiskEcommerceService riskEcommerceService;

    @Autowired
    private SaasBorrowerService saasBorrowerService;

    @Autowired
    private SaasOrderLendRemarkService saasOrderLendRemarkService;


    @RequestMapping(value = "/base", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "借款人用户基础信息", response = UserBaseInfoResponse.class)
    public DataApiResponse<UserBaseInfoResponse> queryBaseInfo(@RequestBody @Valid UserBaseInfoQueryRequest req) {
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();

        String orderNumb = req.getOrderNumb();
        String borrowerCode = getBorrowerCodeByOrderNumbAndMerchantCode(orderNumb, req.getBorrowerCode(), merchantCode);

        OrderApplicationListVo orderApplicationListVo = null;
        List<OrderApplicationListVo> orderApplicationListVoList = orderApplyApplication.listOrderApplicationByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);
        if (CollectionUtils.isNotEmpty(orderApplicationListVoList)) {
            orderApplicationListVo = orderApplicationListVoList.get(0);
        }
        BorrowerPersonalInfoVo borrowerPersonalInfoVo = borrowerBaseInfoApplication.getUserPersonalInfoVo(merchantCode, borrowerCode, orderNumb);
        BorrowerIdentityInfoVo borrowerIdentityInfoVo = borrowerBaseInfoApplication.getUserIdentityInfoVo(borrowerCode, orderNumb);
        BorrowerWorkInfoVo borrowerWorkInfoVo = borrowerBaseInfoApplication.getUserWorkInfoVo(borrowerCode, orderNumb);
        BorrowerEmergentContactVo borrowerEmergentContactVo = borrowerBaseInfoApplication.getUserEmergentContactVo(merchantCode, borrowerCode, orderNumb);
        BorrowerLivingAreaVo borrowerLivingAreaVo = null;

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
        String borrowerCode = getBorrowerCodeByOrderNumbAndMerchantCode(req.getOrderNumb(), req.getBorrowerCode(), merchantCode);
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
        String[] lendCertificateUrlArray = null;
        String lendCertificate = saasOrderLendRemarkService.getLendCertificateByOrderNumb(orderNumb);
        if (StringUtils.isNotEmpty(lendCertificate)) {
            lendCertificateUrlArray = lendCertificate.split(",");
            final String pdfPrefix = configUtil.getAddressURLPrefix();
            for (int i = 0; i < lendCertificateUrlArray.length; i++) {
                String lendCertificateUrl = lendCertificateUrlArray[i];
                lendCertificateUrlArray[i] = pdfPrefix + lendCertificateUrl;
            }
        }
        return new DataApiResponse<>(new OrderDetailQueryResponse(saasOrderDetailVoList, viewContractUrl, downloadContractUrl, lendCertificateUrlArray));
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
    @ParamsValidate
    public DataApiResponse<LoanPlatformDataInfoResponse> getLoanPlatformData(@RequestBody @Valid CreditLoanPlatformRequest req) {
        String orderNumb = req.getOrderNumb();
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        String borrowerCode = getBorrowerCodeByOrderNumbAndMerchantCode(orderNumb, req.getBorrowerCode(), merchantCode);
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
        String borrowerCode = getBorrowerCodeByOrderNumbAndMerchantCode(orderNumb, req.getBorrowerCode(), merchantCode);
        return new DataApiResponse<>(carrierReportApplication.getCarrierReportByMerchantCodeAndBorrowerCode(merchantCode, borrowerCode));
    }

    @RequestMapping(value = "/black/data", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "网贷黑名单信息", response = TongdunReportVo.class)
    public DataApiResponse<TongdunReportVo> getBlackData(@RequestBody @Valid CreditQueryRequest req) {
        String orderNumb = req.getOrderNumb();
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        String borrowerCode = getBorrowerCodeByOrderNumbAndMerchantCode(orderNumb, req.getBorrowerCode(), merchantCode);
        return new DataApiResponse<>(tongdunReportApplication.getTongdunReport(merchantCode, borrowerCode));
    }

    @RequestMapping(value = "/eb/data", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "电商信息")
    @ParamsValidate
    public Response getGxbEbData(@RequestBody @Valid CreditQueryRequest req) {
        String orderNumb = req.getOrderNumb();
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        String borrowerCode = getBorrowerCodeByOrderNumbAndMerchantCode(orderNumb, req.getBorrowerCode(), merchantCode);
        SaasGxbEbVo saasGxbEbVo = saasGxbEbService.getGxbEbTopByBorrowerCode(borrowerCode);
        if (saasGxbEbVo != null) {
            try {
                String content = ossService.getFileContent(saasGxbEbVo.getJsonUrl());
                Map<String, Object> map = JSONUtils.json2map(content);
                return Response.ok().putData(new HashMap(4) {{
                    put("ecommerceBaseInfo", map.get("ecommerceBaseInfo"));
                    put("ecommerceConsigneeAddresses", map.get("ecommerceConsigneeAddresses"));
                    put("ecommerceTrades", map.get("ecommerceTrades"));
                    put("ecommerceBindedBankCards", map.get("ecommerceBindedBankCards"));
                    put("ecommercePaymentAccounts", map.get("ecommercePaymentAccounts"));
                }});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Response.error(CreditErrorCodeEnum.CREDIT_DS_NO_EXITSR);
    }

    @RequestMapping(value = "/eb/url", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取电商url")
    public Response getGxbEbUrl(@RequestBody @Valid CreditQueryRequest req) {
        String orderNumb = req.getOrderNumb();
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        String borrowerCode = getBorrowerCodeByOrderNumbAndMerchantCode(orderNumb, req.getBorrowerCode(), merchantCode);
        SaasBorrowerRealInfoVo borrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(borrowerCode);
        RequestBasicInfo requestBasicInfo = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo();
        SaasBorrowerVo borrowerVo = saasBorrowerService.getByBorrowerCode(borrowerCode);
        if (!Objects.equals("web", requestBasicInfo.getPlatform())) {
            throw new ApiErrorException("请求非法");
        }
        if (borrowerRealInfoVo == null) {
            throw new ApiErrorException("请先实名");
        }
        Object token = redisClient.get(RedisKeyConsts.SAAS_GXB_ECOMMERCE, borrowerCode);
        if (token != null) {
            throw new ApiErrorException("认证中...");
        }
        StringBuilder sb = new StringBuilder(configUtil.getApiWebPath());
        sb.append("/gxb/eb/notice/").append(borrowerCode).append("/").append(requestBasicInfo.getPlatform()).append("/").append(0).append("/");
        GXBEcommerceCrawlingParam param = new GXBEcommerceCrawlingParam();
        param.setIdcard(borrowerRealInfoVo.getIdentityCode());
        param.setName(borrowerRealInfoVo.getName());
        param.setPhone(borrowerVo.getMobile());
        param.setReturnUrl(sb.toString());
        param.setUserCode(borrowerCode);
        param.setMerchantCode(borrowerRealInfoVo.getMerchantCode());
        return Response.ok().putData(ShortUrlUtil.generateShortUrl(riskEcommerceService.getEcommerceCrawlingUrl(param)));
    }


    private String getBorrowerCodeByOrderNumbAndMerchantCode(String orderNumb, String borrowerCode, String merchantCode) {
        if (StringUtils.isNotEmpty(borrowerCode)) {
            SaasBorrowerVo borrowerVo = saasBorrowerService.getByBorrowerCodeAndMerchantCode(borrowerCode, merchantCode);
            if (borrowerVo == null) {
                throw new ApiErrorException("用户不存在");
            }
            return borrowerCode;
        }
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        if (saasOrderVo == null) {
            throw new ApplicationException(OrderErrorCodeEnum.ERROR_ORDER_NUMB);
        }
        return saasOrderVo.getBorrowerCode();
    }

}