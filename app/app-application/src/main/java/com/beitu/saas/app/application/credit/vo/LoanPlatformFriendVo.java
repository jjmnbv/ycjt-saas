package com.beitu.saas.app.application.credit.vo;

import com.beitu.saas.intergration.risk.pojo.LoanPlatformFriendPojo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

@ApiModel(value = "借贷平台好友信息")
public class LoanPlatformFriendVo implements ResponseData {
    
    @ApiModelProperty(value = "姓名")
    private String name;
    
    @ApiModelProperty(value = "性别")
    private String gender;
    
    @ApiModelProperty(value = "身份证号")
    private String idCardNum;
    
    @ApiModelProperty(value = "手机号")
    private String phoneNum;
    
    @ApiModelProperty(value = "地址")
    private String address;
    
    @ApiModelProperty(value = "出生地")
    private String birthPlace;
    
    @ApiModelProperty(value = "昵称")
    private String nickName;
    
    @ApiModelProperty(value = "微信号")
    private String wechat;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
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
    
    public String getNickName() {
        return nickName;
    }
    
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    public String getWechat() {
        return wechat;
    }
    
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
    
    public static LoanPlatformFriendVo convertPojoToVo(LoanPlatformFriendPojo pojo) {
        if (pojo == null) {
            return null;
        }
        LoanPlatformFriendVo vo = new LoanPlatformFriendVo();
        BeanUtils.copyProperties(pojo, vo);
        return vo;
    }
}
