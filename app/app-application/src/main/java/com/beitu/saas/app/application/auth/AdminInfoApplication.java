package com.beitu.saas.app.application.auth;

import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.auth.entity.SaasAdminRole;
import com.beitu.saas.auth.entity.SaasAdminToken;
import com.beitu.saas.auth.entity.SaasRolePermission;
import com.beitu.saas.auth.service.SaasAdminRoleService;
import com.beitu.saas.auth.service.SaasAdminService;
import com.beitu.saas.auth.service.SaasAdminTokenService;
import com.beitu.saas.auth.service.SaasRolePermissionService;
import com.fqgj.common.entity.BaseEntity;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author xiaochong
 * @create 2018/3/22 下午5:33
 * @description
 */
@Component
public class AdminInfoApplication {

    @Autowired
    private SaasAdminTokenService saasAdminTokenService;

    @Autowired
    private SaasAdminService saasAdminService;

    @Autowired
    private SaasAdminRoleService saasAdminRoleService;

    @Autowired
    private SaasRolePermissionService saasRolePermissionService;


    public SaasAdmin getSaasAdminByAccessToken(String token) {
        List<SaasAdminToken> list = saasAdminTokenService.selectByParams(new HashMap<String, Object>(2) {{
            put("token", token);
            put("deleted", false);
        }});
        if (CollectionUtils.isEmpty(list) || TimeUtils.check(new Date(), list.get(0).getExpireDate())) {
            return null;
        }
        SaasAdmin saasAdmin = saasAdminService.getSaasAdminByAdminCode(list.get(0).getAdminCode());
        return saasAdmin;
    }

    public List<Integer> getMenuIdsByAdmin(String adminCode) {
        List<SaasAdminRole> list = saasAdminRoleService.selectByParams(new HashMap<String, Object>(2) {{
            put("adminCode", adminCode);
            put("deleted", false);
        }});
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<SaasRolePermission> saasRolePermissionList = saasRolePermissionService.getMenuPermissionByRoleId(list.get(0).getRoleId().intValue());
        return saasRolePermissionList.parallelStream().map(SaasRolePermission::getRelationId).collect(toList());
    }

    public List<Integer> getButtonIdsByAdmin(String adminCode) {
        List<SaasAdminRole> list = saasAdminRoleService.selectByParams(new HashMap<String, Object>(2) {{
            put("adminCode", adminCode);
            put("deleted", false);
        }});
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<SaasRolePermission> saasRolePermissionList = saasRolePermissionService.getButtonPermissionByRoleId(list.get(0).getRoleId().intValue());
        return saasRolePermissionList.parallelStream().map(SaasRolePermission::getRelationId).collect(toList());
    }

    public void addAdminAndRole(SaasAdmin saasAdmin,Long roleId){
        SaasAdmin entity = (SaasAdmin) saasAdminService.create(saasAdmin);
        SaasAdminRole saasAdminRole = new SaasAdminRole();
        saasAdminRole.setAdminCode(entity.getCode());
        saasAdminRole.setRoleId(roleId);
        saasAdminRoleService.create(saasAdminRole);

    }
}
