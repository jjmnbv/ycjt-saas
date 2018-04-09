package com.beitu.saas.credit.domain;

import com.fqgj.common.api.ResponseData;
import com.beitu.saas.credit.entity.PhoneBookInfo;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
* User: linchenPhoneBookInfoVogyu
* Date: 2017-06-29
* Time: 21:21:33.441
*/
public class PhoneBookInfoVo implements ResponseData,Serializable{

    private Long phoneBookInfoId;

    /**
    *手机号前三位
    */
    private String prefix;
    /**
    *手机号类型
    */
    private String mobileType;
    /**
    *手机号前七位
    */
    private String phoneHeader;
    /**
    *归属地省份
    */
    private String province;
    /**
    *归属地城市
    */
    private String city;
    /**
    *固话区号
    */
    private String areaCode;
    /**
    *邮政编码
    */
    private String postcode;

    public Long getPhoneBookInfoId() {
        return phoneBookInfoId;
    }

    public void setPhoneBookInfoId(Long phoneBookInfoId) {
        this.phoneBookInfoId = phoneBookInfoId;
    }



    public  String getPrefix(){
        return this.prefix;
    }

    public  void setPrefix(String prefix){
        this.prefix = prefix;
    }

    public  String getMobileType(){
        return this.mobileType;
    }

    public  void setMobileType(String mobileType){
        this.mobileType = mobileType;
    }

    public  String getPhoneHeader(){
        return this.phoneHeader;
    }

    public  void setPhoneHeader(String phoneHeader){
        this.phoneHeader = phoneHeader;
    }

    public  String getProvince(){
        return this.province;
    }

    public  void setProvince(String province){
        this.province = province;
    }

    public  String getCity(){
        return this.city;
    }

    public  void setCity(String city){
        this.city = city;
    }

    public  String getAreaCode(){
        return this.areaCode;
    }

    public  void setAreaCode(String areaCode){
        this.areaCode = areaCode;
    }

    public  String getPostcode(){
        return this.postcode;
    }

    public  void setPostcode(String postcode){
        this.postcode = postcode;
    }

    public static PhoneBookInfoVo convertEntityToVO(PhoneBookInfo phoneBookInfo){
        if (phoneBookInfo == null) {
            return null;
        }
        PhoneBookInfoVo phoneBookInfoVo = new PhoneBookInfoVo();
        BeanUtils.copyProperties(phoneBookInfo, phoneBookInfoVo);
        phoneBookInfoVo.setPhoneBookInfoId(phoneBookInfo.getId());
        return phoneBookInfoVo;
    }

}
