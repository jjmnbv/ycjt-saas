package com.fqgj.youjie.auth.service.impl;


import com.beitu.saas.common.aop.TraceAnno;
import com.fqgj.common.utils.CollectionUtils;

import com.fqgj.youjie.auth.dao.AuthAdminRoleDAO;
import com.fqgj.youjie.auth.dao.AuthMenuDAO;
import com.fqgj.youjie.auth.dao.AuthRoleMenuDAO;
import com.fqgj.youjie.auth.domain.Menu;
import com.fqgj.youjie.auth.entity.AuthAdminRoleEntity;
import com.fqgj.youjie.auth.entity.AuthMenuEntity;
import com.fqgj.youjie.auth.entity.AuthMenuEntityConditions;
import com.fqgj.youjie.auth.entity.AuthRoleMenuEntity;
import com.fqgj.youjie.auth.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/13
 * Time: 下午4:19
 */
@Service
public class AdminMenuServiceImpl implements AdminMenuService {

    @Autowired
    private AuthMenuDAO authMenuDAO;

    @Autowired
    private AuthAdminRoleDAO authAdminRoleDAO;

    @Autowired
    private AuthRoleMenuDAO authRoleMenuDAO;

    @Override
    public List<Menu> getAllMenusByParentId(Long parentId) {
        List<AuthMenuEntity> authMenuEntityList = authMenuDAO.getListByParentId(parentId, null);
        if (CollectionUtils.isEmpty(authMenuEntityList)) {
            return null;
        }
        List<Menu> menuList = new ArrayList<>();
        for (AuthMenuEntity authMenuEntity : authMenuEntityList) {
            Menu menu = new Menu(authMenuEntity.getId(), authMenuEntity.getName(), authMenuEntity.getLink());
            menuList.add(menu);
        }
        return menuList;
    }

    @Override
    @TraceAnno
    public List<Menu> getAllMenus() {
        AuthMenuEntityConditions authMenuEntityConditions = new AuthMenuEntityConditions();
        List<AuthMenuEntity> authMenuEntityList = authMenuDAO.selectByExample(authMenuEntityConditions);

        List<Menu> menuList = new ArrayList<>();
        for (AuthMenuEntity authMenuEntity : authMenuEntityList) {
            Menu menu = new Menu(authMenuEntity.getId(), authMenuEntity.getParent(), authMenuEntity.getName(), authMenuEntity.getLink()).setParentId(authMenuEntity.getpId());
            menuList.add(menu);
        }

        return menuList;
    }

    @Override
    public List<Menu> getMenusByIds(List<Long> menuIdsList) {
        List<AuthMenuEntity> authMenuEntityList = authMenuDAO.getListByIds(menuIdsList);
        if (authMenuEntityList == null || authMenuEntityList.size() == 0) {
            return null;
        }
        List<Menu> menuList = new ArrayList<>(authMenuEntityList.size());
        for (AuthMenuEntity authMenuEntity : authMenuEntityList) {
            if (!authMenuEntity.getParent()) {
                Long pId = authMenuEntity.getpId();
                int index = menuIdsList.indexOf(pId);
                if (index < 0) {
                    // 父菜单不存在，则添加父菜单
                    AuthMenuEntity authMenuEntity1 = authMenuDAO.getMenuByPrimaryKey(pId);
                    menuIdsList.add(pId);
                    menuList.add(convertAuthMenuEntityToMenu(authMenuEntity1));
                }
            }
            menuList.add(convertAuthMenuEntityToMenu(authMenuEntity));
        }
        return menuList;
    }

    private Menu convertAuthMenuEntityToMenu(AuthMenuEntity authMenuEntity) {
        return new Menu(authMenuEntity.getId(), authMenuEntity.getParent(), authMenuEntity.getName(), authMenuEntity.getLink()).setParentId(authMenuEntity.getpId());
    }

    @Override
    public Boolean add(Menu menu) {
        AuthMenuEntity authMenuEntity = new AuthMenuEntity();
        authMenuEntity.setName(menu.getKey());
        authMenuEntity.setLink(menu.getValue());
        authMenuEntity.setDeleted(false);
        authMenuEntity.setParent(menu.getParent());
        authMenuEntity.setpId(menu.getParentId());

        return authMenuDAO.save(authMenuEntity) > 0;
    }

    @Override
    public Boolean update(Menu menu) {
        AuthMenuEntity authMenuEntity = authMenuDAO.selectById(menu.getMenuId());
        if (authMenuEntity == null) {
            return false;
        }

        authMenuEntity.setLink(menu.getValue());
        authMenuEntity.setName(menu.getKey());
        return authMenuDAO.update(authMenuEntity) > 0;
    }

    @Override
    public List<Long> getMenuIdsByAdmin(Long adminId) {
        List<AuthAdminRoleEntity> authAdminRoleEntityList = authAdminRoleDAO.getListByAdminId(adminId);
        if (CollectionUtils.isEmpty(authAdminRoleEntityList)) {
            return null;
        }
        List<Long> roleIds = authAdminRoleEntityList.parallelStream().map(AuthAdminRoleEntity::getRoleId).collect(Collectors.toList());
        List<AuthRoleMenuEntity> authRoleMenuEntityList = authRoleMenuDAO.getListByRoleIds(roleIds);
        if (CollectionUtils.isEmpty(authRoleMenuEntityList)) {
            return null;
        }
        return authRoleMenuEntityList.parallelStream().map(AuthRoleMenuEntity::getMenuId).collect(Collectors.toList());
    }

    @Override
    public List<Long> getMenuIdsByRole(Long roleId) {
        List<Long> roleIds = new ArrayList<>();
        roleIds.add(roleId);
        List<AuthRoleMenuEntity> authRoleMenuEntityList = authRoleMenuDAO.getListByRoleIds(roleIds);
        if (CollectionUtils.isEmpty(authRoleMenuEntityList)) {
            return null;
        }

        return authRoleMenuEntityList.parallelStream().map(AuthRoleMenuEntity::getMenuId).collect(Collectors.toList());
    }


    @Override
    @TraceAnno
    @Transactional
    public Boolean addMenuIdToRole(Long roleId, List<Long> menuIds) {
        if (CollectionUtils.isEmpty(menuIds)) {
            return false;
        }
        // 如果存在就先删除
        authRoleMenuDAO.deleteByRoleId(roleId);

        // TODO: 2017/3/14 这里需要优化,筛选出改变的去删除
//        if (CollectionUtils.isNotEmpty(getMenuIdsByAdmin(roleId))) {
//            authRoleMenuDAO.deleteByRoleId(roleId);
//        }
        // 去重
        HashSet<Long> menuSet = new HashSet<>(menuIds.size());
        menuSet.addAll(menuIds);
        menuSet.forEach(menuId -> {
            AuthRoleMenuEntity authRoleMenuEntity = new AuthRoleMenuEntity();
            authRoleMenuEntity.setRoleId(roleId);
            authRoleMenuEntity.setMenuId(menuId);
            authRoleMenuDAO.save(authRoleMenuEntity);
        });
        return true;
    }

    @Override
    public AuthMenuEntity getMenuByPrimaryKey(Long id) {
        return authMenuDAO.getMenuByPrimaryKey(id);
    }
}
