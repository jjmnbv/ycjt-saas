package com.beitu.saas.rest.controller.h5.response;

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

    public List<CreditModuleListVo> getCreditModuleListVoList() {
        return creditModuleListVoList;
    }

    public void setCreditModuleListVoList(List<CreditModuleListVo> creditModuleListVoList) {
        this.creditModuleListVoList = creditModuleListVoList;
    }

    public static class CreditModuleListVo {

        @ApiModelProperty(value = "风控模块CODE")
        private String moduleCode;

        @ApiModelProperty(value = "申请状态（1为未填写，2为未完成，3为完成）")
        private Integer applyStatus;

        @ApiModelProperty(value = "是否必填")
        private Boolean isRequired;

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
            return isRequired;
        }

        public void setRequired(Boolean required) {
            isRequired = required;
        }
    }

}
