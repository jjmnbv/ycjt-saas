package com.beitu.saas.intergration.risk.impl;

import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.intergration.risk.RiskIntergrationService;
import com.beitu.saas.intergration.risk.dto.LoanPlatformCrawlingDto;
import com.beitu.saas.intergration.risk.dto.LoanPlatformQueryDto;
import com.beitu.saas.intergration.risk.enums.LoanPlatformCrawlingCodeEnum;
import com.beitu.saas.intergration.risk.param.LoanPlatformCrawlingParam;
import com.beitu.saas.intergration.risk.param.LoanPlatformQueryParam;
import com.fqgj.common.utils.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        String sign = MD5.md5(orgId + timeMark + website + param.getTaskId() + apiKey);
        
        StringBuilder urlSb = new StringBuilder();
        urlSb.append(configUtil.getJuXinLiApiUrl() + "/");
        urlSb.append(orgId + "/");
        urlSb.append(sign + "/");
        urlSb.append(timeMark + "/");
        urlSb.append(website + "/");
        urlSb.append(param.getTaskId() + "?");
        urlSb.append("jumpUrl=" + param.getJumpUrl());
        
        return new LoanPlatformCrawlingDto(LoanPlatformCrawlingCodeEnum.SUCCESS).setUrl(urlSb.toString());
    }
    
    @Override
    public LoanPlatformQueryDto loanPlatformQuery(LoanPlatformQueryParam param) {
        return null;
    }
    
}
