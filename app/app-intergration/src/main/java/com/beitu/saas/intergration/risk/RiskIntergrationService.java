package com.beitu.saas.intergration.risk;

import com.beitu.saas.intergration.risk.dto.LoanPlatformCrawlingDto;
import com.beitu.saas.intergration.risk.dto.LoanPlatformQueryDto;
import com.beitu.saas.intergration.risk.param.LoanPlatformCrawlingParam;
import com.beitu.saas.intergration.risk.param.LoanPlatformQueryParam;

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

}
