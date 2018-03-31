package com.beitu.saas.auth.entity;
import com.fqgj.common.entity.BaseEntity;
import java.util.Date;
/**
 * User: xiaochong
 * Date: 2018-03-31
 * Time: 21:05:30.249
 * TableDesc:
 */
public class SaasSmsConfigDictionary extends BaseEntity{
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
     *短信业务码
     */
    private String bizCode;


    public  String getSmsType(){
        return this.smsType;
    }

    public  SaasSmsConfigDictionary setSmsType(String smsType){
        this.smsType = smsType;
        return this;
    }

    public  String getSmsReceiver(){
        return this.smsReceiver;
    }

    public  SaasSmsConfigDictionary setSmsReceiver(String smsReceiver){
        this.smsReceiver = smsReceiver;
        return this;
    }

    public  String getSmsContent(){
        return this.smsContent;
    }

    public  SaasSmsConfigDictionary setSmsContent(String smsContent){
        this.smsContent = smsContent;
        return this;
    }

    public  String getBizCode(){
        return this.bizCode;
    }

    public  SaasSmsConfigDictionary setBizCode(String bizCode){
        this.bizCode = bizCode;
        return this;
    }
}
