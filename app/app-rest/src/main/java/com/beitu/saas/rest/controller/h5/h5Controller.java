package com.beitu.saas.rest.controller.h5;

import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.rest.controller.borrow.request.BorrowUserLoginRequest;
import com.beitu.saas.rest.controller.borrow.response.BorrowUserLoginSuccessResponse;
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
 * @create 2018/3/21 下午10:30
 * @description
 */
@Controller
@RequestMapping("/h5")
@Api(description = "h5模块")
public class h5Controller {

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "借款人用户登录", response = BorrowUserLoginSuccessResponse.class)
    public DataApiResponse<BorrowUserLoginSuccessResponse> login(@RequestBody @Valid BorrowUserLoginRequest req) {
        // TODO
        return new DataApiResponse<>(new BorrowUserLoginSuccessResponse());
    }

    @RequestMapping(value = "/user/verifyCode/send", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "借款人用户登录", response = BorrowUserLoginSuccessResponse.class)
    public DataApiResponse<BorrowUserLoginSuccessResponse> login1(@RequestBody @Valid BorrowUserLoginRequest req) {
        // TODO
        return new DataApiResponse<>(new BorrowUserLoginSuccessResponse());
    }

}
