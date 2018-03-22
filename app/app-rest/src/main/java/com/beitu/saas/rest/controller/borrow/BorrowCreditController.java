package com.beitu.saas.rest.controller.borrow;

import com.beitu.saas.app.api.ApiResponse;
import com.beitu.saas.rest.controller.borrow.request.BorrowSaveOrderRequest;
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
 * @create 2018/3/21 下午2:52
 * @description
 */
@Controller
@RequestMapping("/borrow/credit")
@Api(description = "借款人风控模块")
public class BorrowCreditController {

    @RequestMapping(value = "/personal/info/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "借款人个人信息保存", response = ApiResponse.class)
    public ApiResponse save(@RequestBody @Valid BorrowSaveOrderRequest req) {
        // TODO
        return new ApiResponse();
    }

}