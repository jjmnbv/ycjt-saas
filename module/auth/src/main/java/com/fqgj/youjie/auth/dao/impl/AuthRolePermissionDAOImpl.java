package com.fqgj.youjie.auth.dao.impl;

import com.fqgj.common.dao.BaseDAOImpl;

import com.fqgj.youjie.auth.dao.AuthRolePermissionDAO;
import com.fqgj.youjie.auth.entity.AuthRolePermissionEntity;
import com.fqgj.youjie.auth.entity.AuthRolePermissionEntityConditions;
import com.fqgj.youjie.auth.mapper.AuthRolePermissionEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/11
 * Time: 上午11:07
 */
@Repository
public class AuthRolePermissionDAOImpl extends BaseDAOImpl<AuthRolePermissionEntity, Long, AuthRolePermissionEntityConditions> implements AuthRolePermissionDAO {

    @Autowired
    private AuthRolePermissionEntityMapper authRolePermissionEntityMapper;

    @Autowired
    @Override
    public void setBaseMapper() {
        super.setBaseMapper(authRolePermissionEntityMapper);
    }

    @Override
    public List<AuthRolePermissionEntity> getListByRoleId(Long roleId) {
        AuthRolePermissionEntityConditions authRolePermissionEntityConditions = new AuthRolePermissionEntityConditions();
        AuthRolePermissionEntityConditions.Criteria criteria = authRolePermissionEntityConditions.createCriteria();

        criteria.andRoleIdEqualTo(roleId);

        return authRolePermissionEntityMapper.selectByExample(authRolePermissionEntityConditions);
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        AuthRolePermissionEntityConditions authRolePermissionEntityConditions = new AuthRolePermissionEntityConditions();
        AuthRolePermissionEntityConditions.Criteria criteria = authRolePermissionEntityConditions.createCriteria();

        deleteByExample(authRolePermissionEntityConditions);
    }
}
