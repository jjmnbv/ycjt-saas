package com.beitu.saas.order.service.impl;

import com.beitu.saas.order.client.SaasOrderBillDetailService;
import com.beitu.saas.order.dao.SaasOrderBillDetailDao;
import com.beitu.saas.order.domain.QuerySaasOrderBillDetailVo;
import com.beitu.saas.order.domain.SaasOrderBillDetailVo;
import com.beitu.saas.order.entity.SaasOrderBillDetail;
import com.beitu.saas.order.enums.OrderStatusEnum;
import com.beitu.saas.order.vo.LoanDataDetailVo;
import com.beitu.saas.order.vo.LoanStateDetailVo;
import com.beitu.saas.order.vo.DashboardOrderVo;
import com.fqgj.common.api.Page;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * User: jungle
 * Date: 2018-03-25
 * Time: 21:55:45.870
 */
@Module(value = "SAAS订单详情表服务模块")
@NameSpace("com.beitu.saas.order.dao.impl.SaasOrderBillDetailDaoImpl")
@Service
public class SaasOrderBillDetailServiceImpl extends AbstractBaseService implements SaasOrderBillDetailService {

    @Autowired
    private SaasOrderBillDetailDao saasOrderBillDetailDao;

    @Override
    public List<SaasOrderBillDetailVo> listByBorrowerCodeAndMerchantCode(String borrowerCode, String merchantCode) {
        List<SaasOrderBillDetail> saasOrderBillDetailList = saasOrderBillDetailDao.selectByBorrowerCodeAndMerchantCode(borrowerCode, merchantCode);
        if (CollectionUtils.isEmpty(saasOrderBillDetailList)) {
            return null;
        }
        List<SaasOrderBillDetailVo> results = new ArrayList<>(saasOrderBillDetailList.size());
        saasOrderBillDetailList.forEach(saasOrderBillDetail -> results.add(SaasOrderBillDetailVo.convertEntityToVO(saasOrderBillDetail)));
        return results;
    }

    @Override
    public List<SaasOrderBillDetailVo> listByOrderNumbAndMerchantCode(String orderNumb, String merchantCode) {
        List<SaasOrderBillDetail> saasOrderBillDetailList = saasOrderBillDetailDao.selectByOrderNumbAndMerchantCode(orderNumb, merchantCode);
        if (CollectionUtils.isEmpty(saasOrderBillDetailList)) {
            return null;
        }
        List<SaasOrderBillDetailVo> results = new ArrayList<>(saasOrderBillDetailList.size());
        saasOrderBillDetailList.forEach(saasOrderBillDetail -> results.add(SaasOrderBillDetailVo.convertEntityToVO(saasOrderBillDetail)));
        return results;
    }

    @Override
    public List<SaasOrderBillDetailVo> listByQueryOrderBillDetailVoAndPage(QuerySaasOrderBillDetailVo querySaasOrderBillDetailVo, Page page) {
        Map<String, Object> conditions = new HashMap<>(16);
        conditions.put("borrowerCodeList", querySaasOrderBillDetailVo.getBorrowerCodeList());
        conditions.put("merchantCode", querySaasOrderBillDetailVo.getMerchantCode());
        conditions.put("channelCode", querySaasOrderBillDetailVo.getChannelCode());
        conditions.put("repaymentBeginDt", querySaasOrderBillDetailVo.getRepaymentBeginDt());
        conditions.put("repaymentEndDt", querySaasOrderBillDetailVo.getRepaymentEndDt());
        conditions.put("destroy", querySaasOrderBillDetailVo.getDestroy());
        Integer queryOrderStatus = querySaasOrderBillDetailVo.getQueryOrderStatus();
        if (queryOrderStatus != null) {
            conditions.put("destroy", Boolean.FALSE);
            if (OrderStatusEnum.FOR_REIMBURSEMENT.getCode().equals(queryOrderStatus)) {
                conditions.put("repaymentBeginDt", new Date());
            } else if (OrderStatusEnum.OVERDUE.getCode().equals(queryOrderStatus)) {
                conditions.put("repaymentEndDt", new Date());
            } else if (OrderStatusEnum.HAS_BEEN_DESTROY.getCode().equals(queryOrderStatus)) {
                conditions.put("destroy", Boolean.TRUE);
            }
        }
        Integer count = saasOrderBillDetailDao.countByConditions(conditions);
        page.setTotalCount(count);
        if (count == 0) {
            return null;
        }
        conditions.put("page", page);
        List<SaasOrderBillDetail> saasOrderBillDetailList = saasOrderBillDetailDao.selectByConditions(conditions);
        if (CollectionUtils.isEmpty(saasOrderBillDetailList)) {
            return null;
        }
        List<SaasOrderBillDetailVo> results = new ArrayList<>(saasOrderBillDetailList.size());
        saasOrderBillDetailList.forEach(saasOrderBillDetail -> results.add(SaasOrderBillDetailVo.convertEntityToVO(saasOrderBillDetail)));
        return results;
    }

    @Override
    public LoanDataDetailVo getLoanDataDetailVo(String merchantCode) {
        return saasOrderBillDetailDao.selectLoanDataDetail(merchantCode);
    }

    @Override
    public List<LoanStateDetailVo> getLoanStateDetailList(String merchantCode) {
        List<LoanStateDetailVo> loanStateDetailVos = saasOrderBillDetailDao.selectLoanStatDetailList(merchantCode);
        return loanStateDetailVos.size() == 0 ? new ArrayList<>() : saasOrderBillDetailDao.selectLoanStatDetailList(merchantCode);
    }

    @Override
    public List<DashboardOrderVo> getNoRepayOrderListByPage(String merchantCode, Page page) {
        List<DashboardOrderVo> dashboardOrderVos = saasOrderBillDetailDao.selectNoRepayOrder(merchantCode, page);
        return dashboardOrderVos.size() == 0 ? new ArrayList<>() : saasOrderBillDetailDao.selectNoRepayOrder(merchantCode, page);
    }

    @Override
    public List<DashboardOrderVo> getOverdueOrderListByPage(String merchantCode, Page page) {
        List<DashboardOrderVo> dashboardOrderVos = saasOrderBillDetailDao.selectOverdueOrder(merchantCode, page);
        return dashboardOrderVos.size() == 0 ? new ArrayList<>() : saasOrderBillDetailDao.selectOverdueOrder(merchantCode, page);
    }

    @Override
    public SaasOrderBillDetailVo getVisibleOrderBillDetailByOrderNumbAndMerchantCode(String orderNumb, String merchantCode) {
        List<SaasOrderBillDetail> saasOrderBillDetailList = saasOrderBillDetailDao.selectByParams(new HashMap<String, Object>(4) {{
            put("orderNumb", orderNumb);
            put("merchantCode", merchantCode);
            put("visible", Boolean.TRUE);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasOrderBillDetailList)) {
            return null;
        }
        return SaasOrderBillDetailVo.convertEntityToVO(saasOrderBillDetailList.get(0));
    }

}


