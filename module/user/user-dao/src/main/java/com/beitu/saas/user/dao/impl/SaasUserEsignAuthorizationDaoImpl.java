package com.beitu.saas.user.dao.impl;

import com.fqgj.common.base.AbstractBaseMapper;
import com.beitu.saas.user.dao.SaasUserEsignAuthorizationDao;
import com.beitu.saas.user.entity.SaasUserEsignAuthorization;
import org.springframework.stereotype.Repository;

/**
 * User: jungle
 * Date: 2018-04-02
 * Time: 14:27:05.908
 */

@Repository
public class SaasUserEsignAuthorizationDaoImpl extends AbstractBaseMapper<SaasUserEsignAuthorization> implements SaasUserEsignAuthorizationDao {

    @Override
    public int countSuccessEsignAuthorizationByUserCode(String userCode) {
        return this.getSqlSession().selectOne(this.getStatement(".countSuccessEsignAuthorizationByUserCode"), userCode);
    }

}