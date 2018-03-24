package com.beitu.saas.channel.param;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 下午4:42
 */
public class SaasModuleParam {
    /**
     *模块号
     */
    private String moduleCode;
    /**
     *模块名称
     */
    private String moduleDesc;

    public String getModuleCode() {
        return moduleCode;
    }

    public SaasModuleParam setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
        return this;
    }

    public String getModuleDesc() {
        return moduleDesc;
    }

    public SaasModuleParam setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
        return this;
    }
}
