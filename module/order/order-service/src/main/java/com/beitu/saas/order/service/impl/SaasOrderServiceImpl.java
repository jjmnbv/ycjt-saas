package com.beitu.saas.order.service.impl;

import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.dao.SaasOrderDao;
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
    public List<SaasOrderVo> listPreliminaryReviewOrder(String merchantCode, String reviewerCode, Page page) {
        List<SaasOrder> saasOrderList = saasOrderDao.selectByParams(new HashMap<String, Object>(8) {{
            put("merchantCode", merchantCode);
            put("primaryReviewerCode", reviewerCode);
            put("orderStatus", OrderStatusEnum.SUBMIT_PRELIMINARY_REVIEW.getCode());
            put("page", page);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasOrderList)) {
            return null;
        }
        List<SaasOrderVo> results = new ArrayList<>(saasOrderList.size());
        saasOrderList.forEach(saasOrder -> {
            results.add(SaasOrderVo.convertEntityToVO(saasOrder));
        });
        return results;
    }

    @Override
    public List<SaasOrderVo> listFinalReviewOrder(String merchantCode, String reviewerCode, Page page) {
        List<SaasOrder> saasOrderList = saasOrderDao.selectByParams(new HashMap<String, Object>(8) {{
            put("merchantCode", merchantCode);
            put("primaryReviewerCode", reviewerCode);
            put("orderStatus", OrderStatusEnum.SUBMIT_FINAL_REVIEW.getCode());
            put("page", page);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasOrderList)) {
            return null;
        }
        List<SaasOrderVo> results = new ArrayList<>(saasOrderList.size());
        saasOrderList.forEach(saasOrder -> {
            results.add(SaasOrderVo.convertEntityToVO(saasOrder));
        });
        return results;
    }

    @Override
    public SaasOrderVo getByOrderNumb(String orderNumb) {
        List<SaasOrder> saasOrderList = saasOrderDao.selectByParams(new HashMap<String, Object>(8) {{
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