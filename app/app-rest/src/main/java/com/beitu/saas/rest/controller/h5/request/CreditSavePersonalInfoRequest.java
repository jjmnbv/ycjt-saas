package com.beitu.saas.rest.controller.h5.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author linanjun
 * @create 2018/3/22 下午4:40
 * @description
 */
@ApiModel(description = "保存风控模块个人信息")
public class CreditSavePersonalInfoRequest extends ParamsObject {

    @ApiModelProperty(value = "订单号")
    @NotBlank(message = "订单号不能为空")
    private String orderNumb;

    /**
     * QQ
     */
    @ApiModelProperty(value = "QQ")
    private String qq;
    /**
     * 学历
     */
    @ApiModelProperty(value = "学历")
    private Integer education;
    /**
     * 居住地址
     */
    @ApiModelProperty(value = "居住地址")
    private String address;
    /**
     * 居住时长
     */
    @ApiModelProperty(value = "居住时长")
    private String liveDuration;
    /**
     * 婚姻状况
     */
    @ApiModelProperty(value = "婚姻状况")
    private Integer maritalStatus;

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLiveDuration() {
        return liveDuration;
    }

    public void setLiveDuration(String liveDuration) {
        this.liveDuration = liveDuration;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @Override
    public void validate() {

    }

}