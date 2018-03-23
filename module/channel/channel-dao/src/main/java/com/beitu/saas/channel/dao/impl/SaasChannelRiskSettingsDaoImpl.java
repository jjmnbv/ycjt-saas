package com.beitu.saas.channel.dao.impl;
import com.beitu.saas.channel.dao.SaasChannelRiskSettingsDao;
import com.beitu.saas.channel.entity.SaasChannelRiskSettingsEntity;
import com.fqgj.common.base.AbstractBaseMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
* User: fenqiguanjia
* Date: 2018-03-21
* Time: 20:58:19.340
*/

@Repository
public class SaasChannelRiskSettingsDaoImpl extends AbstractBaseMapper<SaasChannelRiskSettingsEntity> implements SaasChannelRiskSettingsDao {

    @Override
    public SaasChannelRiskSettingsEntity selectSaasChannelRiskSettingsEntity(String channelCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("channelCode", channelCode);
        return getSqlSession().selectOne(this.getStatement("selectSaasChannelRiskSettingsEntity"), map);    }
}