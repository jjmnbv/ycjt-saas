package com.beitu.saas.rest.controller;

import com.alibaba.fastjson.JSON;
import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.app.application.credit.CreditApplication;
import com.beitu.saas.app.application.finance.SaasConsumeDayStatApplication;
import com.beitu.saas.intergration.risk.RiskIntergrationService;
import com.beitu.saas.intergration.risk.dto.LoanPlatformCrawlingDto;
import com.beitu.saas.intergration.risk.dto.LoanPlatformQueryDto;
import com.beitu.saas.intergration.risk.enums.LoanPlatformEnum;
import com.beitu.saas.intergration.risk.param.LoanPlatformCrawlingParam;
import com.beitu.saas.intergration.risk.param.LoanPlatformQueryParam;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaochong
 * @create 2017/11/6 下午4:24
 * @description
 */
@RestController
public class OkController {

    private static final Log LOGGER = LogFactory.getLog(OkController.class);

    @Autowired
    private RiskIntergrationService riskIntergrationService;
    @Autowired
    private SaasConsumeDayStatApplication saasConsumeDayStatApplication;

    @RequestMapping("/ok")
    @ResponseBody
    @VisitorAccessible
    @SignIgnore
    public String ok() {
        return "ok";
    }

    @RequestMapping("/stat")
    @ResponseBody
    @VisitorAccessible
    @SignIgnore
    public String stat() {
        LoanPlatformCrawlingParam param = new LoanPlatformCrawlingParam();
        param.setTaskId("jdfsklajkfjalsjfadslfksdj");
        param.setJumpUrl("https://www.baidu.com");
        param.setPlatformEnum(LoanPlatformEnum.WU_YOU_JIE_TIAO);
        LoanPlatformCrawlingDto dto = riskIntergrationService.loanPlatformCrawlingUrl(param);
        return JSON.toJSONString(dto);

//        LoanPlatformQueryParam param = new LoanPlatformQueryParam("964e45dd5f984d6ea2a2f4546973f8e9");
//        LoanPlatformQueryDto dto = riskIntergrationService.loanPlatformQuery(param);
//        return JSON.toJSONString(dto);
    }

    @RequestMapping(value = "/syncTask", method = RequestMethod.POST)
    @ResponseBody
    @VisitorAccessible
    @SignIgnore
    public String syncTask() {
        saasConsumeDayStatApplication.creditAndMsgDayClear();
        return "ok";
    }

}
