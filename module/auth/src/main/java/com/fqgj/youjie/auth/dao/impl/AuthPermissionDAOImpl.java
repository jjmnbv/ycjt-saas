package com.fqgj.youjie.auth.dao.impl;


import com.fqgj.common.dao.BaseDAOImpl;
import com.fqgj.youjie.auth.dao.AuthPermissionDAO;
import com.fqgj.youjie.auth.entity.AuthPermissionEntity;
import com.fqgj.youjie.auth.entity.AuthPermissionEntityConditions;
import com.fqgj.youjie.auth.mapper.AuthPermissionEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/11
 * Time: 上午10:12
 */
@Repository
public class AuthPermissionDAOImpl extends BaseDAOImpl<AuthPermissionEntity, Long, AuthPermissionEntityConditions> implements AuthPermissionDAO {

    @Autowired
    private AuthPermissionEntityMapper authPermissionEntityMapper;

    @Autowired

    @Override
    public void setBaseMapper() {
        super.setBaseMapper(authPermissionEntityMapper);
    }


    @Override
    public List<AuthPermissionEntity> getAll() {
        AuthPermissionEntityConditions authPermissionEntityConditions = new AuthPermissionEntityConditions();
        return authPermissionEntityMapper.selectByExample(authPermissionEntityConditions);
    }

}
