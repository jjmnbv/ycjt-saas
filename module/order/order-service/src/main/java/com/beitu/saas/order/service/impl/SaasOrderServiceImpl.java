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
import com.fqgj.common.utils.DateUtil;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public OrderStatusEnum getOrderStatusByOrderNumb(String orderNumb) {
        List<SaasOrder> saasOrderList = saasOrderDao.selectByParams(new HashMap<String, Object>(4) {{
            put("orderNumb", orderNumb);
            put("expireDate", new Date());
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasOrderList)) {
            return null;
        }
        if (saasOrderList.size() == 1) {
            SaasOrder saasOrder = saasOrderList.get(0);
            if (OrderStatusEnum.FOR_REIMBURSEMENT.getCode().equals(saasOrder.getOrderStatus())) {
                if (DateUtil.countDay(new Date(), saasOrder.getRepaymentDt()) > 0) {
                    return OrderStatusEnum.OVERDUE;
                }
            }
            return OrderStatusEnum.getEnumByCode(saasOrder.getOrderStatus());
        }
        SaasOrder saasOrder = saasOrderList.get(saasOrderList.size() - 1);
        if (OrderStatusEnum.FOR_REIMBURSEMENT.getCode().equals(saasOrder.getOrderStatus())) {
            if (DateUtil.countDay(new Date(), saasOrder.getRepaymentDt()) > 0) {
                return OrderStatusEnum.OVERDUE;
            } else {
                return OrderStatusEnum.IN_EXTEND;
            }
        }
        return OrderStatusEnum.getEnumByCode(saasOrder.getOrderStatus());
    }

    @Override
    public List<SaasOrderVo> listEffectiveOrderByOrderNumb(String orderNumb) {
        List<SaasOrder> saasOrderList = saasOrderDao.selectByParams(new HashMap<String, Object>(4) {{
            put("orderNumb", orderNumb);
            put("expireDate", new Date());
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasOrderList)) {
            return null;
        }
        List<SaasOrderVo> results = new ArrayList<>(saasOrderList.size());
        saasOrderList.forEach(saasOrder -> results.add(SaasOrderVo.convertEntityToVO(saasOrder)));
        return results;
    }

    @Override
    public String getReviewerRefuseOrderNumb(String borrowerCode, String channelCode) {
        SaasOrder saasOrder = saasOrderDao.selectByBorrowerCodeAndChannelCode(borrowerCode, channelCode);
        if (saasOrder == null) {
            return null;
        }
        if (OrderStatusEnum.PRELIMINARY_REVIEWER_REJECT.getCode().equals(saasOrder.getOrderStatus())
                || OrderStatusEnum.FINAL_REVIEWER_REJECT.getCode().equals(saasOrder.getOrderStatus())) {
            return saasOrder.getOrderNumb();
        }
        return null;
    }

    @Override
    public Boolean isReviewing(String borrowerCode, String channelCode) {
        SaasOrder saasOrder = saasOrderDao.selectByBorrowerCodeAndChannelCode(borrowerCode, channelCode);
        if (saasOrder == null) {
            return Boolean.FALSE;
        }
        if (saasOrder.getOrderStatus() > 400) {
            return Boolean.FALSE;
        }
        if (OrderStatusEnum.PRELIMINARY_REVIEWER_REJECT.getCode().equals(saasOrder.getOrderStatus())
                || OrderStatusEnum.PRELIMINARY_REVIEWER_REFUSE.getCode().equals(saasOrder.getOrderStatus())
                || OrderStatusEnum.FINAL_REVIEWER_REJECT.getCode().equals(saasOrder.getOrderStatus())
                || OrderStatusEnum.FINAL_REVIEWER_REFUSE.getCode().equals(saasOrder.getOrderStatus())
                || OrderStatusEnum.LOAN_LENDER_REFUSE.getCode().equals(saasOrder.getOrderStatus())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
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
        conditions.put("repaymentBeginDt", querySaasOrderVo.getRepaymentBeginDt());
        conditions.put("repaymentEndDt", querySaasOrderVo.getRepaymentEndDt());
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
    public SaasOrderVo getByOrderNumbAndMerchantCode(String orderNumb, String merchantCode) {
        return SaasOrderVo.convertEntityToVO(saasOrderDao.selectByOrderNumbAndMerchantCode(orderNumb, merchantCode));
    }

    @Override
    public Boolean updateOrderStatus(Long orderId, Long version, OrderStatusEnum currentOrderStatus, OrderStatusEnum updateOrderStatus) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("id", orderId);
        params.put("version", version);
        params.put("currentOrderStatus", currentOrderStatus.getCode());
        params.put("updateOrderStatus", updateOrderStatus.getCode());
        return saasOrderDao.updateOrderStatus(params) > 0;
    }

    @Override
    public Boolean updateOrderRemark(Long orderId, String remark) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("id", orderId);
        params.put("remark", remark);
        return saasOrderDao.updateOrderRemark(params) > 0;
    }

    @Override
    public List<SaasOrderVo> listAllConfirmReceiptOrderByBorrowerCode(String borrowerCode) {
        List<SaasOrder> saasOrderList = saasOrderDao.selectByBorrowerCodeAndOrderStatusList(borrowerCode,
                Arrays.asList(OrderStatusEnum.TO_CONFIRM_RECEIPT.getCode()));
        if (CollectionUtils.isEmpty(saasOrderList)) {
            return null;
        }
        List<SaasOrderVo> results = new ArrayList<>(saasOrderList.size());
        saasOrderList.forEach(saasOrder -> results.add(SaasOrderVo.convertEntityToVO(saasOrder)));
        return results;
    }

    @Override
    public List<String> listAllConfirmReceiptOrderNumbByMerchantCode(String merchantCode) {
        return saasOrderDao.selectOrderNumbByParams(merchantCode, OrderStatusEnum.TO_CONFIRM_RECEIPT.getCode());
    }

    @Override
    public SaasOrderVo getConfirmExtendOrderByOrderNumb(String orderNumb) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("orderNumb", orderNumb);
        params.put("deleted", Boolean.FALSE);
        params.put("expireDate", new Date());
        params.put("orderStatus", OrderStatusEnum.TO_CONFIRM_EXTEND.getCode());
        List<SaasOrder> saasOrderList = saasOrderDao.selectByParams(params);
        if (CollectionUtils.isEmpty(saasOrderList)) {
            return null;
        }
        return SaasOrderVo.convertEntityToVO(saasOrderList.get(0));
    }

    @Override
    public List<String> listAllConfirmExtendOrderNumbByMerchantCode(String merchantCode) {
        return saasOrderDao.selectOrderNumbByParams(merchantCode, OrderStatusEnum.TO_CONFIRM_EXTEND.getCode());
    }

    @Override
    public Boolean updatePreliminaryReviewerCode(Long orderId, String operatorCode) {
        return saasOrderDao.updatePreliminaryReviewerCode(orderId, operatorCode) > 0;
    }

    @Override
    public Boolean updateFinalReviewerCode(Long orderId, String operatorCode) {
        return saasOrderDao.updateFinalReviewerCode(orderId, operatorCode) > 0;
    }

    @Override
    public SaasOrderVo getConfirmReceiptOrderByOrderNumb(String orderNumb) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("orderNumb", orderNumb);
        params.put("deleted", Boolean.FALSE);
        params.put("expireDate", new Date());
        params.put("orderStatus", OrderStatusEnum.TO_CONFIRM_RECEIPT.getCode());
        List<SaasOrder> saasOrderList = saasOrderDao.selectByParams(params);
        if (CollectionUtils.isEmpty(saasOrderList)) {
            return null;
        }
        return SaasOrderVo.convertEntityToVO(saasOrderList.get(0));
    }

    @Override
    public SaasOrderVo getMainSaasOrderByOrderNumb(String orderNumb) {
        return SaasOrderVo.convertEntityToVO(saasOrderDao.selectMainSaasOrderByOrderNumb(orderNumb));
    }

}