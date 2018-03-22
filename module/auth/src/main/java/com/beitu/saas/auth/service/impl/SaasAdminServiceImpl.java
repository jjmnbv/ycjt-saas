package com.beitu.saas.auth.service.impl;


import com.beitu.saas.auth.dao.SaasAdminDao;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.auth.service.SaasAdminService;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.MD5;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * User: xiaochong
 * Date: 2018-03-22
 * Time: 15:33:55.774
 */
@Module(value = "saas用户表服务模块")
@NameSpace("com.beitu.saas.dao.impl.SaasAdminDaoImpl")
@Service
public class SaasAdminServiceImpl extends AbstractBaseService implements SaasAdminService {


    @Autowired
    private SaasAdminDao saasAdminDao;

    @Override
    public SaasAdmin login(String mobile, String paasword) {
        SaasAdmin saasAdmin = getSaasAdminByMoblie(mobile);
        if (null == saasAdmin) {
            throw new ApplicationException("账号不存在");
        }
        if (!saasAdmin.getEnable()) {
            throw new ApplicationException("账号被禁用");
        }
        if (!saasAdmin.getPassword().equals(MD5.md5(paasword))) {
            throw new ApplicationException("密码错误");
        }
        return saasAdmin;
    }

    @Override
    public SaasAdmin getSaasAdminByMoblie(String mobile) {
        List<SaasAdmin> saasAdminList = saasAdminDao.selectByParams(new HashMap<String, Object>(2) {{
            put("mobile", mobile);
            put("deleted", false);
        }});
        if (CollectionUtils.isNotEmpty(saasAdminList)) {
            return saasAdminList.get(0);
        }
        return null;
    }

    @Override
    public SaasAdmin getSaasAdminByAdminCode(String adminCode) {
        List<SaasAdmin> saasAdminList = saasAdminDao.selectByParams(new HashMap<String, Object>() {{
            put("code", adminCode);
            put("deleted", false);
        }});
        if (CollectionUtils.isNotEmpty(saasAdminList)) {
            if (!saasAdminList.get(0).getEnable()) {
                throw new ApplicationException("账号被禁用");
            }
            return saasAdminList.get(0);
        }
        return null;
    }


}


