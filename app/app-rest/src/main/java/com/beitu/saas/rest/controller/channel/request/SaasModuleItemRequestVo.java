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
public class SaasModuleItemRequestVo {
    /**
     * 模块号
     */
    private String moduleCode;
    /**
     * 字段号
     */
    private String itemCode;
    /**
     * 字段名称
     */
    private String itemDesc;

    /**
     * 是否必填 0 :必填 1:非必填
     */
    private Integer required;

    public String getModuleCode() {
        return moduleCode;
    }

    public SaasModuleItemRequestVo setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
        return this;
    }

    public String getItemCode() {
        return itemCode;
    }

    public SaasModuleItemRequestVo setItemCode(String itemCode) {
        this.itemCode = itemCode;
        return this;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public SaasModuleItemRequestVo setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
        return this;
    }

    public Integer getRequired() {
        return required;
    }

    public SaasModuleItemRequestVo setRequired(Integer required) {
        this.required = required;
        return this;
    }
}
