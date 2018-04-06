package com.beitu.saas.app.application.credit.vo;

import com.beitu.saas.intergration.risk.pojo.LoanPlatformBasicInfoPojo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

@ApiModel(value = "借贷平台基本信息")
public class LoanPlatformBaseInfoVo implements ResponseData {
    
    @ApiModelProperty(value = "姓名")
    private String name;
    
    @ApiModelProperty(value = "昵称")
    private String nickName;
    
    @ApiModelProperty(value = "性别")
    private String gender;
    
    @ApiModelProperty(value = "身份证号")
    private String idCardNum;
    
    @ApiModelProperty(value = "生日")
    private String birthday;
    
    @ApiModelProperty(value = "手机号")
    private String phoneNum;
    
    @ApiModelProperty(value = "地址")
    private String address;
    
    @ApiModelProperty(value = "出生地")
    private String birthPlace;
    
    @ApiModelProperty(value = "账户余额")
    private String balance;
    
    @ApiModelProperty(value = "信用等级")
    private String grade;
    
    @ApiModelProperty(value = "注册时间")
    private String registTime;
    
    @ApiModelProperty(value = "民族")
    private String nation;
    
    @ApiModelProperty(value = "邮箱")
    private String email;
    
    @ApiModelProperty(value = "婚姻状况")
    private String marital;
    
    @ApiModelProperty(value = "微信号")
    private String wechat;
    
    @ApiModelProperty(value = "芝麻分")
    private String zmCredit;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getNickName() {
        return nickName;
    }
    
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getIdCardNum() {
        return idCardNum;
    }
    
    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }
    
    public String getBirthday() {
        return birthday;
    }
    
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    
    public String getPhoneNum() {
        return phoneNum;
    }
    
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getBirthPlace() {
        return birthPlace;
    }
    
    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }
    
    public String getBalance() {
        return balance;
    }
    
    public void setBalance(String balance) {
        this.balance = balance;
    }
    
    public String getGrade() {
        return grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    public String getRegistTime() {
        return registTime;
    }
    
    public void setRegistTime(String registTime) {
        this.registTime = registTime;
    }
    
    public String getNation() {
        return nation;
    }
    
    public void setNation(String nation) {
        this.nation = nation;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMarital() {
        return marital;
    }
    
    public void setMarital(String marital) {
        this.marital = marital;
    }
    
    public String getWechat() {
        return wechat;
    }
    
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
    
    public String getZmCredit() {
        return zmCredit;
    }
    
    public void setZmCredit(String zmCredit) {
        this.zmCredit = zmCredit;
    }
    
    public static LoanPlatformBaseInfoVo convertPojoToVo(LoanPlatformBasicInfoPojo pojo) {
        if (pojo == null) {
            return null;
        }
        LoanPlatformBaseInfoVo vo = new LoanPlatformBaseInfoVo();
        BeanUtils.copyProperties(pojo, vo);
        return vo;
    }
}
