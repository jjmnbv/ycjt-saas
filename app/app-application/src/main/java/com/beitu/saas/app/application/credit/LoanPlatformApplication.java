package com.beitu.saas.app.application.credit;

import com.beitu.saas.app.enums.SaasLoanPlatformEnum;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.intergration.risk.RiskIntergrationService;
import com.beitu.saas.intergration.risk.dto.LoanPlatformCrawlingDto;
import com.beitu.saas.intergration.risk.enums.LoanPlatformCrawlingCodeEnum;
import com.beitu.saas.intergration.risk.enums.LoanPlatformEnum;
import com.beitu.saas.intergration.risk.param.LoanPlatformCrawlingParam;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private RiskIntergrationService riskIntergrationService;

    public String getLoanPlatformUrl(String borrowerCode, String channelCode, SaasLoanPlatformEnum saasLoanPlatformEnum) {
        LoanPlatformCrawlingParam param = new LoanPlatformCrawlingParam();
        param.setPlatformEnum(LoanPlatformEnum.getByCode(saasLoanPlatformEnum.getCode()));
        param.setJumpUrl(configUtil.getH5AddressURLPrefix() + "?channel=" + channelCode + "/formList");
        String taskId = System.currentTimeMillis() + borrowerCode;
        param.setTaskId(taskId);
        LOGGER.info("得到{}借贷平台地址......taskId:{};borrowerCode:{}", saasLoanPlatformEnum.getMsg(), taskId, borrowerCode);
        LoanPlatformCrawlingDto resultDto = riskIntergrationService.loanPlatformCrawlingUrl(param);
        if (!LoanPlatformCrawlingCodeEnum.SUCCESS.getCode().equals(resultDto.getCode())) {
            LOGGER.warn("获取{}借贷平台地址失败......taskId:{};msg:{}", saasLoanPlatformEnum.getMsg(), taskId, resultDto.getMsg());
            throw new ApplicationException("获取借贷平台地址失败,请重试");
        }
        return resultDto.getUrl();
    }

}
