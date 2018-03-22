package com.beitu.saas.app.application.borrower;

import com.beitu.saas.app.application.channel.SaasChannelApplication;
import com.beitu.saas.borrower.client.SaasBorrowerService;
import com.beitu.saas.borrower.client.SaasBorrowerTokenService;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.domain.SaasChannelVo;
import com.fqgj.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private SaasChannelService saasChannelService;

    public SaasBorrowerVo getBorrowerByAccessToken(String token) {
        String borrowerCode = saasBorrowerTokenService.getBorrowerCodeByToken(token);
        if (StringUtils.isEmpty(borrowerCode)) {
            return null;
        }
        return saasBorrowerService.getByBorrowerCode(borrowerCode);
    }

    public String login(String mobile, String channelCode) {
        SaasChannelVo saasChannelVo = saasChannelService.getByChannelCode(channelCode);
        return "";
    }

}
