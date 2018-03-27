package com.beitu.saas.auth.vo;

import com.beitu.saas.auth.entity.SaasMenu;
import com.fqgj.common.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaochong
 * @create 2018/3/23 上午10:30
 * @description
 */
public class FormedMenuVO {

    private List<ParentMenu> list = new ArrayList<>();
    private List<String> menuIds = new ArrayList<>();

    public FormedMenuVO(List<SaasMenu> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            List<SaasMenu> firstMenus = new ArrayList<>();
            Map<Integer, List<SaasMenu>> menuIdMap = new HashMap<>();
            for (SaasMenu entity : list) {
                Integer parentId = entity.getPId();

                List<SaasMenu> temp = new ArrayList<>();
                if (menuIdMap.containsKey(parentId)) {
                    temp = menuIdMap.get(parentId);
                }
                temp.add(entity);
                menuIdMap.put(entity.getPId(), temp);
                if (entity.getPId() == 0) {
                    firstMenus.add(entity);
                }
            }
            for (SaasMenu Menu : firstMenus) {
                menuIds.add(Menu.getId() + "");
                ParentMenu parentMenu = new ParentMenu();
                parentMenu.setId(Menu.getId());
                parentMenu.setParentName(Menu.getName());
                parentMenu.setValue(Menu.getLink());
                parentMenu.setIconUrl(Menu.getIconUrl());
                parentMenu.setNavs(createChildrenVavs(menuIdMap.get(Menu.getId()), menuIdMap));
                this.list.add(parentMenu);
            }
        }
    }


    private List<ChildrenMenu> createChildrenVavs(List<SaasMenu> MenuList, Map<Integer, List<SaasMenu>> menuIdMap) {
        List<ChildrenMenu> childrenMenuList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(MenuList)) {
            for (SaasMenu entity : MenuList) {
                menuIds.add(entity.getId() + "");
                ChildrenMenu item = new ChildrenMenu();
                item.setId(entity.getId());
                item.setKey(entity.getName());
                item.setValue(entity.getLink());
                item.setIconUrl(entity.getIconUrl());
                item.setNavs(createChildrenVavs(menuIdMap.get(entity.getId()), menuIdMap));
                childrenMenuList.add(item);
            }
        }

        return childrenMenuList;
    }


    public class ParentMenu {
        private Long id;
        private String parentName;
        private String value;
        private String iconUrl;
        List<ChildrenMenu> navs = new ArrayList<>();

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

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
        private String iconUrl;
        List<ChildrenMenu> navs = new ArrayList<>();

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

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
