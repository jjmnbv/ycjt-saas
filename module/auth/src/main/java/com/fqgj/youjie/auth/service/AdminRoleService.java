package com.fqgj.youjie.auth.service;

import com.fqgj.common.api.Page;
import com.fqgj.youjie.auth.domain.Role;
import com.fqgj.youjie.auth.enums.RoleKeyEnum;


import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/14
 * Time: 下午1:17
 */
public interface AdminRoleService {

    /**
     * @param role
     * @return
     */
    Boolean add(Role role);

    /**
     * @param roleName
     * @param page
     * @return
     */
    List<Role> searchRoleList(String roleName, Page page);


    /**
     * @param role
     * @return
     */
    Boolean update(Role role);


    /**
     * @param adminId
     * @return
     */
    List<Long> getRoleIdsByAdminId(Long adminId);

    /**
     *
     * @param adminId
     * @param roleIds
     * @return
     */
    Boolean addRoleToAdmin(Long adminId, List<Long> roleIds);

    /**
     * 获取信审人员的角色id
     * @return
     */
    Long getRoleIdForAuditor();

    Long getRoleIdByRoleKey(RoleKeyEnum roleKeyEnum);

    List<Long> getRoleIdByRoleKeyList(List<RoleKeyEnum> roleKeyEnumList);

    /**
     * 获取超级管理员的角色id
     * @return
     */
    Long getRoleIdForSuperAdmin();

    /**
     *
     * @param roleId 角色ID
     * @return
     */
    List<Long> getAdminIdListByRoleId(Long roleId);

    /**
     *
     * @param roleIdList
     * @return
     */
    List<Long> getAdminIdListByRoleIdList(List<Long> roleIdList);

    /**
     * 校验管理员的角色
     * @param adminId 管理员
     * @param roleId 角色id
     * @return
     */
    Boolean checkAdminRole(Long adminId, Long roleId);

    Long getRoleIdForCollection();
}
