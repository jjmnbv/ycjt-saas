package com.beitu.saas.auth.vo;

import com.beitu.saas.auth.entity.SaasMenu;
import com.beitu.saas.common.consts.CommonConsts;
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
                if (entity.getPId() == entity.getId().longValue()) {
                    firstMenus.add(entity);
                }
            }
            for (SaasMenu Menu : firstMenus) {
                menuIds.add(Menu.getId() + "");
                ParentMenu parentMenu = new ParentMenu();
                parentMenu.setId(Menu.getId());
                parentMenu.setName(Menu.getName());
                parentMenu.setPath(Menu.getLink());
                parentMenu.setIcon(CommonConsts.ossURLPrefix + Menu.getIconUrl());
                parentMenu.setChildren(createChildrenVavs(menuIdMap.get(Menu.getId().intValue()), menuIdMap));
                this.list.add(parentMenu);
            }
        }
    }


    private List<ChildrenMenu> createChildrenVavs(List<SaasMenu> MenuList, Map<Integer, List<SaasMenu>> menuIdMap) {
        List<ChildrenMenu> childrenMenuList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(MenuList)) {
            for (SaasMenu entity : MenuList) {
                if (entity.getId().equals(entity.getPId().longValue())) {
                    continue;
                }
                menuIds.add(entity.getId() + "");
                ChildrenMenu item = new ChildrenMenu();
                item.setId(entity.getId());
                item.setName(entity.getName());
                item.setPath(entity.getLink());
                item.setIcon(CommonConsts.ossURLPrefix + entity.getIconUrl());
                item.setChildren(createChildrenVavs(menuIdMap.get(entity.getId()), menuIdMap));
                childrenMenuList.add(item);
            }
        }

        return childrenMenuList;
    }


    public class ParentMenu {
        private Long id;
        private String name;
        private String path;
        private String icon;
        private Boolean checked;
        List<ChildrenMenu> children = new ArrayList<>();

        public Boolean getChecked() {
            return checked;
        }

        public void setChecked(Boolean checked) {
            this.checked = checked;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public List<ChildrenMenu> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenMenu> children) {
            this.children = children;
        }
    }


    public class ChildrenMenu {
        private Long id;
        private String name;
        private String path;
        private String icon;
        private Boolean checked;
        List<ChildrenMenu> children = new ArrayList<>();


        public Boolean getChecked() {
            return checked;
        }

        public void setChecked(Boolean checked) {
            this.checked = checked;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public List<ChildrenMenu> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenMenu> children) {
            this.children = children;
        }
    }

    public List<ParentMenu> getList() {
        return list;
    }

    public List<String> getMenuIds() {
        return menuIds;
    }
}
