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
@ApiModel(description = "新增模块字段")
public class SaasRiskModuleItemRequestParam extends ParamsObject {

    @NotBlank(message = "模块号不能为空")
    @ApiModelProperty(value = "模块号", required = true)
    private String moduleCode;

    @NotBlank(message = "字段号不能为空")
    @ApiModelProperty(value = "字段号", required = true)
    private String itemCode;

    @NotBlank(message = "字段名称不能为空")
    @ApiModelProperty(value = "字段名称", required = true)
    private String itemDesc;

    public String getModuleCode() {
        return moduleCode;
    }

    public SaasRiskModuleItemRequestParam setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
        return this;
    }

    public String getItemCode() {
        return itemCode;
    }

    public SaasRiskModuleItemRequestParam setItemCode(String itemCode) {
        this.itemCode = itemCode;
        return this;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public SaasRiskModuleItemRequestParam setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
        return this;
    }

    @Override
    public void validate() {

    }
}
