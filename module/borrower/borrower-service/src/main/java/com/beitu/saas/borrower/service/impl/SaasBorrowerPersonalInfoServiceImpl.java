package com.beitu.saas.borrower.service.impl;

import com.beitu.saas.borrower.client.SaasBorrowerPersonalInfoService;
import com.beitu.saas.borrower.dao.SaasBorrowerPersonalInfoDao;
import com.beitu.saas.borrower.domain.SaasBorrowerPersonalInfoVo;
import com.beitu.saas.borrower.entity.SaasBorrowerPersonalInfo;
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
 * Time: 15:56:46.996
 */
@Module(value = "SAAS借款人个人信息表服务模块")
@NameSpace("com.beitu.saas.borrower.dao.impl.SaasBorrowerPersonalInfoDaoImpl")
@Service
public class SaasBorrowerPersonalInfoServiceImpl extends AbstractBaseService implements SaasBorrowerPersonalInfoService {


    @Autowired
    private SaasBorrowerPersonalInfoDao saasBorrowerPersonalInfoDao;

    @Override
    public SaasBorrowerPersonalInfoVo getByBorrowerCodeAndOrderNumb(String borrowerCode, String orderNumb) {
        if (StringUtils.isNotEmpty(orderNumb)) {
            List<SaasBorrowerPersonalInfo> saasBorrowerPersonalInfoList = saasBorrowerPersonalInfoDao.selectByParams(new HashMap<String, Object>(4) {{
                put("orderNumb", orderNumb);
                put("deleted", Boolean.FALSE);
            }});
            if (CollectionUtils.isNotEmpty(saasBorrowerPersonalInfoList)) {
                return SaasBorrowerPersonalInfoVo.convertEntityToVO(saasBorrowerPersonalInfoList.get(0));
            }
        }
        List<SaasBorrowerPersonalInfo> saasBorrowerPersonalInfoList = saasBorrowerPersonalInfoDao.selectByParams(new HashMap<String, Object>(4) {{
            put("borrowerCode", borrowerCode);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasBorrowerPersonalInfoList)) {
            return null;
        }
        return SaasBorrowerPersonalInfoVo.convertEntityToVO(saasBorrowerPersonalInfoList.get(0));
    }

}


