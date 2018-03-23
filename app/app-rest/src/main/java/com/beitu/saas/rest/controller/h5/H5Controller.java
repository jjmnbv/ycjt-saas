package com.beitu.saas.rest.controller.h5;

import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.app.api.ApiResponse;
import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.application.SendApplication;
import com.beitu.saas.app.application.borrower.BorrowerApplication;
import com.beitu.saas.app.application.credit.BorrowerBaseInfoApplication;
import com.beitu.saas.app.application.credit.CreditApplication;
import com.beitu.saas.app.application.credit.vo.BorrowerEmergentContactVo;
import com.beitu.saas.app.application.credit.vo.BorrowerIdentityInfoVo;
import com.beitu.saas.app.application.credit.vo.BorrowerWorkInfoVo;
import com.beitu.saas.app.application.order.OrderApplication;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.app.enums.BorrowerOrderApplyStatusEnum;
import com.beitu.saas.channel.enums.ChannelErrorCodeEnum;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.consts.TimeConsts;
import com.beitu.saas.rest.controller.h5.request.*;
import com.beitu.saas.rest.controller.h5.response.*;
import com.beitu.saas.sms.enums.SmsErrorCodeEnum;
import com.beitu.saas.sms.enums.VerifyCodeTypeEnum;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.common.api.annotations.ParamsValidate;
import com.fqgj.common.utils.RandomUtil;
import com.fqgj.common.utils.StrUtils;
import com.fqgj.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

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
    private BorrowerBaseInfoApplication borrowerBaseInfoApplication;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private BorrowerApplication borrowerApplication;

    @Autowired
    private OrderApplication orderApplication;

    @Autowired
    private CreditApplication creditApplication;

    @VisitorAccessible
    @SignIgnore
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
        return new DataApiResponse<>(new UserLoginSuccessResponse(borrowerApplication.login(req.getMobile(), channelCode)));
    }

    @RequestMapping(value = "/user/home", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户首页", response = UserHomeResponse.class)
    public DataApiResponse<UserHomeResponse> home() {
        String borrowerCode = RequestLocalInfo.getCurrentAdmin().getSaasBorrower().getBorrowerCode();
        String channelCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        if (StringUtils.isEmpty(channelCode)) {
            return new DataApiResponse<>(ChannelErrorCodeEnum.DISABLE_CHANNEL);
        }
        return new DataApiResponse<>(new UserHomeResponse(orderApplication.getOrderApplyStatus(borrowerCode, channelCode).getCode()));
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
    public DataApiResponse<CreditModuleListResponse> listCreditModule(@RequestBody @Valid QueryCreditModuleListRequest req) {
        String channelCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        if (StringUtils.isEmpty(channelCode)) {
            return new DataApiResponse<>(ChannelErrorCodeEnum.DISABLE_CHANNEL);
        }
        String borrowerCode = RequestLocalInfo.getCurrentAdmin().getSaasBorrower().getBorrowerCode();
        return new DataApiResponse<>(new CreditModuleListResponse(creditApplication.listCreditModule(channelCode, borrowerCode, req.getOrderNumb())));
    }

    @RequestMapping(value = "/credit/apply/info/get", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取风控模块申请表信息", response = CreditApplyInfoResponse.class)
    public DataApiResponse<CreditApplyInfoResponse> getCreditApplyInfo() {
        // TODO
        return new DataApiResponse<>(new CreditApplyInfoResponse());
    }

    @RequestMapping(value = "/credit/apply/info/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存风控模块申请表信息", response = ApiResponse.class)
    public ApiResponse saveCreditApplyInfo(@RequestBody @Valid CreditSaveApplyInfoRequest req) {
        // TODO
        return new ApiResponse();
    }

    @RequestMapping(value = "/credit/personal/info/get", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取风控模块个人信息", response = CreditPersonalInfoResponse.class)
    public DataApiResponse<CreditPersonalInfoResponse> getCreditPersonalInfo() {
        // TODO
        return new DataApiResponse<>(new CreditPersonalInfoResponse());
    }

    @RequestMapping(value = "/credit/personal/info/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存风控模块个人信息", response = ApiResponse.class)
    public ApiResponse saveCreditPersonalInfo(@RequestBody @Valid CreditSavePersonalInfoRequest req) {
        // TODO
        return new ApiResponse();
    }

    @RequestMapping(value = "/credit/identity/info/get", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取风控模块身份证信息", response = BorrowerIdentityInfoVo.class)
    public DataApiResponse<BorrowerIdentityInfoVo> getCreditIdentityInfo() {
        // TODO
        return new DataApiResponse<>(borrowerBaseInfoApplication.getUserIdentityInfoVo(""));
    }

    @RequestMapping(value = "/credit/identity/info/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存风控模块身份证信息", response = ApiResponse.class)
    public ApiResponse saveCreditIdentityInfo(@RequestBody @Valid CreditSaveIdentityInfoRequest req) {
        // TODO
        return new ApiResponse();
    }

    @RequestMapping(value = "/credit/work/info/get", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取风控模块工作信息", response = BorrowerWorkInfoVo.class)
    public DataApiResponse<BorrowerWorkInfoVo> getCreditWorkInfo() {
        // TODO
        return new DataApiResponse<>(borrowerBaseInfoApplication.getUserWorkInfoVo(""));
    }

    @RequestMapping(value = "/credit/work/info/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存风控模块工作信息", response = ApiResponse.class)
    public ApiResponse saveCreditWorkInfo(@RequestBody @Valid CreditSaveWorkInfoRequest req) {
        // TODO
        return new ApiResponse();
    }

    @RequestMapping(value = "/credit/emergent/contact/get", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取风控模块紧急联系人信息", response = BorrowerEmergentContactVo.class)
    public DataApiResponse<BorrowerEmergentContactVo> getCreditEmergentContact() {
        // TODO'
        return new DataApiResponse<>(borrowerBaseInfoApplication.getUserEmergentContactVo(""));
    }

    @RequestMapping(value = "/credit/emergent/contact/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存风控模块紧急联系人信息", response = ApiResponse.class)
    public ApiResponse saveCreditEmergentContact(@RequestBody @Valid CreditSaveEmergentContactRequest req) {
        // TODO
        return new ApiResponse();
    }


}
