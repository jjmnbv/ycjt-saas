package com.fqgj.youjie.auth.service;



import com.fqgj.youjie.auth.domain.Menu;
import com.fqgj.youjie.auth.entity.AuthMenuEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/13
 * Time: 下午4:18
 */
public interface AdminMenuService {

    /**
     * @param parentId
     * @return
     */
    List<Menu> getAllMenusByParentId(Long parentId);

    /**
     * 获取所有的菜单
     *
     * @return
     */
    List<Menu> getAllMenus();

    /**
     * @param menuIdsList
     * @return
     */
    List<Menu> getMenusByIds(List<Long> menuIdsList);

    /**
     * @param menu
     * @return
     */
    Boolean add(Menu menu);


    /**
     * @param menu
     * @return
     */
    Boolean update(Menu menu);

    /**
     * @param admin
     * @return
     */
    List<Long> getMenuIdsByAdmin(Long admin);

    /**
     * @param roleId
     * @return
     */
    List<Long> getMenuIdsByRole(Long roleId);


    /**
     * 给角色分配菜单
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    Boolean addMenuIdToRole(Long roleId, List<Long> menuIds);


    AuthMenuEntity getMenuByPrimaryKey(Long id);
}
