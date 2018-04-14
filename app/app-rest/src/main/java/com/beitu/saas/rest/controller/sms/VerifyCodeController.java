package com.beitu.saas.rest.controller.sms;

import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.app.api.ApiResponse;
import com.beitu.saas.app.application.SendApplication;
import com.beitu.saas.app.application.channel.SaasChannelApplication;
import com.beitu.saas.app.common.RequestBasicInfo;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.auth.domain.SaasMerchantVo;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.auth.service.SaasAdminService;
import com.beitu.saas.auth.service.SaasMerchantService;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.consts.TimeConsts;
import com.beitu.saas.rest.controller.sms.request.VerifyCodeSendRequest;
import com.beitu.saas.sms.enums.SmsErrorCodeEnum;
import com.beitu.saas.sms.enums.VerifyCodeTypeEnum;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.common.api.annotations.ParamsValidate;
import com.fqgj.common.api.exception.ApiErrorException;
import com.fqgj.common.utils.RandomUtil;
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

@Controller
@RequestMapping("/verifyCode")
@Api(description = "验证码模块")
public class VerifyCodeController {

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private SendApplication sendApplication;

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private SaasChannelApplication saasChannelApplication;

    @Autowired
    private SaasAdminService saasAdminService;

    @Autowired
    private SaasMerchantService saasMerchantService;

    @VisitorAccessible
    @ParamsValidate
    @ResponseBody
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ApiOperation(value = "获取验证码", response = ApiResponse.class)
    public ApiResponse sendVerifyCode(@RequestBody @Valid VerifyCodeSendRequest req) {
        RequestBasicInfo requestBasicInfo = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo();
        String mobile = req.getMobile();
        if (!redisClient.setnx(RedisKeyConsts.H5_LOGIN_VERIFYCODE_KEY, mobile, mobile)) {
            return new ApiResponse(SmsErrorCodeEnum.REPEAT_REQUEST);
        }
        redisClient.expire(RedisKeyConsts.H5_LOGIN_VERIFYCODE_KEY, TimeConsts.ONE_MINUTE, mobile);
        String verifyCode = RandomUtil.getVerifyCode(configUtil.getVerifyCodeLength());
        if (configUtil.isServerTest()) {
            verifyCode = configUtil.getVerifyCodeTestCode();
        }
        if (configUtil.getVerifyCodeReviewMobile().equals(mobile)) {
            verifyCode = configUtil.getVerifyCodeReviewCode();
        }
        VerifyCodeTypeEnum type = VerifyCodeTypeEnum.getEnumByName(req.getType());
        String sign = null;
        String merchantCode = null;
        if (requestBasicInfo.getPlatform().equals("h5")&& StringUtils.isNotEmpty(requestBasicInfo.getChannel())) {
            SaasMerchantVo saasMerchantVo = saasChannelApplication.getMerchantByChannelCode(requestBasicInfo.getChannel());
            if (null != saasMerchantVo) {
                sign = saasMerchantVo.getCompanyName();
                merchantCode=saasMerchantVo.getMerchantCode();
            }
        } else if (requestBasicInfo.getPlatform().equals("web")) {
            SaasAdmin saasAdmin = saasAdminService.getSaasAdminByMoblie(mobile);
            if (null == saasAdmin) {
                throw new ApiErrorException("号码不存在");
            }
            sign = saasMerchantService.getByMerchantCode(saasAdmin.getMerchantCode()).getCompanyName();
            merchantCode=saasAdmin.getMerchantCode();
        }

        sendApplication.sendVerifyCode(mobile, verifyCode, sign,merchantCode,type);
        redisClient.set(RedisKeyConsts.H5_SAVE_LOGIN_VERIFYCODE_KEY, verifyCode, TimeConsts.TWO_MINUTE, mobile);
        return new ApiResponse();
    }

}
