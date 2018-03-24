package com.beitu.saas.rest.controller.channel.response;

import com.beitu.saas.collection.domain.SaasModuleVo;
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
@ApiModel(value = "风控模块列表")
public class SaasModuleResponse implements ResponseData {
    private List<SaasModuleVo> saasModuleVos;

    public SaasModuleResponse(List<SaasModuleVo> saasModuleVos) {
        this.saasModuleVos = saasModuleVos;
    }

    public List<SaasModuleVo> getSaasModuleVos() {
        return saasModuleVos;
    }

    public SaasModuleResponse setSaasModuleVos(List<SaasModuleVo> saasModuleVos) {
        this.saasModuleVos = saasModuleVos;
        return this;
    }
}
