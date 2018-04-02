package com.beitu.saas.rest.controller.credit;

import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.intergration.risk.RiskIntergrationService;
import com.beitu.saas.intergration.risk.param.LoanPlatformQueryParam;
import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.application.credit.LoanPlatformApplication;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.app.enums.SaasLoanPlatformEnum;
import com.beitu.saas.rest.controller.credit.request.GetLoanPlatformUrlRequest;
import com.beitu.saas.rest.controller.credit.response.LoanPlatformUrlResponse;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author linanjun
 * @create 2018/3/30 下午5:11
 * @description
 */
@Controller
@RequestMapping("/credit/loan/platform")
public class LoanPlatformController {

    private static final Log LOGGER = LogFactory.getLog(LoanPlatformController.class);
    
    @Autowired
    private RiskIntergrationService riskIntergrationService;

    @Autowired
    private LoanPlatformApplication loanPlatformApplication;

    @RequestMapping(value = "/get/url", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取多平台借贷URL", response = LoanPlatformUrlResponse.class)
    public DataApiResponse<LoanPlatformUrlResponse> getLoanPlatformUrl(@RequestBody @Valid GetLoanPlatformUrlRequest req) {
        String channelCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        String borrowerCode = RequestLocalInfo.getCurrentAdmin().getSaasBorrower().getBorrowerCode();
        return new DataApiResponse<>(new LoanPlatformUrlResponse(loanPlatformApplication.getLoanPlatformUrl(borrowerCode, channelCode, SaasLoanPlatformEnum.getByCode(req.getLoanPlatformType()))));
    }

    @SignIgnore
    @ResponseBody
    @RequestMapping(value = "/juxinli/callback", consumes = "application/json", method = RequestMethod.POST)
    public void carrierCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("************************* 聚信立回调开始 *************************");
        request.setCharacterEncoding("UTF-8");
        String reqStr = IOUtils.toString(request.getInputStream(), "utf-8");
        LOGGER.info(reqStr);
        write(response, "success");
    
    
        // TODO: 2018/4/2 查询token 入库
    
        LoanPlatformQueryParam param = new LoanPlatformQueryParam();
        param.setToken("");
        riskIntergrationService.loanPlatformQuery(param);
        
        LOGGER.info("************************* 聚信立回调结束 *************************");
    }

    private void write(HttpServletResponse response, String responseStr) throws Exception {
        response.getWriter().write(responseStr);
        response.getWriter().flush();
    }

}
