package com.beitu.saas.rest.controller.module.response;

import com.beitu.saas.channel.domain.SaasModuleItemVo;
import com.beitu.saas.rest.controller.channel.request.SaasModuleItemRequestVo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 下午5:13
 */
@ApiModel(value = "风控模块字段列表")
public class SaasModuleItemResponse implements ResponseData {
    private List<SaasModuleItemVo> saasModuleItemVos;

    public SaasModuleItemResponse(List<SaasModuleItemVo> saasModuleItemVos) {
        this.saasModuleItemVos = saasModuleItemVos;
    }

    public List<SaasModuleItemVo> getSaasModuleItemVos() {
        return saasModuleItemVos;
    }

    public SaasModuleItemResponse setSaasModuleItemVos(List<SaasModuleItemVo> saasModuleItemVos) {
        this.saasModuleItemVos = saasModuleItemVos;
        return this;
    }
}
