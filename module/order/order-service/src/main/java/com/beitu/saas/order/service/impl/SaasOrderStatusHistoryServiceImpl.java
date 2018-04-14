package com.beitu.saas.order.service.impl;

import com.beitu.saas.order.client.SaasOrderStatusHistoryService;
import com.beitu.saas.order.dao.SaasOrderStatusHistoryDao;
import com.beitu.saas.order.domain.SaasOrderStatusHistoryVo;
import com.beitu.saas.order.entity.SaasOrderStatusHistory;
import com.beitu.saas.order.enums.OrderStatusEnum;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: jungle
 * Date: 2018-03-25
 * Time: 21:55:45.879
 */
@Module(value = "SAAS订单状态流水表服务模块")
@NameSpace("com.beitu.saas.order.dao.impl.SaasOrderStatusHistoryDaoImpl")
@Service
public class SaasOrderStatusHistoryServiceImpl extends AbstractBaseService implements SaasOrderStatusHistoryService {

    @Autowired
    private SaasOrderStatusHistoryDao saasOrderStatusHistoryDao;

    @Override
    public String getLoanLendRemark(String orderNumb) {
        List<SaasOrderStatusHistory> saasOrderStatusHistoryList = saasOrderStatusHistoryDao.selectByCurrentOrderStatusAndOrderNumb(OrderStatusEnum.SUBMIT_LOAN_LENDER.getCode(), orderNumb);
        if (CollectionUtils.isEmpty(saasOrderStatusHistoryList)) {
            return null;
        }
        return saasOrderStatusHistoryList.get(saasOrderStatusHistoryList.size() - 1).getRemark();
    }

    @Override
    public SaasOrderStatusHistoryVo getLatestOrderStatusHistoryByOrderNumb(String orderNumb) {
        return SaasOrderStatusHistoryVo.convertEntityToVO(saasOrderStatusHistoryDao.selectLatestOrderStatusHistoryByOrderNumb(orderNumb));
    }

    @Override
    public List<SaasOrderStatusHistoryVo> listOrderStatusHistoryByOrderNumb(String orderNumb) {
        List<SaasOrderStatusHistory> saasOrderStatusHistoryList = saasOrderStatusHistoryDao.selectOrderStatusHistoryByOrderNumb(orderNumb);
        if (CollectionUtils.isEmpty(saasOrderStatusHistoryList)) {
            return null;
        }
        List<SaasOrderStatusHistoryVo> results = new ArrayList<>(saasOrderStatusHistoryList.size());
        saasOrderStatusHistoryList.forEach(saasOrderStatusHistory -> results.add(SaasOrderStatusHistoryVo.convertEntityToVO(saasOrderStatusHistory)));
        return results;
    }

}


