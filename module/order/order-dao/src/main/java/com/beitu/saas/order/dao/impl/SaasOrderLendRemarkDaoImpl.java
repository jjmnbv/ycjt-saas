package com.beitu.saas.order.dao.impl;

import com.fqgj.common.base.AbstractBaseMapper;
import com.beitu.saas.order.dao.SaasOrderLendRemarkDao;
import com.beitu.saas.order.entity.SaasOrderLendRemark;
import org.springframework.stereotype.Repository;

/**
 * User: jungle
 * Date: 2018-04-12
 * Time: 20:39:32.559
 */

@Repository
public class SaasOrderLendRemarkDaoImpl extends AbstractBaseMapper<SaasOrderLendRemark> implements SaasOrderLendRemarkDao {

    @Override
    public String selectLendWayByOrderNumb(String orderNumb) {
        return this.getSqlSession().selectOne(this.getStatement(".selectLendWayByOrderNumb"), orderNumb);
    }

}