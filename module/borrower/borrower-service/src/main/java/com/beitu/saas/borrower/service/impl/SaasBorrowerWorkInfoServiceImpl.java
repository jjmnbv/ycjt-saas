package com.beitu.saas.borrower.service.impl;

import com.beitu.saas.borrower.client.SaasBorrowerWorkInfoService;
import com.beitu.saas.borrower.dao.SaasBorrowerWorkInfoDao;
import com.beitu.saas.borrower.domain.SaasBorrowerPersonalInfoVo;
import com.beitu.saas.borrower.domain.SaasBorrowerWorkInfoVo;
import com.beitu.saas.borrower.entity.SaasBorrowerPersonalInfo;
import com.beitu.saas.borrower.entity.SaasBorrowerWorkInfo;
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
 * Time: 15:56:47.022
 */
@Module(value = "SAAS借款人工作信息表服务模块")
@NameSpace("com.beitu.saas.borrower.dao.impl.SaasBorrowerWorkInfoDaoImpl")
@Service
public class SaasBorrowerWorkInfoServiceImpl extends AbstractBaseService implements SaasBorrowerWorkInfoService {

    @Autowired
    private SaasBorrowerWorkInfoDao saasBorrowerWorkInfoDao;

    @Override
    public SaasBorrowerWorkInfoVo getByBorrowerCodeAndOrderNumb(String borrowerCode, String orderNumb) {
        if (StringUtils.isNotEmpty(orderNumb)) {
            List<SaasBorrowerWorkInfo> saasBorrowerWorkInfoList = saasBorrowerWorkInfoDao.selectByParams(new HashMap<String, Object>(4) {{
                put("orderNumb", orderNumb);
                put("deleted", Boolean.FALSE);
            }});
            if (CollectionUtils.isNotEmpty(saasBorrowerWorkInfoList)) {
                return SaasBorrowerWorkInfoVo.convertEntityToVO(saasBorrowerWorkInfoList.get(0));
            }
        }
        List<SaasBorrowerWorkInfo> saasBorrowerWorkInfoList = saasBorrowerWorkInfoDao.selectByParams(new HashMap<String, Object>(4) {{
            put("borrowerCode", borrowerCode);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasBorrowerWorkInfoList)) {
            return null;
        }
        return SaasBorrowerWorkInfoVo.convertEntityToVO(saasBorrowerWorkInfoList.get(0));
    }

}


