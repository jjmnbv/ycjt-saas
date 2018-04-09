package com.beitu.saas.rest.controller.order.response;

import com.beitu.saas.app.application.order.vo.DashboardOrderShowVo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/4/3
 * Time: 上午11:55
 */
@ApiModel(value = "数据看板逾期信息")
public class DataDashboardOverdueResponse implements ResponseData {
    List<DashboardOrderShowVo> dataDashboardOverdueShowVoList;

    public DataDashboardOverdueResponse(List<DashboardOrderShowVo> dataDashboardOverdueShowVoList) {
        this.dataDashboardOverdueShowVoList = dataDashboardOverdueShowVoList;
    }

    public List<DashboardOrderShowVo> getDataDashboardOverdueShowVoList() {
        return dataDashboardOverdueShowVoList;
    }

    public DataDashboardOverdueResponse setDataDashboardOverdueShowVoList(List<DashboardOrderShowVo> dataDashboardOverdueShowVoList) {
        this.dataDashboardOverdueShowVoList = dataDashboardOverdueShowVoList;
        return this;
    }
}
