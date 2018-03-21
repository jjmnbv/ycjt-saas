package com.beitu.saas.rest.controller.order;

import com.beitu.saas.app.api.ApiResponse;
import com.beitu.saas.rest.controller.order.request.PrimaryOrderCollectRequest;
import com.beitu.saas.rest.controller.order.request.PrimaryOrderDetailRequest;
import com.beitu.saas.rest.controller.order.request.PrimaryOrderQueryRequest;
import com.beitu.saas.rest.controller.order.request.PrimaryOrderRemarkSaveRequest;
import com.fqgj.common.api.Page;
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
 * @create 2018/3/21 下午3:24
 * @description
 */
@Controller
@RequestMapping("/order/primary/review")
@Api(description = "订单初审模块")
public class PrimaryReviewOrderController {

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "初审订单查询", response = PrimaryOrderQueryRequest.class)
    public ApiResponse query(@RequestBody @Valid PrimaryOrderQueryRequest req, Page page) {
        // TODO
        return new ApiResponse();
    }

    @RequestMapping(value = "/remark/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "初审订单备注保存", response = ApiResponse.class)
    public ApiResponse saveRemark(@RequestBody @Valid PrimaryOrderRemarkSaveRequest req) {
        // TODO
        return new ApiResponse();
    }

    @RequestMapping(value = "/collect", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "初审领单", response = ApiResponse.class)
    public ApiResponse collect(@RequestBody @Valid PrimaryOrderCollectRequest req) {
        // TODO
        return new ApiResponse();
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "初审订单详情查看", response = ApiResponse.class)
    public ApiResponse detail(@RequestBody @Valid PrimaryOrderDetailRequest req) {
        // TODO
        return new ApiResponse();
    }

}