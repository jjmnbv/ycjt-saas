package com.beitu.saas.channel.service.impl;

import com.beitu.saas.channel.client.SaasChannelRiskSettingsService;
import com.beitu.saas.channel.dao.SaasChannelRiskSettingsDao;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* User: fenqiguanjia
* Date: 2018-03-21
* Time: 20:58:19.342
*/
@Module(value = "渠道风控配置信息表服务模块")
@NameSpace("com.beitu.saas.channel.dao.impl.SaasChannelRiskSettingsDaoImpl")
@Service
public class SaasChannelRiskSettingsServiceImpl extends AbstractBaseService implements SaasChannelRiskSettingsService {


    @Autowired
    private SaasChannelRiskSettingsDao saasChannelRiskSettingsDao;
}


