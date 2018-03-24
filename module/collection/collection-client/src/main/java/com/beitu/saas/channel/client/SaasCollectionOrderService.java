package com.beitu.saas.channel.client;

import com.beitu.saas.channel.domain.CollectionOrderQueryVo;
import com.beitu.saas.collection.vo.CollectionOrderInfoDetailVo;
import com.fqgj.common.api.Page;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;

/**
 * User: fenqiguanjia
 * Date: 2018-03-21
 * Time: 20:58:19.361
 */
public interface SaasCollectionOrderService<T extends BaseEntity> extends BaseService<T> {

    /**
     * 新增催收订单
     *
     * @param collectionOrderInfoDetailVo
     */
    void createCollectionOrder(CollectionOrderInfoDetailVo collectionOrderInfoDetailVo);

    /**
     * 核销订单
     *
     * @param orderNo
     */
    void closeOrder(String orderNo);


    /**
     * 查询催收订单列表
     *
     * @param collectionOrderQueryParam
     * @return
     */
    List<CollectionOrderInfoDetailVo> getCollectionOrderListByPage(CollectionOrderQueryVo collectionOrderQueryParam, Page page);


}