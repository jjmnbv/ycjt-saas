package com.beitu.saas.collection.dao.impl;

import com.beitu.saas.collection.param.CollectionOrderQueryParam;
import com.beitu.saas.collection.dao.SaasCollectionOrderDao;
import com.beitu.saas.collection.entity.SaasCollectionOrderEntity;
import com.beitu.saas.collection.vo.CollectionOrderInfoDetailVo;
import com.fqgj.common.api.Page;
import com.fqgj.common.base.AbstractBaseMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: fenqiguanjia
 * Date: 2018-03-21
 * Time: 20:58:19.361
 */

@Repository
public class SaasCollectionOrderDaoImpl extends AbstractBaseMapper<SaasCollectionOrderEntity> implements SaasCollectionOrderDao {
    @Override
    public SaasCollectionOrderEntity selectSaasCollectionOrderEntity(String orderNo) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderNo", orderNo);
        return getSqlSession().selectOne(this.getStatement("selectSaasCollectionOrderEntity"), map);
    }


    @Override
    public List<CollectionOrderInfoDetailVo> selectCollectionOrderListByPage(CollectionOrderQueryParam collectionOrderQueryParam, Page page) {
        Map<String, Object> map = new HashMap<>();
        map.put("collectionOrderQueryParam", collectionOrderQueryParam);
        if (page != null) {
            page.setTotalCount(this.queryTotalCollectionOrderListCount(collectionOrderQueryParam));
            map.put("page", page);
        }
        return getSqlSession().selectList(this.getStatement("selectCollectionOrderListByPage"), map);
    }


    @Override
    public Integer queryTotalCollectionOrderListCount(CollectionOrderQueryParam collectionOrderQueryParam) {
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("collectionOrderQueryParam", collectionOrderQueryParam);

        return getSqlSession().selectOne(this.getStatement("collectionOrderQueryParam"), paramMap);
    }
}