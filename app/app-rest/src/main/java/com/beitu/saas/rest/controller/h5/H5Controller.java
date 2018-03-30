package com.beitu.saas.rest.controller.h5;

import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.app.api.ApiResponse;
import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.application.borrower.BorrowerApplication;
import com.beitu.saas.app.application.channel.SaasChannelApplication;
import com.beitu.saas.app.application.credit.CreditApplication;
import com.beitu.saas.app.application.credit.LoanPlatformApplication;
import com.beitu.saas.app.application.credit.vo.BorrowerEmergentContactVo;
import com.beitu.saas.app.application.credit.vo.BorrowerIdentityInfoVo;
import com.beitu.saas.app.application.credit.vo.BorrowerWorkInfoVo;
import com.beitu.saas.app.application.order.OrderApplication;
import com.beitu.saas.app.application.order.vo.OrderDetailVo;
import com.beitu.saas.app.common.RequestLocalInfo;
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
import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.order.client.SaasOrderApplicationService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private LoanPlatformApplication loanPlatformApplication;

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

    @VisitorAccessible
    @ParamsValidate
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "登录", response = UserLoginSuccessResponse.class)
    public DataApiResponse<UserLoginSuccessResponse> login(@RequestBody @Valid UserLoginRequest req) {
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
        String token = borrowerApplication.login(req.getMobile(), channelCode);
        redisClient.del(RedisKeyConsts.H5_SAVE_LOGIN_VERIFYCODE_KEY, req.getMobile());
        return new DataApiResponse<>(new UserLoginSuccessResponse(token));
    }

    @VisitorAccessible
    @ParamsValidate
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
        String headerTitle = "洋葱借条";
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

//    @RequestMapping(value = "/user/apply/status", method = RequestMethod.POST)
//    @ResponseBody
//    @ApiOperation(value = "获取用户订单状态", response = UserOrderStatusResponse.class)
//    public DataApiResponse<UserOrderStatusResponse> getBorrowerStatus() {
//        // TODO
//        return new DataApiResponse<>(new UserOrderStatusResponse(BorrowerOrderApplyStatusEnum.NO_SUBMIT.getType()));
//    }

    @RequestMapping(value = "/credit/list", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "风控项列表获取", response = CreditModuleListResponse.class)
    public DataApiResponse<CreditModuleListResponse> listCreditModule() {
        String channelCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        if (StringUtils.isEmpty(channelCode)) {
            return new DataApiResponse<>(ChannelErrorCodeEnum.DISABLE_CHANNEL);
        }
        String borrowerCode = RequestLocalInfo.getCurrentAdmin().getSaasBorrower().getBorrowerCode();
        return new DataApiResponse<>(new CreditModuleListResponse(creditApplication.listCreditModule(channelCode, borrowerCode)));
    }

    @RequestMapping(value = "/credit/apply/info/get", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取风控模块申请表信息", response = CreditApplyInfoResponse.class)
    public DataApiResponse<CreditApplyInfoResponse> getCreditApplyInfo() {
        String borrowerCode = RequestLocalInfo.getCurrentAdmin().getSaasBorrower().getBorrowerCode();
        CreditApplyInfoResponse response = new CreditApplyInfoResponse();
        SaasOrderApplicationVo saasOrderApplicationVo = saasOrderApplicationService.getByBorrowerCode(borrowerCode);
        if (saasOrderApplicationVo != null) {
            response.setBorrowingDuration(DateUtil.countDays(saasOrderApplicationVo.getRepaymentDt(), new Date()));
            response.setBorrowPurpose(saasOrderApplicationVo.getBorrowPurpose());
            response.setRealCapital(saasOrderApplicationVo.getRealCapital());
            response.setTotalInterestRatio(saasOrderApplicationVo.getTotalInterestRatio());
        }
        response.setNeedRealName(borrowerApplication.needRealName(borrowerCode));
        return new DataApiResponse<>(response);
    }

    @RequestMapping(value = "/credit/apply/info/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存风控模块申请表信息", response = ApiResponse.class)
    public ApiResponse saveCreditApplyInfo(@RequestBody @Valid CreditSaveApplyInfoRequest req) {
        String channelCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        if (StringUtils.isEmpty(channelCode)) {
            return new ApiResponse(ChannelErrorCodeEnum.DISABLE_CHANNEL);
        }
        String borrowerCode = RequestLocalInfo.getCurrentAdmin().getSaasBorrower().getBorrowerCode();
        if (StringUtils.isNotEmpty(req.getUserName()) && StringUtils.isNotEmpty(req.getIdentityCode())) {
            if (!creditApplication.userRealNameAuth(borrowerCode, req.getUserName(), req.getIdentityCode())) {
                return new ApiResponse(BorrowerErrorCodeEnum.USER_PROFILE_REAL_NAME_FAILURE);
            }
        }
        if (borrowerApplication.needRealName(borrowerCode)) {
            return new ApiResponse(BorrowerErrorCodeEnum.USER_PROFILE_NEED_REAL_NAME);
        }
        SaasH5ChannelVo saasH5ChannelVo = saasChannelApplication.getSaasChannelBychannelCode(channelCode);
        if (saasH5ChannelVo == null) {
            throw new ApplicationException(ChannelErrorCodeEnum.DISABLE_CHANNEL);
        }
        SaasOrderApplicationVo addOrderApplication = new SaasOrderApplicationVo();
        addOrderApplication.setBorrowerCode(borrowerCode);
        addOrderApplication.setChannelCode(saasH5ChannelVo.getChannelCode());
        addOrderApplication.setMerchantCode(saasH5ChannelVo.getMerchantCode());
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
        SaasBorrowerPersonalInfoVo saasBorrowerPersonalInfoVo = saasBorrowerPersonalInfoService.getByBorrowerCode(borrowerCode);
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
        SaasBorrowerPersonalInfoVo saasBorrowerPersonalInfoVo = saasBorrowerPersonalInfoService.getByBorrowerCode(borrowerCode);
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
        SaasBorrowerIdentityInfoVo saasBorrowerIdentityInfoVo = saasBorrowerIdentityInfoService.getByBorrowerCode(borrowerCode);
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
        SaasBorrowerIdentityInfoVo saasBorrowerIdentityInfoVo = saasBorrowerIdentityInfoService.getByBorrowerCode(borrowerCode);
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
        SaasBorrowerWorkInfoVo saasBorrowerWorkInfoVo = saasBorrowerWorkInfoService.getByBorrowerCode(borrowerCode);
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
        SaasBorrowerWorkInfoVo saasBorrowerWorkInfoVo = saasBorrowerWorkInfoService.getByBorrowerCode(borrowerCode);
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
        SaasBorrowerEmergentContactVo saasBorrowerEmergentContactVo = saasBorrowerEmergentContactService.getByBorrowerCode(borrowerCode);
        return new DataApiResponse<>(new CreditEmergentContactResponse(saasBorrowerEmergentContactVo));
    }

    @RequestMapping(value = "/credit/emergent/contact/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存风控模块紧急联系人信息", response = ApiResponse.class)
    public ApiResponse saveCreditEmergentContact(@RequestBody @Valid CreditSaveEmergentContactRequest req) {
        String borrowerCode = RequestLocalInfo.getCurrentAdmin().getSaasBorrower().getBorrowerCode();
        SaasBorrowerEmergentContact saasBorrowerEmergentContact = new SaasBorrowerEmergentContact();
        BeanUtils.copyProperties(req, saasBorrowerEmergentContact);
        saasBorrowerEmergentContact.setBorrowerCode(borrowerCode);
        SaasBorrowerEmergentContactVo saasBorrowerEmergentContactVo = saasBorrowerEmergentContactService.getByBorrowerCode(borrowerCode);
        if (saasBorrowerEmergentContactVo == null) {
            saasBorrowerEmergentContactService.create(saasBorrowerEmergentContact);
        } else {
            saasBorrowerEmergentContact.setId(saasBorrowerEmergentContactVo.getSaasBorrowerEmergentContactId());
            saasBorrowerEmergentContactService.updateById(saasBorrowerEmergentContact);
        }
        return new ApiResponse("保存成功");
    }

    @RequestMapping(value = "/credit/loan/platform/get/url", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取多平台借贷URL", response = LoanPlatformUrlResponse.class)
    public DataApiResponse<LoanPlatformUrlResponse> getLoanPlatformUrl(@RequestBody @Valid GetLoanPlatformUrlRequest req) {
        String channelCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        String borrowerCode = RequestLocalInfo.getCurrentAdmin().getSaasBorrower().getBorrowerCode();
        return new DataApiResponse<>(new LoanPlatformUrlResponse(loanPlatformApplication.getLoanPlatformUrl(borrowerCode, channelCode, SaasLoanPlatformEnum.getByCode(req.getLoanPlatformType()))));
    }

    @RequestMapping(value = "/credit/submit", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "提交风控模块", response = ApiResponse.class)
    public ApiResponse submitCreditInfo() {
        String channelCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        if (StringUtils.isEmpty(channelCode)) {
            return new ApiResponse(ChannelErrorCodeEnum.DISABLE_CHANNEL);
        }
        SaasH5ChannelVo saasH5ChannelVo = saasChannelApplication.getSaasChannelBychannelCode(channelCode);
        if (saasH5ChannelVo == null) {
            return new ApiResponse(ChannelErrorCodeEnum.DISABLE_CHANNEL);
        }
        SaasBorrowerVo saasBorrowerVo = RequestLocalInfo.getCurrentAdmin().getSaasBorrower();
        if (!saasH5ChannelVo.getMerchantCode().equals(saasBorrowerVo.getMerchantCode())) {
            return new ApiResponse(BorrowerErrorCodeEnum.NO_ACCESS_RIGHT);
        }
        return creditApplication.submitCreditInfo(saasBorrowerVo.getBorrowerCode(), saasH5ChannelVo.getChannelCode());
    }

    @RequestMapping(value = "/order/list", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户账单列表", response = H5OrderListResponse.class)
    public DataApiResponse<H5OrderListResponse> listOrder() {
        String channelCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        if (StringUtils.isEmpty(channelCode)) {
            return new DataApiResponse(ChannelErrorCodeEnum.DISABLE_CHANNEL);
        }
        SaasH5ChannelVo saasH5ChannelVo = saasChannelApplication.getSaasChannelBychannelCode(channelCode);
        if (saasH5ChannelVo == null) {
            return new DataApiResponse(ChannelErrorCodeEnum.DISABLE_CHANNEL);
        }
        SaasBorrowerVo saasBorrowerVo = RequestLocalInfo.getCurrentAdmin().getSaasBorrower();
        if (!saasH5ChannelVo.getMerchantCode().equals(saasBorrowerVo.getMerchantCode())) {
            return new DataApiResponse(BorrowerErrorCodeEnum.NO_ACCESS_RIGHT);
        }
        return new DataApiResponse(new H5OrderListResponse(orderApplication.listH5Order(saasBorrowerVo.getBorrowerCode(), saasH5ChannelVo.getMerchantCode())));
    }

    @RequestMapping(value = "/order/detail", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户订单详情", response = H5OrderDetailResponse.class)
    public DataApiResponse<H5OrderDetailResponse> getOrderDetail(@RequestBody @Valid QueryOrderDetailRequest req) {
        OrderDetailVo orderDetailVo = orderApplication.getOrderDetailVoByOrderNumb(req.getOrderNumb());
        H5OrderDetailResponse response = new H5OrderDetailResponse();
        BeanUtils.copyProperties(orderDetailVo, response);
        if (OrderStatusEnum.TO_CONFIRM_RECEIPT.getCode().equals(orderDetailVo.getOrderStatus())) {
            response.setContractTitle1("《授权协议》");
            response.setContractUrl1("");
            response.setContractTitle2("《借款合同》");
            response.setContractUrl2("");
            response.setVisible(Boolean.TRUE);
            response.setButtonTitle("查看并签署电子借款合同");
            response.setButtonUrl("");
            response.setHeaderTitle("确认借款");
        } else if (OrderStatusEnum.IN_EXTEND.getCode().equals(orderDetailVo.getOrderStatus())) {
            response.setContractTitle1("《展期合同》");
            response.setContractUrl1("");
            response.setVisible(Boolean.TRUE);
            response.setButtonTitle("查看并签署电子展期合同");
            response.setButtonUrl("");
            response.setHeaderTitle("确认展期");
        } else {
            response.setVisible(Boolean.FALSE);
            response.setHeaderTitle("订单详情");
        }
        return new DataApiResponse(new H5OrderDetailResponse());
    }

}