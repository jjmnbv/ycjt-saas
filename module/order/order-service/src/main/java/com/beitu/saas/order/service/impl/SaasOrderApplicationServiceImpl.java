package com.beitu.saas.order.service.impl;

import com.beitu.saas.order.client.SaasOrderApplicationService;
import com.beitu.saas.order.dao.SaasOrderApplicationDao;
import com.beitu.saas.order.domain.SaasOrderApplicationVo;
import com.beitu.saas.order.entity.SaasOrderApplication;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<SaasOrderApplicationVo> listByBorrowerCodeAndOrderNumb(String borrowerCode, String orderNumb) {
        List<SaasOrderApplication> saasOrderApplicationList = saasOrderApplicationDao.selectAllByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);
        if (CollectionUtils.isEmpty(saasOrderApplicationList)) {
            return null;
        }
        List<SaasOrderApplicationVo> results = new ArrayList<>(saasOrderApplicationList.size());
        saasOrderApplicationList.forEach(saasOrderApplication -> results.add(SaasOrderApplicationVo.convertEntityToVO(saasOrderApplication)));
        return results;
    }

}


