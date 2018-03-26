package com.beitu.saas.channel.domain;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/26
 * Time: 上午11:16
 */
public class SaasChannelDetailVo {
    /**
     *渠道名称
     */
    private String channelName;
    /**
     *渠道号
     */
    private String channelCode;
    /**
     *负责人名字
     */
    private String chargePersonName;
    /**
     *创建人名字
     */
    private String creatorName;
    /**
     *备注
     */
    private String remark;

    /**
     * 风控配置信息
     */
    private List<SaasChannelRiskSettingsVo> saasChannelRiskSettingsVos;

    public String getChannelName() {
        return channelName;
    }

    public SaasChannelDetailVo setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }

    public String getChargePersonName() {
        return chargePersonName;
    }

    public SaasChannelDetailVo setChargePersonName(String chargePersonName) {
        this.chargePersonName = chargePersonName;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public SaasChannelDetailVo setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public SaasChannelDetailVo setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public List<SaasChannelRiskSettingsVo> getSaasChannelRiskSettingsVos() {
        return saasChannelRiskSettingsVos;
    }

    public SaasChannelDetailVo setSaasChannelRiskSettingsVos(List<SaasChannelRiskSettingsVo> saasChannelRiskSettingsVos) {
        this.saasChannelRiskSettingsVos = saasChannelRiskSettingsVos;
        return this;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public SaasChannelDetailVo setChannelCode(String channelCode) {
        this.channelCode = channelCode;
        return this;
    }
}
