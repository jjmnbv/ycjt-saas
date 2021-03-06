package com.beitu.saas.collection.client;

import com.beitu.saas.collection.param.CollectionOrderQueryParam;
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
     * @param orderNo
     */
    void createCollectionOrder(String orderNo);

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
    List<CollectionOrderInfoDetailVo> getCollectionOrderListByPage(CollectionOrderQueryParam collectionOrderQueryParam, Page page);


    /**
     * 查询催收数量
     *
     * @param orderNo
     * @return
     */
    Integer getTotalCollectionOrderCount(String orderNo);

    /**
     * 逻辑删除
     *
     * @param orderNo
     */
    void deleteOrder(String orderNo);

}