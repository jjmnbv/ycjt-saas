package com.fqgj.youjie.auth.vo;

import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.youjie.auth.domain.Menu;
import com.fqgj.youjie.auth.domain.Permission;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2016/10/26
 * Time: 下午5:09
 */
public class MenuWithOperationsVO  {
    private List<UserMenu> list = new ArrayList<>();
    private List<String> menuIds = new ArrayList<>();


    public MenuWithOperationsVO(List<Menu> list, Map<Long, List<Permission>> longListMap) {
        if (CollectionUtils.isNotEmpty(list)) {
            List<Menu> firstMenus = new ArrayList<>();
            Map<Long, List<Menu>> menuIdMap = new HashMap<>();
            for (Menu entity : list) {
                Long parentId = entity.getParentId();

                List<Menu> temp = new ArrayList<>();
                if (menuIdMap.containsKey(parentId)) {
                    temp = menuIdMap.get(parentId);
                }

                temp.add(entity);
                menuIdMap.put(entity.getParentId(), temp);

                if (entity.getParentId() == 0) {
                    firstMenus.add(entity);
                }
            }

            for (Menu Menu : firstMenus) {
                menuIds.add(Menu.getMenuId() + "");
                UserMenu userMenu = new UserMenu();
                userMenu.setId(Menu.getMenuId());
                userMenu.setParentName(Menu.getKey());
                userMenu.setNavs(createChildrenVavs(menuIdMap.get(Menu.getMenuId()), menuIdMap, longListMap));
                this.list.add(userMenu);
            }
        }
    }


    private List<UserMenuItem> createChildrenVavs(List<Menu> MenuList, Map<Long, List<Menu>> menuIdMap, Map<Long, List<Permission>> longListMap) {
        List<UserMenuItem> userMenuItemList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(MenuList)) {
            for (Menu entity : MenuList) {
                menuIds.add(entity.getMenuId() + "");
                UserMenuItem item = new UserMenuItem();
                item.setId("" + entity.getMenuId());
                item.setKey(entity.getKey());
                item.setValue(entity.getValue());
                item.setNavs(createLeafNavs(menuIdMap.get(entity.getMenuId()), longListMap));
                userMenuItemList.add(item);
            }
        }

        return userMenuItemList;
    }

    private List<UserMenuItem> createLeafNavs(List<Menu> MenuList, Map<Long, List<Permission>> longListMap) {
        List<UserMenuItem> userMenuItemList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(MenuList)) {
            for (Menu entity : MenuList) {
                menuIds.add(entity.getMenuId() + "");
                UserMenuItem item = new UserMenuItem();
                item.setId("" + entity.getMenuId());
                item.setKey(entity.getKey());
                item.setValue(entity.getValue());
                item.setNavs(createOperation(entity.getMenuId(), longListMap));
                userMenuItemList.add(item);
            }
        }

        return userMenuItemList;
    }

    private List<UserMenuItem> createOperation(Long menuId, Map<Long, List<Permission>> longListMap) {
        List<UserMenuItem> userMenuItemList = new ArrayList<>();

        if (CollectionUtils.isEmpty(longListMap)) {
            return userMenuItemList;
        }

        if (longListMap.containsKey(menuId)) {
            List<Permission> entityList = longListMap.get(menuId);
            if (CollectionUtils.isNotEmpty(entityList)) {
                for (Permission Permission : entityList) {
                    UserMenuItem item = new UserMenuItem()
                            .setId("a" + Permission.getId())
                            .setKey(Permission.getName())
                            .setValue(Permission.getPermission())
                            .setAction(true);
                    userMenuItemList.add(item);
                }
            }
        }

        return userMenuItemList;
    }


    public class UserMenu {
        private Long id;
        private String parentName;
        List<UserMenuItem> navs = new ArrayList<>();

        public Long getId() {
            return id;
        }

        public UserMenu setId(Long id) {
            this.id = id;
            return this;
        }

        public String getParentName() {
            return parentName;
        }

        public UserMenu setParentName(String parentName) {
            this.parentName = parentName;
            return this;
        }

        public List<UserMenuItem> getNavs() {
            return navs;
        }

        public UserMenu setNavs(List<UserMenuItem> navs) {
            this.navs = navs;
            return this;
        }
    }


    private class UserMenuItem {
        private String id;
        private String key;
        private String value;
        private Boolean isAction = false;

        List<UserMenuItem> navs = new ArrayList<>();

        public Boolean getAction() {
            return isAction;
        }

        public UserMenuItem setAction(Boolean action) {
            isAction = action;
            return this;
        }

        public String getId() {
            return id;
        }

        public UserMenuItem setId(String id) {
            this.id = id;
            return this;
        }

        public String getKey() {
            return key;
        }

        public UserMenuItem setKey(String key) {
            this.key = key;
            return this;
        }

        public String getValue() {
            return value;
        }

        public UserMenuItem setValue(String value) {
            this.value = value;
            return this;
        }

        public List<UserMenuItem> getNavs() {
            return navs;
        }

        public void setNavs(List<UserMenuItem> navs) {
            this.navs = navs;
        }
    }


    public List<UserMenu> getList() {
        return list;
    }

    public MenuWithOperationsVO setList(List<UserMenu> list) {
        this.list = list;
        return this;
    }

    public List<String> getMenuIds() {
        return menuIds;
    }

    public MenuWithOperationsVO setMenuIds(List<String> menuIds) {
        this.menuIds = menuIds;
        return this;
    }
}

