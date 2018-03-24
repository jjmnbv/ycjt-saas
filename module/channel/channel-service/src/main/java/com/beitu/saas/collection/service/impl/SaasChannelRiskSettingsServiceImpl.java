package com.beitu.saas.collection.service.impl;

import com.beitu.saas.collection.client.SaasChannelRiskSettingsService;
import com.beitu.saas.collection.dao.SaasChannelRiskSettingsDao;
import com.beitu.saas.collection.entity.SaasChannelRiskSettingsEntity;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: fenqiguanjia
 * Date: 2018-03-21
 * Time: 20:58:19.342
 */
@Module(value = "渠道风控配置信息表服务模块")
@NameSpace("com.beitu.saas.collection.dao.impl.SaasChannelRiskSettingsDaoImpl")
@Service
public class SaasChannelRiskSettingsServiceImpl extends AbstractBaseService implements SaasChannelRiskSettingsService {


    @Autowired
    private SaasChannelRiskSettingsDao saasChannelRiskSettingsDao;

    @Override
    public List<SaasChannelRiskSettingsEntity> getSaasChannelRiskSettingsByChannelCode(String channelCode) {
        return saasChannelRiskSettingsDao.selectSaasChannelRiskSettingsEntityList(channelCode);
    }
}


