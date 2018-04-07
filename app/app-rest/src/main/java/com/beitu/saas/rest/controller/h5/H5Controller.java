package com.beitu.saas.rest.controller.h5;

import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.app.api.ApiResponse;
import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.application.borrower.BorrowerApplication;
import com.beitu.saas.app.application.channel.SaasChannelApplication;
import com.beitu.saas.app.application.contract.ContractApplication;
import com.beitu.saas.app.application.contract.enums.ContractTypeEnum;
import com.beitu.saas.app.application.contract.thread.GenerateContractThread;
import com.beitu.saas.app.application.credit.CreditApplication;
import com.beitu.saas.app.application.credit.LoanPlatformApplication;
import com.beitu.saas.app.application.credit.vo.BorrowerEmergentContactVo;
import com.beitu.saas.app.application.credit.vo.BorrowerIdentityInfoVo;
import com.beitu.saas.app.application.credit.vo.BorrowerWorkInfoVo;
import com.beitu.saas.app.application.order.OrderApplication;
import com.beitu.saas.app.application.order.vo.OrderDetailVo;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.app.enums.H5OrderDetailButtonTypeEnum;
import com.beitu.saas.app.enums.SaasContractEnum;
import com.beitu.saas.app.enums.SaasLoanPlatformEnum;
import com.beitu.saas.auth.domain.SaasMerchantVo;
import com.beitu.saas.auth.service.SaasMerchantService;
import com.beitu.saas.borrower.client.SaasBorrowerEmergentContactService;
import com.beitu.saas.borrower.client.SaasBorrowerIdentityInfoService;
import com.beitu.saas.borrower.client.SaasBorrowerPersonalInfoService;
import com.beitu.saas.borrower.client.SaasBorrowerWorkInfoService;
import com.beitu.saas.borrower.domain.*;
import com.beitu.saas.borrower.entity.SaasBorrowerEmergentContact;
import com.beitu.saas.borrower.entity.SaasBorrowerIdentityInfo;
import com.beitu.saas.borrower.entity.SaasBorrowerPersonalInfo;
import com.beitu.saas.borrower.entity.SaasBorrowerWorkInfo;
import com.beitu.saas.borrower.enums.BorrowerErrorCodeEnum;
import com.beitu.saas.channel.domain.SaasH5ChannelVo;
import com.beitu.saas.channel.enums.ChannelErrorCodeEnum;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.consts.TermUrlConsts;
import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.common.utils.NetworkUtil;
import com.beitu.saas.common.utils.ThreadPoolUtils;
import com.beitu.saas.order.client.SaasOrderApplicationService;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.domain.SaasOrderApplicationVo;
import com.beitu.saas.order.enums.OrderStatusEnum;
import com.beitu.saas.rest.controller.h5.request.*;
import com.beitu.saas.rest.controller.h5.response.*;
import com.beitu.saas.sms.enums.SmsErrorCodeEnum;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.common.api.annotations.ParamsValidate;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.exception.common.ApplicationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author linanjun
 * @create 2018/3/21 下午10:30
 * @description
 */
@Controller
@RequestMapping("/h5")
@Api(description = "h5模块")
public class H5Controller {

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private BorrowerApplication borrowerApplication;

    @Autowired
    private OrderApplication orderApplication;

    @Autowired
    private CreditApplication creditApplication;

    @Autowired
    private SaasOrderService saasOrderService;

    @Autowired
    private SaasOrderApplicationService saasOrderApplicationService;

    @Autowired
    private SaasChannelApplication saasChannelApplication;

    @Autowired
    private SaasMerchantService saasMerchantService;

    @Autowired
    private SaasBorrowerPersonalInfoService saasBorrowerPersonalInfoService;

    @Autowired
    private SaasBorrowerIdentityInfoService saasBorrowerIdentityInfoService;

    @Autowired
    private SaasBorrowerWorkInfoService saasBorrowerWorkInfoService;

    @Autowired
    private SaasBorrowerEmergentContactService saasBorrowerEmergentContactService;

    @Autowired
    private ContractApplication contractApplication;

    @VisitorAccessible
    @ParamsValidate
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "登录", response = UserLoginSuccessResponse.class)
    public DataApiResponse<UserLoginSuccessResponse> login(@RequestBody @Valid UserLoginRequest req, HttpServletRequest request) {
        String verifyCode = redisClient.get(RedisKeyConsts.H5_SAVE_LOGIN_VERIFYCODE_KEY, req.getMobile());
        if (StringUtils.isEmpty(verifyCode)) {
            return new DataApiResponse<>(SmsErrorCodeEnum.VERIFY_CODE_FAILURE);
        }
        if (!verifyCode.equals(req.getVerifyCode())) {
            return new DataApiResponse<>(SmsErrorCodeEnum.INPUT_WRONG_VERIFY_CODE);
        }
        String channelCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        if (StringUtils.isEmpty(channelCode)) {
            return new DataApiResponse<>(ChannelErrorCodeEnum.DISABLE_CHANNEL);
        }
        String ip = null;
        try {
            ip = NetworkUtil.getIpAddress(request);
        } catch (Exception e) {

        }
        String token = borrowerApplication.login(req.getMobile(), channelCode, req.getPhoneSystem(), ip);
        redisClient.del(RedisKeyConsts.H5_SAVE_LOGIN_VERIFYCODE_KEY, req.getMobile());
        return new DataApiResponse<>(new UserLoginSuccessResponse(token));
    }

    @VisitorAccessible
    @RequestMapping(value = "/channel/info", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "渠道信息", response = UserLoginSuccessResponse.class)
    public DataApiResponse<ChannelInfoResponse> getChannelInfo() {
        String channelCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        if (StringUtils.isEmpty(channelCode)) {
            return new DataApiResponse<>(ChannelErrorCodeEnum.DISABLE_CHANNEL);
        }
        SaasH5ChannelVo saasH5ChannelVo = saasChannelApplication.getSaasChannelBychannelCode(channelCode);
        if (saasH5ChannelVo == null) {
            throw new ApplicationException(ChannelErrorCodeEnum.DISABLE_CHANNEL);
        }
        SaasMerchantVo saasMerchantVo = saasMerchantService.getByMerchantCode(saasH5ChannelVo.getMerchantCode());
        String headerTitle = "银柳SAAS";
        if (saasMerchantVo != null) {
            headerTitle = saasMerchantVo.getCompanyName();
        }
        String picTitle = "已有20000人申请";
        return new DataApiResponse<>(new ChannelInfoResponse(headerTitle, picTitle));
    }

    @RequestMapping(value = "/user/home", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户首页", response = UserHomeResponse.class)
    public DataApiResponse<UserHomeResponse> home() {
        String borrowerCode = RequestLocalInfo.getCurrentAdmin().getSaasBorrower().getBorrowerCode();
        String channelCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        Integer applyType = orderApplication.getOrderApplyStatus(borrowerCode, channelCode).getCode();
        return new DataApiResponse<>(new UserHomeResponse(applyType));
    }

    @RequestMapping(value = "/credit/list", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "风控项列表获取", response = CreditModuleListResponse.class)
    public DataApiResponse<CreditModuleListResponse> listCreditModule() {
        SaasBorrowerVo saasBorrowerVo = RequestLocalInfo.getCurrentAdmin().getSaasBorrower();
        return new DataApiResponse<>(new CreditModuleListResponse(creditApplication.listCreditModule(saasBorrowerVo.getMerchantCode(),saasBorrowerVo.getChannelCode(), saasBorrowerVo.getBorrowerCode())));
    }

    @RequestMapping(value = "/credit/apply/info/get", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取风控模块申请表信息", response = CreditApplyInfoResponse.class)
    public DataApiResponse<CreditApplyInfoResponse> getCreditApplyInfo() {
        String borrowerCode = RequestLocalInfo.getCurrentAdmin().getSaasBorrower().getBorrowerCode();
        String channelCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        String orderNumb = saasOrderService.getReviewerRefuseOrderNumb(borrowerCode, channelCode);

        CreditApplyInfoResponse response = new CreditApplyInfoResponse();

        SaasOrderApplicationVo saasOrderApplicationVo = saasOrderApplicationService.getByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);
        if (saasOrderApplicationVo != null) {
            response.setBorrowingDuration(DateUtil.countDay(saasOrderApplicationVo.getRepaymentDt(), new Date()));
            response.setBorrowPurpose(saasOrderApplicationVo.getBorrowPurpose());
            response.setRealCapital(saasOrderApplicationVo.getRealCapital());
            response.setTotalInterestRatio(saasOrderApplicationVo.getTotalInterestRatio());
        }
        response.setNeedRealName(borrowerApplication.needRealName(borrowerCode));
        if (contractApplication.needDoLicenseContractSign(borrowerCode)) {
            response.setContractTitle1(SaasContractEnum.LICENSE_CONTRACT.getMsg());
            response.setContractUrl1(configUtil.getAddressURLPrefix() + SaasContractEnum.LICENSE_CONTRACT.getUrl());
            response.setContractTitle2(SaasContractEnum.LOAN_CONTRACT.getMsg());
            response.setContractUrl2(configUtil.getAddressURLPrefix() + SaasContractEnum.LOAN_CONTRACT.getUrl());
        } else {
            response.setContractTitle1(SaasContractEnum.LOAN_CONTRACT.getMsg());
            response.setContractUrl1(configUtil.getAddressURLPrefix() + SaasContractEnum.LOAN_CONTRACT.getUrl());
        }
        return new DataApiResponse<>(response);
    }

    @ParamsValidate
    @RequestMapping(value = "/credit/apply/info/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存风控模块申请表信息", response = ApiResponse.class)
    public ApiResponse saveCreditApplyInfo(@RequestBody @Valid CreditSaveApplyInfoRequest req) {
        SaasBorrowerVo saasBorrowerVo = RequestLocalInfo.getCurrentAdmin().getSaasBorrower();
        String borrowerCode = saasBorrowerVo.getBorrowerCode();
        if (StringUtils.isNotEmpty(req.getUserName()) && StringUtils.isNotEmpty(req.getIdentityCode())) {
            if (!creditApplication.userRealNameAuth(saasBorrowerVo.getMerchantCode(), borrowerCode, req.getUserName(), req.getIdentityCode())) {
                return new ApiResponse(BorrowerErrorCodeEnum.USER_PROFILE_REAL_NAME_FAILURE);
            }
        }
        if (borrowerApplication.needRealName(borrowerCode)) {
            return new ApiResponse(BorrowerErrorCodeEnum.USER_PROFILE_NEED_REAL_NAME);
        }
        if (contractApplication.needDoLicenseContractSign(borrowerCode)) {
            ThreadPoolUtils.getTaskInstance().execute(new GenerateContractThread(contractApplication, saasOrderService, borrowerCode, null, ContractTypeEnum.BORROWER_DO_AUTHORIZATION_CONTRACT_SIGN));
        }
        String orderNumb = saasOrderService.getReviewerRefuseOrderNumb(borrowerCode, saasBorrowerVo.getChannelCode());

        SaasOrderApplicationVo addOrderApplication = new SaasOrderApplicationVo();
        addOrderApplication.setBorrowerCode(borrowerCode);
        addOrderApplication.setOrderNumb(orderNumb);
        addOrderApplication.setChannelCode(saasBorrowerVo.getChannelCode());
        addOrderApplication.setMerchantCode(saasBorrowerVo.getMerchantCode());
        addOrderApplication.setRealCapital(req.getRealCapital());
        addOrderApplication.setTotalInterestRatio(req.getTotalInterestRatio().divide(new BigDecimal(100)));
        addOrderApplication.setRepaymentDt(DateUtil.addDate(new Date(), req.getBorrowingDuration()));
        addOrderApplication.setBorrowPurpose(req.getBorrowPurpose());
        saasOrderApplicationService.save(addOrderApplication);
        return new ApiResponse("保存成功");
    }

    @RequestMapping(value = "/credit/personal/info/get", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取风控模块个人信息", response = CreditPersonalInfoResponse.class)
    public DataApiResponse<CreditPersonalInfoResponse> getCreditPersonalInfo() {
        String borrowerCode = RequestLocalInfo.getCurrentAdmin().getSaasBorrower().getBorrowerCode();

        String channelCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        String orderNumb = saasOrderService.getReviewerRefuseOrderNumb(borrowerCode, channelCode);

        SaasBorrowerPersonalInfoVo saasBorrowerPersonalInfoVo = saasBorrowerPersonalInfoService.getByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);
        return new DataApiResponse<>(new CreditPersonalInfoResponse(saasBorrowerPersonalInfoVo));
    }

    @RequestMapping(value = "/credit/personal/info/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存风控模块个人信息", response = ApiResponse.class)
    public ApiResponse saveCreditPersonalInfo(@RequestBody @Valid CreditSavePersonalInfoRequest req) {
        String borrowerCode = RequestLocalInfo.getCurrentAdmin().getSaasBorrower().getBorrowerCode();
        SaasBorrowerPersonalInfo saasBorrowerPersonalInfo = new SaasBorrowerPersonalInfo();
        BeanUtils.copyProperties(req, saasBorrowerPersonalInfo);
        saasBorrowerPersonalInfo.setBorrowerCode(borrowerCode);

        String channelCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        String orderNumb = saasOrderService.getReviewerRefuseOrderNumb(borrowerCode, channelCode);

        SaasBorrowerPersonalInfoVo saasBorrowerPersonalInfoVo = saasBorrowerPersonalInfoService.getByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);
        if (saasBorrowerPersonalInfoVo == null) {
            saasBorrowerPersonalInfoService.create(saasBorrowerPersonalInfo);
        } else {
            saasBorrowerPersonalInfo.setId(saasBorrowerPersonalInfoVo.getSaasBorrowerPersonalInfoId());
            saasBorrowerPersonalInfoService.updateById(saasBorrowerPersonalInfo);
        }
        return new ApiResponse("保存成功");
    }

    @RequestMapping(value = "/credit/identity/info/get", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取风控模块身份证信息", response = BorrowerIdentityInfoVo.class)
    public DataApiResponse<CreditIdentityInfoResponse> getCreditIdentityInfo() {
        String borrowerCode = RequestLocalInfo.getCurrentAdmin().getSaasBorrower().getBorrowerCode();

        String channelCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        String orderNumb = saasOrderService.getReviewerRefuseOrderNumb(borrowerCode, channelCode);
        SaasBorrowerIdentityInfoVo saasBorrowerIdentityInfoVo = saasBorrowerIdentityInfoService.getByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);

        String prefixUrl = configUtil.getAddressURLPrefix();
        return new DataApiResponse<>(new CreditIdentityInfoResponse(saasBorrowerIdentityInfoVo, prefixUrl));
    }

    @RequestMapping(value = "/credit/identity/info/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存风控模块身份证信息", response = ApiResponse.class)
    public ApiResponse saveCreditIdentityInfo(@RequestBody @Valid CreditSaveIdentityInfoRequest req) {
        String borrowerCode = RequestLocalInfo.getCurrentAdmin().getSaasBorrower().getBorrowerCode();
        SaasBorrowerIdentityInfo saasBorrowerIdentityInfo = new SaasBorrowerIdentityInfo();
        BeanUtils.copyProperties(req, saasBorrowerIdentityInfo);
        saasBorrowerIdentityInfo.setBorrowerCode(borrowerCode);
        String channelCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        String orderNumb = saasOrderService.getReviewerRefuseOrderNumb(borrowerCode, channelCode);
        SaasBorrowerIdentityInfoVo saasBorrowerIdentityInfoVo = saasBorrowerIdentityInfoService.getByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);
        if (saasBorrowerIdentityInfoVo == null) {
            saasBorrowerIdentityInfoService.create(saasBorrowerIdentityInfo);
        } else {
            saasBorrowerIdentityInfo.setId(saasBorrowerIdentityInfoVo.getSaasBorrowerIdentityInfoId());
            saasBorrowerIdentityInfoService.updateById(saasBorrowerIdentityInfo);
        }
        return new ApiResponse("保存成功");
    }

    @RequestMapping(value = "/credit/work/info/get", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取风控模块工作信息", response = BorrowerWorkInfoVo.class)
    public DataApiResponse<CreditWorkInfoResponse> getCreditWorkInfo() {
        String borrowerCode = RequestLocalInfo.getCurrentAdmin().getSaasBorrower().getBorrowerCode();

        String channelCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        String orderNumb = saasOrderService.getReviewerRefuseOrderNumb(borrowerCode, channelCode);
        SaasBorrowerWorkInfoVo saasBorrowerWorkInfoVo = saasBorrowerWorkInfoService.getByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);

        return new DataApiResponse<>(new CreditWorkInfoResponse(saasBorrowerWorkInfoVo));
    }

    @RequestMapping(value = "/credit/work/info/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存风控模块工作信息", response = ApiResponse.class)
    public ApiResponse saveCreditWorkInfo(@RequestBody @Valid CreditSaveWorkInfoRequest req) {
        String borrowerCode = RequestLocalInfo.getCurrentAdmin().getSaasBorrower().getBorrowerCode();
        SaasBorrowerWorkInfo saasBorrowerWorkInfo = new SaasBorrowerWorkInfo();
        BeanUtils.copyProperties(req, saasBorrowerWorkInfo);
        saasBorrowerWorkInfo.setBorrowerCode(borrowerCode);
        saasBorrowerWorkInfo.setCareer(req.getCareerType());
        String channelCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        String orderNumb = saasOrderService.getReviewerRefuseOrderNumb(borrowerCode, channelCode);
        SaasBorrowerWorkInfoVo saasBorrowerWorkInfoVo = saasBorrowerWorkInfoService.getByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);
        if (saasBorrowerWorkInfoVo == null) {
            saasBorrowerWorkInfoService.create(saasBorrowerWorkInfo);
        } else {
            saasBorrowerWorkInfo.setId(saasBorrowerWorkInfoVo.getSaasBorrowerWorkInfoId());
            saasBorrowerWorkInfoService.updateById(saasBorrowerWorkInfo);
        }
        return new ApiResponse("保存成功");
    }

    @RequestMapping(value = "/credit/emergent/contact/get", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取风控模块紧急联系人信息", response = BorrowerEmergentContactVo.class)
    public DataApiResponse<CreditEmergentContactResponse> getCreditEmergentContact() {
        String borrowerCode = RequestLocalInfo.getCurrentAdmin().getSaasBorrower().getBorrowerCode();
        String channelCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        String orderNumb = saasOrderService.getReviewerRefuseOrderNumb(borrowerCode, channelCode);
        SaasBorrowerEmergentContactVo saasBorrowerEmergentContactVo = saasBorrowerEmergentContactService.getByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);
        return new DataApiResponse<>(new CreditEmergentContactResponse(saasBorrowerEmergentContactVo));
    }

    @ParamsValidate
    @RequestMapping(value = "/credit/emergent/contact/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存风控模块紧急联系人信息", response = ApiResponse.class)
    public ApiResponse saveCreditEmergentContact(@RequestBody @Valid CreditSaveEmergentContactRequest req) {
        String borrowerCode = RequestLocalInfo.getCurrentAdmin().getSaasBorrower().getBorrowerCode();
        SaasBorrowerEmergentContact saasBorrowerEmergentContact = new SaasBorrowerEmergentContact();
        BeanUtils.copyProperties(req, saasBorrowerEmergentContact);
        saasBorrowerEmergentContact.setBorrowerCode(borrowerCode);
        String channelCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        String orderNumb = saasOrderService.getReviewerRefuseOrderNumb(borrowerCode, channelCode);
        SaasBorrowerEmergentContactVo saasBorrowerEmergentContactVo = saasBorrowerEmergentContactService.getByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);

        if (saasBorrowerEmergentContactVo == null) {
            saasBorrowerEmergentContactService.create(saasBorrowerEmergentContact);
        } else {
            saasBorrowerEmergentContact.setId(saasBorrowerEmergentContactVo.getSaasBorrowerEmergentContactId());
            saasBorrowerEmergentContactService.updateById(saasBorrowerEmergentContact);
        }
        return new ApiResponse("保存成功");
    }

    @RequestMapping(value = "/credit/submit", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "提交风控模块", response = ApiResponse.class)
    public ApiResponse submitCreditInfo() {
        String channelCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        SaasBorrowerVo saasBorrowerVo = RequestLocalInfo.getCurrentAdmin().getSaasBorrower();

        String orderNumb = saasOrderService.getReviewerRefuseOrderNumb(saasBorrowerVo.getBorrowerCode(), channelCode);

        return creditApplication.submitCreditInfo(saasBorrowerVo.getMerchantCode(), saasBorrowerVo.getBorrowerCode(), channelCode, orderNumb);
    }

    @RequestMapping(value = "/order/list", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户账单列表", response = H5OrderListResponse.class)
    public DataApiResponse<H5OrderListResponse> listOrder() {
        SaasBorrowerVo saasBorrowerVo = RequestLocalInfo.getCurrentAdmin().getSaasBorrower();
        return new DataApiResponse(new H5OrderListResponse(orderApplication.listH5Order(saasBorrowerVo.getBorrowerCode(), saasBorrowerVo.getMerchantCode())));
    }

    @RequestMapping(value = "/order/detail", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户订单详情", response = H5OrderDetailResponse.class)
    public DataApiResponse<H5OrderDetailResponse> getOrderDetail(@RequestBody @Valid QueryOrderDetailRequest req) {
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasBorrower().getMerchantCode();
        OrderDetailVo orderDetailVo = orderApplication.getOrderDetailVoByOrderNumbAndMerchantCode(req.getOrderNumb(), merchantCode);
        if (orderDetailVo == null) {
            return new DataApiResponse();
        }
        H5OrderDetailResponse response = new H5OrderDetailResponse();
        BeanUtils.copyProperties(orderDetailVo, response);
        response.setOrderNumb(req.getOrderNumb());
        StringBuilder contractUrl = new StringBuilder();
        contractUrl.append(configUtil.getAddressURLPrefix()).append(TermUrlConsts.pdfViewUrl)
                .append("?file=/").append(orderDetailVo.getTermUrl());
        StringBuilder downloadContractUrl = new StringBuilder();
        downloadContractUrl.append(configUtil.getAddressURLPrefix()).append(orderDetailVo.getTermUrl());
        if (OrderStatusEnum.TO_CONFIRM_RECEIPT.getCode().equals(orderDetailVo.getOrderStatus())) {
            response.setHeaderTitle("确认借款");
            if (contractApplication.needDoLicenseContractSign(orderDetailVo.getBorrowerCode())) {
                response.setContractTitle1(SaasContractEnum.LICENSE_CONTRACT.getMsg());
                response.setContractUrl1(configUtil.getAddressURLPrefix() + SaasContractEnum.LICENSE_CONTRACT.getUrl());
                response.setContract1DownloadUrl("");
                response.setContractTitle2(SaasContractEnum.LOAN_CONTRACT.getMsg());
                response.setContractUrl2(contractUrl.toString());
                response.setContract2DownloadUrl(downloadContractUrl.toString());
            } else {
                response.setContractTitle1(SaasContractEnum.LOAN_CONTRACT.getMsg());
                response.setContractUrl1(contractUrl.toString());
                response.setContract1DownloadUrl(downloadContractUrl.toString());
            }
            response.setVisible(Boolean.TRUE);
            response.setButtonTitle(H5OrderDetailButtonTypeEnum.CONFIRM_RECEIPT_BUTTON_TYPE.getMsg());
            response.setButtonType(H5OrderDetailButtonTypeEnum.CONFIRM_RECEIPT_BUTTON_TYPE.getCode());
        } else if (OrderStatusEnum.TO_CONFIRM_EXTEND.getCode().equals(orderDetailVo.getOrderStatus())) {
            response.setHeaderTitle("确认展期");
            response.setContractTitle1(SaasContractEnum.EXTEND_CONTRACT.getMsg());
            response.setContractUrl1(contractUrl.toString());
            response.setContract1DownloadUrl(downloadContractUrl.toString());
            response.setVisible(Boolean.TRUE);
            response.setButtonTitle(H5OrderDetailButtonTypeEnum.CONFIRM_EXTEND_BUTTON_TYPE.getMsg());
            response.setButtonType(H5OrderDetailButtonTypeEnum.CONFIRM_EXTEND_BUTTON_TYPE.getCode());
        } else {
            response.setVisible(Boolean.FALSE);
            response.setContractTitle1(SaasContractEnum.LOAN_CONTRACT.getMsg());
            response.setContractUrl1(contractUrl.toString());
            response.setContract1DownloadUrl(downloadContractUrl.toString());
            response.setHeaderTitle("订单详情");
        }
        return new DataApiResponse(response);
    }

    @RequestMapping(value = "/order/confirm/{orderNumb}/{buttonType}", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户订单详情按钮操作", response = H5OrderDetailResponse.class)
    public ApiResponse getOrderDetail(@PathVariable(value = "orderNumb") String orderNumb,
                                      @PathVariable(value = "buttonType") Integer buttonType) {
        H5OrderDetailButtonTypeEnum h5OrderDetailButtonTypeEnum = H5OrderDetailButtonTypeEnum.getByCode(buttonType);
        if (h5OrderDetailButtonTypeEnum == null) {
            return new ApiResponse("非法操作参数");
        }
        SaasBorrowerVo saasBorrowerVo = RequestLocalInfo.getCurrentAdmin().getSaasBorrower();
        switch (h5OrderDetailButtonTypeEnum) {
            case CONFIRM_EXTEND_BUTTON_TYPE:
                orderApplication.confirmExtend(saasBorrowerVo.getMerchantCode(), saasBorrowerVo.getBorrowerCode(), orderNumb);
                return new ApiResponse("签署展期合同成功");
            case CONFIRM_RECEIPT_BUTTON_TYPE:
                orderApplication.confirmReceipt(saasBorrowerVo.getMerchantCode(), saasBorrowerVo.getBorrowerCode(), orderNumb);
                return new ApiResponse("签署借款合同成功");
            default:
                return new ApiResponse("操作成功");
        }

    }

}