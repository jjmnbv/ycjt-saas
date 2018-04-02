package com.beitu.saas.user.dao;

import com.fqgj.common.base.BaseMapper;
import com.beitu.saas.user.entity.SaasUserEsignAuthorization;

/**
 * User: jungle
 * Date: 2018-04-02
 * Time: 14:27:05.904
 */

public interface SaasUserEsignAuthorizationDao extends BaseMapper<SaasUserEsignAuthorization> {

    int countSuccessEsignAuthorizationByUserCode(String userCode);

}