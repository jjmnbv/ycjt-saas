package com.beitu.saas.order.service.impl;

import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.dao.SaasOrderDao;
import com.beitu.saas.order.domain.QuerySaasOrderVo;
import com.beitu.saas.order.domain.SaasOrderVo;
import com.beitu.saas.order.entity.SaasOrder;
import com.beitu.saas.order.enums.OrderStatusEnum;
import com.fqgj.common.api.Page;
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
 * Date: 2018-03-23
 * Time: 15:18:54.749
 */
@Module(value = "SAAS订单表服务模块")
@NameSpace("com.beitu.saas.order.dao.impl.SaasOrderDaoImpl")
@Service
public class SaasOrderServiceImpl extends AbstractBaseService implements SaasOrderService {

    @Autowired
    private SaasOrderDao saasOrderDao;

    @Override
    public List<SaasOrderVo> listByBorrowerCodeAndMerchantCode(String borrowerCode, String merchantCode) {
        return null;
    }

    @Override
    public OrderStatusEnum getOrderStatusByOrderNumb(String orderNumb) {
        return null;
    }

    @Override
    public List<SaasOrderVo> listEffectiveOrderByOrderNumb(String orderNumb) {
        return null;
    }

    @Override
    public Boolean isReviewRefuse(String borrowerCode, String channelCode) {
        return Boolean.FALSE;
    }

    @Override
    public List<SaasOrderVo> listByQuerySaasOrderVoAndPage(QuerySaasOrderVo querySaasOrderVo, Page page) {
        Map<String, Object> conditions = new HashMap<>(16);
        conditions.put("borrowerCodeList", querySaasOrderVo.getBorrowerCodeList());
        conditions.put("merchantCode", querySaasOrderVo.getMerchantCode());
        conditions.put("channelCode", querySaasOrderVo.getChannelCode());
        conditions.put("orderStatusList", querySaasOrderVo.getOrderStatusList());
        conditions.put("createdBeginDt", querySaasOrderVo.getCreatedBeginDt());
        conditions.put("createdEndDt", querySaasOrderVo.getCreatedEndDt());
        conditions.put("preliminaryReviewerCode", querySaasOrderVo.getPreliminaryReviewerCode());
        conditions.put("finalReviewerCode", querySaasOrderVo.getFinalReviewerCode());
        conditions.put("loanLenderCode", querySaasOrderVo.getLoanLenderCode());
        conditions.put("repaymentDt", querySaasOrderVo.getRepaymentDt());
        Integer count = saasOrderDao.countByConditions(conditions);
        page.setTotalCount(count);
        if (count == 0) {
            return null;
        }
        conditions.put("page", page);
        List<SaasOrder> saasOrderList = saasOrderDao.selectByConditions(conditions);
        if (CollectionUtils.isEmpty(saasOrderList)) {
            return null;
        }
        List<SaasOrderVo> results = new ArrayList<>(saasOrderList.size());
        saasOrderList.forEach(SaasOrder -> results.add(SaasOrderVo.convertEntityToVO(SaasOrder)));
        return results;
    }

    @Override
    public SaasOrderVo getByOrderNumb(String orderNumb) {
        List<SaasOrder> saasOrderList = saasOrderDao.selectByParams(new HashMap<String, Object>(4) {{
            put("orderNumb", orderNumb);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasOrderList)) {
            return null;
        }
        return SaasOrderVo.convertEntityToVO(saasOrderList.get(0));
    }

    @Override
    public Boolean updateOrderStatus(Long orderId, OrderStatusEnum currentOrderStatus, OrderStatusEnum updateOrderStatus) {
        return null;
    }

}