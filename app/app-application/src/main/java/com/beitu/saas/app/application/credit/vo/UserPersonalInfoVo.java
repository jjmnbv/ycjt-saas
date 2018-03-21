package com.beitu.saas.app.application.credit.vo;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/21 下午4:02
 * @description
 */
@ApiModel(value = "用户个人信息模块")
public class UserPersonalInfoVo implements ResponseData {

    @ApiModelProperty(value = "客户姓名")
    private String userName;

    @ApiModelProperty(value = "客户身份证")
    private String identityCode;

    @ApiModelProperty(value = "客户手机号")
    private String mobile;

    @ApiModelProperty(value = "客户性别")
    private String gender;

    @ApiModelProperty(value = "客户年龄")
    private Integer age;

    @ApiModelProperty(value = "客户籍贯")
    private String nativePlace;

    @ApiModelProperty(value = "学历")
    private Integer education;

    @ApiModelProperty(value = "QQ")
    private String qq;

    @ApiModelProperty(value = "婚姻状况")
    private Integer maritalStatus;

    @ApiModelProperty(value = "手机操作系统")
    private String phoneSystem;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getPhoneSystem() {
        return phoneSystem;
    }

    public void setPhoneSystem(String phoneSystem) {
        this.phoneSystem = phoneSystem;
    }
}
