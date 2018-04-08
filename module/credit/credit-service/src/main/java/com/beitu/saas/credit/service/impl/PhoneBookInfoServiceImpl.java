package com.beitu.saas.credit.service.impl;

import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import com.beitu.saas.credit.client.PhoneBookInfoService;
import com.beitu.saas.credit.dao.PhoneBookInfoDao;
import com.beitu.saas.credit.domain.PhoneBookInfoVo;
import com.beitu.saas.credit.entity.PhoneBookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* User: linchengyu
* Date: 2017-06-29
* Time: 21:21:33.462
*/
@Module(value = "号码归属地信息表服务模块")
@NameSpace("com.beitu.saas.credit.dao.impl.PhoneBookInfoDaoImpl")
@Service
public class PhoneBookInfoServiceImpl extends AbstractBaseService implements PhoneBookInfoService {
    
    @Autowired
    private PhoneBookInfoDao phoneBookInfoDao;
    
    @Override
    public PhoneBookInfoVo getPhoneBookInfoByPhonePrefix(String prefixSeven) {
        PhoneBookInfo phoneBookInfo = phoneBookInfoDao.getByPhoneHeader(prefixSeven);
        return PhoneBookInfoVo.convertEntityToVO(phoneBookInfo);
    }
    
    @Override
    public PhoneBookInfoVo getPhoneBookInfoByAreaCode(String areaCode) {
        PhoneBookInfo phoneBookInfo = phoneBookInfoDao.getByAreaCode(areaCode);
        return PhoneBookInfoVo.convertEntityToVO(phoneBookInfo);
    }

    @Override
    public List<PhoneBookInfoVo> getAll() {
        List<PhoneBookInfo> phoneBookInfoList = phoneBookInfoDao.findAll();
        List<PhoneBookInfoVo> results = new ArrayList<>(phoneBookInfoList.size());
        phoneBookInfoList.forEach(phoneBookInfo -> results.add(PhoneBookInfoVo.convertEntityToVO(phoneBookInfo)));
        return results;
    }
}


