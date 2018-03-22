package com.beitu.saas.app.application.credit.vo;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/21 下午9:18
 * @description
 */
@ApiModel(value = "用户工作信息模块")
public class BorrowerWorkInfoVo implements ResponseData {

    /**
     * 职业
     */
    @ApiModelProperty(value = "职业")
    private Integer careerType;
    /**
     * 月收入
     */
    @ApiModelProperty(value = "月收入")
    private Integer salary;
    /**
     * 发薪日
     */
    @ApiModelProperty(value = "发薪日")
    private Integer payDay;
    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    private String companyName;
    /**
     * 公司详细地址
     */
    @ApiModelProperty(value = "公司地址")
    private String companyDetailAddress;

    public Integer getCareerType() {
        return careerType;
    }

    public void setCareerType(Integer careerType) {
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

}
