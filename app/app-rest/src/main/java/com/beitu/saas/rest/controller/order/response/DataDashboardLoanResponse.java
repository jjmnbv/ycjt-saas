package com.beitu.saas.rest.controller.order.response;

import com.beitu.saas.app.application.order.vo.DataDashboardLoanShowVo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/4/3
 * Time: 上午11:55
 */
@ApiModel(value = "数据看板放款信息")
public class DataDashboardLoanResponse implements ResponseData {
    private DataDashboardLoanShowVo dataDashboardLoanShowVo;

    public DataDashboardLoanResponse(DataDashboardLoanShowVo dataDashboardLoanShowVo) {
        this.dataDashboardLoanShowVo = dataDashboardLoanShowVo;
    }

    public DataDashboardLoanShowVo getDataDashboardLoanShowVo() {
        return dataDashboardLoanShowVo;
    }

    public DataDashboardLoanResponse setDataDashboardLoanShowVo(DataDashboardLoanShowVo dataDashboardLoanShowVo) {
        this.dataDashboardLoanShowVo = dataDashboardLoanShowVo;
        return this;
    }
}
