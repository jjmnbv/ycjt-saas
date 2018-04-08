package com.beitu.saas.borrower.service.impl;

import com.beitu.saas.borrower.client.SaasBorrowerService;
import com.beitu.saas.borrower.dao.SaasBorrowerDao;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.borrower.entity.SaasBorrower;
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
 * Date: 2018-03-22
 * Time: 15:56:46.944
 */
@Module(value = "SAAS借款人表服务模块")
@NameSpace("com.beitu.saas.borrower.dao.impl.SaasBorrowerDaoImpl")
@Service
public class SaasBorrowerServiceImpl extends AbstractBaseService implements SaasBorrowerService {

    @Autowired
    private SaasBorrowerDao saasBorrowerDao;

    @Override
    public SaasBorrowerVo getByBorrowerCodeAndMerchantCode(String borrowerCode, String merchantCode) {
        List<SaasBorrower> saasBorrowerList = saasBorrowerDao.selectByParams(new HashMap<String, Object>(4) {{
            put("borrowerCode", borrowerCode);
            put("merchantCode", merchantCode);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasBorrowerList)) {
            return null;
        }
        return SaasBorrowerVo.convertEntityToVO(saasBorrowerList.get(0));
    }

    @Override
    public SaasBorrowerVo getByMobileAndMerchantCode(String mobile, String merchantCode) {
        List<SaasBorrower> saasBorrowerList = saasBorrowerDao.selectByParams(new HashMap<String, Object>(4) {{
            put("mobile", mobile);
            put("merchantCode", merchantCode);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasBorrowerList)) {
            return null;
        }
        return SaasBorrowerVo.convertEntityToVO(saasBorrowerList.get(0));
    }

    @Override
    public SaasBorrower create(SaasBorrowerVo saasBorrowerVo) {
        return saasBorrowerDao.insert(SaasBorrowerVo.convertVOToEntity(saasBorrowerVo));
    }

    @Override
    public String getMobileByBorrowerCode(String borrowerCode) {
        List<SaasBorrower> saasBorrowerList = saasBorrowerDao.selectByParams(new HashMap<String, Object>(2) {{
            put("borrowerCode", borrowerCode);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasBorrowerList)) {
            return null;
        }
        return saasBorrowerList.get(0).getMobile();
    }

    @Override
    public Boolean isSaasBorrower(String userCode) {
        List<SaasBorrower> saasBorrowerList = saasBorrowerDao.selectByParams(new HashMap<String, Object>(2) {{
            put("borrowerCode", userCode);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasBorrowerList)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public SaasBorrowerVo getByBorrowerCode(String borrowerCode) {
        List<SaasBorrower> saasBorrowerList = saasBorrowerDao.selectByParams(new HashMap<String, Object>(2) {{
            put("borrowerCode", borrowerCode);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasBorrowerList)) {
            return null;
        }
        return SaasBorrowerVo.convertEntityToVO(saasBorrowerList.get(0));
    }

}


