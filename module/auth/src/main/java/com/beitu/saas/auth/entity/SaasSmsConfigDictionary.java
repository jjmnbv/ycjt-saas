package com.beitu.saas.auth.entity;
import com.fqgj.common.entity.BaseEntity;

/**
* User: xiaochong
* Date: 2018-03-26
* Time: 11:27:30.474
* TableDesc:
*/
public class SaasSmsConfigDictionary extends BaseEntity {
    /**
    *短信类型
    */
    private String smsType;
    /**
    *
    */
    private String smsReceiver;
    /**
    *短信内容
    */
    private String smsContent;
    /**
    *cms关联id
    */
    private Long cmsId;


    public  String getSmsType(){
        return this.smsType;
    }

    public SaasSmsConfigDictionary setSmsType(String smsType){
        this.smsType = smsType;
        return this;
    }

    public  String getSmsReceiver(){
        return this.smsReceiver;
    }

    public SaasSmsConfigDictionary setSmsReceiver(String smsReceiver){
        this.smsReceiver = smsReceiver;
        return this;
    }

    public  String getSmsContent(){
        return this.smsContent;
    }

    public SaasSmsConfigDictionary setSmsContent(String smsContent){
        this.smsContent = smsContent;
        return this;
    }

    public  Long getCmsId(){
        return this.cmsId;
    }

    public SaasSmsConfigDictionary setCmsId(Long cmsId){
        this.cmsId = cmsId;
        return this;
    }
}
