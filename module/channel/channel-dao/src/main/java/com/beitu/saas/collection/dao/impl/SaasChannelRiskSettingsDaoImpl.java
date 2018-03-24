package com.beitu.saas.collection.dao.impl;
import com.beitu.saas.collection.dao.SaasChannelRiskSettingsDao;
import com.beitu.saas.collection.entity.SaasChannelRiskSettingsEntity;
import com.fqgj.common.base.AbstractBaseMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* User: fenqiguanjia
* Date: 2018-03-21
* Time: 20:58:19.340
*/

@Repository
public class SaasChannelRiskSettingsDaoImpl extends AbstractBaseMapper<SaasChannelRiskSettingsEntity> implements SaasChannelRiskSettingsDao {

    @Override
    public List<SaasChannelRiskSettingsEntity> selectSaasChannelRiskSettingsEntityList(String channelCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("channelCode", channelCode);
        return getSqlSession().selectList(this.getStatement("selectSaasChannelRiskSettingsEntityList"), map);    }
}