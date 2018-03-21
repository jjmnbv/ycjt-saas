package com.fqgj.youjie.auth.service.impl;

import com.fqgj.common.api.Page;
import com.fqgj.common.utils.CollectionUtils;

import com.fqgj.youjie.auth.dao.AuthAdminRoleDAO;
import com.fqgj.youjie.auth.dao.AuthRoleDAO;
import com.fqgj.youjie.auth.domain.Role;
import com.fqgj.youjie.auth.entity.AuthAdminRoleEntity;
import com.fqgj.youjie.auth.entity.AuthRoleEntity;
import com.fqgj.youjie.auth.enums.RoleKeyEnum;
import com.fqgj.youjie.auth.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/14
 * Time: 下午1:17
 */
@Service
public class AdminRoleServiceImpl implements AdminRoleService {

    @Autowired
    private AuthAdminRoleDAO authAdminRoleDAO;

    @Autowired
    private AuthRoleDAO authRoleDAO;

    @Override
    public Boolean add(Role role) {
        AuthRoleEntity authRoleEntity = new AuthRoleEntity();
        authRoleEntity.setName(role.getName());
        authRoleEntity.setDeleted(false);
        authRoleEntity.setDescription(role.getDescription());

        return authRoleDAO.save(authRoleEntity) > 0;
    }

    @Override
    public List<Role> searchRoleList(String roleName, Page page) {
        List<AuthRoleEntity> entities = authRoleDAO.getListByNameAndPage(roleName, page);
        if (CollectionUtils.isEmpty(entities)) {
            return null;
        }

        List<Role> roleList = new ArrayList<>();
        for (AuthRoleEntity authRoleEntity : entities) {
            roleList.add(new Role(authRoleEntity.getId(), authRoleEntity.getName(), authRoleEntity.getDescription()));
        }

        return roleList;
    }

    @Override
    public Boolean update(Role role) {
        AuthRoleEntity authRoleEntity = authRoleDAO.selectById(role.getRoleId());
        if (authRoleEntity == null) {
            return false;
        }

        authRoleEntity.setName(role.getName());
        authRoleEntity.setDescription(role.getDescription());
        return authRoleDAO.update(authRoleEntity) > 0;
    }

    @Override
    public List<Long> getRoleIdsByAdminId(Long adminId) {
        List<AuthAdminRoleEntity> authAdminRoleEntityList = authAdminRoleDAO.getListByAdminId(adminId);
        if (authAdminRoleEntityList == null) {
            return null;
        }

        return authAdminRoleEntityList.parallelStream().map(AuthAdminRoleEntity::getRoleId).collect(Collectors.toList());
    }

    @Override
    public Boolean addRoleToAdmin(Long adminId, List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return false;
        }

        if (CollectionUtils.isNotEmpty(getRoleIdsByAdminId(adminId))) {
            authAdminRoleDAO.deleteByAdminId(adminId);
        }

        for (Long roleId : roleIds) {
            AuthAdminRoleEntity authAdminRoleEntity = new AuthAdminRoleEntity();
            authAdminRoleEntity.setAdminId(adminId);
            authAdminRoleEntity.setRoleId(roleId);

            authAdminRoleDAO.save(authAdminRoleEntity);
        }

        return true;
    }

    /**
     * 获取信审人员的角色id
     *
     * @return
     */
    @Override
    public Long getRoleIdForAuditor() {
        return authRoleDAO.getRoleIdByRoleKey(RoleKeyEnum.AUDITOR.getType());
    }

    @Override
    public Long getRoleIdByRoleKey(RoleKeyEnum roleKeyEnum) {
        return authRoleDAO.getRoleIdByRoleKey(roleKeyEnum.getType());
    }

    @Override
    public List<Long> getRoleIdByRoleKeyList(List<RoleKeyEnum> roleKeyEnumList) {
        return authRoleDAO.getRoleIdByRoleKeyList(this.getTypes(roleKeyEnumList));
    }

    private List<String> getTypes(List<RoleKeyEnum> roleKeyEnumList) {
        List<String> types = new ArrayList<>();
        for (RoleKeyEnum roleKeyEnum : roleKeyEnumList) {
            types.add(roleKeyEnum.getType());
        }
        return types;
    }

    /**
     * 获取超级管理员的角色id
     *
     * @return
     */
    @Override
    public Long getRoleIdForSuperAdmin() {
        return authRoleDAO.getRoleIdByRoleKey(RoleKeyEnum.SUPERADMIN.getType());
    }

    /**
     * @param roleId 角色ID
     * @return
     */
    @Override
    public List<Long> getAdminIdListByRoleId(Long roleId) {
           return authAdminRoleDAO.getAdminIdListByRoleId(roleId);
    }

    /**
     * @param roleIdList
     * @return
     */
    @Override
    public List<Long> getAdminIdListByRoleIdList(List<Long> roleIdList) {
        return authAdminRoleDAO.getAdminIdListByRoleIdList(roleIdList);
    }

    /**
     * 校验管理员的角色
     *
     * @param adminId 管理员
     * @param roleId  角色id
     * @return
     */
    @Override
    public Boolean checkAdminRole(Long adminId, Long roleId) {
        return authAdminRoleDAO.checkAdminRole(adminId,roleId);
    }

    @Override
    public Long getRoleIdForCollection() {
        return null;
    }
}
