package com.beitu.saas.auth.service.impl;

import com.beitu.saas.auth.dao.SaasRolePermissionDao;
import com.beitu.saas.auth.entity.SaasRolePermission;
import com.beitu.saas.auth.enums.PermissionTypeEnum;
import com.beitu.saas.auth.service.SaasRolePermissionService;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.697
*/
@Module(value = "服务模块")
@NameSpace("com.beitu.saas.dao.impl.SaasRolePermissionDaoImpl")
@Service
public class SaasRolePermissionServiceImpl extends AbstractBaseService implements SaasRolePermissionService {


    @Autowired
    private SaasRolePermissionDao saasRolePermissionDao;

    @Override
    public List<SaasRolePermission> getMenuPermissionByRoleId(Integer roleId) {
        return this.selectByParams(new HashMap<String,Object>(3){{
            put("roleId",roleId);
            put("permissionType", PermissionTypeEnum.MENU_PERMISSION.getKey());
            put("deleted",false);
        }});
    }

    @Override
    public List<SaasRolePermission> getButtonPermissionByRoleId(Integer roleId) {
        return this.selectByParams(new HashMap<String,Object>(3){{
            put("roleId",roleId);
            put("permissionType", PermissionTypeEnum.BUTTON_PERMISSION.getKey());
            put("deleted",false);
        }});
    }
}


