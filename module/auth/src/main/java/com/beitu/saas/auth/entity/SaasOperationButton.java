package com.beitu.saas.auth.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:16.656
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


    public  String getName(){
        return this.name;
    }

    public  SaasOperationButton setName(String name){
        this.name = name;
        return this;
    }

    public  Integer getPId(){
        return this.pId;
    }

    public  SaasOperationButton setPId(Integer pId){
        this.pId = pId;
        return this;
    }
}
