package com.beitu.saas.intergration.risk;

import com.beitu.saas.intergration.risk.dto.LoanPlatformCrawlingDto;
import com.beitu.saas.intergration.risk.dto.LoanPlatformQueryDto;
import com.beitu.saas.intergration.risk.dto.LoanPlatformTaskIdPrefixDto;
import com.beitu.saas.intergration.risk.param.LoanPlatformCrawlingParam;
import com.beitu.saas.intergration.risk.param.LoanPlatformQueryParam;
import com.beitu.saas.intergration.risk.param.LoanPlatformTaskIdPrefixParam;
import com.beitu.saas.intergration.risk.param.LoanPlatformValidatePrefixParam;

public interface RiskIntergrationService {
    
    /**
     * 获取借贷平台爬虫H5 URL地址
     *
     * @param param
     * @return
     */
    LoanPlatformCrawlingDto loanPlatformCrawlingUrl(LoanPlatformCrawlingParam param);
    
    /**
     * 获取借贷平台爬虫结果
     *
     * @param param
     * @return
     */
    LoanPlatformQueryDto loanPlatformQuery(LoanPlatformQueryParam param);
    
    /**
     * 借贷平台taskId前缀生成
     *
     * @param param
     * @return
     */
    LoanPlatformTaskIdPrefixDto generateLoanPlatformTaskIdPrefix(LoanPlatformTaskIdPrefixParam param);
    
    /**
     * 借贷平台参数签名校验
     *
     * @param param
     * @return
     */
    Boolean validateLoanPlatformCallbackPrefix(LoanPlatformValidatePrefixParam param);

}
