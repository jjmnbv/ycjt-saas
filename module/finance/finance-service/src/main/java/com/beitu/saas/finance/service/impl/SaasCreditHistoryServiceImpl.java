package com.beitu.saas.finance.service.impl;

import com.beitu.saas.common.utils.PojoUtil;
import com.beitu.saas.finance.client.SaasCreditHistoryService;
import com.beitu.saas.finance.client.SaasMerchantCreditInfoService;
import com.beitu.saas.finance.client.enums.OpTypeEnum;
import com.beitu.saas.finance.client.param.CreditHistoryQueryParam;
import com.beitu.saas.finance.dao.SaasCreditHistoryDao;
import com.beitu.saas.finance.entity.SaasCreditHistoryEntity;
import com.fqgj.common.api.Page;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * User: fenqiguanjia
 * Date: 2018-03-23
 * Time: 20:33:10.974
 */
@Module(value = "账户点券流水表服务模块")
@NameSpace("com.fqgj.demo.dao.impl.SaasCreditHistoryDaoImpl")
@Service
public class SaasCreditHistoryServiceImpl extends AbstractBaseService implements SaasCreditHistoryService {


    @Autowired
    private SaasCreditHistoryDao saasCreditHistoryDao;

    @Autowired
    private SaasMerchantCreditInfoService saasMerchantCreditInfoService;

    @Override
    public Long getYesterdayCreditStatCredit(String merchantCode, Date yesterday) {
        Long totalConsumeCreditCount = saasCreditHistoryDao.selectYesterdayCreditStatCredit(merchantCode, yesterday);
        return totalConsumeCreditCount == null ? 0 : totalConsumeCreditCount;
    }

    @Override
    public List<SaasCreditHistoryEntity> getCreditListByParam(CreditHistoryQueryParam param, Page page) {
        Map map = PojoUtil.convert2Map(param);
        map.put("page", page);
        if (null != page) {
            page.setTotalCount(saasCreditHistoryDao.queryTotalCreditListByParam(map));
        }
        return saasCreditHistoryDao.selectCreditListByParam(map);
    }

    @Override
    public SaasCreditHistoryEntity addExpenditureCreditHistory(String merchantCode, Long credit, String opName, String comment) {
        Long currentCredit = saasMerchantCreditInfoService.getCreditByMerchantCode(merchantCode);
        SaasCreditHistoryEntity saasCreditHistoryEntity = new SaasCreditHistoryEntity();
        saasCreditHistoryEntity.setComment(comment);
        saasCreditHistoryEntity.setMerchantCode(merchantCode);
        saasCreditHistoryEntity.setCurrentCredit(currentCredit - credit);
        saasCreditHistoryEntity.setCredit(credit);
        saasCreditHistoryEntity.setOpName(opName);
        saasCreditHistoryEntity.setOpType(OpTypeEnum.EXPENDITURE.getKey());
        return saasCreditHistoryDao.insert(saasCreditHistoryEntity);
    }

    @Override
    public SaasCreditHistoryEntity addIncomeCreditHistory(String merchantCode, Long credit, String opName, String comment) {
        Long currentCredit = saasMerchantCreditInfoService.getCreditByMerchantCode(merchantCode);
        SaasCreditHistoryEntity saasCreditHistoryEntity = new SaasCreditHistoryEntity();
        saasCreditHistoryEntity.setComment(comment);
        saasCreditHistoryEntity.setMerchantCode(merchantCode);
        saasCreditHistoryEntity.setCurrentCredit(currentCredit + credit);
        saasCreditHistoryEntity.setCredit(credit);
        saasCreditHistoryEntity.setOpName(opName);
        saasCreditHistoryEntity.setOpType(OpTypeEnum.INCOME.getKey());
        return saasCreditHistoryDao.insert(saasCreditHistoryEntity);
    }
}


