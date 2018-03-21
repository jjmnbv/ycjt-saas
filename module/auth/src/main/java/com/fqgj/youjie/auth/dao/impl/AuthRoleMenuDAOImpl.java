package com.fqgj.youjie.auth.dao.impl;

import com.fqgj.common.dao.BaseDAOImpl;
import com.fqgj.common.utils.CollectionUtils;

import com.fqgj.youjie.auth.dao.AuthRoleMenuDAO;
import com.fqgj.youjie.auth.entity.AuthRoleMenuEntity;
import com.fqgj.youjie.auth.entity.AuthRoleMenuEntityConditions;
import com.fqgj.youjie.auth.mapper.AuthRoleMenuEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/11
 * Time: 上午10:33
 */
@Repository
public class AuthRoleMenuDAOImpl extends BaseDAOImpl<AuthRoleMenuEntity, Long, AuthRoleMenuEntityConditions> implements AuthRoleMenuDAO {

    @Autowired
    private AuthRoleMenuEntityMapper authRoleMenuEntityMapper;

    @Autowired

    @Override
    public void setBaseMapper() {
        super.setBaseMapper(authRoleMenuEntityMapper);
    }


    @Override
    public List<AuthRoleMenuEntity> getListByRoleIds(List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return null;
        }

        AuthRoleMenuEntityConditions conditions = new AuthRoleMenuEntityConditions();
        AuthRoleMenuEntityConditions.Criteria criteria = conditions.createCriteria();

        criteria.andRoleIdIn(roleIds);
        return authRoleMenuEntityMapper.selectByExample(conditions);
    }

    @Override
    public Boolean deleteByRoleId(Long roleId) {
        AuthRoleMenuEntityConditions conditions = new AuthRoleMenuEntityConditions();
        AuthRoleMenuEntityConditions.Criteria criteria = conditions.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        return authRoleMenuEntityMapper.deleteByExample(conditions) > 0;
    }
}
