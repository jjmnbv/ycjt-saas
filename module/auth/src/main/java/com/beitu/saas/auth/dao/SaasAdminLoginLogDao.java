package com.beitu.saas.auth.dao;

import com.beitu.saas.auth.entity.SaasAdminLoginLog;
import com.fqgj.common.base.BaseMapper;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:33:55.837
*/

public interface SaasAdminLoginLogDao  extends BaseMapper<SaasAdminLoginLog> {

    SaasAdminLoginLog selectLoginLogByCodeTop(String adminCode);
}