package com.beitu.saas.rest.controller.finance;

import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.common.utils.HttpClientUtil;
import com.beitu.saas.finance.client.*;
import com.beitu.saas.finance.client.param.CreditHistoryQueryParam;
import com.beitu.saas.finance.client.param.SmsHistoryQueryParam;
import com.beitu.saas.finance.entity.SaasCreditHistoryEntity;
import com.beitu.saas.rest.controller.finance.request.CreditHistoryRequest;
import com.beitu.saas.rest.controller.finance.request.SmsHistoryRequest;
import com.beitu.saas.rest.controller.finance.response.AccountInfoResponse;
import com.beitu.saas.rest.controller.finance.response.CreditHistoryResponse;
import com.beitu.saas.rest.controller.finance.response.SmsHistoryResponse;
import com.beitu.saas.sms.util.HttpUtils;
import com.fqgj.common.api.Page;
import com.fqgj.common.api.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author xiaochong
 * @create 2018/3/28 下午6:23
 * @description
 */
@RestController
@RequestMapping("/finance")
@Api(description = "财务模块")
public class FinanceController {

    @Autowired
    private SaasMerchantBalanceInfoService saasMerchantBalanceInfoService;

    @Autowired
    private SaasMerchantCreditInfoService saasMerchantCreditInfoService;

    @Autowired
    private SaasMerchantSmsInfoService saasMerchantSmsInfoService;

    @Autowired
    private SaasCreditHistoryService saasCreditHistoryService;

    @Autowired
    private SaasSmsHistoryService saasSmsHistoryService;

    @RequestMapping(value = "/account/info", method = RequestMethod.GET)
    @ApiOperation(value = "账户余额", response = AccountInfoResponse.class)
    public Response info() {
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        BigDecimal balanceByMerchantCode = saasMerchantBalanceInfoService.getBalanceByMerchantCode(merchantCode);
        Long creditByMerchantCode = saasMerchantCreditInfoService.getCreditByMerchantCode(merchantCode);
        Long smsByMerchantCode = saasMerchantSmsInfoService.getSmsByMerchantCode(merchantCode);
        AccountInfoResponse accountInfoResponse = new AccountInfoResponse(balanceByMerchantCode, creditByMerchantCode, smsByMerchantCode);
        return Response.ok().putData(accountInfoResponse);
    }

    @RequestMapping(value = "/history/credit/{currentPage}/{pageSize}", method = RequestMethod.POST)
    @ApiOperation(value = "点券使用明细", response = CreditHistoryResponse.class)
    public Response creditHistory(@PathVariable(value = "currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize, @RequestBody CreditHistoryRequest request) {
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        CreditHistoryQueryParam param = new CreditHistoryQueryParam();
        param.setMerchantCode(merchantCode);
        if (null != request) {
            BeanUtils.copyProperties(request, param);
        }
        List<SaasCreditHistoryEntity> list = saasCreditHistoryService.getCreditListByParam(param, page);
        CreditHistoryResponse response = new CreditHistoryResponse(list, page);
        return Response.ok().putData(response);
    }

    @RequestMapping(value = "/history/sms/{currentPage}/{pageSize}", method = RequestMethod.POST)
    @ApiOperation(value = "短信使用明细", response = SmsHistoryResponse.class)
    public Response smsHistory(@PathVariable(value = "currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize, @RequestBody SmsHistoryRequest request) {
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        SmsHistoryQueryParam param = new SmsHistoryQueryParam();
        param.setMerchantCode(merchantCode);
        if (null != request) {
            BeanUtils.copyProperties(request, param);
        }
        List list = saasSmsHistoryService.getSmsListByParam(param, page);
        SmsHistoryResponse response = new SmsHistoryResponse(list, page);
        return Response.ok().putData(response);
    }
}
