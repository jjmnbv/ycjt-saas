package com.fqgj.youjie.auth.dao;


import com.fqgj.common.dao.BaseDAO;
import com.fqgj.youjie.auth.entity.AuthPermissionEntity;
import com.fqgj.youjie.auth.entity.AuthPermissionEntityConditions;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/11
 * Time: 上午10:11
 */
public interface AuthPermissionDAO extends BaseDAO<AuthPermissionEntity, Long, AuthPermissionEntityConditions> {

    /**
     * 获取所有的操作权限
     *
     * @return
     */
    List<AuthPermissionEntity> getAll();


}
