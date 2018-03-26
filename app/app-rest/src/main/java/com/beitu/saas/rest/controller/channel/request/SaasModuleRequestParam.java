package com.beitu.saas.rest.controller.channel.request;

import io.swagger.annotations.ApiModel;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 上午11:30
 */
@ApiModel(description = "风控模块")
public class SaasModuleRequestParam {
    /**
     * 模块号
     */
    private String moduleCode;

    /**
     * 是否必填 0 :非必填 1:必填
     */
    private Integer required;

    public String getModuleCode() {
        return moduleCode;
    }

    public SaasModuleRequestParam setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
        return this;
    }


    public Integer getRequired() {
        return required;
    }

    public SaasModuleRequestParam setRequired(Integer required) {
        this.required = required;
        return this;
    }
}
