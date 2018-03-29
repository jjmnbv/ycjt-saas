package com.beitu.saas.finance.service.impl;

import com.beitu.saas.common.utils.PojoUtil;
import com.beitu.saas.finance.client.SaasSmsHistoryService;
import com.beitu.saas.finance.client.param.CreditHistoryQueryParam;
import com.beitu.saas.finance.client.param.SmsHistoryQueryParam;
import com.beitu.saas.finance.dao.SaasSmsHistoryDao;
import com.beitu.saas.finance.entity.SaasCreditHistoryEntity;
import com.beitu.saas.finance.entity.SaasSmsHistoryEntity;
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
 * Time: 20:33:11.076
 */
@Module(value = "账户短信条数流水表服务模块")
@NameSpace("com.fqgj.demo.dao.impl.SaasSmsHistoryDaoImpl")
@Service
public class SaasSmsHistoryServiceImpl extends AbstractBaseService implements SaasSmsHistoryService {


    @Autowired
    private SaasSmsHistoryDao saasSmsHistoryDao;

    @Override
    public Long getYesterdaySmsStatCredit(String merchantCode, Date yesterday) {
        Long totalConsumeSmsCount = saasSmsHistoryDao.selectYesterdaySmsStatCredit(merchantCode, yesterday);
        return totalConsumeSmsCount == null ? 0 : totalConsumeSmsCount;
    }

    @Override
    public List<SaasSmsHistoryEntity> getSmsListByParam(SmsHistoryQueryParam param, Page page) {
        Map map = PojoUtil.convert2Map(param);
        map.put("page", page);
        if (null != page) {
            page.setTotalCount(saasSmsHistoryDao.queryTotalSmsListByParam(map));
        }
        return saasSmsHistoryDao.selectSmsListByParam(map);
    }
}


