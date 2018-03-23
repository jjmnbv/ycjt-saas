package com.beitu.saas.channel.service.impl;

import com.beitu.saas.channel.param.SaasChannelParam;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.dao.SaasChannelDao;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.fqgj.common.api.Page;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<SaasChannelEntity> getSaasChannelList(SaasChannelParam param, Page page) {
        return saasChannelDao.selectChannelEntityList(param, page);
    }

    @Override
    public void operateSaasChannel(String channelCode, Integer status) {
        SaasChannelEntity channelEntity = saasChannelDao.selectChannelEntityByChannelCode(channelCode);
        channelEntity.setChannelStatus(status);
        saasChannelDao.updateByPrimaryKey(channelEntity);
    }

    @Override
    public SaasChannelEntity getSaasChannelBychannelCode(String channelCode) {
        SaasChannelEntity channelEntity = saasChannelDao.selectChannelEntityByChannelCode(channelCode);
        return channelEntity;
    }
}


