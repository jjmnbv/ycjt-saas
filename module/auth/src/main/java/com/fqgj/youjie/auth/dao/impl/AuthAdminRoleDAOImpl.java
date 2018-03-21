package com.fqgj.youjie.auth.dao.impl;


import com.fqgj.common.dao.BaseDAOImpl;
import com.fqgj.youjie.auth.dao.AuthAdminRoleDAO;
import com.fqgj.youjie.auth.entity.AuthAdminRoleEntity;
import com.fqgj.youjie.auth.entity.AuthAdminRoleEntityConditions;
import com.fqgj.youjie.auth.mapper.AuthAdminRoleEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.fqgj.common.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/11
 * Time: 上午10:03
 */
@Repository
public class AuthAdminRoleDAOImpl extends BaseDAOImpl<AuthAdminRoleEntity, Long, AuthAdminRoleEntityConditions> implements AuthAdminRoleDAO {
    @Autowired
    private AuthAdminRoleEntityMapper authAdminRoleEntityMapper;


    @Override
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(authAdminRoleEntityMapper);
    }

    @Override
    public List<AuthAdminRoleEntity> getListByAdminId(Long adminId) {
        AuthAdminRoleEntityConditions conditions = new AuthAdminRoleEntityConditions();
        AuthAdminRoleEntityConditions.Criteria criteria = conditions.createCriteria();

        criteria.andAdminIdEqualTo(adminId);

        return authAdminRoleEntityMapper.selectByExample(conditions);
    }

    @Override
    public Boolean deleteByAdminId(Long adminId) {
        AuthAdminRoleEntityConditions conditions = new AuthAdminRoleEntityConditions();
        AuthAdminRoleEntityConditions.Criteria criteria = conditions.createCriteria();

        criteria.andAdminIdEqualTo(adminId);

        return authAdminRoleEntityMapper.deleteByExample(conditions) > 0;
    }

    /**
     * @param roleId 角色ID
     * @return
     */
    @Override
    public List<Long> getAdminIdListByRoleId(Long roleId) {
        AuthAdminRoleEntityConditions conditions = new AuthAdminRoleEntityConditions();
        AuthAdminRoleEntityConditions.Criteria criteria = conditions.createCriteria();

        criteria.andRoleIdEqualTo(roleId);

        List<AuthAdminRoleEntity> adminRoleEntities = authAdminRoleEntityMapper.selectByExample(conditions);
        if (CollectionUtils.isEmpty(adminRoleEntities)){
            return null;
        }

        return this.convertAdminIdList(adminRoleEntities);

    }

    /**
     * @param roleIdList
     * @return
     */
    @Override
    public List<Long> getAdminIdListByRoleIdList(List<Long> roleIdList) {
        AuthAdminRoleEntityConditions conditions = new AuthAdminRoleEntityConditions();
        AuthAdminRoleEntityConditions.Criteria criteria = conditions.createCriteria();

        criteria.andRoleIdIn(roleIdList);
        criteria.andDeletedEqualTo(false);

        List<AuthAdminRoleEntity> adminRoleEntities = authAdminRoleEntityMapper.selectByExample(conditions);
        if (CollectionUtils.isEmpty(adminRoleEntities)){
            return null;
        }

        return this.convertAdminIdList(adminRoleEntities);
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
        AuthAdminRoleEntityConditions conditions = new AuthAdminRoleEntityConditions();
        AuthAdminRoleEntityConditions.Criteria criteria = conditions.createCriteria();

        criteria.andRoleIdEqualTo(roleId);
        criteria.andAdminIdEqualTo(adminId);
        List<AuthAdminRoleEntity> adminRoleEntities = authAdminRoleEntityMapper.selectByExample(conditions);
        if (CollectionUtils.isNotEmpty(adminRoleEntities) && adminRoleEntities.size() > 0){
            return Boolean.TRUE;
        }

        return Boolean.FALSE;

    }

    /**
     *
     * @param adminRoleEntities 用户角色
     * @return
     */
    private List<Long> convertAdminIdList(List<AuthAdminRoleEntity> adminRoleEntities) {
        List<Long> adminIdList = new ArrayList<>();
        for (AuthAdminRoleEntity authAdminRoleEntity : adminRoleEntities){
            adminIdList.add(authAdminRoleEntity.getAdminId());
        }
        return adminIdList;
    }


}
