package com.beitu.saas.order.dao.impl;
import com.fqgj.common.base.AbstractBaseMapper;
import com.beitu.saas.order.dao.SaasOrderStatusHistoryDao;
import com.beitu.saas.order.entity.SaasOrderStatusHistory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
* User: jungle
* Date: 2018-03-25
* Time: 21:55:45.874
*/

@Repository
public class SaasOrderStatusHistoryDaoImpl extends AbstractBaseMapper<SaasOrderStatusHistory> implements SaasOrderStatusHistoryDao {

    @Override
    public SaasOrderStatusHistory selectOrderStatusHistoryByOrderNumb(String orderNumb) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderNumb", orderNumb);
        return getSqlSession().selectOne(this.getStatement("selectOrderStatusHistoryByOrderNumb"), map);
    }
}