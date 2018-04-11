package com.beitu.saas.rest.controller.order.response;

import com.beitu.saas.app.application.order.vo.DashboardOverdueOrderShowVo;
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
    List<DashboardOverdueOrderShowVo> dataDashboardOverdueShowVoList;

    public DataDashboardOverdueResponse(List<DashboardOverdueOrderShowVo> dataDashboardOverdueShowVoList) {
        this.dataDashboardOverdueShowVoList = dataDashboardOverdueShowVoList;
    }

    public List<DashboardOverdueOrderShowVo> getDataDashboardOverdueShowVoList() {
        return dataDashboardOverdueShowVoList;
    }

    public DataDashboardOverdueResponse setDataDashboardOverdueShowVoList(List<DashboardOverdueOrderShowVo> dataDashboardOverdueShowVoList) {
        this.dataDashboardOverdueShowVoList = dataDashboardOverdueShowVoList;
        return this;
    }
}
