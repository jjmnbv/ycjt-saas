package com.beitu.saas.app.application.credit.vo;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/23 下午12:16
 * @description
 */
public class CreditModuleListVo implements ResponseData {

    @ApiModelProperty(value = "风控模块CODE")
    private String moduleCode;

    @ApiModelProperty(value = "申请状态（1为未填写，2为未完成，3为完成）")
    private Integer applyStatus;

    @ApiModelProperty(value = "是否必填")
    private Boolean required;

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

}
