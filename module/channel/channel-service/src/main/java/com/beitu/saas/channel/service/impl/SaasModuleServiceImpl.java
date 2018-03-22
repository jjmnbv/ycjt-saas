package com.beitu.saas.channel.service.impl;

import com.beitu.saas.channel.client.SaasModuleService;
import com.beitu.saas.channel.dao.SaasModuleDao;
import com.beitu.saas.channel.dao.SaasModuleItemDao;
import com.beitu.saas.channel.entity.SaasModuleEntity;
import com.beitu.saas.channel.entity.SaasModuleItemEntity;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* User: fenqiguanjia
* Date: 2018-03-21
* Time: 20:58:19.384
*/
@Module(value = "风控模块信息表服务模块")
@NameSpace("com.beitu.saas.channel.dao.impl.SaasModuleDaoImpl")
@Service
public class SaasModuleServiceImpl extends AbstractBaseService implements SaasModuleService {


    @Autowired
    private SaasModuleDao saasModuleDao;
    @Autowired
    private SaasModuleItemDao saasModuleItemDao;

    @Override
    public List<SaasModuleEntity> getSaasModuleEntityList() {
        return saasModuleDao.selectModuleEntityList();
    }
}


