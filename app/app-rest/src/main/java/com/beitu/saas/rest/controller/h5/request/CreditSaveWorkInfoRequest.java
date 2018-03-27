package com.beitu.saas.rest.controller.h5.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author linanjun
 * @create 2018/3/22 下午2:12
 * @description
 */
@ApiModel(description = "保存风控模块申请表信息")
public class CreditSaveWorkInfoRequest extends ParamsObject {

    /**
     * 职业
     */
    @ApiModelProperty(value = "职业")
    @NotBlank(message = "职业不能为空")
    private String careerType;
    /**
     * 月收入
     */
    @ApiModelProperty(value = "月收入")
    @NotNull(message = "月收入不能为空")
    private Integer salary;
    /**
     * 发薪日
     */
    @ApiModelProperty(value = "发薪日")
    @NotNull(message = "发薪日不能为空")
    private Integer payDay;
    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    @NotBlank(message = "公司名称不能为空")
    private String companyName;
    /**
     * 公司详细地址
     */
    @ApiModelProperty(value = "公司地址")
    @NotBlank(message = "公司地址不能为空")
    private String companyDetailAddress;

    public String getCareerType() {
        return careerType;
    }

    public void setCareerType(String careerType) {
        this.careerType = careerType;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getPayDay() {
        return payDay;
    }

    public void setPayDay(Integer payDay) {
        this.payDay = payDay;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDetailAddress() {
        return companyDetailAddress;
    }

    public void setCompanyDetailAddress(String companyDetailAddress) {
        this.companyDetailAddress = companyDetailAddress;
    }

    @Override
    public void validate() {

    }

}