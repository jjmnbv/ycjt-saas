package com.beitu.saas.auth.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
* User: xiaochong
* Date: 2018-03-23
* Time: 11:07:24.383
*/
public class SaasMenuVo implements ResponseData,Serializable{

    private Long saasMenuId;

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

    public Long getSaasMenuId() {
        return saasMenuId;
    }

    public void setSaasMenuId(Long saasMenuId) {
        this.saasMenuId = saasMenuId;
    }



    public  String getName(){
        return this.name;
    }

    public  void setName(String name){
        this.name = name;
    }

    public  String getLink(){
        return this.link;
    }

    public  void setLink(String link){
        this.link = link;
    }

    public  Integer getPId(){
        return this.pId;
    }

    public  void setPId(Integer pId){
        this.pId = pId;
    }

    public  Boolean getIsParent(){
        return this.isParent;
    }

    public  void setIsParent(Boolean isParent){
        this.isParent = isParent;
    }

    public  Long getSort(){
        return this.sort;
    }

    public  void setSort(Long sort){
        this.sort = sort;
    }
}
