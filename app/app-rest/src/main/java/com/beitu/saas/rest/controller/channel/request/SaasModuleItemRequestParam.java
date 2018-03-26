package com.beitu.saas.rest.controller.channel.request;

import io.swagger.annotations.ApiModel;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 上午11:31
 */
@ApiModel(description = "风控模块字段")
public class SaasModuleItemRequestParam {
    /**
     * 模块号
     */
    private String moduleCode;
    /**
     * 字段号
     */
    private String itemCode;

    /**
     * 是否必填 0 :非必填 1:必填
     */
    private Integer required;

    public String getModuleCode() {
        return moduleCode;
    }

    public SaasModuleItemRequestParam setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
        return this;
    }

    public String getItemCode() {
        return itemCode;
    }

    public SaasModuleItemRequestParam setItemCode(String itemCode) {
        this.itemCode = itemCode;
        return this;
    }


    public Integer getRequired() {
        return required;
    }

    public SaasModuleItemRequestParam setRequired(Integer required) {
        this.required = required;
        return this;
    }
}
