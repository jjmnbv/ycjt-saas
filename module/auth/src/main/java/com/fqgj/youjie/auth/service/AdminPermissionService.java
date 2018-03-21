package com.fqgj.youjie.auth.service;



import com.fqgj.youjie.auth.domain.Permission;
import com.fqgj.youjie.auth.vo.MenuWithOperationsVO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/14
 * Time: 下午4:04
 */
public interface AdminPermissionService {

    /**
     * 获取所有的操作与menuid的一个映射
     *
     * @return
     */
    MenuWithOperationsVO getAllPermissionAndMenuIdMap();

    /**
     * 绑定操作到菜单
     *
     * @param permission
     * @return
     */
    Boolean addPermission(Permission permission);

    /**
     * 获取所有的permission
     *
     * @return
     */
    List<Permission> getAllPermissions();

    /**
     * @param roleId
     * @return
     */
    List<Long> getPermissionIdsByRoleId(long roleId);

    /**
     * 把操作权限分配给角色
     *
     * @param roleId
     * @param permissionIds
     * @return
     */
    Boolean addPermissionToRole(Long roleId, List<Long> permissionIds);


}
