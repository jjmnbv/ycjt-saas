package com.fqgj.youjie.auth.dao;



import com.fqgj.common.dao.BaseDAO;
import com.fqgj.youjie.auth.entity.AuthRoleMenuEntity;
import com.fqgj.youjie.auth.entity.AuthRoleMenuEntityConditions;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/11
 * Time: 上午10:30
 */
public interface AuthRoleMenuDAO extends BaseDAO<AuthRoleMenuEntity, Long, AuthRoleMenuEntityConditions> {

    /**
     * 获取角色拥有的menu_id list
     *
     * @param roleIds
     * @return
     */
    List<AuthRoleMenuEntity> getListByRoleIds(List<Long> roleIds);

    /**
     *
     * @param roleId
     * @return
     */
    Boolean deleteByRoleId(Long roleId);
}
