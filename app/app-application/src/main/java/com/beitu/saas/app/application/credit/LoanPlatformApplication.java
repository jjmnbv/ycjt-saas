package com.beitu.saas.app.application.credit;

import com.alibaba.fastjson.JSON;
import com.beitu.saas.app.application.credit.pojo.JuxinliCallbackDataPojo;
import com.beitu.saas.app.enums.SaasLoanPlatformEnum;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.consts.TimeConsts;
import com.beitu.saas.intergration.risk.RiskIntergrationService;
import com.beitu.saas.intergration.risk.dto.LoanPlatformCrawlingDto;
import com.beitu.saas.intergration.risk.dto.LoanPlatformQueryDto;
import com.beitu.saas.intergration.risk.dto.LoanPlatformTaskIdPrefixDto;
import com.beitu.saas.intergration.risk.enums.LoanPlatformCrawlingCodeEnum;
import com.beitu.saas.intergration.risk.enums.LoanPlatformEnum;
import com.beitu.saas.intergration.risk.param.LoanPlatformCrawlingParam;
import com.beitu.saas.intergration.risk.param.LoanPlatformQueryParam;
import com.beitu.saas.intergration.risk.param.LoanPlatformTaskIdPrefixParam;
import com.beitu.saas.intergration.risk.param.LoanPlatformValidatePrefixParam;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.common.utils.JSONUtils;
import com.fqgj.common.utils.MD5;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private RiskIntergrationService riskIntergrationService;
    
    public String getLoanPlatformUrl(String borrowerCode, String channelCode, SaasLoanPlatformEnum saasLoanPlatformEnum) {
        String userCode = borrowerCode;
        String website = saasLoanPlatformEnum.getWebsite();
        String value = redisClient.get(RedisKeyConsts.H5_LOAN_PLATFORM_CRAWLING, userCode, website);
        if (StringUtils.isNotEmpty(value)) {
            throw new ApplicationException("正在认证中,请稍后再试");
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
        if (!LoanPlatformCrawlingCodeEnum.SUCCESS.getCode().equals(resultDto.getCode())) {
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
        LOGGER.info(JSON.toJSONString(dto));
        
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
        String prefix = null;
        String userCode = null;
        if (taskId.contains("_")) {
            String[] prefixAndUserCode = taskId.split("_");
            prefix = prefixAndUserCode[0];
            userCode = prefixAndUserCode[1];
        }
        LoanPlatformValidatePrefixParam param = new LoanPlatformValidatePrefixParam(timestamp, userCode, website, prefix);
        if (!riskIntergrationService.validateLoanPlatformCallbackPrefix(param)) {
            return "redirect:" + "";
        }
        redisClient.set(RedisKeyConsts.H5_LOAN_PLATFORM_CRAWLING, timestamp, TimeConsts.THREE_MINUTE, userCode, website);
        return "redirect:" + configUtil.getAddressURLPrefix() + configUtil.getH5AddressURLPrefix()
                + "?channel=" + channelCode + "#/formList";
    }
    
    private Boolean validateSign(JuxinliCallbackDataPojo pojo) {
        String orgId = configUtil.getJuXinLiOrgId();
        String apiKey = configUtil.getJuXinLiApiKey();
        String token = pojo.getToken();
        String sign = pojo.getSign();
        return Objects.equals(sign, MD5.md5(orgId + apiKey + token));
    }
    
    private Boolean validateTaskIdPrefix(JuxinliCallbackDataPojo pojo) {
        String prefix = null;
        String userCode = null;
        String taskId = pojo.getTaskId();
        if (taskId.contains("_")) {
            String[] prefixAndUserCode = taskId.split("_");
            prefix = prefixAndUserCode[0];
            userCode = prefixAndUserCode[1];
        }
        String website = pojo.getWebsite();
        String timestamp = pollingRedisTimestamp(userCode, website);
        LoanPlatformValidatePrefixParam validateParam = new LoanPlatformValidatePrefixParam(timestamp, userCode, website, prefix);
        if (!riskIntergrationService.validateLoanPlatformCallbackPrefix(validateParam)) {
            return Boolean.FALSE;
        }
        redisClient.del(RedisKeyConsts.H5_LOAN_PLATFORM_CRAWLING, userCode, website);
        return Boolean.TRUE;
    }
    
    private String pollingRedisTimestamp(String userCode, String website) {
        Integer cnt = 0;
        String timestamp = null;
        while (cnt < 20 || StringUtils.isEmpty(timestamp)) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timestamp = redisClient.get(RedisKeyConsts.H5_LOAN_PLATFORM_CRAWLING, userCode, website);
            cnt++;
        }
        LOGGER.info("polling for " + cnt + "times!!!");
        return timestamp;
    }
}
