package com.beitu.saas.app.application.credit.async;

import com.beitu.saas.common.handle.carrier.CarrierData;
import com.beitu.saas.common.handle.carrier.CarrierHandler;
import com.beitu.saas.common.handle.carrier.domain.CarriersBillVo;
import com.beitu.saas.common.handle.carrier.domain.CarriersPhoneCallMonthVo;
import com.beitu.saas.common.handle.carrier.domain.CarriersPhoneCallVo;
import com.beitu.saas.common.handle.carrier.domain.CarriersVo;
import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.common.utils.DecimalUtils;
import com.beitu.saas.credit.client.*;
import com.beitu.saas.credit.domain.*;
import com.beitu.saas.credit.entity.SaasCreditCarrierExt;
import com.beitu.saas.credit.enums.CreditCarrierRecordTypeEnum;
import com.beitu.saas.credit.enums.CreditErrorCodeEnum;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.exception.common.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author linanjun
 * @create 2018/4/6 下午8:12
 * @description
 */
@Service
public class CarrierAsyncApplication {

    @Autowired
    private SaasCreditCarrierService saasCreditCarrierService;

    @Autowired
    private SaasCreditCarrierBaseService saasCreditCarrierBaseService;

    @Autowired
    private SaasCreditCarrierExtService saasCreditCarrierExtService;

    @Autowired
    private SaasCreditCarrierBillService saasCreditCarrierBillService;

    @Autowired
    private SaasCreditCarrierRecordService saasCreditCarrierRecordService;

    @Autowired
    private CarrierHandler carrierHandler;

    @Autowired
    private ServletContext servletContext;

    @Transactional(rollbackFor = RuntimeException.class)
    public void generateCarrierReport(String merchantCode, String borrowerCode) {
        SaasCreditCarrierVo saasCreditCarrierVo = saasCreditCarrierService.getByMerchantCodeAndBorrowerCode(merchantCode, borrowerCode);
        if (saasCreditCarrierVo == null) {
            throw new ApplicationException(CreditErrorCodeEnum.CREDIT_CONTACT_CARRIER_DATA_LACK);
        }
        if (saasCreditCarrierVo.getSuccess()) {
            return;
        }
        Long recordId = saasCreditCarrierVo.getSaasCreditCarrierId();
        CarriersVo carriersVo = carrierHandler.getCarriersVo(saasCreditCarrierVo.getUrl());

        generateCarrierBaseReport(carriersVo, recordId);
        generateCarrierBillReport(carriersVo, recordId);
        generateCarrierExtReport(carriersVo, recordId);
        generateCarrierRecordReport(carriersVo, recordId);

        saasCreditCarrierService.updateSuccess(recordId);
    }

    private void generateCarrierBaseReport(CarriersVo carriersVo, Long recordId) {
        SaasCreditCarrierBaseVo creditCarrierBaseVo = getSaasCreditCarrierBaseVo(carriersVo);
        creditCarrierBaseVo.setRecordId(recordId);
        saasCreditCarrierBaseService.addSaasCreditCarrierBase(creditCarrierBaseVo);
    }

    private void generateCarrierBillReport(CarriersVo carriersVo, Long recordId) {
        Map<String, SaasCreditCarrierBillVo> creditCarrierBillMap = new HashMap<>(8);
        if (CollectionUtils.isNotEmpty(carriersVo.getCarriersBillVoList())) {
            for (CarriersBillVo carriersBillVo : carriersVo.getCarriersBillVoList()) {
                if (StringUtils.isNotEmpty(carriersBillVo.getTotalFee())) {
                    SaasCreditCarrierBillVo creditCarrierBillVo = getSaasCreditCarrierBillVo(carriersBillVo);
                    String key = DateUtil.getDate(creditCarrierBillVo.getBillDate(), "yyyy-MM");
                    creditCarrierBillMap.put(key, creditCarrierBillVo);
                }
            }
        }
        if (CollectionUtils.isNotEmpty(carriersVo.getCarrierMap().values())) {
            for (CarriersPhoneCallMonthVo carriersPhoneCallMonthVo : carriersVo.getCarrierMap().values()) {
                SaasCreditCarrierBillVo creditCarrierBillVo = creditCarrierBillMap.get(carriersPhoneCallMonthVo.getMonth());
                if (creditCarrierBillVo == null) {
                    creditCarrierBillVo = new SaasCreditCarrierBillVo();
                    String key = carriersPhoneCallMonthVo.getMonth();
                    creditCarrierBillVo.setBillDate(DateUtil.getDate(key, "yyyy-MM"));
                    creditCarrierBillMap.put(key, creditCarrierBillVo);
                }
                creditCarrierBillVo.setCallingTime(carriersPhoneCallMonthVo.getCalling());
                creditCarrierBillVo.setCallingDuration(StringUtils.isEmpty(carriersPhoneCallMonthVo.getCallingTime()) ? 0 : Integer.valueOf(carriersPhoneCallMonthVo.getCallingTime()));
                creditCarrierBillVo.setCalledTime(carriersPhoneCallMonthVo.getCalled());
                creditCarrierBillVo.setCalledDuration(StringUtils.isEmpty(carriersPhoneCallMonthVo.getCalledTime()) ? 0 : Integer.valueOf(carriersPhoneCallMonthVo.getCalledTime()));
            }
        }

        List<SaasCreditCarrierBillVo> creditCarrierBillVos = new ArrayList<>(creditCarrierBillMap.values().size());
        if (CollectionUtils.isNotEmpty(creditCarrierBillMap.values())) {
            for (SaasCreditCarrierBillVo creditCarrierBillVo : creditCarrierBillMap.values()) {
                if (creditCarrierBillVo.getBillDate() != null) {
                    creditCarrierBillVo.setRecordId(recordId);
                    creditCarrierBillVos.add(creditCarrierBillVo);
                }
            }
        }
        if (creditCarrierBillVos.size() > 1) {
            Collections.sort(creditCarrierBillVos, (o1, o2) -> (o2.getBillDate().compareTo(o1.getBillDate())));
        }
        saasCreditCarrierBillService.batchAddSaasCreditCarrierBill(creditCarrierBillVos);
    }

    private void generateCarrierExtReport(CarriersVo carriersVo, Long recordId) {
        SaasCreditCarrierExtVo saasCreditCarrierExtVo = getSaasCreditCarrierExtVo(carriersVo);
        saasCreditCarrierExtVo.setRecordId(recordId);
        saasCreditCarrierExtService.addSaasCreditCarrierExt(saasCreditCarrierExtVo);
    }

    private void generateCarrierRecordReport(CarriersVo carriersVo, Long recordId) {
        List<SaasCreditCarrierRecordVo> creditCarrierRecordVos = new ArrayList<>(carriersVo.getTotalPhoneRecords());
        if (CollectionUtils.isNotEmpty(carriersVo.getHighFrequencyDeviceContactList())) {
            for (CarriersPhoneCallVo carriersPhoneCallVo : carriersVo.getHighFrequencyDeviceContactList()) {
                String phone = carriersPhoneCallVo.getPeernumber();
                SaasCreditCarrierRecordVo creditCarrierRecordVo = getSaasCreditCarrierRecordVo(carriersPhoneCallVo, CreditCarrierRecordTypeEnum.HIGH_FREQ, recordId);
                creditCarrierRecordVo.setLocation(getLocationByPhone(phone));
                creditCarrierRecordVos.add(creditCarrierRecordVo);
            }
        }
        if (CollectionUtils.isNotEmpty(carriersVo.getActiveRegionPhoneCallVoList())) {
            for (CarriersPhoneCallVo carriersPhoneCallVo : carriersVo.getActiveRegionPhoneCallVoList()) {
                SaasCreditCarrierRecordVo creditCarrierRecordVo = getSaasCreditCarrierRecordVo(carriersPhoneCallVo, CreditCarrierRecordTypeEnum.ACTIVE_REGION, recordId);
                creditCarrierRecordVos.add(creditCarrierRecordVo);
            }
        }
        if (CollectionUtils.isNotEmpty(carriersVo.getLongCallDurationContactVoList())) {
            for (CarriersPhoneCallVo carriersPhoneCallVo : carriersVo.getLongCallDurationContactVoList()) {
                SaasCreditCarrierRecordVo creditCarrierRecordVo = getSaasCreditCarrierRecordVo(carriersPhoneCallVo, CreditCarrierRecordTypeEnum.LONG_DURATION, recordId);
                creditCarrierRecordVos.add(creditCarrierRecordVo);
            }
        }
        if (CollectionUtils.isNotEmpty(carriersVo.getContactRegionPhoneCallVoList())) {
            for (CarriersPhoneCallVo carriersPhoneCallVo : carriersVo.getContactRegionPhoneCallVoList()) {
                SaasCreditCarrierRecordVo creditCarrierRecordVo = getSaasCreditCarrierRecordVo(carriersPhoneCallVo, CreditCarrierRecordTypeEnum.CONTACT_REGION, recordId);
                creditCarrierRecordVos.add(creditCarrierRecordVo);
            }
        }
        saasCreditCarrierRecordService.batchAddSaasCreditCarrierRecord(creditCarrierRecordVos);
    }

    private SaasCreditCarrierBillVo getSaasCreditCarrierBillVo(CarriersBillVo carriersBillVo) {
        SaasCreditCarrierBillVo creditCarrierBillVo = new SaasCreditCarrierBillVo();
        if (carriersBillVo.getBaseFee() != null) {
            creditCarrierBillVo.setBaseFee(DecimalUtils.round(Double.valueOf(carriersBillVo.getBaseFee()).doubleValue(), 2));
        }

        if (carriersBillVo.getTotalFee() != null) {
            creditCarrierBillVo.setTotalFee(DecimalUtils.round(Double.valueOf(carriersBillVo.getTotalFee()).doubleValue(), 2));
        }

        if (carriersBillVo.getBillDate() != null) {
            Date billDate = DateUtil.getDate(carriersBillVo.getBillDate(), "yyyy-MM-dd HH:mm:ss");
            if (billDate == null) {
                billDate = DateUtil.getDate(carriersBillVo.getBillDate(), "yyyy-MM");
            }
            creditCarrierBillVo.setBillDate(billDate);
        }

        return creditCarrierBillVo;
    }

    private SaasCreditCarrierBaseVo getSaasCreditCarrierBaseVo(CarriersVo carriersVo) {
        SaasCreditCarrierBaseVo creditCarrierBaseVo = new SaasCreditCarrierBaseVo();
        if (carriersVo == null) {
            return creditCarrierBaseVo;
        } else {
            creditCarrierBaseVo.setCarrierType(carriersVo.getCarrierType());
            creditCarrierBaseVo.setMobile(carriersVo.getMobile());
            creditCarrierBaseVo.setBalance(BigDecimal.valueOf(carriersVo.getAvailablebalance() == null ? 0.0D : (double) carriersVo.getAvailablebalance().floatValue()));
            creditCarrierBaseVo.setName(carriersVo.getRealName());
            creditCarrierBaseVo.setIdentityNo(carriersVo.getIdNumber());
            creditCarrierBaseVo.setProvince(carriersVo.getProvince());
            creditCarrierBaseVo.setPlanName(carriersVo.getPriceplanname());
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(carriersVo.getOpentime())) {
                Date registerTime = null;
                if (carriersVo.getOpentime().length() > 10) {
                    registerTime = DateUtil.getDate(carriersVo.getOpentime().substring(0, 19), "yyyy-MM-dd HH:mm:ss");
                }

                if (registerTime == null) {
                    registerTime = DateUtil.getDate(carriersVo.getOpentime(), "yyyy-MM-dd");
                }

                creditCarrierBaseVo.setRegisterTime(registerTime);
            }
            return creditCarrierBaseVo;
        }
    }

    private SaasCreditCarrierExtVo getSaasCreditCarrierExtVo(CarriersVo carriersVo) {
        SaasCreditCarrierExtVo creditCarrierExtVo = new SaasCreditCarrierExtVo();
        creditCarrierExtVo.setActiveRegion(carriersVo.getActiveRegion() != null ? carriersVo.getActiveRegion() : "未知");
        creditCarrierExtVo.setRegisterRegion(carriersVo.getRegisterRegion() != null ? carriersVo.getRegisterRegion() : "未知");
        creditCarrierExtVo.setMostContactRegion(carriersVo.getMostContactRegion() != null ? carriersVo.getMostContactRegion() : "未知");
        creditCarrierExtVo.setInteractionCount(carriersVo.getInteractionContactCnt());
        creditCarrierExtVo.setNightDuration(carriersVo.getNightCallDuration());
        creditCarrierExtVo.setTotalDuration(carriersVo.getTotalCallDuration());
        return creditCarrierExtVo;
    }

    private SaasCreditCarrierRecordVo getSaasCreditCarrierRecordVo(CarriersPhoneCallVo carriersPhoneCallVo, CreditCarrierRecordTypeEnum creditCarrierRecordTypeEnum, Long recordId) {
        SaasCreditCarrierRecordVo creditCarrierRecordVo = new SaasCreditCarrierRecordVo();
        if (CreditCarrierRecordTypeEnum.HIGH_FREQ.equals(creditCarrierRecordTypeEnum) ||
                CreditCarrierRecordTypeEnum.LONG_DURATION.equals(creditCarrierRecordTypeEnum) ||
                CreditCarrierRecordTypeEnum.CONTACT_REGION.equals(creditCarrierRecordTypeEnum)) {
            creditCarrierRecordVo.setPhone(carriersPhoneCallVo.getPeernumber());
        }

        if (CreditCarrierRecordTypeEnum.MERCHANT.equals(creditCarrierRecordTypeEnum)) {
            creditCarrierRecordVo.setPhone(carriersPhoneCallVo.getPeernumber());
            creditCarrierRecordVo.setMerchant(carriersPhoneCallVo.getPeercarrier());
        }

        if (CreditCarrierRecordTypeEnum.ACTIVE_REGION.equals(creditCarrierRecordTypeEnum) ||
                CreditCarrierRecordTypeEnum.LONG_DURATION.equals(creditCarrierRecordTypeEnum) ||
                CreditCarrierRecordTypeEnum.CONTACT_REGION.equals(creditCarrierRecordTypeEnum)) {
            String location = "无";
            if (StringUtils.isNotEmpty(carriersPhoneCallVo.getLocation())) {
                location = carriersPhoneCallVo.getLocation();
            }
            creditCarrierRecordVo.setLocation(location);
        }
        creditCarrierRecordVo.setRecordId(recordId);
        creditCarrierRecordVo.setTotalDuration(carriersPhoneCallVo.getCallTime() == null ? 0 : Integer.valueOf(carriersPhoneCallVo.getCallTime()));
        creditCarrierRecordVo.setCallingTime(carriersPhoneCallVo.getCalling() == null ? 0 : carriersPhoneCallVo.getCalling());
        creditCarrierRecordVo.setCallingDuration(carriersPhoneCallVo.getCallingTime() == null ? 0 : Integer.valueOf(carriersPhoneCallVo.getCallingTime()));
        creditCarrierRecordVo.setCalledTime(carriersPhoneCallVo.getCalled() == null ? 0 : carriersPhoneCallVo.getCalled());
        creditCarrierRecordVo.setCalledDuration(carriersPhoneCallVo.getCalledTime() == null ? 0 : Integer.valueOf(carriersPhoneCallVo.getCalledTime()));
        creditCarrierRecordVo.setType(creditCarrierRecordTypeEnum.getType());
        return creditCarrierRecordVo;
    }

    public String getLocationByPhone(String phone) {
        Map<String, String> phoneBookMap = null;
        if (this.servletContext != null) {
            phoneBookMap = (Map) this.servletContext.getAttribute("phoneBookMap");
        }
        String location = CarrierData.getLocation(phone, phoneBookMap);
        if (StringUtils.isEmpty(location)) {
            location = "无";
        }
        return location;
    }

}
