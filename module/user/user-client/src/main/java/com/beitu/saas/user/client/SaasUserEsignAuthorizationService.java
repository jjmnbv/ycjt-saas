package com.beitu.saas.user.client;

import com.beitu.saas.user.domain.SaasUserEsignAuthorizationVo;
import com.beitu.saas.user.entity.SaasUserEsignAuthorization;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-04-02
 * Time: 14:27:05.997
 */
public interface SaasUserEsignAuthorizationService<T extends BaseEntity> extends BaseService<T> {

    Boolean isSuccessAuthorization(String userCode);

    SaasUserEsignAuthorizationVo getSuccessEsignAuthorizationByUserCode(String userCode);

    SaasUserEsignAuthorizationVo getByUserCode(String userCode);

    SaasUserEsignAuthorization create(SaasUserEsignAuthorizationVo saasUserEsignAuthorizationVo);

    Boolean updateSaasUserEsignAuthorization(Long id, String authorizationUrl);

}