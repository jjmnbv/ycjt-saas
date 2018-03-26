package com.beitu.saas.auth.service.impl;

import com.beitu.saas.auth.dao.SaasRoleDao;
import com.beitu.saas.auth.entity.SaasRole;
import com.beitu.saas.auth.service.SaasRoleService;
import com.fqgj.common.api.Page;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.common.base.PageUtils;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.691
*/
@Module(value = "服务模块")
@NameSpace("com.beitu.saas.auth.dao.impl.SaasRoleDaoImpl")
@Service
public class SaasRoleServiceImpl extends AbstractBaseService implements SaasRoleService {


    @Autowired
    private SaasRoleDao saasRoleDao;

    @Override
    public List<SaasRole> getRoleListByMerchantCode(String merchantCode, Page page){
        Map<String, Object> map = new HashMap<String, Object>(2) {{
            put("merchantCode", merchantCode);
            put("deleted", false);
            put("page", page);
        }};
        page.setTotalCount(this.queryTotal(map));
        return this.selectByParams(map);
    }
}


