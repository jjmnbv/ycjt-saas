package com.beitu.saas.app.application.credit.vo;

import com.fqgj.common.api.ResponseData;

import java.util.List;

/**
 * Created by linchengyu on 17/6/23.
 */
public class CreditCarrierReportVo implements ResponseData {

    /**
     * 运营商认证信息
     */
    private CreditCarrierBaseVo creditCarrierBaseVo;

    /**
     * 号码总览
     */
    private CreditCarrierOverviewVo creditCarrierOverviewVo;

    /**
     * 催收数据
     */
    private CreditDunningDataVo dunningInfo;
    /**
     * 金融敏感信息
     */
    private List<CreditCarrierFinancialListVo> financialSensitive;
    /**
     * 高频联系人
     */
    private List<CreditCarrierHighFreqListVo> highFreq;
    /**
     * 长时间联系人
     */
    private List<CreditCarrierLongDurationListVo> longDuration;
    /**
     * 商户通话记录
     */
    private List<CreditCarrierMerchantListVo> merchant;

    /**
     * 通话活跃区域
     */
    private List<CreditCarrierActiveRegionListVo> activeRegion;
    /**
     * 联系人所在地
     */
    private List<CreditCarrierContactRegionListVo> contactRegion;
    /**
     * 月通话情况
     */
    private List<CreditCarrierBillListVo> billList;

    public CreditCarrierBaseVo getCreditCarrierBaseVo() {
        return creditCarrierBaseVo;
    }

    public void setCreditCarrierBaseVo(CreditCarrierBaseVo creditCarrierBaseVo) {
        this.creditCarrierBaseVo = creditCarrierBaseVo;
    }

    public CreditCarrierOverviewVo getCreditCarrierOverviewVo() {
        return creditCarrierOverviewVo;
    }

    public void setCreditCarrierOverviewVo(CreditCarrierOverviewVo creditCarrierOverviewVo) {
        this.creditCarrierOverviewVo = creditCarrierOverviewVo;
    }

    public CreditDunningDataVo getDunningInfo() {
        return dunningInfo;
    }

    public void setDunningInfo(CreditDunningDataVo dunningInfo) {
        this.dunningInfo = dunningInfo;
    }

    public List<CreditCarrierFinancialListVo> getFinancialSensitive() {
        return financialSensitive;
    }

    public void setFinancialSensitive(List<CreditCarrierFinancialListVo> financialSensitive) {
        this.financialSensitive = financialSensitive;
    }

    public List<CreditCarrierHighFreqListVo> getHighFreq() {
        return highFreq;
    }

    public void setHighFreq(List<CreditCarrierHighFreqListVo> highFreq) {
        this.highFreq = highFreq;
    }

    public List<CreditCarrierLongDurationListVo> getLongDuration() {
        return longDuration;
    }

    public void setLongDuration(List<CreditCarrierLongDurationListVo> longDuration) {
        this.longDuration = longDuration;
    }

    public List<CreditCarrierMerchantListVo> getMerchant() {
        return merchant;
    }

    public void setMerchant(List<CreditCarrierMerchantListVo> merchant) {
        this.merchant = merchant;
    }

    public List<CreditCarrierActiveRegionListVo> getActiveRegion() {
        return activeRegion;
    }

    public void setActiveRegion(List<CreditCarrierActiveRegionListVo> activeRegion) {
        this.activeRegion = activeRegion;
    }

    public List<CreditCarrierContactRegionListVo> getContactRegion() {
        return contactRegion;
    }

    public void setContactRegion(List<CreditCarrierContactRegionListVo> contactRegion) {
        this.contactRegion = contactRegion;
    }

    public List<CreditCarrierBillListVo> getBillList() {
        return billList;
    }

    public void setBillList(List<CreditCarrierBillListVo> billList) {
        this.billList = billList;
    }

}
