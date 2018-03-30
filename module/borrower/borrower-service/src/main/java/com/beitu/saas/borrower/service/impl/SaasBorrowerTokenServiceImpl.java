package com.beitu.saas.borrower.service.impl;

import com.beitu.saas.borrower.client.SaasBorrowerTokenService;
import com.beitu.saas.borrower.dao.SaasBorrowerTokenDao;
import com.beitu.saas.borrower.domain.SaasBorrowerTokenVo;
import com.beitu.saas.borrower.entity.SaasBorrowerToken;
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
 * Time: 15:56:47.013
 */
@Module(value = "SAAS借款人TOKEN表服务模块")
@NameSpace("com.beitu.saas.borrower.dao.impl.SaasBorrowerTokenDaoImpl")
@Service
public class SaasBorrowerTokenServiceImpl extends AbstractBaseService implements SaasBorrowerTokenService {

    @Autowired
    private SaasBorrowerTokenDao saasBorrowerTokenDao;

    @Override
    public String getBorrowerCodeByToken(String token) {
        return null;
    }

    @Override
    public SaasBorrowerToken create(String borrowerCode, String merchantCode) {
        SaasBorrowerToken saasBorrowerToken = new SaasBorrowerToken();
        saasBorrowerToken.setBorrowerCode(borrowerCode);
        saasBorrowerToken.setMerchantCode(merchantCode);
        saasBorrowerToken.setToken(saasBorrowerToken.createToken());
        saasBorrowerToken.setExpireDate(saasBorrowerToken.createExpireDate());
        return saasBorrowerTokenDao.insert(saasBorrowerToken);
    }

    @Override
    public SaasBorrowerToken refreshToken(String borrowerCode, String merchantCode) {
        List<SaasBorrowerToken> saasBorrowerTokenList = saasBorrowerTokenDao.selectByParams(new HashMap<String, Object>(4) {{
            put("borrowerCode", borrowerCode);
            put("merchantCode", merchantCode);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasBorrowerTokenList)) {
            return create(borrowerCode, merchantCode);
        }
        SaasBorrowerToken saasBorrowerToken = saasBorrowerTokenList.get(0);
        saasBorrowerToken.setToken(saasBorrowerToken.createToken());
        saasBorrowerToken.setExpireDate(saasBorrowerToken.createExpireDate());
        if (saasBorrowerTokenDao.updateByPrimaryKey(saasBorrowerToken) > 0) {
            return saasBorrowerToken;
        }
        return null;
    }
}


