package com.beitu.saas.channel.client;

import com.beitu.saas.channel.entity.SaasChannelRiskSettingsEntity;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;

/**
 * User: fenqiguanjia
 * Date: 2018-03-21
 * Time: 20:58:19.341
 */
public interface SaasChannelRiskSettingsService<T extends BaseEntity> extends BaseService<T> {
    /**
     * 根据渠道号渠道信息
     */
    List<SaasChannelRiskSettingsEntity> getSaasChannelRiskSettingsByChannelCode(String channelCode);

    void deleteRiskSettingsByChannelCode(String channelCode);

    SaasChannelRiskSettingsEntity createDefaultChannelRiskSettings(String channelCode);

}