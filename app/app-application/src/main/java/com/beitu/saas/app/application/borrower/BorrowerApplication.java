package com.beitu.saas.app.application.borrower;

import com.beitu.saas.app.application.borrower.vo.BorrowerInfoVo;
import com.beitu.saas.app.application.borrower.vo.BorrowerManagerInfoVo;
import com.beitu.saas.app.application.channel.SaasChannelApplication;
import com.beitu.saas.borrower.BorrowerInfoParam;
import com.beitu.saas.borrower.client.*;
import com.beitu.saas.borrower.domain.SaasBorrowerLoginLogVo;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.borrower.entity.SaasBorrower;
import com.beitu.saas.borrower.entity.SaasBorrowerLoginLog;
import com.beitu.saas.borrower.enums.BorrowerErrorCodeEnum;
import com.beitu.saas.borrower.vo.SaasBorrowerManagerVo;
import com.beitu.saas.channel.domain.SaasH5ChannelVo;
import com.beitu.saas.channel.enums.ChannelErrorCodeEnum;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.enums.RestCodeEnum;
import com.beitu.saas.common.utils.MobileUtil;
import com.beitu.saas.common.utils.identityNumber.vo.IdcardInfoExtractor;
import com.beitu.saas.common.utils.location.BDLocationUtils;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.base.services.redis.TimeConsts;
import com.fqgj.common.api.Page;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.HttpUtil;
import com.fqgj.common.utils.JSONUtils;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.net.util.IPAddressUtil;

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

    private static final Log LOG = LogFactory.getLog(BorrowerApplication.class);

    @Autowired
    private SaasBorrowerTokenService saasBorrowerTokenService;

    @Autowired
    private SaasBorrowerService saasBorrowerService;

    @Autowired
    private SaasBorrowerLoginLogService saasBorrowerLoginLogService;

    @Autowired
    private SaasChannelApplication saasChannelApplication;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;
    @Autowired
    private SaasBorrowerPersonalInfoService saasBorrowerPersonalInfoService;

    @Transactional(rollbackFor = RuntimeException.class)
    public String login(String mobile, String channelCode, String phoneSystem, String ip) {
        SaasH5ChannelVo saasH5ChannelVo = saasChannelApplication.getSaasChannelBychannelCode(channelCode);
        if (saasH5ChannelVo == null) {
            throw new ApplicationException(ChannelErrorCodeEnum.DISABLE_CHANNEL);
        }
        SaasBorrowerVo saasBorrowerVo = saasBorrowerService.getByMobileAndMerchantCode(mobile, saasH5ChannelVo.getMerchantCode());
        String borrowerCode;
        if (saasBorrowerVo != null) {
            borrowerCode = saasBorrowerVo.getBorrowerCode();
        } else {
            saasBorrowerVo = new SaasBorrowerVo();
            saasBorrowerVo.setMerchantCode(saasH5ChannelVo.getMerchantCode());
            saasBorrowerVo.setChannelCode(saasH5ChannelVo.getChannelCode());
            saasBorrowerVo.setMobile(mobile);
            SaasBorrower saasBorrower = saasBorrowerService.create(saasBorrowerVo);
            borrowerCode = saasBorrower.getBorrowerCode();
        }
        String token = saasBorrowerTokenService.refreshToken(borrowerCode, saasH5ChannelVo.getMerchantCode()).getToken();

        if (StringUtils.isNotEmpty(ip)) {
            SaasBorrowerLoginLog saasBorrowerLoginLog = new SaasBorrowerLoginLog();
            saasBorrowerLoginLog.setMerchantCode(saasH5ChannelVo.getMerchantCode());
            saasBorrowerLoginLog.setChannelCode(channelCode);
            saasBorrowerLoginLog.setBorrowerCode(borrowerCode);
            saasBorrowerLoginLog.setPhoneSystem(phoneSystem);
            saasBorrowerLoginLog.setLoginIp(ip);
            try {
                saasBorrowerLoginLog.setLoginIpAddress(BDLocationUtils.getGeoInfoByIpAddress(ip).get("address"));
            } catch (Exception e) {
                LOG.warn("........根据IP({})获取地址失败........", ip);
            }
            saasBorrowerLoginLogService.create(saasBorrowerLoginLog);
        }

        redisClient.set(RedisKeyConsts.SAAS_TOKEN_KEY, borrowerCode, TimeConsts.AN_HOUR, token);
        return token;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public String createBorrower(String mobile, String channelCode, String merchantCode) {
        if (channelCode == null) {
            throw new ApplicationException(ChannelErrorCodeEnum.DISABLE_CHANNEL);
        }
        if (!MobileUtil.isMobile(mobile)) {
            throw new ApplicationException(BorrowerErrorCodeEnum.ILLEGAL_MOBILE);
        }
        SaasBorrowerVo saasBorrowerVo = saasBorrowerService.getByMobileAndMerchantCode(mobile, merchantCode);
        if (saasBorrowerVo == null) {
            saasBorrowerVo = new SaasBorrowerVo();
            saasBorrowerVo.setMerchantCode(merchantCode);
            saasBorrowerVo.setChannelCode(channelCode);
            saasBorrowerVo.setMobile(mobile);
            SaasBorrower saasBorrower = saasBorrowerService.create(saasBorrowerVo);
            String borrowerCode = saasBorrower.getBorrowerCode();
            saasBorrowerTokenService.create(saasBorrower.getBorrowerCode(), merchantCode);
            return borrowerCode;
        }
        return saasBorrowerVo.getBorrowerCode();
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

    public BorrowerInfoVo getBorrowerInfoVoByBorrowerCode(String merchantCode, String borrowerCode) {
        BorrowerInfoVo borrowerInfoVo = new BorrowerInfoVo();
        SaasBorrowerVo saasBorrowerVo = saasBorrowerService.getByBorrowerCodeAndMerchantCode(borrowerCode, merchantCode);
        if (saasBorrowerVo == null) {
            throw new ApplicationException(RestCodeEnum.BORROWER_NOT_EXIST_ERROR);
        }
        borrowerInfoVo.setBorrowerMobile(saasBorrowerVo.getMobile());
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(borrowerCode);
        if (saasBorrowerRealInfoVo != null) {
            borrowerInfoVo.setBorrowerName(saasBorrowerRealInfoVo.getName());
            IdcardInfoExtractor idcardInfoExtractor = new IdcardInfoExtractor(saasBorrowerRealInfoVo.getIdentityCode());
            borrowerInfoVo.setBorrowerAge(idcardInfoExtractor.getAge());
            borrowerInfoVo.setBorrowerGender(idcardInfoExtractor.getGender());
        }
        return borrowerInfoVo;
    }


    public List<BorrowerManagerInfoVo> getBorrowerInfoListByPage(BorrowerInfoParam borrowerInfoParam, Page page) {
        List<SaasBorrowerManagerVo> borrowerInfoList = saasBorrowerService.getBorrowerInfoList(borrowerInfoParam, page);
        List<BorrowerManagerInfoVo> borrowerManagerInfoVos = new ArrayList<>();

        borrowerInfoList.stream().forEach(x -> {
            BorrowerManagerInfoVo borrowerManagerInfoVo = new BorrowerManagerInfoVo();
            BeanUtils.copyProperties(x, borrowerManagerInfoVo);

            IdcardInfoExtractor idcardInfoExtractor = new IdcardInfoExtractor(x.getIdentityCode());
            borrowerManagerInfoVo.setAge(idcardInfoExtractor.getAge());
            borrowerManagerInfoVo.setGender(idcardInfoExtractor.getGender());
            Integer recentZmScore=saasBorrowerPersonalInfoService.getRecentZmCreditScoreByBorrowerCode(x.getBorrowerCode());
            borrowerManagerInfoVo.setZmCreditScore(recentZmScore);
            SaasH5ChannelVo saasH5ChannelVo = saasChannelApplication.getSaasChannelBychannelCode(x.getChannelCode());
            if (saasH5ChannelVo != null) {
                borrowerManagerInfoVo.setChannelName(saasH5ChannelVo.getChannelName());
            }

            borrowerManagerInfoVos.add(borrowerManagerInfoVo);
        });

        return borrowerManagerInfoVos;
    }


}