package com.beitu.saas.app.application.auth;

import com.beitu.saas.auth.entity.SaasRole;
import com.beitu.saas.auth.service.SaasRolePermissionService;
import com.beitu.saas.auth.service.SaasRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xiaochong
 * @create 2018/3/23 下午8:47
 * @description
 */
@Component
public class RoleApplication {

    @Autowired
    private SaasRoleService saasRoleService;

    @Autowired
    private SaasRolePermissionService saasRolePermissionService;

    public void addRoleAndEmpower(String roleName, String currentName,String merchantCode,List menuIds,List buttonIds){
        SaasRole saasRole = new SaasRole();
        saasRole.setName(roleName);
        saasRole.setCreateName(currentName);
        saasRole.setEnabled(true);
        saasRole.setMerchantCode(merchantCode);
        SaasRole entity = (SaasRole) saasRoleService.create(saasRole);
        saasRolePermissionService.addPermissionToRole(entity.getId().intValue(),menuIds,buttonIds);
    }
}
