package com.fqgj.youjie.auth.vo;

import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.youjie.auth.domain.Menu;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/14
 * Time: 上午9:51
 * <p>
 * 经过组织过的菜单
 */
public class FormedMenuVO {

    private List<ParentMenu> list = new ArrayList<>();
    private List<String> menuIds = new ArrayList<>();

    public FormedMenuVO(List<Menu> list) {
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
                ParentMenu parentMenu = new ParentMenu();
                parentMenu.setId(Menu.getMenuId());
                parentMenu.setParentName(Menu.getKey());
                parentMenu.setValue(Menu.getValue());
                parentMenu.setNavs(createChildrenVavs(menuIdMap.get(Menu.getMenuId()), menuIdMap));
                this.list.add(parentMenu);
            }
        }
    }


    private List<ChildrenMenu> createChildrenVavs(List<Menu> MenuList, Map<Long, List<Menu>> menuIdMap) {
        List<ChildrenMenu> childrenMenuList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(MenuList)) {
            for (Menu entity : MenuList) {
                menuIds.add(entity.getMenuId() + "");
                ChildrenMenu item = new ChildrenMenu();
                item.setId(entity.getMenuId());
                item.setKey(entity.getKey());
                item.setValue(entity.getValue());
                item.setNavs(createChildrenVavs(menuIdMap.get(entity.getMenuId()), menuIdMap));
                childrenMenuList.add(item);
            }
        }

        return childrenMenuList;
    }


    public class ParentMenu {
        private Long id;
        private String parentName;
        private String value;
        List<ChildrenMenu> navs = new ArrayList<>();

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Long getId() {
            return id;
        }

        public ParentMenu setId(Long id) {
            this.id = id;
            return this;
        }

        public String getParentName() {
            return parentName;
        }

        public ParentMenu setParentName(String parentName) {
            this.parentName = parentName;
            return this;
        }

        public List<ChildrenMenu> getNavs() {
            return navs;
        }

        public ParentMenu setNavs(List<ChildrenMenu> navs) {
            this.navs = navs;
            return this;
        }
    }


    private class ChildrenMenu {
        private Long id;
        private String key;
        private String value;

        List<ChildrenMenu> navs = new ArrayList<>();

        public Long getId() {
            return id;
        }

        public ChildrenMenu setId(Long id) {
            this.id = id;
            return this;
        }

        public String getKey() {
            return key;
        }

        public ChildrenMenu setKey(String key) {
            this.key = key;
            return this;
        }

        public String getValue() {
            return value;
        }

        public ChildrenMenu setValue(String value) {
            this.value = value;
            return this;
        }

        public List<ChildrenMenu> getNavs() {
            return navs;
        }

        public void setNavs(List<ChildrenMenu> navs) {
            this.navs = navs;
        }
    }

    public List<ParentMenu> getList() {
        return list;
    }

    public List<String> getMenuIds() {
        return menuIds;
    }
}
