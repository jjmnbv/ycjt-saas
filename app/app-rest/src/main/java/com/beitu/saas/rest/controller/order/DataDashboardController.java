package com.beitu.saas.rest.controller.order;

import com.beitu.saas.app.application.order.OrderApplication;
import com.beitu.saas.app.application.order.vo.DataDashboardShowVo;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.rest.controller.order.response.DataDashboardResponse;
import com.fqgj.common.api.Page;
import com.fqgj.common.api.ResponseData;
import com.fqgj.common.response.ModuleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/4/3
 * Time: 上午11:52
 */

@Controller
@RequestMapping("/orderDashboard")
@Api(description = "数据看板")
public class DataDashboardController {
    @Autowired
    private OrderApplication orderApplication;

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "数据看板", response = DataDashboardResponse.class)
    public ModuleResponse query(Page page) {
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        DataDashboardShowVo dataDashboardShowVo = orderApplication.getDataDashboardInfo(merchantCode, page);
        DataDashboardResponse dataDashboardResponse = new DataDashboardResponse(dataDashboardShowVo);
        return new ModuleResponse<>(dataDashboardResponse, page);

    }
}
