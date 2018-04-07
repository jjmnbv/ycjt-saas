package com.beitu.saas.credit.service.impl;

import com.beitu.saas.credit.client.SaasCreditCarrierBillService;
import com.beitu.saas.credit.dao.SaasCreditCarrierBillDao;
import com.beitu.saas.credit.domain.SaasCreditCarrierBillVo;
import com.beitu.saas.credit.entity.SaasCreditCarrierBill;
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
 * Date: 2018-04-06
 * Time: 18:11:44.647
 */
@Module(value = "运营商报告账单详情表服务模块")
@NameSpace("com.beitu.saas.credit.dao.impl.SaasCreditCarrierBillDaoImpl")
@Service
public class SaasCreditCarrierBillServiceImpl extends AbstractBaseService implements SaasCreditCarrierBillService {

    @Autowired
    private SaasCreditCarrierBillDao saasCreditCarrierBillDao;

    @Override
    public void batchAddSaasCreditCarrierBill(List list) {
        saasCreditCarrierBillDao.batchAddSaasCreditCarrierBill(list);
    }

    @Override
    public List<SaasCreditCarrierBillVo> listByRecordId(Long recordId) {
        List<SaasCreditCarrierBill> saasCreditCarrierBillList = saasCreditCarrierBillDao.selectByParams(new HashMap<String, Object>(4) {{
            put("recordId", recordId);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasCreditCarrierBillList)) {
            return null;
        }
        List<SaasCreditCarrierBillVo> results = new ArrayList<>(saasCreditCarrierBillList.size());
        saasCreditCarrierBillList.forEach(saasCreditCarrierBill -> results.add(SaasCreditCarrierBillVo.convertEntityToVO(saasCreditCarrierBill)));
        return results;
    }
}


