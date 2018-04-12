package com.beitu.saas.rest.controller.borrow;

import com.beitu.saas.app.api.ApiResponse;
import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.application.borrower.BorrowerApplication;
import com.beitu.saas.rest.controller.borrow.request.BorrowSaveUserInfoRequest;
import com.beitu.saas.rest.controller.borrow.request.BorrowUserLoginRequest;
import com.beitu.saas.rest.controller.borrow.response.BorrowUserLoginSuccessResponse;
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
 * @create 2018/3/21 下午2:20
 * @description
 */
@Controller
@RequestMapping("/borrow/user")
@Api(description = "借款人用户模块")
public class BorrowUserController {

    @Autowired
    private BorrowerApplication borrowerApplication;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "借款人用户登录", response = BorrowUserLoginSuccessResponse.class)
    public DataApiResponse<BorrowUserLoginSuccessResponse> login(@RequestBody @Valid BorrowUserLoginRequest req) {
        // TODO
        return new DataApiResponse<>(new BorrowUserLoginSuccessResponse());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "借款人用户信息保存", response = ApiResponse.class)
    public ApiResponse save(@RequestBody @Valid BorrowSaveUserInfoRequest req) {
        // TODO
        return new ApiResponse();
    }


    @RequestMapping(value = "/borrowerInfoGet", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "客户管理类别", response = ApiResponse.class)
    public ApiResponse getBorrowerInfoListByPage(@RequestBody @Valid BorrowSaveUserInfoRequest req) {
        // TODO
//        borrowerApplication.getBorrowerInfoListByPage(req)
        return new ApiResponse();
    }

}