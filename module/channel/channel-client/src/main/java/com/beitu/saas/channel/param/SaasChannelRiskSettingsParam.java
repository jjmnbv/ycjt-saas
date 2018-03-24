package com.beitu.saas.channel.param;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 上午11:00
 */
public class SaasChannelRiskSettingsParam {
    /**
     *渠道号
     */
    private String channelCode;
    /**
     *模块号
     */
    private String moduleCode;
    /**
     *字段号
     */
    private String itemCode;
    /**
     *是否必填(0-否,1-是)
     */
    private Integer required;

    public String getChannelCode() {
        return channelCode;
    }

    public SaasChannelRiskSettingsParam setChannelCode(String channelCode) {
        this.channelCode = channelCode;
        return this;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public SaasChannelRiskSettingsParam setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
        return this;
    }

    public String getItemCode() {
        return itemCode;
    }

    public SaasChannelRiskSettingsParam setItemCode(String itemCode) {
        this.itemCode = itemCode;
        return this;
    }

    public Integer getRequired() {
        return required;
    }

    public SaasChannelRiskSettingsParam setRequired(Integer required) {
        this.required = required;
        return this;
    }
}
