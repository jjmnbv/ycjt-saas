package com.beitu.saas.borrower.domain;

import com.beitu.saas.borrower.entity.SaasBorrowerWorkInfo;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 20:25:59.906
 */
public class SaasBorrowerWorkInfoVo implements ResponseData, Serializable {

    private Long saasBorrowerWorkInfoId;

    /**
     * 借款人CODE
     */
    private String borrowerCode;
    /**
     * 订单号
     */
    private String orderNumb;
    /**
     * 职业
     */
    private String career;
    /**
     * 月收入
     */
    private Integer salary;
    /**
     * 发薪日
     */
    private Integer payDay;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 公司详细地址
     */
    private String companyDetailAddress;
    /**
     * 是否成功
     */
    private Boolean success;

    public Long getSaasBorrowerWorkInfoId() {
        return saasBorrowerWorkInfoId;
    }

    public void setSaasBorrowerWorkInfoId(Long saasBorrowerWorkInfoId) {
        this.saasBorrowerWorkInfoId = saasBorrowerWorkInfoId;
    }


    public String getBorrowerCode() {
        return this.borrowerCode;
    }

    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }

    public String getOrderNumb() {
        return this.orderNumb;
    }

    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
    }

    public String getCareer() {
        return this.career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public Integer getSalary() {
        return this.salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getPayDay() {
        return this.payDay;
    }

    public void setPayDay(Integer payDay) {
        this.payDay = payDay;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDetailAddress() {
        return this.companyDetailAddress;
    }

    public void setCompanyDetailAddress(String companyDetailAddress) {
        this.companyDetailAddress = companyDetailAddress;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public static SaasBorrowerWorkInfoVo convertEntityToVO(SaasBorrowerWorkInfo saasBorrowerWorkInfo) {
        if (saasBorrowerWorkInfo == null) {
            return null;
        }
        SaasBorrowerWorkInfoVo saasBorrowerWorkInfoVo = new SaasBorrowerWorkInfoVo();
        BeanUtils.copyProperties(saasBorrowerWorkInfo, saasBorrowerWorkInfoVo);
        saasBorrowerWorkInfoVo.setSaasBorrowerWorkInfoId(saasBorrowerWorkInfo.getId());
        return saasBorrowerWorkInfoVo;
    }

    public static SaasBorrowerWorkInfo convertVOToEntity(SaasBorrowerWorkInfoVo saasBorrowerWorkInfoVo) {
        if (saasBorrowerWorkInfoVo == null) {
            return null;
        }
        SaasBorrowerWorkInfo saasBorrowerWorkInfo = new SaasBorrowerWorkInfo();
        BeanUtils.copyProperties(saasBorrowerWorkInfoVo, saasBorrowerWorkInfo);
        saasBorrowerWorkInfo.setId(saasBorrowerWorkInfoVo.getSaasBorrowerWorkInfoId());
        return saasBorrowerWorkInfo;
    }

}
