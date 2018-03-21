package com.fqgj.youjie.auth.dao;

import com.fqgj.common.api.Page;
import com.fqgj.common.dao.BaseDAO;
import com.fqgj.youjie.auth.entity.AuthRoleEntity;
import com.fqgj.youjie.auth.entity.AuthRoleEntityConditions;


import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/11
 * Time: 上午10:20
 */
public interface AuthRoleDAO extends BaseDAO<AuthRoleEntity, Long, AuthRoleEntityConditions> {

    /**
     * 根据名字搜索角色列表
     *
     * @param name
     * @param page
     * @return
     */
    List<AuthRoleEntity> getListByNameAndPage(String name, Page page);


    /**
     *获取 用户角色的roleId
     * @param roleKey
     * @return
     */
    Long getRoleIdByRoleKey(String roleKey);

    /**
     *
     * @param types
     * @return
     */
    List<Long> getRoleIdByRoleKeyList(List<String> types);
}
