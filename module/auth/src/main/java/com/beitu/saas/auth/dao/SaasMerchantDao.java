package com.beitu.saas.auth.dao;

import com.beitu.saas.auth.entity.SaasMerchant;
import com.fqgj.common.base.BaseMapper;

import java.util.List;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.654
*/

public interface SaasMerchantDao  extends BaseMapper<SaasMerchant> {

    List<String> selectAllMerchantCode();
}