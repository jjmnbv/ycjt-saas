package com.beitu.saas.auth.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: xiaochong
* Date: 2018-03-23
* Time: 11:07:25.654
* TableDesc:
*/
public class SaasMenu extends BaseEntity {
    /**
    *菜单名
    */
    private String name;
    /**
    *菜单链接
    */
    private String link;
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


    public  String getName(){
        return this.name;
    }

    public SaasMenu setName(String name){
        this.name = name;
        return this;
    }

    public  String getLink(){
        return this.link;
    }

    public SaasMenu setLink(String link){
        this.link = link;
        return this;
    }

    public  Integer getPId(){
        return this.pId;
    }

    public SaasMenu setPId(Integer pId){
        this.pId = pId;
        return this;
    }

    public  Boolean getIsParent(){
        return this.isParent;
    }

    public SaasMenu setIsParent(Boolean isParent){
        this.isParent = isParent;
        return this;
    }

    public  Long getSort(){
        return this.sort;
    }

    public SaasMenu setSort(Long sort){
        this.sort = sort;
        return this;
    }
}
