package com.beitu.saas.auth.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.671
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
}
