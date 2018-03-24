package com.beitu.saas.app.application.borrower;

import com.beitu.saas.app.application.channel.SaasChannelApplication;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.client.SaasBorrowerService;
import com.beitu.saas.borrower.client.SaasBorrowerTokenService;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.borrower.entity.SaasBorrower;
import com.beitu.saas.channel.domain.SaasH5ChannelVo;
import com.beitu.saas.channel.enums.ChannelErrorCodeEnum;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.base.services.redis.TimeConsts;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.exception.common.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author linanjun
 * @create 2018/3/22 上午10:32
 * @description
 */
@Service
public class BorrowerApplication {

    @Autowired
    private SaasBorrowerTokenService saasBorrowerTokenService;

    @Autowired
    private SaasBorrowerService saasBorrowerService;

    @Autowired
    private SaasChannelApplication saasChannelApplication;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;

    public SaasBorrowerVo getBorrowerByAccessToken(String token) {
        String borrowerCode = saasBorrowerTokenService.getBorrowerCodeByToken(token);
        if (StringUtils.isEmpty(borrowerCode)) {
            return null;
        }
        return saasBorrowerService.getByBorrowerCode(borrowerCode);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public String login(String mobile, String channelCode) {
        SaasH5ChannelVo saasH5ChannelVo = saasChannelApplication.getSaasChannelBychannelCode(channelCode);
        if (saasH5ChannelVo == null) {
            throw new ApplicationException(ChannelErrorCodeEnum.DISABLE_CHANNEL);
        }
        SaasBorrowerVo saasBorrowerVo = saasBorrowerService.getByMobileAndMerchantCode(mobile, saasH5ChannelVo.getMerchantCode());
        String token;
        if (saasBorrowerVo != null) {
            token = saasBorrowerTokenService.refreshToken(saasBorrowerVo.getBorrowerCode(), saasH5ChannelVo.getMerchantCode()).getToken();
        } else {
            saasBorrowerVo = new SaasBorrowerVo();
            saasBorrowerVo.setMerchantCode(saasH5ChannelVo.getMerchantCode());
            saasBorrowerVo.setChannelCode(saasH5ChannelVo.getChannelCode());
            saasBorrowerVo.setMobile(mobile);
            SaasBorrower saasBorrower = saasBorrowerService.create(saasBorrowerVo);
            token = saasBorrowerTokenService.create(saasBorrower.getBorrowerCode(), saasH5ChannelVo.getMerchantCode()).getToken();
        }
        redisClient.set(RedisKeyConsts.SAAS_TOKEN_KEY, saasBorrowerVo.getBorrowerCode(), TimeConsts.AN_HOUR, token);
        return token;
    }

    public Boolean needRealName(String borrowerCode) {
        return saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(borrowerCode) != null;
    }

}