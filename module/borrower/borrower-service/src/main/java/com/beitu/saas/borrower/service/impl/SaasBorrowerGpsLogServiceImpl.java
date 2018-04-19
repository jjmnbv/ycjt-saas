package com.beitu.saas.borrower.service.impl;

import com.beitu.saas.borrower.client.SaasBorrowerGpsLogService;
import com.beitu.saas.borrower.dao.SaasBorrowerGpsLogDao;
import com.beitu.saas.borrower.domain.SaasBorrowerGpsLogVo;
import com.beitu.saas.borrower.entity.SaasBorrowerGpsLog;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.common.utils.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fqgj.log.enhance.Module;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: linchengyu
 * Date: 2018-04-19
 * Time: 14:04:03.578
 */
@Module(value = "借款人GPS信息表服务模块")
@NameSpace("com.beitu.saas.borrower.dao.impl.SaasBorrowerGpsLogDaoImpl")
@Service
public class SaasBorrowerGpsLogServiceImpl extends AbstractBaseService implements SaasBorrowerGpsLogService {
    
    @Autowired
    private SaasBorrowerGpsLogDao saasBorrowerGpsLogDao;
    
    @Override
    public Boolean batchAddSaasBorrowerGpsLogVo(List list) {
        if (CollectionUtils.isEmpty(list)) {
            return Boolean.FALSE;
        }
        List<SaasBorrowerGpsLog> addList = new ArrayList<>(list.size());
        list.forEach(object -> addList.add(SaasBorrowerGpsLogVo.convertVO2Entity((SaasBorrowerGpsLogVo) object)));
        return saasBorrowerGpsLogDao.batchAddSaasBorrowerGpsLog(addList) > 0;
    }
    
    @Override
    public Date getLastDateByBorrowerCodeAndMerchantCode(String merchantCode, String borrowerCode) {
        List<SaasBorrowerGpsLog> logs = saasBorrowerGpsLogDao.selectByBorrowerCodeAndMerchantCode(merchantCode, borrowerCode, 1);
        if (CollectionUtils.isEmpty(logs)) {
            return null;
        }
        return logs.get(0).getLogTime();
    }
    
    @Override
    public List<SaasBorrowerGpsLogVo> listByBorrowerCodeAndMerchantCode(String merchantCode, String borrowerCode) {
        List<SaasBorrowerGpsLog> logs = saasBorrowerGpsLogDao.selectByBorrowerCodeAndMerchantCode(merchantCode, borrowerCode, null);
        if (CollectionUtils.isEmpty(logs)) {
            return null;
        }
        List<SaasBorrowerGpsLogVo> list = new ArrayList<>(logs.size());
        logs.forEach(object -> list.add(SaasBorrowerGpsLogVo.convertEntity2VO((SaasBorrowerGpsLog) object)));
        return list;
    }
    
}


