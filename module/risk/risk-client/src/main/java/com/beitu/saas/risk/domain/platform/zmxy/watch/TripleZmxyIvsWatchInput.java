package com.beitu.saas.risk.domain.platform.zmxy.watch;

import com.beitu.saas.risk.domain.platform.TripleServiceBaseInput;

public class TripleZmxyIvsWatchInput extends TripleServiceBaseInput {
    private String openId;
    //证件号 备注：证件号、姓名、手机号、地址、银行卡、电子邮箱必须传其中两项
    private String certNo;
    //姓名
    private String name;
    //手机号，最多传入三个，多个手机号之间用|分隔
    private String mobile;
    //电子邮箱，最多传入两个，多个邮箱之间用|分隔
    private String email;
    //银行卡号，最多输入两个，多个银行卡号之间用|分隔
    private String bankCard;
    //国际移动设备标志
    private String imei;
    //国际移动用户识别码
    private String imsi;

    //wifi的物理地址
    private String wifimac;
    //用户地址，最多输入三个，多个地址之间用|分隔
    private String address;
    //证件类型
    private String certType;
    //ip地址
    private String ip;
    //物理地址
    private String mac;

    public String getOpenId() {
        return openId;
    }

    public TripleZmxyIvsWatchInput setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public String getCertNo() {
        return certNo;
    }

    public TripleZmxyIvsWatchInput setCertNo(String certNo) {
        this.certNo = certNo;
        return this;
    }

    public String getName() {
        return name;
    }

    public TripleZmxyIvsWatchInput setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public TripleZmxyIvsWatchInput setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public TripleZmxyIvsWatchInput setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getBankCard() {
        return bankCard;
    }

    public TripleZmxyIvsWatchInput setBankCard(String bankCard) {
        this.bankCard = bankCard;
        return this;
    }

    public String getImei() {
        return imei;
    }

    public TripleZmxyIvsWatchInput setImei(String imei) {
        this.imei = imei;
        return this;
    }

    public String getImsi() {
        return imsi;
    }

    public TripleZmxyIvsWatchInput setImsi(String imsi) {
        this.imsi = imsi;
        return this;
    }

    public String getWifimac() {
        return wifimac;
    }

    public TripleZmxyIvsWatchInput setWifimac(String wifimac) {
        this.wifimac = wifimac;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public TripleZmxyIvsWatchInput setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCertType() {
        return certType;
    }

    public TripleZmxyIvsWatchInput setCertType(String certType) {
        this.certType = certType;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public TripleZmxyIvsWatchInput setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getMac() {
        return mac;
    }

    public TripleZmxyIvsWatchInput setMac(String mac) {
        this.mac = mac;
        return this;
    }

    @Override
    public String getIdentityNo() {
        return "";
    }
}
