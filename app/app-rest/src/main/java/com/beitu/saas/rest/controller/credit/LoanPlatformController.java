package com.beitu.saas.rest.controller.credit;

import com.beitu.saas.app.api.ApiResponse;
import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.application.credit.LoanPlatformApplication;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.app.enums.SaasLoanPlatformEnum;
import com.beitu.saas.rest.controller.credit.request.GetLoanPlatformUrlRequest;
import com.beitu.saas.rest.controller.credit.response.CarrierH5Response;
import com.beitu.saas.rest.controller.credit.response.LoanPlatformUrlResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @author linanjun
 * @create 2018/3/30 下午5:11
 * @description
 */
@Controller
@RequestMapping("/credit/loan/platform")
public class LoanPlatformController {

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

    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "H5运营商认证地址获取接口", response = CarrierH5Response.class)
    public ApiResponse getCarrierH5() {
        return new ApiResponse();
    }

}
