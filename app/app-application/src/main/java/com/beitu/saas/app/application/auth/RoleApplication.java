package com.beitu.saas.app.application.auth;

import com.beitu.saas.auth.entity.SaasAdminRole;
import com.beitu.saas.auth.entity.SaasOperationButton;
import com.beitu.saas.auth.entity.SaasRole;
import com.beitu.saas.auth.enums.PermissionTypeEnum;
import com.beitu.saas.auth.service.SaasAdminRoleService;
import com.beitu.saas.auth.service.SaasOperationButtonService;
import com.beitu.saas.auth.service.SaasRolePermissionService;
import com.beitu.saas.auth.service.SaasRoleService;
import com.fqgj.common.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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

    @Autowired
    private SaasOperationButtonService saasOperationButtonService;

    @Autowired
    private SaasAdminRoleService saasAdminRoleService;


    @Transactional(rollbackFor = Exception.class)
    public void addRoleAndEmpower(String roleName, String currentName, String merchantCode, List menuIds, List buttonIds) {
        SaasRole saasRole = new SaasRole();
        saasRole.setName(roleName);
        saasRole.setCreateName(currentName);
        saasRole.setEnabled(true);
        saasRole.setMerchantCode(merchantCode);
        SaasRole entity = (SaasRole) saasRoleService.create(saasRole);
        saasRolePermissionService.addPermissionToRole(entity.getId().intValue(), menuIds, buttonIds);
    }

    public Boolean hasButtonPermission(String buttonKey, String adminCode) {
        List<SaasOperationButton> buttonList = saasOperationButtonService.selectByParams(new HashMap<String, Object>(2) {{
            put("key", buttonKey);
            put("deleted", false);
        }});
        if (CollectionUtils.isEmpty(buttonList)) {
            return false;
        }
        List<SaasAdminRole> saasAdminRoleList = saasAdminRoleService.selectByParams(new HashMap<String, Object>(2) {{
            put("adminCode", adminCode);
            put("deleted", false);
        }});
        if (CollectionUtils.isEmpty(saasAdminRoleList)) {
            return false;
        }
        List list = saasRolePermissionService.selectByParams(new HashMap<String, Object>(4) {{
            put("roleId", saasAdminRoleList.get(0).getRoleId());
            put("relationId", buttonList.get(0).getId());
            put("permissionType", PermissionTypeEnum.BUTTON_PERMISSION.getKey());
            put("deleted", false);
        }});
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        return true;

    }
}
