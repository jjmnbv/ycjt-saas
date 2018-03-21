package com.fqgj.youjie.auth.dao.impl;

import com.fqgj.common.api.Page;
import com.fqgj.common.dao.BaseDAOImpl;
import com.fqgj.common.utils.CollectionUtils;

import com.fqgj.youjie.auth.dao.AuthRoleDAO;
import com.fqgj.youjie.auth.entity.AuthRoleEntity;
import com.fqgj.youjie.auth.entity.AuthRoleEntityConditions;
import com.fqgj.youjie.auth.mapper.AuthRoleEntityMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/11
 * Time: 上午10:22
 */
@Repository
public class AuthRoleDAOImpl extends BaseDAOImpl<AuthRoleEntity, Long, AuthRoleEntityConditions> implements AuthRoleDAO {

    @Autowired
    private AuthRoleEntityMapper authRoleEntityMapper;

    @Autowired

    @Override
    public void setBaseMapper() {
        super.setBaseMapper(authRoleEntityMapper);
    }


    @Override
    public List<AuthRoleEntity> getListByNameAndPage(String name, Page page) {
        AuthRoleEntityConditions conditions = new AuthRoleEntityConditions();
        AuthRoleEntityConditions.Criteria criteria = conditions.createCriteria();
        if (StringUtils.isNotEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        conditions.setPage(page);
        page.setTotalCount(countByExample(conditions));
        return authRoleEntityMapper.selectByExample(conditions);
    }

    /**
     * 获取 用户角色的roleId
     *
     * @param roleKey
     * @return
     */
    @Override
    public Long getRoleIdByRoleKey(String roleKey) {
        AuthRoleEntityConditions conditions = new AuthRoleEntityConditions();
        AuthRoleEntityConditions.Criteria criteria = conditions.createCriteria();
        criteria.andRoleKeyEqualTo(roleKey);
        criteria.andDeletedEqualTo(Boolean.FALSE);
        List<AuthRoleEntity>  roleEntities = authRoleEntityMapper.selectByExample(conditions);
        if (CollectionUtils.isEmpty(roleEntities)){
            return null;
        }
        return roleEntities.get(0).getId();
    }


    /**
     * @param types
     * @return
     */
    @Override
    public List<Long> getRoleIdByRoleKeyList(List<String> types) {
        AuthRoleEntityConditions conditions = new AuthRoleEntityConditions();
        AuthRoleEntityConditions.Criteria criteria = conditions.createCriteria();
        criteria.andRoleKeyIn(types);
        criteria.andDeletedEqualTo(Boolean.FALSE);
        List<AuthRoleEntity>  roleEntities = authRoleEntityMapper.selectByExample(conditions);
        if (CollectionUtils.isEmpty(roleEntities)){
            return null;
        }
        List<Long> roleIdList = new ArrayList<>();
        for (AuthRoleEntity roleEntity : roleEntities) {
            roleIdList.add(roleEntity.getId());
        }
        return roleIdList;
    }
}
