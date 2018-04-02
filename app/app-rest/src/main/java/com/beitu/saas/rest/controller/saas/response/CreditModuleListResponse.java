package com.beitu.saas.rest.controller.saas.response;

import com.beitu.saas.app.application.credit.vo.CreditModuleListVo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author linanjun
 * @create 2018/3/22 上午11:46
 * @description
 */
@ApiModel(value = "风控模块列表信息")
public class CreditModuleListResponse implements ResponseData {

    @ApiModelProperty(value = "风控模块列表")
    private List<CreditModuleListVo> creditModuleListVoList;

    public CreditModuleListResponse(List<CreditModuleListVo> creditModuleListVoList) {
        this.creditModuleListVoList = creditModuleListVoList;
    }

    public List<CreditModuleListVo> getCreditModuleListVoList() {
        return creditModuleListVoList;
    }

    public void setCreditModuleListVoList(List<CreditModuleListVo> creditModuleListVoList) {
        this.creditModuleListVoList = creditModuleListVoList;
    }

}
