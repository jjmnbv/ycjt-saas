package com.beitu.saas.credit.service.impl;

import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.credit.client.SaasCreditDunningService;
import com.beitu.saas.credit.dao.SaasCreditDunningDao;
import com.beitu.saas.credit.domain.SaasCreditDunningVo;
import com.beitu.saas.credit.entity.SaasCreditDunning;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.662
 */
@Module(value = "用户催收数据查询表服务模块")
@NameSpace("com.beitu.saas.credit.dao.impl.SaasCreditDunningDaoImpl")
@Service
public class SaasCreditDunningServiceImpl extends AbstractBaseService implements SaasCreditDunningService {

    @Autowired
    private SaasCreditDunningDao saasCreditDunningDao;

    @Override
    public SaasCreditDunningVo getByMerchantCodeAndBorrowerCode(String merchantCode, String borrowerCode) {
        List<SaasCreditDunning> saasCreditDunningList = saasCreditDunningDao.selectByParams(new HashMap<String, Object>(4) {{
            put("merchantCode", merchantCode);
            put("borrowerCode", borrowerCode);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasCreditDunningList)) {
            return null;
        }
        return SaasCreditDunningVo.convertEntityToVO(saasCreditDunningList.get(saasCreditDunningList.size() - 1));
    }

    @Override
    public Boolean effectivenessCreditDunning(String borrowerCode) {
        List<SaasCreditDunning> saasCreditDunningList = saasCreditDunningDao.selectByParams(new HashMap<String, Object>(4) {{
            put("borrowerCode", borrowerCode);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasCreditDunningList)) {
            return Boolean.FALSE;
        }
        SaasCreditDunning latestSaasCreditDunning = saasCreditDunningList.get(saasCreditDunningList.size() - 1);
        if (latestSaasCreditDunning.getSuccess() && DateUtil.isExceedOneMonth(latestSaasCreditDunning.getGmtCreate())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public SaasCreditDunning addSaasCreditDunning(SaasCreditDunningVo saasCreditDunningVo) {
        return saasCreditDunningDao.insert(SaasCreditDunningVo.convertVOToEntity(saasCreditDunningVo));
    }

    @Override
    public Boolean updateSuccess(Long id) {
        SaasCreditDunning saasCreditDunning = new SaasCreditDunning();
        saasCreditDunning.setSuccess(Boolean.TRUE);
        saasCreditDunning.setId(id);
        return saasCreditDunningDao.updateByPrimaryKey(saasCreditDunning) > 0;
    }

}


