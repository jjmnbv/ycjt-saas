package com.fqgj.youjie.auth.dao;


import com.fqgj.common.dao.BaseDAO;
import com.fqgj.youjie.auth.entity.AuthRolePermissionEntity;
import com.fqgj.youjie.auth.entity.AuthRolePermissionEntityConditions;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/11
 * Time: 上午11:06
 */
public interface AuthRolePermissionDAO extends BaseDAO<AuthRolePermissionEntity, Long, AuthRolePermissionEntityConditions> {

    /**
     *
     * @param roleId
     * @return
     */
    List<AuthRolePermissionEntity> getListByRoleId(Long roleId);

    /**
     *
     * @param roleId
     * @return
     */
    void deleteByRoleId(Long roleId);

}
