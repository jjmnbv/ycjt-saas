package com.beitu.saas.order.service.impl;

import com.beitu.saas.common.utils.OrderNoUtil;
import com.beitu.saas.order.client.SaasOrderApplicationService;
import com.beitu.saas.order.dao.SaasOrderApplicationDao;
import com.beitu.saas.order.domain.SaasOrderApplicationVo;
import com.beitu.saas.order.entity.SaasOrderApplication;
import com.beitu.saas.order.enums.OrderApplyStatusMsgEnum;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * User: jungle
 * Date: 2018-03-23
 * Time: 15:18:54.767
 */
@Module(value = "SAAS订单申请表服务模块")
@NameSpace("com.beitu.saas.order.dao.impl.SaasOrderApplicationDaoImpl")
@Service
public class SaasOrderApplicationServiceImpl extends AbstractBaseService implements SaasOrderApplicationService {

    @Autowired
    private SaasOrderApplicationDao saasOrderApplicationDao;

    @Override
    public SaasOrderApplication save(SaasOrderApplicationVo saasOrderApplicationVo) {
        SaasOrderApplication saasOrderApplication = SaasOrderApplicationVo.convertVOToEntity(saasOrderApplicationVo);
        SaasOrderApplication oldSaasOrderApplication = saasOrderApplicationDao.selectByBorrowerCodeAndOrderNumb(saasOrderApplicationVo.getBorrowerCode(), saasOrderApplicationVo.getOrderNumb());
        if (oldSaasOrderApplication == null) {
            return saasOrderApplicationDao.insert(saasOrderApplication);
        }
        saasOrderApplication.setId(oldSaasOrderApplication.getId());
        saasOrderApplicationDao.updateByPrimaryKey(saasOrderApplication);
        return saasOrderApplication;
    }

    @Override
    public SaasOrderApplicationVo getByBorrowerCodeAndOrderNumb(String borrowerCode, String orderNumb) {
        return SaasOrderApplicationVo.convertEntityToVO(saasOrderApplicationDao.selectByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb));
    }

    @Override
    public Boolean updateOrderNumbByBorrowerCode(String orderNumb, String borrowerCode) {
        return saasOrderApplicationDao.updateOrderNumbByBorrowerCode(orderNumb, borrowerCode) > 0;
    }

}


