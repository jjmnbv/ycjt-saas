package com.beitu.saas.app.application.channel.vo;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 下午4:42
 */
public class SaasModuleVo implements ResponseData {
    /**
     *模块号
     */
    @ApiModelProperty(value = "模块code")
    private String moduleCode;
    /**
     *模块名称
     */
    @ApiModelProperty(value = "模块名称")
    private String moduleDesc;

    public String getModuleCode() {
        return moduleCode;
    }

    public SaasModuleVo setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
        return this;
    }

    public String getModuleDesc() {
        return moduleDesc;
    }

    public SaasModuleVo setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
        return this;
    }
}
