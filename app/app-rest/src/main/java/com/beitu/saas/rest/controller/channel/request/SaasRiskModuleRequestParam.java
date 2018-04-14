package com.beitu.saas.rest.controller.channel.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 上午11:09
 */
@ApiModel(description = "新增风控模块")
public class SaasRiskModuleRequestParam extends ParamsObject {

    @NotBlank(message = "模块号不能为空")
    @ApiModelProperty(value = "模块号", required = true)
    private String moduleCode;

    @NotBlank(message = "模块名称不能为空")
    @ApiModelProperty(value = "模块名称", required = true)
    private String moduleDesc;

    public String getModuleCode() {
        return moduleCode;
    }

    public SaasRiskModuleRequestParam setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
        return this;
    }

    public String getModuleDesc() {
        return moduleDesc;
    }

    public SaasRiskModuleRequestParam setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
        return this;
    }

    @Override
    public void validate() {

    }
}
