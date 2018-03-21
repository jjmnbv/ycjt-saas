package com.fqgj.youjie.auth.dao;



import com.fqgj.common.dao.BaseDAO;
import com.fqgj.youjie.auth.entity.AuthAdminRoleEntity;
import com.fqgj.youjie.auth.entity.AuthAdminRoleEntityConditions;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/11
 * Time: 上午9:59
 */
public interface AuthAdminRoleDAO extends BaseDAO<AuthAdminRoleEntity, Long, AuthAdminRoleEntityConditions> {
    /**
     * @param adminId
     * @return
     */
    List<AuthAdminRoleEntity> getListByAdminId(Long adminId);

    /**
     *
     * @param adminId
     * @return
     */
    Boolean deleteByAdminId(Long adminId);


    /**
     *
     * @param roleId 角色ID
     * @return
     */
    List<Long> getAdminIdListByRoleId(Long roleId);

    /**
     * 校验管理员的角色
     *
     * @param adminId 管理员
     * @param roleId  角色id
     * @return
     */
    Boolean checkAdminRole(Long adminId, Long roleId);

    /**
     *
     * @param roleIdList
     * @return
     */
    List<Long> getAdminIdListByRoleIdList(List<Long> roleIdList);
}
