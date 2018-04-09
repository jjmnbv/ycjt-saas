package com.beitu.saas.app.application.credit;

import com.beitu.saas.app.application.credit.pojo.JuxinliCallbackDataPojo;
import com.beitu.saas.app.enums.SaasLoanPlatformEnum;
import com.beitu.saas.borrower.client.SaasBorrowerLoanCrawlService;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.client.SaasBorrowerService;
import com.beitu.saas.borrower.domain.SaasBorrowerLoanCrawlVo;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.borrower.entity.SaasBorrowerRealInfo;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.consts.TimeConsts;
import com.beitu.saas.common.enums.RestCodeEnum;
import com.beitu.saas.common.handle.oss.OSSService;
import com.beitu.saas.finance.client.SaasCreditHistoryService;
import com.beitu.saas.finance.client.enums.CreditConsumeEnum;
import com.beitu.saas.intergration.risk.RiskIntergrationService;
import com.beitu.saas.intergration.risk.dto.LoanPlatformCrawlingDto;
import com.beitu.saas.intergration.risk.dto.LoanPlatformQueryDto;
import com.beitu.saas.intergration.risk.dto.LoanPlatformTaskIdPrefixDto;
import com.beitu.saas.intergration.risk.enums.LoanPlatformCrawlingCodeEnum;
import com.beitu.saas.intergration.risk.enums.LoanPlatformEnum;
import com.beitu.saas.intergration.risk.enums.LoanPlatformQueryCodeEnum;
import com.beitu.saas.intergration.risk.param.LoanPlatformCrawlingParam;
import com.beitu.saas.intergration.risk.param.LoanPlatformQueryParam;
import com.beitu.saas.intergration.risk.param.LoanPlatformTaskIdPrefixParam;
import com.beitu.saas.intergration.risk.param.LoanPlatformValidatePrefixParam;
import com.beitu.saas.intergration.risk.pojo.LoanPlatformQueryPojo;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.risk.domain.carrier.h5.enums.CarrierH5TypeEnum;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.common.utils.JSONUtils;
import com.fqgj.common.utils.MD5;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author linanjun
 * @create 2018/3/30 下午4:24
 * @description
 */
@Component
public class LoanPlatformApplication {

    private static final Log LOGGER = LogFactory.getLog(LoanPlatformApplication.class);

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private OSSService ossService;

    @Autowired
    private RiskIntergrationService riskIntergrationService;

    @Autowired
    private SaasBorrowerLoanCrawlService saasBorrowerLoanCrawlService;

    @Autowired
    private SaasBorrowerService saasBorrowerService;

    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;

    @Autowired
    private SaasCreditHistoryService saasCreditHistoryService;

    public String getLoanPlatformUrl(String borrowerCode, String channelCode, SaasLoanPlatformEnum saasLoanPlatformEnum) {
        String userCode = borrowerCode;
        String website = saasLoanPlatformEnum.getWebsite();
        String value = redisClient.get(RedisKeyConsts.H5_LOAN_PLATFORM_CRAWLING, userCode, website);
        if (StringUtils.isNotEmpty(value)) {
            throw new ApplicationException("正在认证中,请稍后再试");
        }
        if (saasBorrowerLoanCrawlService.effectivenessLoanCrawl(borrowerCode, saasLoanPlatformEnum.getCode())) {
            throw new ApplicationException("已认证，无需重复认证");
        }
        LoanPlatformCrawlingParam param = new LoanPlatformCrawlingParam();
        LoanPlatformEnum platformEnum = LoanPlatformEnum.getByCode(saasLoanPlatformEnum.getCode());
        param.setPlatformEnum(platformEnum);
        LoanPlatformTaskIdPrefixParam prefixParam = new LoanPlatformTaskIdPrefixParam(borrowerCode, platformEnum);
        LoanPlatformTaskIdPrefixDto prefixDto = riskIntergrationService.generateLoanPlatformTaskIdPrefix(prefixParam);
        if (prefixDto == null) {
            return null;
        }
        String taskId = prefixDto.getPrefix() + "_" + borrowerCode;
        param.setTaskId(taskId);

        StringBuilder urlSb = new StringBuilder();
        urlSb.append(configUtil.getApiWebPath() + "/credit/loan/platform/juxinli/crawling?param=");
        urlSb.append(taskId + "_");
        urlSb.append(saasLoanPlatformEnum.getWebsite() + "_");
        urlSb.append(prefixDto.getTimestamp() + "_");
        urlSb.append(channelCode);
        param.setJumpUrl(urlSb.toString());

        LOGGER.info("得到{}借贷平台地址......taskId:{};borrowerCode:{}", saasLoanPlatformEnum.getMsg(), taskId, borrowerCode);
        LoanPlatformCrawlingDto resultDto = riskIntergrationService.loanPlatformCrawlingUrl(param);
        if (!Objects.equals(LoanPlatformCrawlingCodeEnum.SUCCESS.getCode(), resultDto.getCode())) {
            LOGGER.warn("获取{}借贷平台地址失败......taskId:{};msg:{}", saasLoanPlatformEnum.getMsg(), taskId, resultDto.getMsg());
            throw new ApplicationException("获取借贷平台地址失败,请重试");
        }
        return resultDto.getUrl();
    }

    public String juxinliCallbackProcess(String requestString) {
        JuxinliCallbackDataPojo pojo;
        try {
            pojo = JSONUtils.json2pojoAndOffUnknownField(requestString, JuxinliCallbackDataPojo.class);
        } catch (Exception e) {
            return "数据解析失败";
        }
        if (pojo == null) {
            return "数据解析失败";
        }
        if (!pojo.validate()) {
            return "回调必要数据缺失";
        }
        if (!Objects.equals(pojo.getSuccess(), "true")) {
            return "爬取失败";
        }
        if (!validateSign(pojo)) {
            return "回调验签失败";
        }
        if (!validateTaskIdPrefix(pojo)) {
            return "回调TaskId验证不通过";
        }
        LoanPlatformQueryParam param = new LoanPlatformQueryParam(pojo.getToken());
        LoanPlatformQueryDto dto = riskIntergrationService.loanPlatformQuery(param);
        if (!Objects.equals(LoanPlatformQueryCodeEnum.SUCCESS.getCode(), dto.getCode())) {
            return "查询" + pojo.getWebsite() + "借贷平台爬虫结果失败......taskId:" + pojo.getTaskId() + ";msg:" + dto.getMsg();
        }

        SaasLoanPlatformEnum platformEnum = SaasLoanPlatformEnum.getByWebsite(pojo.getWebsite());
        String userCode = getUserCodeFromTaskId(pojo.getTaskId());
        String url = uploadLoanPlatformData(userCode, platformEnum, dto.getData());
        SaasBorrowerLoanCrawlVo vo = new SaasBorrowerLoanCrawlVo(userCode, pojo.getTaskId(), pojo.getToken(), platformEnum.getCode(), url);

        SaasBorrowerVo saasBorrowerVo = saasBorrowerService.getByBorrowerCode(userCode);
        if (saasBorrowerVo == null) {
            throw new ApplicationException(RestCodeEnum.BORROWER_NOT_EXIST_ERROR);
        }
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(saasBorrowerVo.getBorrowerCode());
        saasCreditHistoryService.addExpenditureCreditHistory(saasBorrowerVo.getMerchantCode(), saasBorrowerRealInfoVo.getName(), CreditConsumeEnum.RISK_MULTI_PLATFORM_LENDING);

        if (!saasBorrowerLoanCrawlService.addSaasBorrowerLoanCrawl(vo)) {
            return "数据库结果写入失败";
        }
        redisClient.del(RedisKeyConsts.H5_LOAN_PLATFORM_CRAWLING, userCode, pojo.getWebsite());
        return null;
    }

    public String juxinliCrawlingProcess(String paramString) {
        String taskId = null;
        String website = null;
        String timestamp = null;
        String channelCode = null;
        if (paramString.contains("_")) {
            String[] params = paramString.split("_");
            taskId = params[0] + "_" + params[1];
            website = params[2];
            timestamp = params[3];
            channelCode = params[4];
        }
        String prefix = getPrefixFromTaskId(taskId);
        String userCode = getUserCodeFromTaskId(taskId);
        LoanPlatformValidatePrefixParam param = new LoanPlatformValidatePrefixParam(timestamp, userCode, website, prefix);
        if (!riskIntergrationService.validateLoanPlatformCallbackPrefix(param)) {
            return "redirect:" + "";
        }
        redisClient.set(RedisKeyConsts.H5_LOAN_PLATFORM_CRAWLING, timestamp, TimeConsts.THREE_MINUTE, userCode, website);
        return "redirect:" + configUtil.getAddressURLPrefix() + configUtil.getH5AddressURLPrefix()
                + "?channel=" + channelCode + "#/thirdLoading";
    }

    public LoanPlatformQueryPojo getLoanPlatformData(String borrowerCode, SaasLoanPlatformEnum platformEnum) {
        SaasBorrowerLoanCrawlVo vo = saasBorrowerLoanCrawlService.getSaasBorrowerLoanCrawl(borrowerCode, platformEnum.getCode());
        if (vo == null) {
            return null;
        }
        String data = ossService.getFileContent(vo.getUrl());
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        LoanPlatformQueryPojo pojo = null;
        try {
            pojo = JSONUtils.json2pojoAndOffUnknownField(data, LoanPlatformQueryPojo.class);
        } catch (Exception e) {
        }
        return pojo;
    }

    private Boolean validateSign(JuxinliCallbackDataPojo pojo) {
        String orgId = configUtil.getJuXinLiOrgId();
        String apiKey = configUtil.getJuXinLiApiKey();
        String token = pojo.getToken();
        String sign = pojo.getSign();
        return Objects.equals(sign, MD5.md5(orgId + apiKey + token));
    }

    private Boolean validateTaskIdPrefix(JuxinliCallbackDataPojo pojo) {
        String taskId = pojo.getTaskId();
        String prefix = getPrefixFromTaskId(taskId);
        String userCode = getUserCodeFromTaskId(taskId);
        String website = pojo.getWebsite();
        String timestamp = pollingRedisTimestamp(userCode, website);
        LoanPlatformValidatePrefixParam validateParam = new LoanPlatformValidatePrefixParam(timestamp, userCode, website, prefix);
        if (!riskIntergrationService.validateLoanPlatformCallbackPrefix(validateParam)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private String pollingRedisTimestamp(String userCode, String website) {
        String timestamp = null;
        for (int i = 0; i < 40; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timestamp = redisClient.get(RedisKeyConsts.H5_LOAN_PLATFORM_CRAWLING, userCode, website);
            if (StringUtils.isNotEmpty(timestamp)) {
                break;
            }
        }
        return timestamp;
    }

    private String getPrefixFromTaskId(String taskId) {
        if (StringUtils.isEmpty(taskId)) {
            return null;
        }
        String prefix = null;
        if (taskId.contains("_")) {
            String[] prefixAndUserCode = taskId.split("_");
            prefix = prefixAndUserCode[0];
        }
        return prefix;
    }

    private String getUserCodeFromTaskId(String taskId) {
        if (StringUtils.isEmpty(taskId)) {
            return null;
        }
        String userCode = null;
        if (taskId.contains("_")) {
            String[] prefixAndUserCode = taskId.split("_");
            userCode = prefixAndUserCode[1];
        }
        return userCode;
    }

    private String uploadLoanPlatformData(String userCode, SaasLoanPlatformEnum platformEnum, String data) {
        String loanPlatformUrl = "loanPlatformData/";
        if (configUtil.isServerTest()) {
            loanPlatformUrl += "test/";
        }
        String userTime = "saas_" + userCode + "_" + new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        loanPlatformUrl += platformEnum.getWebsite() + "_" + userTime + "_" + MD5.md5(userTime + System.currentTimeMillis()) + ".json";
        return ossService.uploadFile(loanPlatformUrl, data);
    }

}
