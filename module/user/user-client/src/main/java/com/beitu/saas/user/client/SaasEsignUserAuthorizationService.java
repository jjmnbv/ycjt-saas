package com.beitu.saas.user.client;

import com.beitu.saas.user.domain.SaasEsignUserAuthorizationVo;
import com.beitu.saas.user.domain.SaasUserEsignAuthorizationVo;
import com.beitu.saas.user.entity.SaasEsignUserAuthorization;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-04-17
 * Time: 11:27:43.192
 */
public interface SaasEsignUserAuthorizationService<T extends BaseEntity> extends BaseService<T> {

    SaasEsignUserAuthorization addSaasEsignUserAuthorizationVo(SaasEsignUserAuthorizationVo saasEsignUserAuthorizationVo);

    Boolean isSuccessAuthorization(String userCode);

    SaasEsignUserAuthorizationVo getByUserCode(String userCode);

}