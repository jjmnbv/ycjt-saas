package com.beitu.saas.rest.controller.contract;

import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.application.auth.MerchantApplication;
import com.beitu.saas.app.application.contract.ContractApplication;
import com.beitu.saas.app.application.contract.consts.ContractConsts;
import com.beitu.saas.app.application.order.OrderCalculateApplication;
import com.beitu.saas.auth.domain.MerchantContractInfoVo;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.auth.enums.ContractConfigTypeEnum;
import com.beitu.saas.auth.service.SaasAdminService;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.client.SaasBorrowerService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.enums.RestCodeEnum;
import com.beitu.saas.common.handle.oss.OSSService;
import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.domain.SaasOrderVo;
import com.beitu.saas.order.enums.OrderErrorCodeEnum;
import com.beitu.saas.rest.controller.contract.request.OrderExtendContractInfoRequest;
import com.beitu.saas.rest.controller.contract.request.OrderLoanContractInfoRequest;
import com.beitu.saas.rest.controller.contract.request.UserLicenseContractInfoRequest;
import com.beitu.saas.rest.controller.contract.response.OrderExtendContractInfoResponse;
import com.beitu.saas.rest.controller.contract.response.OrderLoanContractInfoResponse;
import com.beitu.saas.rest.controller.contract.response.UserLicenseContractInfoResponse;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.NumberToCNUtil;
import com.fqgj.exception.common.ApplicationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private OSSService ossService;

    @RequestMapping(value = "/order/extend", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "展期协议详情", response = OrderExtendContractInfoResponse.class)
    public DataApiResponse<OrderExtendContractInfoResponse> getExtendContractDetail(@RequestBody @Valid OrderExtendContractInfoRequest req) {
        String userCode = redisClient.get(RedisKeyConsts.SAAS_TOKEN_KEY, req.getToken());
        if (StringUtils.isEmpty(userCode)) {
            throw new ApplicationException(RestCodeEnum.TOKEN_NOT_AVAILABLE);
        }
        OrderExtendContractInfoResponse response = new OrderExtendContractInfoResponse();
        String borrowerCode = null;
        String merchantCode = null;
        List<SaasOrderVo> saasOrderVoList = saasOrderService.listEffectiveOrderByOrderNumb(req.getOrderNumb());
        if (CollectionUtils.isNotEmpty(saasOrderVoList)) {
            SaasOrderVo saasOrderVo = saasOrderVoList.get(saasOrderVoList.size() - 1);
            response.setRealCapital(saasOrderVo.getRealCapital());
            response.setFirstOrderNo(saasOrderVo.getSaasOrderId() + "");
            response.setInscribeDate(DateUtil.getDate(new Date()));
            Date createdDt = new Date();
            if (saasOrderVo.getRepaymentDt().compareTo(createdDt) > 0) {
                createdDt = saasOrderVo.getRepaymentDt();
            }
            response.setCreatedDt(DateUtil.getDate(DateUtil.addDate(createdDt, 1)));
            borrowerCode = saasOrderVo.getBorrowerCode();
            merchantCode = saasOrderVo.getMerchantCode();
        } else {
            SaasBorrowerVo saasBorrowerVo = saasBorrowerService.getByBorrowerCodeAndMerchantCode(userCode, merchantCode);
            if (saasBorrowerVo != null) {
                borrowerCode = saasBorrowerVo.getBorrowerCode();
            } else {
                SaasAdmin saasAdmin = saasAdminService.getSaasAdminByAdminCode(userCode);
                if (saasAdmin != null) {
                    merchantCode = saasAdmin.getMerchantCode();
                }
            }
        }
        if (StringUtils.isNotEmpty(borrowerCode)) {
            SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(borrowerCode);
            if (saasBorrowerRealInfoVo != null) {
                response.setBorrowUserName(saasBorrowerRealInfoVo.getName());
                response.setBorrowIdentityNo(saasBorrowerRealInfoVo.getIdentityCode());
            }
            String sealUrl = contractApplication.getUserSealUrl(userCode);
            if (StringUtils.isNotEmpty(sealUrl)) {
                response.setBorrowStamp(Boolean.TRUE);
                response.setBorrowStampUrl(ContractConsts.SEAL_BASE64_DATA_PREFIX + ossService.getFileContent(sealUrl));
            }
        }
        if (StringUtils.isNotEmpty(merchantCode)) {
            MerchantContractInfoVo merchantContractInfoVo = merchantApplication.getMerchantContractInfo(merchantCode);
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
                response.setLenderStampUrl(ContractConsts.SEAL_BASE64_DATA_PREFIX + ossService.getFileContent(sealUrl));
            }
            if (StringUtils.isNotEmpty(merchantContractInfoVo.getContractUrl())) {
                response.setLenderStamp(Boolean.TRUE);
                response.setLenderStampUrl(ContractConsts.SEAL_BASE64_DATA_PREFIX + ossService.getFileContent(merchantContractInfoVo.getContractUrl()));
            }
        }
        return new DataApiResponse(response);
    }

    @SignIgnore
    @RequestMapping(value = "/license", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "授权协议详情", response = UserLicenseContractInfoResponse.class)
    public DataApiResponse<UserLicenseContractInfoResponse> getLicenseContractDetail(@RequestBody @Valid UserLicenseContractInfoRequest req) {
        String userCode = redisClient.get(RedisKeyConsts.SAAS_TOKEN_KEY, req.getToken());
        if (StringUtils.isEmpty(userCode)) {
            throw new ApplicationException(RestCodeEnum.TOKEN_NOT_AVAILABLE);
        }
        UserLicenseContractInfoResponse response = new UserLicenseContractInfoResponse();
        response.setInscribeDate(DateUtil.getDate(new Date()));
        if (saasBorrowerService.isSaasBorrower(userCode)) {
            SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(userCode);
            if (saasBorrowerRealInfoVo != null) {
                response.setUserName(saasBorrowerRealInfoVo.getName());
                response.setUserCode("身份证号：" + saasBorrowerRealInfoVo.getIdentityCode());
            }
            String sealUrl = contractApplication.getUserSealUrl(userCode);
            if (StringUtils.isNotEmpty(sealUrl)) {
                response.setUserStamp(Boolean.TRUE);
                response.setUserStampUrl(ContractConsts.SEAL_BASE64_DATA_PREFIX + ossService.getFileContent(sealUrl));
            }
            return new DataApiResponse(response);
        }
        SaasAdmin saasAdmin = saasAdminService.getSaasAdminByAdminCode(userCode);
        if (saasAdmin != null) {
            MerchantContractInfoVo merchantContractInfoVo = merchantApplication.getMerchantContractInfo(saasAdmin.getMerchantCode());
            if (ContractConfigTypeEnum.PERSONAL_CONTRACT.getKey().equals(merchantContractInfoVo.getContractType())) {
                response.setUserName(merchantContractInfoVo.getName());
                response.setUserCode("身份证号：" + merchantContractInfoVo.getCode());
            } else {
                response.setUserName(merchantContractInfoVo.getName());
                response.setUserCode("统一信用代码：" + merchantContractInfoVo.getCode());
            }
            String sealUrl = contractApplication.getUserSealUrl(userCode);
            if (StringUtils.isNotEmpty(sealUrl)) {
                response.setUserStamp(Boolean.TRUE);
                response.setUserStampUrl(ContractConsts.SEAL_BASE64_DATA_PREFIX + ossService.getFileContent(sealUrl));
            }
            if (StringUtils.isNotEmpty(merchantContractInfoVo.getContractUrl())) {
                response.setUserStamp(Boolean.TRUE);
                response.setUserStampUrl(ContractConsts.SEAL_BASE64_DATA_PREFIX + ossService.getFileContent(merchantContractInfoVo.getContractUrl()));
            }
        }
        return new DataApiResponse(response);
    }

    @SignIgnore
    @RequestMapping(value = "/loan", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "借款协议详情", response = OrderLoanContractInfoResponse.class)
    public DataApiResponse<OrderLoanContractInfoResponse> getLoanContractDetail(@RequestBody @Valid OrderLoanContractInfoRequest req) {
        String userCode = redisClient.get(RedisKeyConsts.SAAS_TOKEN_KEY, req.getToken());
        if (StringUtils.isEmpty(userCode)) {
            throw new ApplicationException(RestCodeEnum.TOKEN_NOT_AVAILABLE);
        }
        OrderLoanContractInfoResponse response = new OrderLoanContractInfoResponse();
        String borrowerCode = null;
        String merchantCode = null;
        SaasOrderVo saasOrderVo = saasOrderService.getMainSaasOrderByOrderNumb(req.getOrderNumb());
        if (saasOrderVo != null) {
            if (!userCode.equals(saasOrderVo.getBorrowerCode()) && !userCode.equals(saasOrderVo.getMerchantCode())) {
                throw new ApplicationException(OrderErrorCodeEnum.NO_PERMISSION_OPERATE_ORDER);
            }
            response.setOrderNo(saasOrderVo.getSaasOrderId() + "");
            response.setRealCapital(saasOrderVo.getRealCapital());
            response.setRealCapitalCN(NumberToCNUtil.number2CNMontrayUnit(saasOrderVo.getRealCapital()));
            response.setCreatedDt(DateUtil.getDate(new Date()));
            response.setRepaymentDt(DateUtil.getDate(saasOrderVo.getRepaymentDt()));
            response.setDeadline(DateUtil.countDay(saasOrderVo.getRepaymentDt(), new Date()));
            response.setAmount(orderCalculateApplication.getAmount(saasOrderVo));
            response.setTotalInterestRatio(saasOrderVo.getTotalInterestRatio().multiply(new BigDecimal("100")).setScale(0).toString());
            response.setInscribeDate(DateUtil.getDate(new Date()));
            borrowerCode = saasOrderVo.getBorrowerCode();
            merchantCode = saasOrderVo.getMerchantCode();
        } else {
            if (saasBorrowerService.isSaasBorrower(userCode)) {
                borrowerCode = userCode;
            } else {
                SaasAdmin saasAdmin = saasAdminService.getSaasAdminByAdminCode(userCode);
                if (saasAdmin != null) {
                    merchantCode = saasAdmin.getMerchantCode();
                }
            }
        }
        if (StringUtils.isNotEmpty(borrowerCode)) {
            SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(borrowerCode);
            if (saasBorrowerRealInfoVo != null) {
                response.setBorrowUserName(saasBorrowerRealInfoVo.getName());
                response.setBorrowIdentityNo(saasBorrowerRealInfoVo.getIdentityCode());
            }
            String sealUrl = contractApplication.getUserSealUrl(userCode);
            if (StringUtils.isNotEmpty(sealUrl)) {
                response.setBorrowStamp(Boolean.TRUE);
                response.setBorrowStampUrl(ContractConsts.SEAL_BASE64_DATA_PREFIX + ossService.getFileContent(sealUrl));
            }
        }
        if (StringUtils.isNotEmpty(merchantCode)) {
            MerchantContractInfoVo merchantContractInfoVo = merchantApplication.getMerchantContractInfo(merchantCode);
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
                response.setLenderStampUrl(ContractConsts.SEAL_BASE64_DATA_PREFIX + ossService.getFileContent(sealUrl));
            }
            if (StringUtils.isNotEmpty(merchantContractInfoVo.getContractUrl())) {
                response.setLenderStamp(Boolean.TRUE);
                response.setLenderStampUrl(ContractConsts.SEAL_BASE64_DATA_PREFIX + ossService.getFileContent(merchantContractInfoVo.getContractUrl()));
            }
        }
        response.setInscribeDate(DateUtil.getDate(new Date()));
        return new DataApiResponse(response);
    }


}
