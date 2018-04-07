package com.beitu.saas.credit.service.impl;

import com.beitu.saas.credit.client.SaasCreditBmpService;
import com.beitu.saas.credit.dao.SaasCreditBmpDao;
import com.beitu.saas.credit.domain.SaasCreditBmpVo;
import com.beitu.saas.credit.entity.SaasCreditBmp;
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
 * Time: 18:11:44.604
 */
@Module(value = "电话邦匹配数据查询表服务模块")
@NameSpace("com.beitu.saas.credit.dao.impl.SaasCreditBmpDaoImpl")
@Service
public class SaasCreditBmpServiceImpl extends AbstractBaseService implements SaasCreditBmpService {

    @Autowired
    private SaasCreditBmpDao saasCreditBmpDao;

    @Override
    public SaasCreditBmp addSaasCreditBmp(SaasCreditBmpVo saasCreditBmpVo) {
        return saasCreditBmpDao.insert(SaasCreditBmpVo.convertVOToEntity(saasCreditBmpVo));
    }

    @Override
    public SaasCreditBmpVo getByMerchantCodeAndBorrowerCode(String merchantCode, String borrowerCode) {
        List<SaasCreditBmp> saasCreditBmpList = saasCreditBmpDao.selectByParams(new HashMap<String, Object>(4) {{
            put("merchantCode", merchantCode);
            put("borrowerCode", borrowerCode);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasCreditBmpList)) {
            return null;
        }
        return SaasCreditBmpVo.convertEntityToVO(saasCreditBmpList.get(0));
    }

    @Override
    public Boolean updateSuccess(Long id) {
        SaasCreditBmp saasCreditBmp = new SaasCreditBmp();
        saasCreditBmp.setSuccess(Boolean.TRUE);
        saasCreditBmp.setId(id);
        return saasCreditBmpDao.updateByPrimaryKey(saasCreditBmp) > 0;
    }
}


