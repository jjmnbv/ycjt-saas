package com.beitu.saas.user.service.impl;

import com.beitu.saas.user.client.SaasUserEsignAuthorizationService;
import com.beitu.saas.user.dao.SaasUserEsignAuthorizationDao;
import com.beitu.saas.user.entity.SaasUserEsignAuthorization;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * User: jungle
 * Date: 2018-04-02
 * Time: 14:27:06.001
 */
@Module(value = "SAAS用户e签宝授权信息表服务模块")
@NameSpace("com.beitu.saas.user.dao.impl.SaasUserEsignAuthorizationDaoImpl")
@Service
public class SaasUserEsignAuthorizationServiceImpl extends AbstractBaseService implements SaasUserEsignAuthorizationService {

    @Autowired
    private SaasUserEsignAuthorizationDao saasUserEsignAuthorizationDao;

    @Override
    public Boolean isSuccessAuthorization(String userCode) {
        return saasUserEsignAuthorizationDao.countSuccessEsignAuthorizationByUserCode(userCode) > 0;
    }

    @Override
    public String getSealUrlByUserCode(String userCode) {
        List<SaasUserEsignAuthorization> authorizationList = saasUserEsignAuthorizationDao.selectByParams(new HashMap<String, Object>(4) {{
            put("userCode", userCode);
            put("success", Boolean.TRUE);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(authorizationList)) {
            return null;
        }
        return authorizationList.get(0).getSealUrl();
    }

    @Override
    public void create() {

    }
}