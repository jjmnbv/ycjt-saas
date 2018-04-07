package com.beitu.saas.app.application.credit.vo;

import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.credit.domain.SaasCreditCarrierBaseVo;
import com.fqgj.common.api.ResponseData;
import com.fqgj.common.utils.StrUtils;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CreditCarrierBaseVo implements ResponseData, Serializable {

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证
     */
    private String identityNo;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 入网时间
     */
    private String registerTime;

    /**
     * 号码使用时长
     */
    private String usageTime;

    /**
     * 运营商名称
     */
    private String carrierType;

    /**
     * 套餐
     */
    private String planName;

    /**
     * 余额
     */
    private String balance;

    /**
     * 归属省份
     */
    private String province;

    public CreditCarrierBaseVo(SaasCreditCarrierBaseVo saasCreditCarrierBaseVo) {
        if (saasCreditCarrierBaseVo != null) {
            this.name = saasCreditCarrierBaseVo.getName();
            this.identityNo = saasCreditCarrierBaseVo.getIdentityNo();
            this.mobile = saasCreditCarrierBaseVo.getMobile();
            this.planName = saasCreditCarrierBaseVo.getPlanName();
            if (null != saasCreditCarrierBaseVo.getRegisterTime()) {
                this.registerTime = DateUtil.getDate(saasCreditCarrierBaseVo.getRegisterTime(), "yyyy-MM-dd");
                Integer years = DateUtil.getCurrentYear() - Integer.valueOf(DateUtil.getYears(saasCreditCarrierBaseVo.getRegisterTime()));
                Integer months = Integer.valueOf(DateUtil.getMonthS(new Date())) - Integer.valueOf((DateUtil.getMonthS(saasCreditCarrierBaseVo.getRegisterTime())));
                if (months < 0) {
                    years = years - 1;
                    months = 12 + months;
                }
                this.usageTime = "使用时长" + (years > 0 ? (years + "年") : "") + (months > 0 ? months : 1) + "个月";
            }
            if (null != saasCreditCarrierBaseVo.getBalance()) {
                balance = StrUtils.doubleDecimalStrValue(saasCreditCarrierBaseVo.getBalance().doubleValue()) + "元";
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getUsageTime() {
        return usageTime;
    }

    public void setUsageTime(String usageTime) {
        this.usageTime = usageTime;
    }

    public String getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(String carrierType) {
        this.carrierType = carrierType;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
