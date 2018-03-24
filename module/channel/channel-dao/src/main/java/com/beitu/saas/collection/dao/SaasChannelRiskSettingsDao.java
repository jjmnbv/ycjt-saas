package com.beitu.saas.collection.dao;

import com.beitu.saas.collection.entity.SaasChannelRiskSettingsEntity;
import com.fqgj.common.base.BaseMapper;

import java.util.List;

/**
* User: fenqiguanjia
* Date: 2018-03-21
* Time: 20:58:19.339
*/

public interface SaasChannelRiskSettingsDao  extends BaseMapper<SaasChannelRiskSettingsEntity> {
    List<SaasChannelRiskSettingsEntity> selectSaasChannelRiskSettingsEntityList(String channelCode);
}