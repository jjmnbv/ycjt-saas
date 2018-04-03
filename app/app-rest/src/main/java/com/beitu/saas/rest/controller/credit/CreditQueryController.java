package com.beitu.saas.rest.controller.credit;

import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.application.borrower.BorrowerApplication;
import com.beitu.saas.app.application.credit.BorrowerBaseInfoApplication;
import com.beitu.saas.app.application.credit.vo.*;
import com.beitu.saas.app.application.order.OrderApplication;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.domain.SaasOrderVo;
import com.beitu.saas.order.enums.OrderErrorCodeEnum;
import com.beitu.saas.rest.controller.credit.request.UserBaseInfoQueryRequest;
import com.beitu.saas.rest.controller.credit.response.UserBaseInfoResponse;
import com.fqgj.exception.common.ApplicationException;
import io.swagger.annotations.Api;
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
 * @create 2018/3/21 下午10:04
 * @description
 */
@Controller
@RequestMapping("/credit/query")
@Api(description = "借款人风控查询模块")
public class CreditQueryController {

    @Autowired
    private BorrowerBaseInfoApplication borrowerBaseInfoApplication;

    @Autowired
    private SaasOrderService saasOrderService;

    @RequestMapping(value = "/base", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "借款人用户基础信息", response = UserBaseInfoResponse.class)
    public DataApiResponse<UserBaseInfoResponse> queryBaseInfo(@RequestBody @Valid UserBaseInfoQueryRequest req) {
        String orderNumb = req.getOrderNumb();
        String borrowerCode = getBorrowerCodeByOrderNumb(orderNumb);

        BorrowerPersonalInfoVo borrowerPersonalInfoVo = borrowerBaseInfoApplication.getUserPersonalInfoVo(borrowerCode, orderNumb);
        BorrowerIdentityInfoVo borrowerIdentityInfoVo = borrowerBaseInfoApplication.getUserIdentityInfoVo(borrowerCode, orderNumb);
        BorrowerWorkInfoVo borrowerWorkInfoVo = borrowerBaseInfoApplication.getUserWorkInfoVo(borrowerCode, orderNumb);
        BorrowerEmergentContactVo borrowerEmergentContactVo = borrowerBaseInfoApplication.getUserEmergentContactVo(borrowerCode, orderNumb);
        BorrowerLivingAreaVo borrowerLivingAreaVo = borrowerBaseInfoApplication.getUserLivingAreaVo(borrowerCode, orderNumb);

        return new DataApiResponse<>(new UserBaseInfoResponse(borrowerPersonalInfoVo, borrowerIdentityInfoVo, borrowerWorkInfoVo, borrowerEmergentContactVo, borrowerLivingAreaVo));
    }

    private String getBorrowerCodeByOrderNumb(String orderNumb) {
        SaasOrderVo saasOrderVo = saasOrderService.getByOrderNumb(orderNumb);
        if (saasOrderVo == null) {
            throw new ApplicationException(OrderErrorCodeEnum.ERROR_ORDER_NUMB);
        }
        return saasOrderVo.getBorrowerCode();
    }

}