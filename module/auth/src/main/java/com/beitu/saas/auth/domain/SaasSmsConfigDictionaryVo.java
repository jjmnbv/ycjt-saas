package com.beitu.saas.auth.domain;

import com.fqgj.common.api.ResponseData;

import java.io.Serializable;

/**
* User: xiaochong
* Date: 2018-03-26
* Time: 11:27:29.243
*/
public class SaasSmsConfigDictionaryVo implements ResponseData,Serializable{

    private Long saasSmsConfigDictionaryId;

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

    public Long getSaasSmsConfigDictionaryId() {
        return saasSmsConfigDictionaryId;
    }

    public void setSaasSmsConfigDictionaryId(Long saasSmsConfigDictionaryId) {
        this.saasSmsConfigDictionaryId = saasSmsConfigDictionaryId;
    }



    public  String getSmsType(){
        return this.smsType;
    }

    public  void setSmsType(String smsType){
        this.smsType = smsType;
    }

    public  String getSmsReceiver(){
        return this.smsReceiver;
    }

    public  void setSmsReceiver(String smsReceiver){
        this.smsReceiver = smsReceiver;
    }

    public  String getSmsContent(){
        return this.smsContent;
    }

    public  void setSmsContent(String smsContent){
        this.smsContent = smsContent;
    }

    public  Long getCmsId(){
        return this.cmsId;
    }

    public  void setCmsId(Long cmsId){
        this.cmsId = cmsId;
    }
}
