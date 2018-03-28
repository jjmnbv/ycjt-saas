package com.beitu.saas.app.application.borrower;

import com.beitu.saas.app.application.borrower.vo.BorrowerInfoVo;
import com.beitu.saas.app.application.channel.SaasChannelApplication;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.client.SaasBorrowerService;
import com.beitu.saas.borrower.client.SaasBorrowerTokenService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.borrower.entity.SaasBorrower;
import com.beitu.saas.channel.domain.SaasH5ChannelVo;
import com.beitu.saas.channel.enums.ChannelErrorCodeEnum;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.utils.identityNumber.vo.IdcardInfoExtractor;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.base.services.redis.TimeConsts;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.exception.common.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(borrowerCode) == null;
    }

    /**
     * 得到 用户CODE
     *
     * @param mobile       手机号
     * @param name         姓名
     * @param identityCode 身份证号
     * @param merchantCode 机构号
     * @return 为null 表示查询结果为null；不为null 表示查询参数存在
     */
    public List<String> listBorrowerCodeByMobileAndNameAndIdentityCode(String mobile, String name, String identityCode, String merchantCode) {
        List<String> borrowerCodeList = new ArrayList<>(4);
        if (StringUtils.isNotEmpty(mobile)) {
            SaasBorrowerVo saasBorrowerVo = saasBorrowerService.getByMobileAndMerchantCode(mobile, merchantCode);
            if (saasBorrowerVo == null) {
                return null;
            }
            borrowerCodeList.add(saasBorrowerVo.getBorrowerCode());
        }
        if (StringUtils.isNotEmpty(identityCode)) {
            SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByIdentityCodeAndMerchantCode(identityCode, merchantCode);
            if (saasBorrowerRealInfoVo == null) {
                return null;
            }
            if (borrowerCodeList.size() == 0) {
                borrowerCodeList.add(saasBorrowerRealInfoVo.getBorrowerCode());
            } else if (borrowerCodeList.size() > 0 && !borrowerCodeList.contains(saasBorrowerRealInfoVo.getBorrowerCode())) {
                return null;
            }
        }
        if (StringUtils.isNotEmpty(name)) {
            List<SaasBorrowerRealInfoVo> saasBorrowerRealInfoVoList = saasBorrowerRealInfoService.listBorrowerRealInfoByNameAndMerchantCode(name, merchantCode);
            if (CollectionUtils.isEmpty(saasBorrowerRealInfoVoList)) {
                return null;
            }
            if (borrowerCodeList.size() == 0) {
                borrowerCodeList.addAll(saasBorrowerRealInfoVoList.stream().map(SaasBorrowerRealInfoVo::getBorrowerCode).collect(Collectors.toList()));
            } else if (borrowerCodeList.size() > 0 && !borrowerCodeList.contains(borrowerCodeList.get(0))) {
                return null;
            }
        }
        return borrowerCodeList;
    }

    public BorrowerInfoVo getBorrowerInfoVoByBorrowerCode(String borrowerCode) {
        BorrowerInfoVo borrowerInfoVo = new BorrowerInfoVo();
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(borrowerCode);
        if (saasBorrowerRealInfoVo != null) {
            borrowerInfoVo.setBorrowerName(saasBorrowerRealInfoVo.getName());
            IdcardInfoExtractor idcardInfoExtractor = new IdcardInfoExtractor(saasBorrowerRealInfoVo.getIdentityCode());
            borrowerInfoVo.setBorrowerAge(idcardInfoExtractor.getAge());
            borrowerInfoVo.setBorrowerGender(idcardInfoExtractor.getGender());
        }
        SaasBorrowerVo saasBorrowerVo = saasBorrowerService.getByBorrowerCode(borrowerCode);
        borrowerInfoVo.setBorrowerMobile(saasBorrowerVo.getMobile());
        return borrowerInfoVo;
    }


}