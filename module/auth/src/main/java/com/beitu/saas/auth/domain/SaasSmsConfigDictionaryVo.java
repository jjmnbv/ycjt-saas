package com.beitu.saas.auth.domain;
import com.fqgj.common.api.ResponseData;
import java.io.Serializable;
import java.util.Date;
/**
 * User: xiaochong
 * Date: 2018-03-31
 * Time: 21:05:29.320
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
     *短信业务码
     */
    private String bizCode;

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

    public  String getBizCode(){
        return this.bizCode;
    }

    public  void setBizCode(String bizCode){
        this.bizCode = bizCode;
    }
}
