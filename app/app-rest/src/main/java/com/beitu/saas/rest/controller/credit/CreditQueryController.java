package com.beitu.saas.rest.controller.credit;

import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.rest.controller.credit.request.UserBaseInfoQueryRequest;
import com.beitu.saas.rest.controller.credit.response.UserBaseInfoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @RequestMapping(value = "/base", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "借款人用户基础信息", response = UserBaseInfoResponse.class)
    public DataApiResponse<UserBaseInfoResponse> queryBaseInfo(@RequestBody @Valid UserBaseInfoQueryRequest req) {
        // TODO
        return new DataApiResponse<>(new UserBaseInfoResponse());
    }


}