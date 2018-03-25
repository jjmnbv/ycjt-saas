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
    public SaasBorrowerRealInfo create(String borrowerCode, String name, String identityCode) {
        SaasBorrowerRealInfo saasBorrowerRealInfo = new SaasBorrowerRealInfo();
        saasBorrowerRealInfo.setBorrowerCode(borrowerCode);
        saasBorrowerRealInfo.setName(name);
        saasBorrowerRealInfo.setIdentityCode(identityCode);
        IdcardInfoExtractor idcardInfoExtractor = new IdcardInfoExtractor(identityCode);
        saasBorrowerRealInfo.setGender(IdcardInfoExtractor.MALE.equals(idcardInfoExtractor.getGender()) ? 1 : 0);
        saasBorrowerRealInfo.setNativePlace(idcardInfoExtractor.getProvince());
        return saasBorrowerRealInfoDao.insert(saasBorrowerRealInfo);
    }

}


