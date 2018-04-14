package com.beitu.saas.auth.service;
import com.beitu.saas.auth.entity.SaasAdminLoginLog;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import javax.servlet.http.HttpServletRequest;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:33:55.838
*/
public interface SaasAdminLoginLogService<T extends BaseEntity> extends BaseService<T> {

    Boolean addAdminLoginLog(HttpServletRequest request, String adminCode);

    Boolean equalLoginIp(String adminCode, String ip);

    SaasAdminLoginLog getLoginLogByCodeTop(String adminCode);
}