package com.beitu.saas.app.application.verifycode;

import com.beitu.saas.app.application.SendApplication;
import com.beitu.saas.app.application.verifycode.vo.VerifyCode;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.fqgj.base.services.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by linchengyu on 17/6/17.
 */
@Service
public class VerifyCodeApplication {

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private SendApplication sendApplication;

    @Autowired
    private ConfigUtil configUtil;

    public void sendVerifyCode(VerifyCode verifyCode) {

//        Boolean mobileExist = userCheckApplication.isMobileExist(verifycode.getMobile());
//        if ((VerifyCodeTypeEnum.REGISTER.equals(verifycode.getType()) || VerifyCodeTypeEnum.MODIFY_MOBILE.equals(verifycode.getType())) && mobileExist) {
//            throw new UserException(UserErrorCodeEnum.MOBILE_EXIST);
//        }
//        if (VerifyCodeTypeEnum.RESET_PASSWORD.equals(verifycode.getType()) && !mobileExist) {
//            throw new UserException(UserErrorCodeEnum.USER_NOT_EXIST);
//        }
//
//        Boolean userDeviceBinded = Boolean.FALSE;
//        if (mobileExist) {
//            userDeviceBinded = userCheckApplication.hasUserDeviceBinded(new UserLoginRegVo(verifycode.getMobile().getMobile()));
//        }
//        if (VerifyCodeTypeEnum.BIND_DEVICE.equals(verifycode.getType()) && userDeviceBinded) {
//            throw new UserException(UserErrorCodeEnum.USER_DEVICE_HAS_BINDED);
//        }
//
//        String codeValue = RandomUtil.getVerifyCode(configUtil.getVerifyCodeLength());
//        Boolean isTestServer = RequestLocalInfo.getCurrentEnvIsTest();
//        if (isTestServer != null && isTestServer) {
//            codeValue = configUtil.getVerifyCodeTestCode();
//        }
//        if (configUtil.getVerifyCodeReviewMobile().equals(verifycode.getMobile().getMobile())) {
//            codeValue = configUtil.getVerifyCodeReviewCode();
//        }
//
//        sendApplication.sendCodeAndNotifyMessage(null, verifycode.getMobile().getMobile(), codeValue, verifycode.getType());
//
//        String redisVerifyCodeKey = redisKeyGenerator(verifycode);
//        redisClient.set(redisVerifyCodeKey, codeValue, TimeConsts.FIVE_MINUTES);
    }

    public void verifyVerifyCode(VerifyCode verifyCode) {

//        String redisVerifyCodeKey = redisKeyGenerator(verifycode);
//        String redisVerifyCode = redisClient.get(redisVerifyCodeKey);
//
//        if (!verifycode.getVerifyCode().equals(redisVerifyCode)) {
//            throw new ApiErrorException(VerifycodeErrorCodeEnum.VERIFYCODE_NOT_CORRECT);
//        }
    }

    private String redisKeyGenerator(VerifyCode verifyCode) {
        return RedisKeyConsts.VERIFY_CODE_PREFIX + verifyCode.getType().getDesc() + verifyCode.getMobile().getMobile();
    }
}
