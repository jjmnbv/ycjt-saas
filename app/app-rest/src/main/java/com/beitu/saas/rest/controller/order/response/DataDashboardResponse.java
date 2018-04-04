package com.beitu.saas.rest.controller.order.response;

import com.beitu.saas.app.application.order.vo.DataDashboardShowVo;
import com.fqgj.common.api.ResponseData;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/4/3
 * Time: 上午11:55
 */
public class DataDashboardResponse  implements ResponseData {
    private DataDashboardShowVo dataDashboardShowVo;

    public DataDashboardResponse(DataDashboardShowVo dataDashboardShowVo) {
        this.dataDashboardShowVo = dataDashboardShowVo;
    }

    public DataDashboardShowVo getDataDashboardShowVo() {
        return dataDashboardShowVo;
    }

    public DataDashboardResponse setDataDashboardShowVo(DataDashboardShowVo dataDashboardShowVo) {
        this.dataDashboardShowVo = dataDashboardShowVo;
        return this;
    }
}
