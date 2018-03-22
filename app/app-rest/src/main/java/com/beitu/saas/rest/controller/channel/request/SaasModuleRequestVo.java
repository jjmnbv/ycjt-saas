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
public class SaasModuleRequestVo {
    /**
     * 模块号
     */
    private String moduleCode;
    /**
     * 模块名称
     */
    private String moduleDesc;
    /**
     * 是否必填 0 :必填 1:非必填
     */
    private Integer required;

    public String getModuleCode() {
        return moduleCode;
    }

    public SaasModuleRequestVo setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
        return this;
    }

    public String getModuleDesc() {
        return moduleDesc;
    }

    public SaasModuleRequestVo setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
        return this;
    }

    public Integer getRequired() {
        return required;
    }

    public SaasModuleRequestVo setRequired(Integer required) {
        this.required = required;
        return this;
    }
}
