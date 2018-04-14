package com.beitu.saas.risk.domain.platform.zmxy;

import java.io.Serializable;

/**
 * @author linanjun
 * @create 2018/3/6 上午10:55
 * @description
 */
public class IvsQuery implements Serializable {
    private String openId;
    private String certNo;
    private String name;
    private String mobile;
    private String email;
    private String bankCard;
    private String imei;
    private String imsi;
    private String wifimac;
    private String address;
    private String certType;
    private String ip;
    private String mac;
    private Long borrowId;

    public IvsQuery() {
    }

    public String getOpenId() {
        return this.openId;
    }

    public IvsQuery setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public String getCertNo() {
        return this.certNo;
    }

    public IvsQuery setCertNo(String certNo) {
        this.certNo = certNo;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public IvsQuery setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return this.mobile;
    }

    public IvsQuery setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getEmail() {
        return this.email;
    }

    public IvsQuery setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getBankCard() {
        return this.bankCard;
    }

    public IvsQuery setBankCard(String bankCard) {
        this.bankCard = bankCard;
        return this;
    }

    public String getImei() {
        return this.imei;
    }

    public IvsQuery setImei(String imei) {
        this.imei = imei;
        return this;
    }

    public String getImsi() {
        return this.imsi;
    }

    public IvsQuery setImsi(String imsi) {
        this.imsi = imsi;
        return this;
    }

    public String getWifimac() {
        return this.wifimac;
    }

    public IvsQuery setWifimac(String wifimac) {
        this.wifimac = wifimac;
        return this;
    }

    public String getAddress() {
        return this.address;
    }

    public IvsQuery setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCertType() {
        return this.certType;
    }

    public IvsQuery setCertType(String certType) {
        this.certType = certType;
        return this;
    }

    public String getIp() {
        return this.ip;
    }

    public IvsQuery setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getMac() {
        return this.mac;
    }

    public IvsQuery setMac(String mac) {
        this.mac = mac;
        return this;
    }

    public Long getBorrowId() {
        return this.borrowId;
    }

    public IvsQuery setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
        return this;
    }

    public String toString() {
        return "IvsQuery{openId='" + this.openId + '\'' + ", certNo='" + this.certNo + '\'' + ", name='" + this.name + '\'' + ", mobile='" + this.mobile + '\'' + ", email='" + this.email + '\'' + ", bankCard='" + this.bankCard + '\'' + ", wifimac='" + this.wifimac + '\'' + ", address='" + this.address + '\'' + ", certType='" + this.certType + '\'' + ", ip='" + this.ip + '\'' + ", mac='" + this.mac + '\'' + ", imei='" + this.imei + '\'' + ", imsi='" + this.imsi + '\'' + '}';
    }

}
