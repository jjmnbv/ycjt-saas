package com.beitu.saas.credit.service.impl;

import com.beitu.saas.credit.client.SaasCreditTongdunService;
import com.beitu.saas.credit.dao.SaasCreditTongdunDao;
import com.beitu.saas.credit.domain.SaasCreditTongdunVo;
import com.beitu.saas.credit.entity.SaasCreditTongdun;
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
 * Time: 18:11:44.673
 */
@Module(value = "SAAS同盾信用记录查询表服务模块")
@NameSpace("com.beitu.saas.credit.dao.impl.SaasCreditTongdunDaoImpl")
@Service
public class SaasCreditTongdunServiceImpl extends AbstractBaseService implements SaasCreditTongdunService {

    @Autowired
    private SaasCreditTongdunDao saasCreditTongdunDao;

    @Override
    public SaasCreditTongdun addSaasCreditTongdun(SaasCreditTongdunVo saasCreditTongdunVo) {
        return saasCreditTongdunDao.insert(SaasCreditTongdunVo.convertVOToEntity(saasCreditTongdunVo));
    }

    @Override
    public SaasCreditTongdunVo getByMerchantCodeAndBorrowerCode(String merchantCode, String borrowerCode) {
        List<SaasCreditTongdun> saasCreditTongdunList = saasCreditTongdunDao.selectByParams(new HashMap<String, Object>(4) {{
            put("merchantCode", merchantCode);
            put("borrowerCode", borrowerCode);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasCreditTongdunList)) {
            return null;
        }
        return SaasCreditTongdunVo.convertEntityToVO(saasCreditTongdunList.get(0));
    }

    @Override
    public SaasCreditTongdunVo getByMobileAndIdentityCode(String mobile, String identityCode) {
        List<SaasCreditTongdun> saasCreditTongdunList = saasCreditTongdunDao.selectByParams(new HashMap<String, Object>(4) {{
            put("mobile", mobile);
            put("identityCode", identityCode);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasCreditTongdunList)) {
            return null;
        }
        return SaasCreditTongdunVo.convertEntityToVO(saasCreditTongdunList.get(0));
    }

    @Override
    public Boolean updateSuccess(Long id) {
        SaasCreditTongdun saasCreditTongdun = new SaasCreditTongdun();
        saasCreditTongdun.setSuccess(Boolean.TRUE);
        saasCreditTongdun.setId(id);
        return saasCreditTongdunDao.updateByPrimaryKey(saasCreditTongdun) > 0;
    }
}


