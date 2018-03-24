package com.beitu.saas.collection.service.impl;

import com.beitu.saas.collection.client.SaasModuleItemService;
import com.beitu.saas.collection.dao.SaasModuleItemDao;
import com.beitu.saas.collection.entity.SaasModuleItemEntity;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* User: fenqiguanjia
* Date: 2018-03-21
* Time: 20:58:19.394
*/
@Module(value = "模块字段信息表服务模块")
@NameSpace("com.beitu.saas.collection.dao.impl.SaasModuleItemDaoImpl")
@Service
public class SaasModuleItemServiceImpl extends AbstractBaseService implements SaasModuleItemService {


    @Autowired
    private SaasModuleItemDao saasModuleItemDao;


    @Override
    public List<SaasModuleItemEntity> getSaasModuleItemEntityList(String moduleCode) {
        return saasModuleItemDao.selectModuleItemEntityList(moduleCode);
    }
}


