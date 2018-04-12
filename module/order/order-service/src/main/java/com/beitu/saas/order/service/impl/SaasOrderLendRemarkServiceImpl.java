package com.beitu.saas.order.service.impl;

import com.beitu.saas.order.client.SaasOrderLendRemarkService;
import com.beitu.saas.order.dao.SaasOrderLendRemarkDao;
import com.beitu.saas.order.domain.SaasOrderLendRemarkVo;
import com.beitu.saas.order.entity.SaasOrderLendRemark;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: jungle
 * Date: 2018-04-12
 * Time: 20:39:32.653
 */
@Module(value = "SAAS订单放款备注表服务模块")
@NameSpace("com.beitu.saas.order.dao.impl.SaasOrderLendRemarkDaoImpl")
@Service
public class SaasOrderLendRemarkServiceImpl extends AbstractBaseService implements SaasOrderLendRemarkService {

    @Autowired
    private SaasOrderLendRemarkDao saasOrderLendRemarkDao;

    @Override
    public SaasOrderLendRemark save(SaasOrderLendRemarkVo saasOrderLendRemarkVo) {
        SaasOrderLendRemark saasOrderLendRemark = SaasOrderLendRemarkVo.convertVOToEntity(saasOrderLendRemarkVo);
        return saasOrderLendRemarkDao.insert(saasOrderLendRemark);
    }

    @Override
    public String getLendWayByOrderNumb(String orderNumb) {
        return null;
    }

}


