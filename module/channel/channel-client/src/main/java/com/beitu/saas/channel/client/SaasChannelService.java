package com.beitu.saas.channel.client;

import com.beitu.saas.channel.param.SaasChannelParam;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.fqgj.common.api.Page;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;

/**
 * User: fenqiguanjia
 * Date: 2018-03-21
 * Time: 20:58:19.270
 */
public interface SaasChannelService<T extends BaseEntity> extends BaseService<T> {

    /**
     * 渠道列表
     */
    List<SaasChannelEntity> getSaasChannelList(SaasChannelParam param, Page page);

    /**
     * 操作渠道功能
     */
    void operateSaasChannel(String channelCode, Integer status);


    /**
     * 根据渠道号渠道信息
     */
    SaasChannelEntity getSaasChannelByChannelCode(String channelCode);
}