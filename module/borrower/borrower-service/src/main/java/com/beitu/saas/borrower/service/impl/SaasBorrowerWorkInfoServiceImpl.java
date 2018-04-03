package com.beitu.saas.borrower.service.impl;

import com.beitu.saas.borrower.client.SaasBorrowerWorkInfoService;
import com.beitu.saas.borrower.dao.SaasBorrowerWorkInfoDao;
import com.beitu.saas.borrower.domain.SaasBorrowerWorkInfoVo;
import com.beitu.saas.borrower.entity.SaasBorrowerWorkInfo;
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
 * Time: 15:56:47.022
 */
@Module(value = "SAAS借款人工作信息表服务模块")
@NameSpace("com.beitu.saas.borrower.dao.impl.SaasBorrowerWorkInfoDaoImpl")
@Service
public class SaasBorrowerWorkInfoServiceImpl extends AbstractBaseService implements SaasBorrowerWorkInfoService {

    @Autowired
    private SaasBorrowerWorkInfoDao saasBorrowerWorkInfoDao;


    @Override
    public int countByBorrowerCode(String borrowerCode) {
        return saasBorrowerWorkInfoDao.countH5SaveInfoByBorrowerCode(borrowerCode);
    }

    @Override
    public SaasBorrowerWorkInfoVo getByBorrowerCodeAndOrderNumb(String borrowerCode, String orderNumb) {
        return SaasBorrowerWorkInfoVo.convertEntityToVO(saasBorrowerWorkInfoDao.selectByBorrowerCodeAndOrderNumb(borrowerCode,orderNumb));
    }

    @Override
    public Boolean updateOrderNumbByBorrowerCode(String orderNumb, String borrowerCode) {
        return saasBorrowerWorkInfoDao.updateOrderNumbByBorrowerCode(orderNumb, borrowerCode) == 1;
    }

}


