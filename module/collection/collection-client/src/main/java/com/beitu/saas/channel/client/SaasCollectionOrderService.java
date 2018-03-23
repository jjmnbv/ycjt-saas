package com.beitu.saas.channel.client;

import com.beitu.saas.channel.domain.OrderInfoVo;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
 * User: fenqiguanjia
 * Date: 2018-03-21
 * Time: 20:58:19.361
 */
public interface SaasCollectionOrderService<T extends BaseEntity> extends BaseService<T> {

    /**
     * 新增催收订单
     *
     * @param orderInfoVo
     */
    void createCollectionOrder(OrderInfoVo orderInfoVo);

    /**
     * 核销订单
     *
     * @param orderNo
     */
    void closeOrder(String orderNo);

}