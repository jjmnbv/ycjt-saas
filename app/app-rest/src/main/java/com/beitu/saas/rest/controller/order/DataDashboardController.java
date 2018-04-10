package com.beitu.saas.rest.controller.order;

import com.beitu.saas.app.application.order.OrderApplication;
import com.beitu.saas.app.application.order.vo.DashboardOverdueOrderShowVo;
import com.beitu.saas.app.application.order.vo.DataDashboardLoanShowVo;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.rest.controller.order.response.DataDashboardLoanResponse;
import com.beitu.saas.rest.controller.order.response.DataDashboardOverdueResponse;
import com.fqgj.common.api.Page;
import com.fqgj.common.api.Response;
import com.fqgj.common.response.ModuleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @RequestMapping(value = "/loan/query", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "放款数据", response = DataDashboardLoanResponse.class)
    public Response loanQuery() {
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        DataDashboardLoanShowVo dataDashboardLoanShowVo = orderApplication.getDataDashboardInfo(merchantCode);
        DataDashboardLoanResponse dataDashboardLoanResponse = new DataDashboardLoanResponse(dataDashboardLoanShowVo);
        return Response.ok().putData(dataDashboardLoanResponse);

    }

    @RequestMapping(value = "overdue/query/{menuType}", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "逾期数据-menuType:0-待收 1-逾期", response = DataDashboardOverdueResponse.class)
    public ModuleResponse overdueQuery(@PathVariable(value = "menuType") Integer menuType, Page page) {
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        List<DashboardOverdueOrderShowVo> dashboardOverdueOrderShowVos = orderApplication.getDataDashboardOverdueShowInfo(menuType, merchantCode, page);
        DataDashboardOverdueResponse dataDashboardOverdueResponse = new DataDashboardOverdueResponse(dashboardOverdueOrderShowVos);
        return new ModuleResponse<>(dataDashboardOverdueResponse, page);

    }
}
