package com.beitu.saas.rest.controller.credit;

import com.beitu.saas.app.annotations.SignIgnore;
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
import org.apache.commons.lang3.StringUtils;
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
    private LoanPlatformApplication loanPlatformApplication;
    
    @RequestMapping(value = "/get/url", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取多平台借贷URL", response = LoanPlatformUrlResponse.class)
    public DataApiResponse<LoanPlatformUrlResponse> getLoanPlatformUrl(@RequestBody @Valid GetLoanPlatformUrlRequest req) {
        String channelCode = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo().getChannel();
        String borrowerCode = RequestLocalInfo.getCurrentAdmin().getSaasBorrower().getBorrowerCode();
        return new DataApiResponse<>(new LoanPlatformUrlResponse(loanPlatformApplication.getLoanPlatformUrl(borrowerCode, channelCode, SaasLoanPlatformEnum.getByCode(req.getLoanPlatformType()))));
    }
    
    /**
     * 借贷平台爬取成功回调接口
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @SignIgnore
    @ResponseBody
    @RequestMapping(value = "/juxinli/callback", consumes = "application/json", method = RequestMethod.POST)
    public void loanPlatformCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("************************* 聚信立回调开始 *************************");
        request.setCharacterEncoding("UTF-8");
        String reqStr = IOUtils.toString(request.getInputStream(), "utf-8");
        String result;
        try {
            result = loanPlatformApplication.juxinliCallbackProcess(reqStr);
        } catch (Exception e) {
            write(response, "error");
            LOGGER.warn("************************* 聚信立回调处理失败:{} *************************", e);
            return;
        }
        if (StringUtils.isNotEmpty(result)) {
            write(response, "error");
            LOGGER.warn("************************* 聚信立回调处理失败:{} *************************", result);
            return;
        }
        write(response, "success");
        LOGGER.info("************************* 聚信立回调处理成功 *************************");
    }
    
    private void write(HttpServletResponse response, String responseStr) throws Exception {
        response.getWriter().write(responseStr);
        response.getWriter().flush();
    }
    
    /**
     * 聚信立认证成功跳转URL地址接口
     *
     * @param request
     * @return
     */
    @SignIgnore
    @RequestMapping("/juxinli/crawling")
    public String juxinliCrawlingCallback(HttpServletRequest request) {
        String paramString = request.getParameter("param");
        return loanPlatformApplication.juxinliCrawlingProcess(paramString);
    }
    
}
