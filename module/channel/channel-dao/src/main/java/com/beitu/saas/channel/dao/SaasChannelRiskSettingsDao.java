package com.beitu.saas.channel.dao;

import com.beitu.saas.channel.entity.SaasChannelRiskSettingsEntity;
import com.fqgj.common.base.BaseMapper;

/**
* User: fenqiguanjia
* Date: 2018-03-21
* Time: 20:58:19.339
*/

public interface SaasChannelRiskSettingsDao  extends BaseMapper<SaasChannelRiskSettingsEntity> {
    SaasChannelRiskSettingsEntity selectSaasChannelRiskSettingsEntity(String channelCode);
}