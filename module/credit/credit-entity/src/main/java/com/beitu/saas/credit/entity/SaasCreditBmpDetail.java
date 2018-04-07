package com.beitu.saas.credit.entity;

import com.fqgj.common.entity.BaseEntity;

/**
 * User: jungle
 * Date: 2018-04-06
 * Time: 18:11:44.617
 * TableDesc:电话邦匹配数据详情表
 */
public class SaasCreditBmpDetail extends BaseEntity {
    /**
     * 电话邦匹配数据查询表ID
     */
    private Long recordId;
    /**
     * 匹配号码电话邦的唯一ID
     */
    private String dhbId;
    /**
     * 商户名称
     */
    private String name;
    /**
     * 电话描述
     */
    private String teldesc;
    /**
     * 电话类型 - 1: 手机 2: 传真 3:电话 4:热线 9:隐藏（反查）
     */
    private Integer teltype;
    /**
     * 电话号码
     */
    private String telnum;
    /**
     * 电话等级 - 1: 认证 2: 普通
     */
    private Integer telrank;
    /**
     * 国家码
     */
    private Integer country;
    /**
     * 城市ID
     */
    private Integer cityId;
    /**
     * 商户logo下载地址
     */
    private String logo;
    /**
     * 商户宣传语
     */
    private String slogan;
    /**
     * 商户的标志性图片的下载地址
     */
    private String image;
    /**
     * 商户地址
     */
    private String addr;
    /**
     * 企业官方网址
     */
    private String web;
    /**
     * 电话邦的详情页地址
     */
    private String url;
    /**
     * 标记说明
     */
    private String flagType;
    /**
     * 标记的用户数
     */
    private Integer flagNum;
    /**
     * 标记的ID
     */
    private Integer flagFid;
    /**
     * 电话类别名称
     */
    private String catnames;
    /**
     * 金融标签
     */
    private String itagIds;


    public Long getRecordId() {
        return this.recordId;
    }

    public SaasCreditBmpDetail setRecordId(Long recordId) {
        this.recordId = recordId;
        return this;
    }

    public String getDhbId() {
        return this.dhbId;
    }

    public SaasCreditBmpDetail setDhbId(String dhbId) {
        this.dhbId = dhbId;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public SaasCreditBmpDetail setName(String name) {
        this.name = name;
        return this;
    }

    public String getTeldesc() {
        return this.teldesc;
    }

    public SaasCreditBmpDetail setTeldesc(String teldesc) {
        this.teldesc = teldesc;
        return this;
    }

    public Integer getTeltype() {
        return this.teltype;
    }

    public SaasCreditBmpDetail setTeltype(Integer teltype) {
        this.teltype = teltype;
        return this;
    }

    public String getTelnum() {
        return this.telnum;
    }

    public SaasCreditBmpDetail setTelnum(String telnum) {
        this.telnum = telnum;
        return this;
    }

    public Integer getTelrank() {
        return this.telrank;
    }

    public SaasCreditBmpDetail setTelrank(Integer telrank) {
        this.telrank = telrank;
        return this;
    }

    public Integer getCountry() {
        return this.country;
    }

    public SaasCreditBmpDetail setCountry(Integer country) {
        this.country = country;
        return this;
    }

    public Integer getCityId() {
        return this.cityId;
    }

    public SaasCreditBmpDetail setCityId(Integer cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getLogo() {
        return this.logo;
    }

    public SaasCreditBmpDetail setLogo(String logo) {
        this.logo = logo;
        return this;
    }

    public String getSlogan() {
        return this.slogan;
    }

    public SaasCreditBmpDetail setSlogan(String slogan) {
        this.slogan = slogan;
        return this;
    }

    public String getImage() {
        return this.image;
    }

    public SaasCreditBmpDetail setImage(String image) {
        this.image = image;
        return this;
    }

    public String getAddr() {
        return this.addr;
    }

    public SaasCreditBmpDetail setAddr(String addr) {
        this.addr = addr;
        return this;
    }

    public String getWeb() {
        return this.web;
    }

    public SaasCreditBmpDetail setWeb(String web) {
        this.web = web;
        return this;
    }

    public String getUrl() {
        return this.url;
    }

    public SaasCreditBmpDetail setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getFlagType() {
        return this.flagType;
    }

    public SaasCreditBmpDetail setFlagType(String flagType) {
        this.flagType = flagType;
        return this;
    }

    public Integer getFlagNum() {
        return this.flagNum;
    }

    public SaasCreditBmpDetail setFlagNum(Integer flagNum) {
        this.flagNum = flagNum;
        return this;
    }

    public Integer getFlagFid() {
        return this.flagFid;
    }

    public SaasCreditBmpDetail setFlagFid(Integer flagFid) {
        this.flagFid = flagFid;
        return this;
    }

    public String getCatnames() {
        return this.catnames;
    }

    public SaasCreditBmpDetail setCatnames(String catnames) {
        this.catnames = catnames;
        return this;
    }

    public String getItagIds() {
        return this.itagIds;
    }

    public SaasCreditBmpDetail setItagIds(String itagIds) {
        this.itagIds = itagIds;
        return this;
    }
}
