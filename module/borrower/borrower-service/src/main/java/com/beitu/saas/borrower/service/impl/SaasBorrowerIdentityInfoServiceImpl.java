package com.beitu.saas.borrower.service.impl;

import com.beitu.saas.borrower.client.SaasBorrowerIdentityInfoService;
import com.beitu.saas.borrower.dao.SaasBorrowerIdentityInfoDao;
import com.beitu.saas.borrower.domain.SaasBorrowerIdentityInfoVo;
import com.beitu.saas.borrower.entity.SaasBorrowerIdentityInfo;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 15:56:46.986
 */
@Module(value = "SAAS借款人身份证信息表服务模块")
@NameSpace("com.beitu.saas.borrower.dao.impl.SaasBorrowerIdentityInfoDaoImpl")
@Service
public class SaasBorrowerIdentityInfoServiceImpl extends AbstractBaseService implements SaasBorrowerIdentityInfoService {

    @Autowired
    private SaasBorrowerIdentityInfoDao saasBorrowerIdentityInfoDao;

    @Override
    public SaasBorrowerIdentityInfoVo getByBorrowerCodeAndOrderNumb(String borrowerCode, String orderNumb) {
        if (StringUtils.isNotEmpty(orderNumb)) {
            List<SaasBorrowerIdentityInfo> saasBorrowerIdentityInfoList = saasBorrowerIdentityInfoDao.selectByParams(new HashMap<String, Object>(4) {{
                put("orderNumb", orderNumb);
                put("deleted", Boolean.FALSE);
            }});
            if (CollectionUtils.isNotEmpty(saasBorrowerIdentityInfoList)) {
                return SaasBorrowerIdentityInfoVo.convertEntityToVO(saasBorrowerIdentityInfoList.get(0));
            }
        }
        List<SaasBorrowerIdentityInfo> saasBorrowerIdentityInfoList = saasBorrowerIdentityInfoDao.selectByParams(new HashMap<String, Object>(4) {{
            put("borrowerCode", borrowerCode);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasBorrowerIdentityInfoList)) {
            return null;
        }
        return SaasBorrowerIdentityInfoVo.convertEntityToVO(saasBorrowerIdentityInfoList.get(0));
    }

}


