package com.beitu.saas.credit.dao;

import com.fqgj.common.base.BaseMapper;
import com.beitu.saas.credit.entity.PhoneBookInfo;

import java.util.List;

/**
 * User: linchengyu
 * Date: 2017-06-29
 * Time: 21:21:33.509
 */

public interface PhoneBookInfoDao extends BaseMapper<PhoneBookInfo> {

    PhoneBookInfo getByPhoneHeader(String phoneHeader);

    PhoneBookInfo getByAreaCode(String areaCode);

    List<PhoneBookInfo> findAll();

}