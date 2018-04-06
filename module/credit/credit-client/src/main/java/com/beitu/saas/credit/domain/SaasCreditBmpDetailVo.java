package com.beitu.saas.credit.domain;
import com.fqgj.common.api.ResponseData;
import java.io.Serializable;
import java.util.Date;
/**
* User: jungle
* Date: 2018-04-06
* Time: 18:11:44.621
*/
public class SaasCreditBmpDetailVo implements ResponseData,Serializable{

    private Long saasCreditBmpDetailId;

    /**
    *电话邦匹配数据查询表ID
    */
    private Long recordId;
    /**
    *匹配号码电话邦的唯一ID
    */
    private String dhbId;
    /**
    *商户名称
    */
    private String name;
    /**
    *电话描述
    */
    private String teldesc;
    /**
    *电话类型 - 1: 手机 2: 传真 3:电话 4:热线 9:隐藏（反查）
    */
    private Integer teltype;
    /**
    *电话号码
    */
    private String telnum;
    /**
    *电话等级 - 1: 认证 2: 普通
    */
    private Integer telrank;
    /**
    *国家码
    */
    private Integer country;
    /**
    *城市ID
    */
    private Integer cityId;
    /**
    *商户logo下载地址
    */
    private String logo;
    /**
    *商户宣传语
    */
    private String slogan;
    /**
    *商户的标志性图片的下载地址
    */
    private String image;
    /**
    *商户地址
    */
    private String addr;
    /**
    *企业官方网址
    */
    private String web;
    /**
    *电话邦的详情页地址
    */
    private String url;
    /**
    *标记说明
    */
    private String flagType;
    /**
    *标记的用户数
    */
    private Integer flagNum;
    /**
    *标记的ID
    */
    private Integer flagFid;
    /**
    *电话类别名称
    */
    private String catnames;
    /**
    *金融标签
    */
    private String itagIds;

    public Long getSaasCreditBmpDetailId() {
        return saasCreditBmpDetailId;
    }

    public void setSaasCreditBmpDetailId(Long saasCreditBmpDetailId) {
        this.saasCreditBmpDetailId = saasCreditBmpDetailId;
    }



    public  Long getRecordId(){
        return this.recordId;
    }

    public  void setRecordId(Long recordId){
        this.recordId = recordId;
    }

    public  String getDhbId(){
        return this.dhbId;
    }

    public  void setDhbId(String dhbId){
        this.dhbId = dhbId;
    }

    public  String getName(){
        return this.name;
    }

    public  void setName(String name){
        this.name = name;
    }

    public  String getTeldesc(){
        return this.teldesc;
    }

    public  void setTeldesc(String teldesc){
        this.teldesc = teldesc;
    }

    public  Integer getTeltype(){
        return this.teltype;
    }

    public  void setTeltype(Integer teltype){
        this.teltype = teltype;
    }

    public  String getTelnum(){
        return this.telnum;
    }

    public  void setTelnum(String telnum){
        this.telnum = telnum;
    }

    public  Integer getTelrank(){
        return this.telrank;
    }

    public  void setTelrank(Integer telrank){
        this.telrank = telrank;
    }

    public  Integer getCountry(){
        return this.country;
    }

    public  void setCountry(Integer country){
        this.country = country;
    }

    public  Integer getCityId(){
        return this.cityId;
    }

    public  void setCityId(Integer cityId){
        this.cityId = cityId;
    }

    public  String getLogo(){
        return this.logo;
    }

    public  void setLogo(String logo){
        this.logo = logo;
    }

    public  String getSlogan(){
        return this.slogan;
    }

    public  void setSlogan(String slogan){
        this.slogan = slogan;
    }

    public  String getImage(){
        return this.image;
    }

    public  void setImage(String image){
        this.image = image;
    }

    public  String getAddr(){
        return this.addr;
    }

    public  void setAddr(String addr){
        this.addr = addr;
    }

    public  String getWeb(){
        return this.web;
    }

    public  void setWeb(String web){
        this.web = web;
    }

    public  String getUrl(){
        return this.url;
    }

    public  void setUrl(String url){
        this.url = url;
    }

    public  String getFlagType(){
        return this.flagType;
    }

    public  void setFlagType(String flagType){
        this.flagType = flagType;
    }

    public  Integer getFlagNum(){
        return this.flagNum;
    }

    public  void setFlagNum(Integer flagNum){
        this.flagNum = flagNum;
    }

    public  Integer getFlagFid(){
        return this.flagFid;
    }

    public  void setFlagFid(Integer flagFid){
        this.flagFid = flagFid;
    }

    public  String getCatnames(){
        return this.catnames;
    }

    public  void setCatnames(String catnames){
        this.catnames = catnames;
    }

    public  String getItagIds(){
        return this.itagIds;
    }

    public  void setItagIds(String itagIds){
        this.itagIds = itagIds;
    }
}
