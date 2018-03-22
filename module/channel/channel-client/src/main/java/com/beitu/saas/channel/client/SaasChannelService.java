package com.beitu.saas.channel.client;

import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
* User: fenqiguanjia
* Date: 2018-03-21
* Time: 20:58:19.270
*/
public interface SaasChannelService<T extends BaseEntity> extends BaseService<T> {

    /**
     * 创建渠道
     *
     */
    void createChannel();

}