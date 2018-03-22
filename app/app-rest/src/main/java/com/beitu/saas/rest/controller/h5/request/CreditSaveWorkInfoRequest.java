package com.beitu.saas.rest.controller.h5.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/22 下午2:12
 * @description
 */
@ApiModel(description = "保存风控模块申请表信息")
public class CreditSaveWorkInfoRequest extends ParamsObject {

    @ApiModelProperty(value = "职业")
    private String profession;

    @ApiModelProperty(value = "月收入")
    private String monthlyIncome;

    @ApiModelProperty(value = "发薪日")
    private Integer payDay;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "公司地址")
    private String companyAddress;

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
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

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    @Override
    public void validate() {

    }

}