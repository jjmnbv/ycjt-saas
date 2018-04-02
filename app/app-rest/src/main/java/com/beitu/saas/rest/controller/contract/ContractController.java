package com.beitu.saas.rest.controller.contract;

import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.api.ModuleApiResponse;
import com.beitu.saas.app.application.auth.MerchantApplication;
import com.beitu.saas.app.application.contract.ContractApplication;
import com.beitu.saas.app.application.order.OrderCalculateApplication;
import com.beitu.saas.app.application.order.vo.QueryOrderVo;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.auth.domain.MerchantContractInfoVo;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.auth.enums.ContractConfigTypeEnum;
import com.beitu.saas.auth.service.SaasAdminService;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.client.SaasBorrowerService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.enums.RestCodeEnum;
import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.common.utils.StringUtil;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.domain.SaasOrderVo;
import com.beitu.saas.rest.controller.contract.request.OrderExtendContractInfoRequest;
import com.beitu.saas.rest.controller.contract.response.OrderExtendContractInfoResponse;
import com.beitu.saas.rest.controller.order.request.LendingOrderQueryRequest;
import com.beitu.saas.rest.controller.order.response.LendingOrderListResponse;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.exception.common.ApplicationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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
 * @create 2018/4/2 下午5:19
 * @description
 */
@Controller
@RequestMapping("/contract")
@Api(description = "订单合同模块")
public class ContractController {

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private SaasBorrowerService saasBorrowerService;

    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;

    @Autowired
    private SaasAdminService saasAdminService;

    @Autowired
    private MerchantApplication merchantApplication;

    @Autowired
    private ContractApplication contractApplication;

    @Autowired
    private SaasOrderService saasOrderService;

    @Autowired
    private OrderCalculateApplication orderCalculateApplication;

    @SignIgnore
    @RequestMapping(value = "/order/extend", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "展期协议详情", response = LendingOrderListResponse.class)
    public DataApiResponse<OrderExtendContractInfoResponse> getExtendContractDetail(@RequestBody @Valid OrderExtendContractInfoRequest req) {
        String userCode = redisClient.get(RedisKeyConsts.SAAS_TOKEN_KEY, req.getToken());
        if (StringUtils.isEmpty(userCode)) {
            throw new ApplicationException(RestCodeEnum.TOKEN_NOT_AVAILABLE);
        }
        OrderExtendContractInfoResponse response = new OrderExtendContractInfoResponse();
        SaasBorrowerVo saasBorrowerVo = saasBorrowerService.getByBorrowerCode(userCode);
        if (saasBorrowerVo != null) {
            SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(saasBorrowerVo.getBorrowerCode());
            response.setBorrowUserName(saasBorrowerRealInfoVo.getName());
            response.setBorrowIdentityNo(saasBorrowerRealInfoVo.getIdentityCode());
            String sealUrl = contractApplication.getUserSealUrl(userCode);
            if (StringUtils.isNotEmpty(sealUrl)) {
                response.setBorrowStamp(Boolean.TRUE);
                response.setBorrowStampUrl(sealUrl);
            }
        }
        SaasAdmin saasAdmin = saasAdminService.getSaasAdminByAdminCode(userCode);
        if (saasAdmin != null) {
            MerchantContractInfoVo merchantContractInfoVo = merchantApplication.getMerchantContractInfo(saasAdmin.getMerchantCode());
            if (ContractConfigTypeEnum.PERSONAL_CONTRACT.getKey().equals(merchantContractInfoVo.getContractType())) {
                response.setLenderUserName(merchantContractInfoVo.getName());
                response.setLenderIdentityNo("身份证号：" + merchantContractInfoVo.getCode());
            } else {
                response.setLenderUserName(merchantContractInfoVo.getName());
                response.setLenderIdentityNo("统一信用代码：" + merchantContractInfoVo.getCode());
            }
            String sealUrl = contractApplication.getUserSealUrl(userCode);
            if (StringUtils.isNotEmpty(sealUrl)) {
                response.setLenderStamp(Boolean.TRUE);
                response.setLenderStampUrl(sealUrl);
            }
            if (StringUtils.isNotEmpty(merchantContractInfoVo.getContractUrl())) {
                response.setLenderStamp(Boolean.TRUE);
                response.setLenderStampUrl(merchantContractInfoVo.getContractUrl());
            }
        }
        SaasOrderVo saasOrderVo = saasOrderService.getConfirmExtendOrderByOrderNumb(req.getOrderNumb());
        if (saasOrderVo != null) {
            response.setOrderNo(saasOrderVo.getSaasOrderId() + "");
            response.setRealCapital(saasOrderVo.getRealCapital());
            response.setCreatedDt(DateUtil.getDate(saasOrderVo.getCreatedDt()));
            response.setRepaymentDt(DateUtil.getDate(saasOrderVo.getRepaymentDt()));
            response.setTotalInterestRatio(orderCalculateApplication.getInterestRatio(saasOrderVo.getTotalInterestRatio()));
            response.setFirstOrderNo(saasOrderVo.getRelationOrderId() + "");
            response.setInscribeDate(DateUtil.getDate(new Date()));
        }
        return new DataApiResponse(new OrderExtendContractInfoResponse());
    }

    @SignIgnore
    @RequestMapping(value = "/license", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "授权协议详情", response = LendingOrderListResponse.class)
    public DataApiResponse<LendingOrderListResponse> getLicenseContractDetail(@RequestBody @Valid LendingOrderQueryRequest req) {
        SaasAdmin saasAdmin = RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
        QueryOrderVo queryOrderVo = new QueryOrderVo();
        queryOrderVo.setMerchantCode(saasAdmin.getMerchantCode());
        BeanUtils.copyProperties(req, queryOrderVo);
        return new ModuleApiResponse(new LendingOrderListResponse(orderApplication.listForLendingOrder(queryOrderVo, page)), page);
    }

    @SignIgnore
    @RequestMapping(value = "/loan", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "借款协议详情", response = LendingOrderListResponse.class)
    public DataApiResponse<LendingOrderListResponse> getLoanContractDetail(@RequestBody @Valid LendingOrderQueryRequest req) {
        SaasAdmin saasAdmin = RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
        QueryOrderVo queryOrderVo = new QueryOrderVo();
        queryOrderVo.setMerchantCode(saasAdmin.getMerchantCode());
        BeanUtils.copyProperties(req, queryOrderVo);
        return new ModuleApiResponse(new LendingOrderListResponse(orderApplication.listForLendingOrder(queryOrderVo, page)), page);
    }


}
