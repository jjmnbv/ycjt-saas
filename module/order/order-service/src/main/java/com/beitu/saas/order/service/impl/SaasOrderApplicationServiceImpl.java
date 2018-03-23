package com.beitu.saas.order.service.impl;

import com.beitu.saas.common.utils.OrderNoUtil;
import com.beitu.saas.order.client.SaasOrderApplicationService;
import com.beitu.saas.order.dao.SaasOrderApplicationDao;
import com.beitu.saas.order.domain.SaasOrderApplicationVo;
import com.beitu.saas.order.entity.SaasOrderApplication;
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
    public SaasOrderApplicationVo getByOrderNumb(String orderNumb) {
        List<SaasOrderApplication> saasOrderApplicationList = saasOrderApplicationDao.selectByParams(new HashMap<String, Object>(4) {{
            put("orderNumb", orderNumb);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasOrderApplicationList)) {
            return null;
        }
        return SaasOrderApplicationVo.convertEntityToVO(saasOrderApplicationList.get(0));
    }

    @Override
    public SaasOrderApplication create(SaasOrderApplicationVo saasOrderApplicationVo) {
        SaasOrderApplication saasOrderApplication = SaasOrderApplicationVo.convertVOToEntity(saasOrderApplicationVo);
        saasOrderApplication.setOrderNumb(OrderNoUtil.makeOrderNum());
        saasOrderApplication.setLateFeeRatio(new BigDecimal("0.24"));
        return saasOrderApplicationDao.insert(saasOrderApplication);
    }

}


