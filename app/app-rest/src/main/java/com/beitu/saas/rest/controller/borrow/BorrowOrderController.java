package com.beitu.saas.rest.controller.borrow;

import com.beitu.saas.app.api.ApiResponse;
import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.rest.controller.borrow.request.BorrowSaveOrderRequest;
import com.beitu.saas.rest.controller.borrow.request.BorrowSubmitOrderRequest;
import com.beitu.saas.rest.controller.borrow.response.BorrowOrderSaveSuccessResponse;
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
 * @create 2018/3/21 下午2:18
 * @description
 */
@Controller
@RequestMapping("/borrow/order")
@Api(description = "借款人订单模块")
public class BorrowOrderController {

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "订单信息保存", response = BorrowOrderSaveSuccessResponse.class)
    public DataApiResponse<BorrowOrderSaveSuccessResponse> save(@RequestBody @Valid BorrowSaveOrderRequest req) {
        // TODO
        return new DataApiResponse<>(new BorrowOrderSaveSuccessResponse());
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "提交订单信息", response = ApiResponse.class)
    public ApiResponse submit(@RequestBody @Valid BorrowSubmitOrderRequest req) {
        // TODO
        return new ApiResponse();
    }

}
