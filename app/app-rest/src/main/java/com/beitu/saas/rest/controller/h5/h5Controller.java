package com.beitu.saas.rest.controller.h5;

import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.app.api.ApiResponse;
import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.rest.controller.borrow.request.BorrowUserLoginRequest;
import com.beitu.saas.rest.controller.borrow.response.BorrowUserLoginSuccessResponse;
import com.beitu.saas.rest.controller.h5.request.UserLoginRequest;
import com.beitu.saas.rest.controller.h5.request.VerifyCodeSendRequest;
import com.beitu.saas.rest.controller.h5.response.UserHomeResponse;
import com.beitu.saas.rest.controller.h5.response.UserLoginSuccessResponse;
import com.fqgj.base.services.redis.RedisClient;
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
 * @create 2018/3/21 下午10:30
 * @description
 */
@Controller
@RequestMapping("/h5")
@Api(description = "h5模块")
public class H5Controller {

    @Autowired
    private RedisClient redisClient;

    @VisitorAccessible
    @RequestMapping(value = "/user/verifyCode/send", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取验证码", response = ApiResponse.class)
    public ApiResponse sendVerifyCode(@RequestBody @Valid VerifyCodeSendRequest req) {
        return new ApiResponse();
    }

    @VisitorAccessible
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "登录", response = UserLoginSuccessResponse.class)
    public DataApiResponse<UserLoginSuccessResponse> login(@RequestBody @Valid UserLoginRequest req) {
        // TODO
        return new DataApiResponse<>(new UserLoginSuccessResponse());
    }

    @RequestMapping(value = "/user/home", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户首页", response = UserHomeResponse.class)
    public DataApiResponse<UserHomeResponse> home() {
        // TODO
        return new DataApiResponse<>(new UserHomeResponse());
    }

    @RequestMapping(value = "/credit/list", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "风控项列表获取", response = UserHomeResponse.class)
    public DataApiResponse<UserHomeResponse> listCreditModule() {
        // TODO
        return new DataApiResponse<>(new UserHomeResponse());
    }

}
