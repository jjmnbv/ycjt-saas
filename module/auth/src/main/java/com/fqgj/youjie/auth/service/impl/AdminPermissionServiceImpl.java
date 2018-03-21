package com.fqgj.youjie.auth.service.impl;

import com.fqgj.common.utils.CollectionUtils;

import com.fqgj.youjie.auth.dao.AuthPermissionDAO;
import com.fqgj.youjie.auth.dao.AuthRolePermissionDAO;
import com.fqgj.youjie.auth.domain.Menu;
import com.fqgj.youjie.auth.domain.Permission;
import com.fqgj.youjie.auth.entity.AuthPermissionEntity;
import com.fqgj.youjie.auth.entity.AuthRolePermissionEntity;
import com.fqgj.youjie.auth.service.AdminMenuService;
import com.fqgj.youjie.auth.service.AdminPermissionService;
import com.fqgj.youjie.auth.vo.MenuWithOperationsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/14
 * Time: 下午4:04
 */
@Service
public class AdminPermissionServiceImpl implements AdminPermissionService {

    @Autowired
    private AdminMenuService adminMenuService;

    @Autowired
    private AuthPermissionDAO authPermissionDAO;

    @Autowired
    private AuthRolePermissionDAO authRolePermissionDAO;

    @Override
    public MenuWithOperationsVO getAllPermissionAndMenuIdMap() {
        List<Menu> allMenus = adminMenuService.getAllMenus();
        List<Permission> allPermissions = getAllPermissions();

        Map<Long, List<Permission>> menuIdAndPermissionMap = new HashMap<>();


        if (CollectionUtils.isNotEmpty(allPermissions)) {
            for (Permission permission : allPermissions) {
                List<Permission> permissionList = new ArrayList<>();
                if (menuIdAndPermissionMap.containsKey(permission.getMenuId())) {
                    permissionList = menuIdAndPermissionMap.get(permission.getMenuId());
                }

                permissionList.add(permission);
                menuIdAndPermissionMap.put(permission.getMenuId(), permissionList);
            }

            return new MenuWithOperationsVO(allMenus, menuIdAndPermissionMap);
        }

        return null;
    }

    @Override
    public Boolean addPermission(Permission permission) {
        AuthPermissionEntity authPermissionEntity = new AuthPermissionEntity();
        authPermissionEntity.setMenuId(permission.getMenuId());
        authPermissionEntity.setPermission(permission.getPermission());
        authPermissionEntity.setName(permission.getName());
        authPermissionEntity.setDescription(permission.getDescription());
        authPermissionEntity.setDeleted(false);

        return authPermissionDAO.save(authPermissionEntity) > 0;
    }

    @Override
    public List<Permission> getAllPermissions() {
        List<AuthPermissionEntity> authPermissionEntityList = authPermissionDAO.getAll();
        if (CollectionUtils.isEmpty(authPermissionEntityList)) {
            return null;
        }

        List<Permission> permissions = new ArrayList<>();
        for (AuthPermissionEntity authPermissionEntity : authPermissionEntityList) {
            permissions.add(new Permission().setId(authPermissionEntity.getId())
                    .setName(authPermissionEntity.getName())
                    .setMenuId(authPermissionEntity.getMenuId())
                    .setPermission(authPermissionEntity.getPermission())
                    .setDescription(authPermissionEntity.getDescription()));
        }

        return permissions;
    }

    @Override
    public List<Long> getPermissionIdsByRoleId(long roleId) {
        List<Long> ids = new ArrayList<>();
        List<AuthRolePermissionEntity> authRolePermissionEntityList = authRolePermissionDAO.getListByRoleId(roleId);
        if (CollectionUtils.isNotEmpty(authRolePermissionEntityList)) {
            ids = authRolePermissionEntityList.parallelStream().map(AuthRolePermissionEntity::getRoleId).collect(Collectors.toList());
        }

        return ids;
    }


    @Override
    public Boolean addPermissionToRole(Long roleId, List<Long> permissionIds) {
        authRolePermissionDAO.deleteByRoleId(roleId);
        for (Long permissionId : permissionIds) {
            AuthRolePermissionEntity authRolePermissionEntity = new AuthRolePermissionEntity();
            authRolePermissionEntity.setRoleId(roleId);
            authRolePermissionEntity.setPermissionId(permissionId);
            authRolePermissionEntity.setDeleted(false);
            authRolePermissionDAO.save(authRolePermissionEntity);
        }

        return true;
    }
}
