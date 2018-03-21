package com.beitu.saas.channel.service.impl;

import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.dao.SaasChannelDao;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* User: fenqiguanjia
* Date: 2018-03-21
* Time: 20:58:19.277
*/
@Module(value = "渠道信息表服务模块")
@NameSpace("com.beitu.saas.channel.dao.impl.SaasChannelDaoImpl")
@Service
public class SaasChannelServiceImpl extends AbstractBaseService implements SaasChannelService {


    @Autowired
    private SaasChannelDao saasChannelDao;
}


