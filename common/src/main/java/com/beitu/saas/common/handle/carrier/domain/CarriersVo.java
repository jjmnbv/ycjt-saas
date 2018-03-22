package com.beitu.saas.common.handle.carrier.domain;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

public class CarriersVo implements Serializable {
    private static final long serialVersionUID = 5759759593970403199L;
    
    private String carrierType;//运营商类型
    //基本属性
    private String mobile;//手机号
    private Float availablebalance = 0f;//话费余额
    private String netWorkTime;//入网时间
    private String realName;//姓名
    private String idNumber;//身份证
    private String opentime;//入网年份
    private String province;//省份
    private String priceplanname;//套餐
    
    private Integer totalPhoneRecords = 0;//通话记录数
    private LinkedHashMap<String, CarriersPhoneCallVo> carriersPhoneCallMap;//通话记录统计  手机号统计
    private List<CarriersPhoneCallVo> carriersPhoneCallVoList;//通话记录统计  手机号统计
    private List<CarriersPhoneCallVo> carriersCallVoList;//通话记录统计(单条数据)
    
    private Integer totalMonths = 0;//消费月数
    private List<CarriersBillVo> carriersBillVoList;//月账单
    private LinkedHashMap<String, CarriersPhoneCallMonthVo> carrierMap;//每月统计
    private List<CarriersPhoneCallVo> merchantPhoneCallVoList;//商户通话记录
    private List<CarriersPhoneCallVo> highFrequencyDeviceContactList;//高频率联系人通话纪录
    private List<CarriersPhoneCallVo> activeRegionPhoneCallVoList;//通话活跃区域记录
    
    private String activeRegion;//活跃地区
    private String registerRegion;//入网地区
    private String mostContactRegion;//联系人最多地区
    private Integer interactionContactCnt = 0;//互通联系人数量
    private Integer nightCallDuration = 0;//夜间（23-06时）通话时长
    private Integer totalCallDuration = 0;//总通话时长
    private List<CarriersPhoneCallVo> contactRegionPhoneCallVoList;//联系人所在地记录
    private List<CarriersPhoneCallVo> longCallDurationContactVoList;//长时间联系人记录
    
    public LinkedHashMap<String, CarriersPhoneCallVo> getCarriersPhoneCallMap() {
        return carriersPhoneCallMap;
    }
    
    public CarriersVo setCarriersPhoneCallMap(LinkedHashMap<String, CarriersPhoneCallVo> carriersPhoneCallMap) {
        this.carriersPhoneCallMap = carriersPhoneCallMap;
        return this;
    }
    
    public String getCarrierType() {
        return carrierType;
    }
    
    public CarriersVo setCarrierType(String carrierType) {
        this.carrierType = carrierType;
        return this;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public CarriersVo setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }
    
    public Float getAvailablebalance() {
        return availablebalance;
    }
    
    public CarriersVo setAvailablebalance(Float availablebalance) {
        this.availablebalance = availablebalance;
        return this;
    }
    
    public String getNetWorkTime() {
        return netWorkTime;
    }
    
    public CarriersVo setNetWorkTime(String netWorkTime) {
        this.netWorkTime = netWorkTime;
        return this;
    }
    
    public String getRealName() {
        return realName;
    }
    
    public CarriersVo setRealName(String realName) {
        this.realName = realName;
        return this;
    }
    
    public String getIdNumber() {
        return idNumber;
    }
    
    public CarriersVo setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }
    
    public String getOpentime() {
        return opentime;
    }
    
    public CarriersVo setOpentime(String opentime) {
        this.opentime = opentime;
        return this;
    }
    
    public String getProvince() {
        return province;
    }
    
    public CarriersVo setProvince(String province) {
        this.province = province;
        return this;
    }
    
    public String getPriceplanname() {
        return priceplanname;
    }
    
    public CarriersVo setPriceplanname(String priceplanname) {
        this.priceplanname = priceplanname;
        return this;
    }
    
    public Integer getTotalMonths() {
        return totalMonths;
    }
    
    public CarriersVo setTotalMonths(Integer totalMonths) {
        this.totalMonths = totalMonths;
        return this;
    }
    
    public List<CarriersBillVo> getCarriersBillVoList() {
        return carriersBillVoList;
    }
    
    public CarriersVo setCarriersBillVoList(List<CarriersBillVo> carriersBillVoList) {
        this.carriersBillVoList = carriersBillVoList;
        return this;
    }
    
    public Integer getTotalPhoneRecords() {
        return totalPhoneRecords;
    }
    
    public CarriersVo setTotalPhoneRecords(Integer totalPhoneRecords) {
        this.totalPhoneRecords = totalPhoneRecords;
        return this;
    }
    
    public List<CarriersPhoneCallVo> getCarriersPhoneCallVoList() {
        return carriersPhoneCallVoList;
    }
    
    public CarriersVo setCarriersPhoneCallVoList(List<CarriersPhoneCallVo> carriersPhoneCallVoList) {
        this.carriersPhoneCallVoList = carriersPhoneCallVoList;
        return this;
    }
    
    public List<CarriersPhoneCallVo> getCarriersCallVoList() {
        return carriersCallVoList;
    }
    
    public CarriersVo setCarriersCallVoList(List<CarriersPhoneCallVo> carriersCallVoList) {
        this.carriersCallVoList = carriersCallVoList;
        return this;
    }
    
    public LinkedHashMap<String, CarriersPhoneCallMonthVo> getCarrierMap() {
        return carrierMap;
    }
    
    public CarriersVo setCarrierMap(LinkedHashMap<String, CarriersPhoneCallMonthVo> carrierMap) {
        this.carrierMap = carrierMap;
        return this;
    }
    
    public List<CarriersPhoneCallVo> getMerchantPhoneCallVoList() {
        return merchantPhoneCallVoList;
    }
    
    public CarriersVo setMerchantPhoneCallVoList(List<CarriersPhoneCallVo> merchantPhoneCallVoList) {
        this.merchantPhoneCallVoList = merchantPhoneCallVoList;
        return this;
    }
    
    public List<CarriersPhoneCallVo> getHighFrequencyDeviceContactList() {
        return highFrequencyDeviceContactList;
    }
    
    public CarriersVo setHighFrequencyDeviceContactList(List<CarriersPhoneCallVo> highFrequencyDeviceContactList) {
        this.highFrequencyDeviceContactList = highFrequencyDeviceContactList;
        return this;
    }
    
    public List<CarriersPhoneCallVo> getActiveRegionPhoneCallVoList() {
        return activeRegionPhoneCallVoList;
    }
    
    public CarriersVo setActiveRegionPhoneCallVoList(List<CarriersPhoneCallVo> activeRegionPhoneCallVoList) {
        this.activeRegionPhoneCallVoList = activeRegionPhoneCallVoList;
        return this;
    }
    
    public String getActiveRegion() {
        return activeRegion;
    }
    
    public CarriersVo setActiveRegion(String activeRegion) {
        this.activeRegion = activeRegion;
        return this;
    }
    
    public String getRegisterRegion() {
        return registerRegion;
    }
    
    public CarriersVo setRegisterRegion(String registerRegion) {
        this.registerRegion = registerRegion;
        return this;
    }
    
    public String getMostContactRegion() {
        return mostContactRegion;
    }
    
    public CarriersVo setMostContactRegion(String mostContactRegion) {
        this.mostContactRegion = mostContactRegion;
        return this;
    }
    
    public Integer getInteractionContactCnt() {
        return interactionContactCnt;
    }
    
    public CarriersVo setInteractionContactCnt(Integer interactionContactCnt) {
        this.interactionContactCnt = interactionContactCnt;
        return this;
    }
    
    public Integer getNightCallDuration() {
        return nightCallDuration;
    }
    
    public CarriersVo setNightCallDuration(Integer nightCallDuration) {
        this.nightCallDuration = nightCallDuration;
        return this;
    }
    
    public Integer getTotalCallDuration() {
        return totalCallDuration;
    }
    
    public CarriersVo setTotalCallDuration(Integer totalCallDuration) {
        this.totalCallDuration = totalCallDuration;
        return this;
    }
    
    public List<CarriersPhoneCallVo> getContactRegionPhoneCallVoList() {
        return contactRegionPhoneCallVoList;
    }
    
    public CarriersVo setContactRegionPhoneCallVoList(List<CarriersPhoneCallVo> contactRegionPhoneCallVoList) {
        this.contactRegionPhoneCallVoList = contactRegionPhoneCallVoList;
        return this;
    }
    
    public List<CarriersPhoneCallVo> getLongCallDurationContactVoList() {
        return longCallDurationContactVoList;
    }
    
    public CarriersVo setLongCallDurationContactVoList(List<CarriersPhoneCallVo> longCallDurationContactVoList) {
        this.longCallDurationContactVoList = longCallDurationContactVoList;
        return this;
    }
}
