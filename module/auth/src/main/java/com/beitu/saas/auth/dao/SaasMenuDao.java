package com.beitu.saas.auth.dao;

import com.beitu.saas.auth.entity.SaasMenu;
import com.fqgj.common.base.BaseMapper;

import java.util.List;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.647
*/

public interface SaasMenuDao  extends BaseMapper<SaasMenu> {

    List<SaasMenu> selectListByIds(List<Integer> ids);
}