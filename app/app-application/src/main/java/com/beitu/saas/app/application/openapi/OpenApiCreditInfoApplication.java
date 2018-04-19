package com.beitu.saas.app.application.openapi;

import com.beitu.saas.app.application.openapi.vo.*;
import com.beitu.saas.borrower.client.SaasBorrowerContactInfoService;
import com.beitu.saas.borrower.client.SaasBorrowerGpsLogService;
import com.beitu.saas.borrower.domain.SaasBorrowerContactInfoVo;
import com.beitu.saas.borrower.domain.SaasBorrowerGpsLogVo;
import com.beitu.saas.credit.client.*;
import com.beitu.saas.credit.domain.*;
import com.beitu.saas.credit.entity.SaasCreditBmp;
import com.beitu.saas.credit.entity.SaasCreditCarrier;
import com.beitu.saas.credit.entity.SaasCreditDunning;
import com.beitu.saas.credit.entity.SaasCreditTongdun;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class OpenApiCreditInfoApplication {
    
    private static final Log LOGGER = LogFactory.getLog(OpenApiCreditInfoApplication.class);
    
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
    private SaasCreditDunningService saasCreditDunningService;
    
    @Autowired
    private SaasCreditDunningDetailService saasCreditDunningDetailService;
    
    @Autowired
    private SaasCreditBmpService saasCreditBmpService;
    
    @Autowired
    private SaasCreditBmpDetailService saasCreditBmpDetailService;
    
    @Autowired
    private SaasCreditTongdunService saasCreditTongdunService;
    
    @Autowired
    private SaasCreditTongdunDetailService saasCreditTongdunDetailService;
    
    @Autowired
    private SaasBorrowerGpsLogService saasBorrowerGpsLogService;
    
    @Autowired
    private SaasBorrowerContactInfoService saasBorrowerContactInfoService;
    
    @Transactional
    public Long addCreditCarrierInfo(OrderPushUserCarrierInfoVo carrierInfo, String borrowerCode, String merchantCode) {
        Long carrierId = null;
        if (carrierInfo != null) {
            SaasCreditCarrierVo saasCreditCarrierVo = new SaasCreditCarrierVo();
            BeanUtils.copyProperties(carrierInfo, saasCreditCarrierVo);
            saasCreditCarrierVo.setBorrowerCode(borrowerCode);
            saasCreditCarrierVo.setMerchantCode(merchantCode);
            saasCreditCarrierVo.setSuccess(Boolean.TRUE);
            SaasCreditCarrier saasCreditCarrier = saasCreditCarrierService.addSaasCreditCarrier(saasCreditCarrierVo);
            if (saasCreditCarrier != null) {
                carrierId = saasCreditCarrier.getId();
            }
        }
        OrderPushUserCarrierBaseVo carrierBase = carrierInfo.getBase();
        if (carrierId != null && carrierBase != null) {
            SaasCreditCarrierBaseVo creditCarrierBaseVo = new SaasCreditCarrierBaseVo();
            BeanUtils.copyProperties(carrierBase, creditCarrierBaseVo);
            creditCarrierBaseVo.setRecordId(carrierId);
            saasCreditCarrierBaseService.addSaasCreditCarrierBase(creditCarrierBaseVo);
        }
        OrderPushUserCarrierExtVo carrierExt = carrierInfo.getExt();
        if (carrierId != null && carrierExt != null) {
            SaasCreditCarrierExtVo saasCreditCarrierExtVo = new SaasCreditCarrierExtVo();
            BeanUtils.copyProperties(carrierExt, saasCreditCarrierExtVo);
            saasCreditCarrierExtVo.setRecordId(carrierId);
            saasCreditCarrierExtService.addSaasCreditCarrierExt(saasCreditCarrierExtVo);
        }
        List<OrderPushUserCarrierBillVo> carrierBills = carrierInfo.getBills();
        if (carrierId != null && CollectionUtils.isNotEmpty(carrierBills)) {
            List<SaasCreditCarrierBillVo> creditCarrierBillVos = new ArrayList<>(carrierBills.size());
            for (OrderPushUserCarrierBillVo carrierBill : carrierBills) {
                SaasCreditCarrierBillVo saasCreditCarrierBillVo = new SaasCreditCarrierBillVo();
                BeanUtils.copyProperties(carrierBill, saasCreditCarrierBillVo);
                saasCreditCarrierBillVo.setRecordId(carrierId);
                creditCarrierBillVos.add(saasCreditCarrierBillVo);
            }
            if (creditCarrierBillVos.size() > 1) {
                Collections.sort(creditCarrierBillVos, (o1, o2) -> (o2.getBillDate().compareTo(o1.getBillDate())));
            }
            saasCreditCarrierBillService.batchAddSaasCreditCarrierBill(creditCarrierBillVos);
        }
        List<OrderPushUserCarrierRecordVo> carrierRecords = carrierInfo.getRecords();
        if (carrierId != null && CollectionUtils.isNotEmpty(carrierRecords)) {
            List<SaasCreditCarrierRecordVo> creditCarrierRecordVos = new ArrayList<>(carrierRecords.size());
            for (OrderPushUserCarrierRecordVo carrierRecord : carrierRecords) {
                SaasCreditCarrierRecordVo saasCreditCarrierRecordVo = new SaasCreditCarrierRecordVo();
                BeanUtils.copyProperties(carrierRecord, saasCreditCarrierRecordVo);
                saasCreditCarrierRecordVo.setRecordId(carrierId);
                creditCarrierRecordVos.add(saasCreditCarrierRecordVo);
            }
            saasCreditCarrierRecordService.batchAddSaasCreditCarrierRecord(creditCarrierRecordVos);
        }
        return carrierId;
    }
    
    @Transactional
    public void addCreditDunningInfo(OrderPushUserDunningInfoVo dunningInfo, String borrowerCode, String merchantCode, Long carrierId) {
        Long dunningId = null;
        if (dunningInfo != null) {
            SaasCreditDunningVo saasCreditDunningVo = new SaasCreditDunningVo();
            BeanUtils.copyProperties(dunningInfo, saasCreditDunningVo);
            saasCreditDunningVo.setBorrowerCode(borrowerCode);
            saasCreditDunningVo.setMerchantCode(merchantCode);
            saasCreditDunningVo.setCarrierId(carrierId);
            if (carrierId == null) {
                saasCreditDunningVo.setCarrierId(0L);
            }
            saasCreditDunningVo.setSuccess(Boolean.TRUE);
            SaasCreditDunning saasCreditDunning = saasCreditDunningService.addSaasCreditDunning(saasCreditDunningVo);
            if (saasCreditDunning != null) {
                dunningId = saasCreditDunning.getId();
            }
        }
        List<OrderPushUserDunningDetailVo> dunningDetails = dunningInfo.getDetails();
        if (dunningId != null && CollectionUtils.isNotEmpty(dunningDetails)) {
            List<SaasCreditDunningDetailVo> creditDunningDetailVos = new ArrayList<>(dunningDetails.size());
            for (OrderPushUserDunningDetailVo dunningDetail : dunningDetails) {
                SaasCreditDunningDetailVo saasCreditDunningDetailVo = new SaasCreditDunningDetailVo();
                BeanUtils.copyProperties(dunningDetail, saasCreditDunningDetailVo);
                saasCreditDunningDetailVo.setRecordId(dunningId);
                creditDunningDetailVos.add(saasCreditDunningDetailVo);
            }
            saasCreditDunningDetailService.batchAddSaasCreditDunningDetail(creditDunningDetailVos);
        }
    }
    
    @Transactional
    public void addCreditBmpInfo(OrderPushUserBmpInfoVo bmpInfo, String borrowerCode, String merchantCode) {
        Long bmpId = null;
        if (bmpInfo != null) {
            SaasCreditBmpVo saasCreditBmpVo = new SaasCreditBmpVo();
            BeanUtils.copyProperties(bmpInfo, saasCreditBmpVo);
            saasCreditBmpVo.setBorrowerCode(borrowerCode);
            saasCreditBmpVo.setMerchantCode(merchantCode);
            saasCreditBmpVo.setSuccess(Boolean.TRUE);
            SaasCreditBmp saasCreditBmp = saasCreditBmpService.addSaasCreditBmp(saasCreditBmpVo);
            if (saasCreditBmp != null) {
                bmpId = saasCreditBmp.getId();
            }
        }
        List<OrderPushUserBmpDetailVo> bmpDetails = bmpInfo.getDetails();
        if (bmpId != null && CollectionUtils.isNotEmpty(bmpDetails)) {
            List<SaasCreditBmpDetailVo> creditBmpDetailVos = new ArrayList<>(bmpDetails.size());
            for (OrderPushUserBmpDetailVo bmpDetail : bmpDetails) {
                SaasCreditBmpDetailVo saasCreditBmpDetailVo = new SaasCreditBmpDetailVo();
                BeanUtils.copyProperties(bmpDetail, saasCreditBmpDetailVo);
                saasCreditBmpDetailVo.setRecordId(bmpId);
                creditBmpDetailVos.add(saasCreditBmpDetailVo);
            }
            saasCreditBmpDetailService.batchAddSaasCreditBmpDetailVo(creditBmpDetailVos);
        }
    }
    
    @Transactional
    public void addCreditTongdunInfo(OrderPushUserTongdunInfoVo tongdunInfo, String borrowerCode, String merchantCode) {
        Long tongdunId = null;
        if (tongdunInfo != null) {
            SaasCreditTongdunVo saasCreditTongdunVo = new SaasCreditTongdunVo();
            BeanUtils.copyProperties(tongdunInfo, saasCreditTongdunVo);
            saasCreditTongdunVo.setBorrowerCode(borrowerCode);
            saasCreditTongdunVo.setMerchantCode(merchantCode);
            saasCreditTongdunVo.setSuccess(Boolean.TRUE);
            SaasCreditTongdun saasCreditTongdun = saasCreditTongdunService.addSaasCreditTongdun(saasCreditTongdunVo);
            if (saasCreditTongdun != null) {
                tongdunId = saasCreditTongdun.getId();
            }
        }
        OrderPushUserTongdunDetailVo tongdunDetail = tongdunInfo.getDetail();
        if (tongdunId != null && tongdunDetail != null) {
            SaasCreditTongdunDetailVo saasCreditTongdunDetailVo = new SaasCreditTongdunDetailVo();
            BeanUtils.copyProperties(tongdunDetail, saasCreditTongdunDetailVo);
            saasCreditTongdunDetailVo.setRecordId(tongdunId);
            saasCreditTongdunDetailService.addSaasCreditTongdunDetail(saasCreditTongdunDetailVo);
        }
    }
    
    @Transactional
    public void addLocationsInfo(List<OrderPushUserLocationInfoVo> locationsInfo, String borrowerCode, String merchantCode) {
        if (CollectionUtils.isEmpty(locationsInfo)) {
            return;
        }
        Date lastDate = saasBorrowerGpsLogService.getLastDateByBorrowerCodeAndMerchantCode(merchantCode, borrowerCode);
        List<SaasBorrowerGpsLogVo> addList = new ArrayList<>(locationsInfo.size());
        locationsInfo.forEach(object -> {
            Date logTime = object.getGmtCreate();
            if (lastDate == null || (logTime != null && logTime.after(lastDate))) {
                SaasBorrowerGpsLogVo gpsLogVo = new SaasBorrowerGpsLogVo();
                BeanUtils.copyProperties(object, gpsLogVo);
                gpsLogVo.setMerchantCode(merchantCode);
                gpsLogVo.setBorrowerCode(borrowerCode);
                gpsLogVo.setLogTime(logTime);
                addList.add(gpsLogVo);
            }
        });
        saasBorrowerGpsLogService.batchAddSaasBorrowerGpsLogVo(addList);
    }
    
    public void addContactsInfo(OrderPushUserContactsInfoVo contactsInfo, String borrowerCode, String merchantCode) {
        if (contactsInfo == null) {
            return;
        }
        SaasBorrowerContactInfoVo vo = new SaasBorrowerContactInfoVo(merchantCode, borrowerCode, contactsInfo.getUrl());
        saasBorrowerContactInfoService.addContactInfo(vo);
    }
    
}
