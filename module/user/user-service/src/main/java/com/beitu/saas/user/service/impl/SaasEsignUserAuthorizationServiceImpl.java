package com.beitu.saas.user.service.impl;

import com.beitu.saas.user.client.SaasEsignUserAuthorizationService;
import com.beitu.saas.user.dao.SaasEsignUserAuthorizationDao;
import com.beitu.saas.user.domain.SaasEsignUserAuthorizationVo;
import com.beitu.saas.user.entity.SaasEsignUserAuthorization;
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
 * Date: 2018-04-17
 * Time: 11:27:43.192
 */
@Module(value = "SAAS用户e签宝授权信息表服务模块")
@NameSpace("com.beitu.saas.user.dao.impl.SaasEsignUserAuthorizationDaoImpl")
@Service
public class SaasEsignUserAuthorizationServiceImpl extends AbstractBaseService implements SaasEsignUserAuthorizationService {

    @Autowired
    private SaasEsignUserAuthorizationDao saasEsignUserAuthorizationDao;

    @Override
    public SaasEsignUserAuthorization addSaasEsignUserAuthorizationVo(SaasEsignUserAuthorizationVo saasEsignUserAuthorizationVo) {
        SaasEsignUserAuthorization saasEsignUserAuthorization = SaasEsignUserAuthorizationVo.convertVOToEntity(saasEsignUserAuthorizationVo);
        return saasEsignUserAuthorizationDao.insert(saasEsignUserAuthorization);
    }

    @Override
    public Boolean isSuccessAuthorization(String userCode) {
        return saasEsignUserAuthorizationDao.queryTotal(new HashMap<String, Object>(4) {{
            put("userCode", userCode);
            put("deleted", Boolean.FALSE);
        }}) > 0;
    }

    @Override
    public SaasEsignUserAuthorizationVo getByUserCode(String userCode) {
        List<SaasEsignUserAuthorization> saasEsignUserAuthorizationList = saasEsignUserAuthorizationDao.selectByParams(new HashMap<String, Object>(4) {{
            put("userCode", userCode);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasEsignUserAuthorizationList)) {
            return null;
        }
        return SaasEsignUserAuthorizationVo.convertEntityToVO(saasEsignUserAuthorizationList.get(0));
    }
}


