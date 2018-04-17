package com.beitu.saas.user.service.impl;

import com.beitu.saas.user.client.SaasEsignAccountService;
import com.beitu.saas.user.dao.SaasEsignAccountDao;
import com.beitu.saas.user.domain.SaasEsignAccountVo;
import com.beitu.saas.user.entity.SaasEsignAccount;
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
 * Time: 11:27:43.171
 */
@Module(value = "SAASe签宝账户信息表服务模块")
@NameSpace("com.beitu.saas.user.dao.impl.SaasEsignAccountDaoImpl")
@Service
public class SaasEsignAccountServiceImpl extends AbstractBaseService implements SaasEsignAccountService {

    @Autowired
    private SaasEsignAccountDao saasEsignAccountDao;

    @Override
    public SaasEsignAccount addSaasEsignAccountVo(SaasEsignAccountVo saasEsignAccountVo) {
        SaasEsignAccount saasEsignAccount = SaasEsignAccountVo.convertVOToEntity(saasEsignAccountVo);
        saasEsignAccountDao.insert(saasEsignAccount);
        return saasEsignAccount;
    }

    @Override
    public SaasEsignAccountVo getByCode(String code) {
        List<SaasEsignAccount> saasEsignAccountList = saasEsignAccountDao.selectByParams(new HashMap<String, Object>(4) {{
            put("code", code);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasEsignAccountList)) {
            return null;
        }
        return SaasEsignAccountVo.convertEntityToVO(saasEsignAccountList.get(0));
    }

    @Override
    public SaasEsignAccountVo getBySaasEsignCode(String saasEsignCode) {
        List<SaasEsignAccount> saasEsignAccountList = saasEsignAccountDao.selectByParams(new HashMap<String, Object>(4) {{
            put("saasEsignCode", saasEsignCode);
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasEsignAccountList)) {
            return null;
        }
        return SaasEsignAccountVo.convertEntityToVO(saasEsignAccountList.get(0));
    }
}


