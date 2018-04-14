package com.beitu.saas.auth.dao;

import com.beitu.saas.auth.entity.SaasOperationButton;
import com.fqgj.common.base.BaseMapper;

import java.util.List;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.673
*/

public interface SaasOperationButtonDao  extends BaseMapper<SaasOperationButton> {

    List<SaasOperationButton> selectListByIds(List<Integer> ids);
}