package com.beitu.saas.intergration.risk.impl;

import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.utils.HttpClientUtil;
import com.beitu.saas.intergration.risk.RiskIntergrationService;
import com.beitu.saas.intergration.risk.consts.JuxinliApiCodeConst;
import com.beitu.saas.intergration.risk.dto.LoanPlatformCrawlingDto;
import com.beitu.saas.intergration.risk.dto.LoanPlatformQueryDto;
import com.beitu.saas.intergration.risk.dto.LoanPlatformTaskIdPrefixDto;
import com.beitu.saas.intergration.risk.enums.LoanPlatformCrawlingCodeEnum;
import com.beitu.saas.intergration.risk.enums.LoanPlatformQueryCodeEnum;
import com.beitu.saas.intergration.risk.param.LoanPlatformCrawlingParam;
import com.beitu.saas.intergration.risk.param.LoanPlatformQueryParam;
import com.beitu.saas.intergration.risk.param.LoanPlatformTaskIdPrefixParam;
import com.beitu.saas.intergration.risk.param.LoanPlatformValidatePrefixParam;
import com.beitu.saas.intergration.risk.pojo.LoanPlatformQueryPojo;
import com.fqgj.common.utils.JSONUtils;
import com.fqgj.common.utils.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Service
public class RiskIntergrationServiceImpl implements RiskIntergrationService {
    
    @Autowired
    private ConfigUtil configUtil;
    
    @Override
    public LoanPlatformCrawlingDto loanPlatformCrawlingUrl(LoanPlatformCrawlingParam param) {
        if (param == null) {
            return new LoanPlatformCrawlingDto(LoanPlatformCrawlingCodeEnum.PARAM_ERROR, "输入参数为空");
        }
        String paramValidateResult = param.validate();
        if (StringUtils.isNotEmpty(paramValidateResult)) {
            return new LoanPlatformCrawlingDto(LoanPlatformCrawlingCodeEnum.PARAM_ERROR, paramValidateResult);
        }
        
        String orgId = configUtil.getJuXinLiOrgId();
        String timeMark = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String website = param.getPlatformEnum().getWebsite();
        String apiKey = configUtil.getJuXinLiApiKey();
        String taskId = param.getTaskId();
        String sign = MD5.md5(orgId + timeMark + website + taskId + apiKey);
        
        String url = configUtil.getJuXinLiApiUrl() + configUtil.getJuXinLiCrawlingPath();
        StringBuilder urlSb = new StringBuilder();
        urlSb.append(url + "/");
        urlSb.append(orgId + "/");
        urlSb.append(sign + "/");
        urlSb.append(timeMark + "/");
        urlSb.append(website + "/");
        urlSb.append(taskId + "?");
        urlSb.append("jumpUrl=" + param.getJumpUrl());
        
        return new LoanPlatformCrawlingDto(LoanPlatformCrawlingCodeEnum.SUCCESS).setUrl(urlSb.toString());
    }
    
    @Override
    public LoanPlatformQueryDto loanPlatformQuery(LoanPlatformQueryParam param) {
        if (param == null) {
            return new LoanPlatformQueryDto(LoanPlatformQueryCodeEnum.PARAM_ERROR, "输入参数为空");
        }
        String paramValidateResult = param.validate();
        if (StringUtils.isNotEmpty(paramValidateResult)) {
            return new LoanPlatformQueryDto(LoanPlatformQueryCodeEnum.PARAM_ERROR, paramValidateResult);
        }
        
        String orgId = configUtil.getJuXinLiOrgId();
        String apiKey = configUtil.getJuXinLiApiKey();
        String sign = MD5.md5(orgId + apiKey);
    
        String url = configUtil.getJuXinLiApiUrl() + configUtil.getJuXinLiQueryPath();
        StringBuilder urlSb = new StringBuilder();
        urlSb.append(url + "?");
        urlSb.append("orgId=" + orgId + "&");
        urlSb.append("sign=" + sign + "&");
        urlSb.append("token=" + param.getToken());
        String response = HttpClientUtil.doGet(urlSb.toString());
        if (response.contains("<")) {
            return new LoanPlatformQueryDto(LoanPlatformQueryCodeEnum.RESPONSE_ERROR, "接口返回异常");
        }
    
        LoanPlatformQueryPojo pojo = null;
        try {
            pojo = JSONUtils.json2pojoAndOffUnknownField(response, LoanPlatformQueryPojo.class);
        } catch (Exception e) {
        }
        if (pojo == null) {
            return new LoanPlatformQueryDto(LoanPlatformQueryCodeEnum.RESPONSE_ERROR, "接口返回数据异常");
        }
        
        if (!pojo.getSuccess() || !Objects.equals(JuxinliApiCodeConst.SUCCESS, pojo.getCode())) {
            return new LoanPlatformQueryDto(LoanPlatformQueryCodeEnum.RESULT_ERROR, pojo.getMsg());
        }
        if (pojo.getDetail() == null) {
            return new LoanPlatformQueryDto(LoanPlatformQueryCodeEnum.RESULT_ERROR, "接口返回数据为空");
        }
        
        return new LoanPlatformQueryDto(LoanPlatformQueryCodeEnum.SUCCESS)
                .setData(response)
                .setDetailInfo(pojo.getDetail());
    }
    
    @Override
    public LoanPlatformTaskIdPrefixDto generateLoanPlatformTaskIdPrefix(LoanPlatformTaskIdPrefixParam param) {
        if (param == null || !param.validate()) {
            return null;
        }
        String userCode = param.getUserCode();
        String website = param.getPlatformEnum().getWebsite();
        String orgId = configUtil.getJuXinLiOrgId();
        String apiKey = configUtil.getJuXinLiApiKey();
        String timeMark = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String prefix = signParams(timeMark, orgId, userCode, apiKey, website);
        return new LoanPlatformTaskIdPrefixDto(timeMark, prefix);
    }
    
    @Override
    public Boolean validateLoanPlatformCallbackPrefix(LoanPlatformValidatePrefixParam param) {
        if (param == null || !param.validate()) {
            return Boolean.FALSE;
        }
        String userCode = param.getUserCode();
        String website = param.getWebsite();
        String orgId = configUtil.getJuXinLiOrgId();
        String apiKey = configUtil.getJuXinLiApiKey();
        String timeMark = param.getTimestamp();
        String prefix = param.getPrefix();
        return Objects.equals(prefix, signParams(timeMark, orgId, userCode, apiKey, website));
    }
    
    private String signParams(String timeMark, String orgId, String userCode, String apiKey, String website) {
        return MD5.md5(timeMark + orgId + userCode + apiKey + website);
    }
}
