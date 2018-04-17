package com.beitu.saas.user.client;

import com.beitu.saas.user.domain.SaasEsignAccountVo;
import com.beitu.saas.user.entity.SaasEsignAccount;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-04-17
 * Time: 11:27:43.166
 */
public interface SaasEsignAccountService<T extends BaseEntity> extends BaseService<T> {

    SaasEsignAccount addSaasEsignAccountVo(SaasEsignAccountVo saasEsignAccountVo);

    SaasEsignAccountVo getByCode(String code);

    SaasEsignAccountVo getBySaasEsignCode(String saasEsignCode);

}