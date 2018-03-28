package com.beitu.saas.order.service.impl;

import com.beitu.saas.order.client.SaasOrderBillDetailService;
import com.beitu.saas.order.dao.SaasOrderBillDetailDao;
import com.beitu.saas.order.domain.QuerySaasOrderBillDetailVo;
import com.beitu.saas.order.domain.SaasOrderBillDetailVo;
import com.beitu.saas.order.vo.LoanDataDetailVo;
import com.beitu.saas.order.vo.NoRepayOrderVo;
import com.beitu.saas.order.vo.OverdueOrderVo;
import com.fqgj.common.api.Page;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }

    @Override
    public List<SaasOrderBillDetailVo> listByOrderNumb(String orderNumb) {
        return null;
    }

    @Override
    public List<SaasOrderBillDetailVo> listByQueryOrderBillDetailVoAndPage(QuerySaasOrderBillDetailVo querySaasOrderBillDetailVo, Page page) {
        return null;
    }

    @Override
    public LoanDataDetailVo getLoanDataDetailVo(String merchantCode) {
        return saasOrderBillDetailDao.selectLoanDataDetail(merchantCode);
    }

    @Override
    public List<NoRepayOrderVo> getNoRepayOrderListByPage(String merchantCode, Page page) {
        return saasOrderBillDetailDao.selectNoRepayOrder(merchantCode, page);
    }

    @Override
    public List<OverdueOrderVo> getOverdueOrderListByPage(String merchantCode, Page page) {
        return saasOrderBillDetailDao.selectOverdueOrder(merchantCode, page);
    }

}


