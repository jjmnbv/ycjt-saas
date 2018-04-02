package com.beitu.saas.rest.controller.saas;

import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.app.api.ApiResponse;
import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.application.borrower.BorrowerApplication;
import com.beitu.saas.app.application.channel.SaasChannelApplication;
import com.beitu.saas.app.application.contract.ContractApplication;
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
import com.beitu.saas.rest.controller.saas.request.*;
import com.beitu.saas.rest.controller.saas.response.*;
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

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author linanjun
 * @create 2018/3/21 下午10:30
 * @description
 */
@Controller
@RequestMapping("/saas")
@Api(description = "SAAS模块进件")
public class SaasController {

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

    @Autowired
    private ContractApplication contractApplication;

    @ParamsValidate
    @RequestMapping(value = "/borrower/create", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "创建客户信息", response = SaasCreateBorrowerSuccessResponse.class)
    public DataApiResponse<SaasCreateBorrowerSuccessResponse> createBorrower(@RequestBody @Valid SaasCreateBorrowerRequest req) {
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        if (StringUtils.isEmpty(merchantCode)) {
            return new DataApiResponse<>(ChannelErrorCodeEnum.DISABLE_CHANNEL);
        }
        String channelCode = saasChannelApplication.getDefaultSaasChannelCodeByMerchantCode(merchantCode);
        String borrowerCode = borrowerApplication.createBorrower(req.getMobile(), channelCode, merchantCode);
        if (borrowerApplication.needRealName(borrowerCode)) {
            if (!creditApplication.userRealNameAuth(borrowerCode, req.getName(), req.getIdentityCode())) {
                return new DataApiResponse(BorrowerErrorCodeEnum.USER_PROFILE_REAL_NAME_FAILURE);
            }
        }
        return new DataApiResponse<>(new SaasCreateBorrowerSuccessResponse(borrowerCode));
    }

    @RequestMapping(value = "/credit/apply/info/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存风控模块申请表信息", response = ApiResponse.class)
    public ApiResponse saveCreditApplyInfo(@RequestBody @Valid SaasCreditSaveApplyInfoRequest req) {
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        if (StringUtils.isEmpty(merchantCode)) {
            return new DataApiResponse<>(ChannelErrorCodeEnum.DISABLE_CHANNEL);
        }
        String channelCode = saasChannelApplication.getDefaultSaasChannelCodeByMerchantCode(merchantCode);
        SaasOrderApplicationVo addOrderApplication = new SaasOrderApplicationVo();
        addOrderApplication.setBorrowerCode(req.getBorrowerCode());
        addOrderApplication.setChannelCode(channelCode);
        addOrderApplication.setMerchantCode(merchantCode);
        addOrderApplication.setRealCapital(req.getRealCapital());
        addOrderApplication.setTotalInterestRatio(req.getTotalInterestRatio().divide(new BigDecimal(100)));
        addOrderApplication.setRepaymentDt(DateUtil.addDate(new Date(), req.getBorrowingDuration()));
        addOrderApplication.setBorrowPurpose(req.getBorrowPurpose());
        saasOrderApplicationService.save(addOrderApplication);
        return new ApiResponse("保存成功");
    }

    @RequestMapping(value = "/credit/personal/info/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存风控模块个人信息", response = ApiResponse.class)
    public ApiResponse saveCreditPersonalInfo(@RequestBody @Valid SaasCreditSavePersonalInfoRequest req) {
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

    @RequestMapping(value = "/credit/identity/info/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存风控模块身份证信息", response = ApiResponse.class)
    public ApiResponse saveCreditIdentityInfo(@RequestBody @Valid SaasCreditSaveIdentityInfoRequest req) {
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

    @RequestMapping(value = "/credit/work/info/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存风控模块工作信息", response = ApiResponse.class)
    public ApiResponse saveCreditWorkInfo(@RequestBody @Valid SaasCreditSaveWorkInfoRequest req) {
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

    @RequestMapping(value = "/credit/emergent/contact/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存风控模块紧急联系人信息", response = ApiResponse.class)
    public ApiResponse saveCreditEmergentContact(@RequestBody @Valid SaasCreditSaveEmergentContactRequest req) {
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

}