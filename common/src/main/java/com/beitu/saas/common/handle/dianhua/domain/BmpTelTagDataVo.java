package com.beitu.saas.common.handle.dianhua.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by linchengyu on 17/8/3.
 */
public class BmpTelTagDataVo {
    
    private String status;
    
    private String id;
    
    private String name;
    
    private String teldesc;
    
    private Integer teltype;
    
    private String telnum;
    
    private Integer telrank;
    
    private Integer country;
    
    private Integer cityid;
    
    private String logo;
    
    private String Slogan;
    
    private String image;
    
    private String addr;
    
    private String web;
    
    private String url;
    
    private BmpTelFlagVo flag;
    
    private List<String> catnames;
    
    @JsonProperty("itag_ids")
    private List<Integer> itagIds;
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getTeldesc() {
        return teldesc;
    }
    
    public void setTeldesc(String teldesc) {
        this.teldesc = teldesc;
    }
    
    public Integer getTeltype() {
        return teltype;
    }
    
    public void setTeltype(Integer teltype) {
        this.teltype = teltype;
    }
    
    public String getTelnum() {
        return telnum;
    }
    
    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }
    
    public Integer getTelrank() {
        return telrank;
    }
    
    public void setTelrank(Integer telrank) {
        this.telrank = telrank;
    }
    
    public Integer getCountry() {
        return country;
    }
    
    public void setCountry(Integer country) {
        this.country = country;
    }
    
    public Integer getCityid() {
        return cityid;
    }
    
    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }
    
    public String getLogo() {
        return logo;
    }
    
    public void setLogo(String logo) {
        this.logo = logo;
    }
    
    public String getSlogan() {
        return Slogan;
    }
    
    public void setSlogan(String slogan) {
        Slogan = slogan;
    }
    
    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    public String getAddr() {
        return addr;
    }
    
    public void setAddr(String addr) {
        this.addr = addr;
    }
    
    public String getWeb() {
        return web;
    }
    
    public void setWeb(String web) {
        this.web = web;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public BmpTelFlagVo getFlag() {
        return flag;
    }
    
    public void setFlag(BmpTelFlagVo flag) {
        this.flag = flag;
    }
    
    public List<String> getCatnames() {
        return catnames;
    }
    
    public void setCatnames(List<String> catnames) {
        this.catnames = catnames;
    }
    
    public List<Integer> getItagIds() {
        return itagIds;
    }
    
    public void setItagIds(List<Integer> itagIds) {
        this.itagIds = itagIds;
    }
}
