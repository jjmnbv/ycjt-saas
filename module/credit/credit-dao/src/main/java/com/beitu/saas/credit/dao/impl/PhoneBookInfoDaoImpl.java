package com.beitu.saas.credit.dao.impl;

import com.fqgj.common.base.AbstractBaseMapper;
import com.fqgj.common.utils.CollectionUtils;
import com.beitu.saas.credit.dao.PhoneBookInfoDao;
import com.beitu.saas.credit.entity.PhoneBookInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: linchengyu
 * Date: 2017-06-29
 * Time: 21:21:33.514
 */

@Repository
public class PhoneBookInfoDaoImpl extends AbstractBaseMapper<PhoneBookInfo> implements PhoneBookInfoDao {
    
    @Override
    public PhoneBookInfo getByPhoneHeader(String phoneHeader) {
        List<PhoneBookInfo> phoneBookInfos = this.getSqlSession().selectList(this.getStatement(".selectByPhoneHeader"), phoneHeader);
        if (CollectionUtils.isNotEmpty(phoneBookInfos)) {
            return phoneBookInfos.get(0);
        }
        return null;
    }
    
    @Override
    public PhoneBookInfo getByAreaCode(String areaCode) {
        List<PhoneBookInfo> phoneBookInfos = this.getSqlSession().selectList(this.getStatement(".selectByAreaCode"), areaCode);
        if (CollectionUtils.isNotEmpty(phoneBookInfos)) {
            return phoneBookInfos.get(0);
        }
        return null;
    }
    
    @Override
    public List<PhoneBookInfo> findAll() {
        List<PhoneBookInfo> phoneBookInfos = this.getSqlSession().selectList(this.getStatement(".selectAll"));
        if (CollectionUtils.isNotEmpty(phoneBookInfos)) {
            return phoneBookInfos;
        }
        return null;
    }
    
}