package com.beitu.saas.auth.service.impl;

import com.beitu.saas.auth.dao.SaasAdminRoleDao;
import com.beitu.saas.auth.entity.SaasAdminRole;
import com.beitu.saas.auth.service.SaasAdminRoleService;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:33:55.847
*/
@Module(value = "服务模块")
@NameSpace("com.beitu.saas.auth.dao.impl.SaasAdminRoleDaoImpl")
@Service
public class SaasAdminRoleServiceImpl extends AbstractBaseService implements SaasAdminRoleService {


    @Autowired
    private SaasAdminRoleDao saasAdminRoleDao;


    @Override
    public Integer updateByAdminCode(SaasAdminRole record) {
        return saasAdminRoleDao.updateByAdminCode(record);
    }

    @Override
    public Long getRoleIdByAdminCode(String adminCode){
        List<SaasAdminRole> list = this.selectByParams(new HashMap<String, Object>() {{
            put("adminCode", adminCode);
            put("deleted", false);
        }});
        if (CollectionUtils.isNotEmpty(list)){
            return list.get(0).getRoleId();
        }
        return null;
    }
}


