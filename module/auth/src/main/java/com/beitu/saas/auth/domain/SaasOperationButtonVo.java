package com.beitu.saas.auth.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
* User: xiaochong
* Date: 2018-03-23
* Time: 12:13:46.839
*/
public class SaasOperationButtonVo implements ResponseData,Serializable{

    private Long saasOperationButtonId;

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


    public Long getSaasOperationButtonId() {
        return saasOperationButtonId;
    }

    public void setSaasOperationButtonId(Long saasOperationButtonId) {
        this.saasOperationButtonId = saasOperationButtonId;
    }



    public  String getName(){
        return this.name;
    }

    public  void setName(String name){
        this.name = name;
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
