package com.beitu.saas.auth.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:16.647
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


    public  String getName(){
        return this.name;
    }

    public  SaasMenu setName(String name){
        this.name = name;
        return this;
    }

    public  String getLink(){
        return this.link;
    }

    public  SaasMenu setLink(String link){
        this.link = link;
        return this;
    }

    public  Integer getPId(){
        return this.pId;
    }

    public  SaasMenu setPId(Integer pId){
        this.pId = pId;
        return this;
    }
}
