package com.beitu.saas.auth.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: xiaochong
* Date: 2018-03-23
* Time: 12:13:48.048
* TableDesc:
*/
public class SaasOperationButton extends BaseEntity {
    /**
    *按钮名
    */
    private String name;
    /**
    *父菜单id
    */
    private Integer pId;
    /**
    *是否为父菜单
    */
    private Boolean isParent;
    /**
    *排序
    */
    private Long sort;

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public  String getName(){
        return this.name;
    }

    public SaasOperationButton setName(String name){
        this.name = name;
        return this;
    }

    public  Integer getPId(){
        return this.pId;
    }

    public SaasOperationButton setPId(Integer pId){
        this.pId = pId;
        return this;
    }

    public  Boolean getIsParent(){
        return this.isParent;
    }

    public SaasOperationButton setIsParent(Boolean isParent){
        this.isParent = isParent;
        return this;
    }

    public  Long getSort(){
        return this.sort;
    }

    public SaasOperationButton setSort(Long sort){
        this.sort = sort;
        return this;
    }
}
