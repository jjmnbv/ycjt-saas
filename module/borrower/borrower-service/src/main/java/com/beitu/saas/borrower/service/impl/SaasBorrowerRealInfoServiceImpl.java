package com.beitu.saas.borrower.service.impl;

import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.dao.SaasBorrowerRealInfoDao;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.borrower.entity.SaasBorrowerRealInfo;
import com.beitu.saas.common.utils.identityNumber.IdentityNumberUtil;
import com.beitu.saas.common.utils.identityNumber.vo.IdcardInfoExtractor;
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
 * Date: 2018-03-22
 * Time: 15:56:47.005
 */
@Module(value = "SAAS借款人实名信息表服务模块")
@NameSpace("com.beitu.saas.borrower.dao.impl.SaasBorrowerRealInfoDaoImpl")
@Service
public class SaasBorrowerRealInfoServiceImpl extends AbstractBaseService implements SaasBorrowerRealInfoService {


    @Autowired
    private SaasBorrowerRealInfoDao saasBorrowerRealInfoDao;

    @Override
    public SaasBorrowerRealInfoVo getBorrowerRealInfoByBorrowerCode(String borrowerCode) {
        List<SaasBorrowerRealInfo> saasBorrowerRealInfoList = saasBorrowerRealInfoDao.selectByParams(new HashMap<String, Object>(4) {{
            put("borrowerCode", borrowerCode);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasBorrowerRealInfoList)) {
            return null;
        }
        return SaasBorrowerRealInfoVo.convertEntityToVO(saasBorrowerRealInfoList.get(0));
    }

    @Override
    public SaasBorrowerRealInfoVo getBorrowerRealInfoByIdentityCodeAndMerchantCode(String identityCode, String merchantCode) {
        List<SaasBorrowerRealInfo> saasBorrowerRealInfoList = saasBorrowerRealInfoDao.selectByParams(new HashMap<String, Object>(4) {{
            put("merchantCode", merchantCode);
            put("identityCode", identityCode);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasBorrowerRealInfoList)) {
            return null;
        }
        return SaasBorrowerRealInfoVo.convertEntityToVO(saasBorrowerRealInfoList.get(0));
    }

    @Override
    public List<SaasBorrowerRealInfoVo> listBorrowerRealInfoByNameAndMerchantCode(String name, String merchantCode) {
        List<SaasBorrowerRealInfo> saasBorrowerRealInfoList = saasBorrowerRealInfoDao.selectByParams(new HashMap<String, Object>(4) {{
            put("merchantCode", merchantCode);
            put("name", name);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasBorrowerRealInfoList)) {
            return null;
        }
        List<SaasBorrowerRealInfoVo> results = new ArrayList<>(saasBorrowerRealInfoList.size());
        saasBorrowerRealInfoList.forEach(saasBorrowerRealInfo -> results.add(SaasBorrowerRealInfoVo.convertEntityToVO(saasBorrowerRealInfo)));
        return results;
    }

    @Override
    public SaasBorrowerRealInfo create(String merchantCode, String borrowerCode, String name, String identityCode) {
        SaasBorrowerRealInfo saasBorrowerRealInfo = new SaasBorrowerRealInfo();
        saasBorrowerRealInfo.setBorrowerCode(borrowerCode);
        saasBorrowerRealInfo.setMerchantCode(merchantCode);
        saasBorrowerRealInfo.setName(name);
        saasBorrowerRealInfo.setIdentityCode(identityCode.toUpperCase());
        IdcardInfoExtractor idcardInfoExtractor = new IdcardInfoExtractor(identityCode);
        saasBorrowerRealInfo.setGender(IdcardInfoExtractor.MALE.equals(idcardInfoExtractor.getGender()) ? 1 : 0);
        saasBorrowerRealInfo.setNativePlace(idcardInfoExtractor.getProvince());
        return saasBorrowerRealInfoDao.insert(saasBorrowerRealInfo);
    }

}


