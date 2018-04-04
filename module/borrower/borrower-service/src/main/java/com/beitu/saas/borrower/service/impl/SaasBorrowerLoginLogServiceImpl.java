package com.beitu.saas.borrower.service.impl;

import com.beitu.saas.borrower.client.SaasBorrowerLoginLogService;
import com.beitu.saas.borrower.dao.SaasBorrowerLoginLogDao;
import com.beitu.saas.borrower.domain.SaasBorrowerLoginLogVo;
import com.beitu.saas.borrower.entity.SaasBorrowerLoginLog;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: jungle
 * Date: 2018-04-03
 * Time: 15:11:35.426
 */
@Module(value = "SAAS借款人登录日志表服务模块")
@NameSpace("com.beitu.saas.borrower.dao.impl.SaasBorrowerLoginLogDaoImpl")
@Service
public class SaasBorrowerLoginLogServiceImpl extends AbstractBaseService implements SaasBorrowerLoginLogService {

    @Autowired
    private SaasBorrowerLoginLogDao saasBorrowerLoginLogDao;

    @Override
    public String getBorrowerPhoneSystem(String borrowerCode) {
        return saasBorrowerLoginLogDao.selectPhoneSystemByBorrowerCode(borrowerCode);
    }

    @Override
    public List<SaasBorrowerLoginLogVo> listBorrowerLivingArea(String borrowerCode) {
        List<SaasBorrowerLoginLog> saasBorrowerLoginLogList = saasBorrowerLoginLogDao.listBorrowerLivingArea(borrowerCode);
        if (CollectionUtils.isEmpty(saasBorrowerLoginLogList)) {
            return null;
        }
        List<SaasBorrowerLoginLogVo> results = new ArrayList<>(saasBorrowerLoginLogList.size());
        saasBorrowerLoginLogList.forEach(saasBorrowerLoginLog -> results.add(SaasBorrowerLoginLogVo.convertEntityToVO(saasBorrowerLoginLog)));
        return results;
    }

}