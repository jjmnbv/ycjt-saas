package com.beitu.saas.app.application.credit;

import com.beitu.saas.app.application.credit.async.CarrierAsyncApplication;
import com.beitu.saas.app.application.credit.vo.*;
import com.beitu.saas.app.enums.CarrierStatusEnum;
import com.beitu.saas.common.utils.ThreadPoolUtils;
import com.beitu.saas.credit.client.*;
import com.beitu.saas.credit.domain.*;
import com.beitu.saas.credit.enums.CreditBmpDetailTagEnum;
import com.beitu.saas.credit.enums.CreditCarrierRecordTypeEnum;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author linanjun
 * @create 2018/4/6 下午8:12
 * @description
 */
@Service
public class CarrierReportApplication {

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
    private CarrierAsyncApplication carrierAsyncApplication;

    @Autowired
    private SaasCreditDunningService saasCreditDunningService;

    @Autowired
    private SaasCreditDunningDetailService saasCreditDunningDetailService;

    @Autowired
    private SaasCreditBmpService saasCreditBmpService;

    @Autowired
    private SaasCreditBmpDetailService saasCreditBmpDetailService;

    public CreditCarrierReportVo getCarrierReportByMerchantCodeAndBorrowerCode(String merchantCode, String borrowerCode) {
        CreditCarrierReportVo creditCarrierReportVo = new CreditCarrierReportVo();
        SaasCreditCarrierVo saasCreditCarrierVo = saasCreditCarrierService.getByMerchantCodeAndBorrowerCode(merchantCode, borrowerCode);
        if (saasCreditCarrierVo == null) {
            creditCarrierReportVo.setStatus(CarrierStatusEnum.UNAUTHORIZED.getCode());
            return creditCarrierReportVo;
        }
        if (!saasCreditCarrierVo.getSuccess()) {
            creditCarrierReportVo.setStatus(CarrierStatusEnum.FAILURE.getCode());
            return creditCarrierReportVo;
        }
        Long recordId = saasCreditCarrierVo.getSaasCreditCarrierId();

        SaasCreditCarrierBaseVo saasCreditCarrierBaseVo = saasCreditCarrierBaseService.getByRecordId(recordId);
        creditCarrierReportVo.setCreditCarrierBaseVo(new CreditCarrierBaseVo(saasCreditCarrierBaseVo));

        SaasCreditCarrierExtVo saasCreditCarrierExtVo = saasCreditCarrierExtService.getByRecordId(recordId);
        creditCarrierReportVo.setCreditCarrierOverviewVo(new CreditCarrierOverviewVo(saasCreditCarrierExtVo));

        SaasCreditDunningVo creditDunningVo = saasCreditDunningService.getByMerchantCodeAndBorrowerCode(merchantCode, borrowerCode);
        if (creditDunningVo != null && Boolean.TRUE.equals(creditDunningVo.getSuccess())) {
            Long dunningId = creditDunningVo.getSaasCreditDunningId();
            List<SaasCreditDunningDetailVo> saasCreditDunningDetailVoList = saasCreditDunningDetailService.listByRecordId(dunningId, null);
            creditCarrierReportVo.setDunningInfo(new CreditDunningDataVo(saasCreditDunningDetailVoList));
        }

        List<SaasCreditCarrierRecordVo> financialList = saasCreditCarrierRecordService.listByRecordIdAndRecordTypeEnum(recordId, CreditCarrierRecordTypeEnum.FINANCIAL);
        if (CollectionUtils.isNotEmpty(financialList)) {
            List<CreditCarrierFinancialListVo> creditCarrierFinancialListVoList = new ArrayList<>(financialList.size());
            financialList.forEach(saasCreditCarrierRecordVo -> creditCarrierFinancialListVoList.add(new CreditCarrierFinancialListVo(saasCreditCarrierRecordVo)));
            creditCarrierReportVo.setFinancialSensitive(creditCarrierFinancialListVoList);
        }

        final List<CreditBmpMatchVo> creditBmpMatchVos = new ArrayList<>();
        SaasCreditBmpVo creditBmpVo = saasCreditBmpService.getByMerchantCodeAndBorrowerCode(merchantCode, borrowerCode);
        if (creditBmpVo != null && Boolean.TRUE.equals(creditBmpVo.getSuccess())) {
            Long bmpId = creditBmpVo.getSaasCreditBmpId();
            List<SaasCreditBmpDetailVo> creditBmpDetailVoList = saasCreditBmpDetailService.listByRecordId(bmpId);
            getMatchList(creditBmpDetailVoList, creditBmpMatchVos);
        }
        List<SaasCreditCarrierRecordVo> highFreqList = saasCreditCarrierRecordService.listByRecordIdAndRecordTypeEnum(recordId, CreditCarrierRecordTypeEnum.HIGH_FREQ);
        if (CollectionUtils.isNotEmpty(highFreqList)) {
            List<CreditCarrierHighFreqListVo> creditCarrierHighFreqListVoList = new ArrayList<>(highFreqList.size());
            highFreqList.forEach(saasCreditCarrierRecordVo -> creditCarrierHighFreqListVoList.add(new CreditCarrierHighFreqListVo(saasCreditCarrierRecordVo, creditBmpMatchVos)));
            creditCarrierReportVo.setHighFreq(creditCarrierHighFreqListVoList);
        }

        List<SaasCreditCarrierRecordVo> longDurationList = saasCreditCarrierRecordService.listByRecordIdAndRecordTypeEnum(recordId, CreditCarrierRecordTypeEnum.LONG_DURATION);
        if (CollectionUtils.isNotEmpty(longDurationList)) {
            List<CreditCarrierLongDurationListVo> creditCarrierLongDurationListVoList = new ArrayList<>(longDurationList.size());
            highFreqList.forEach(saasCreditCarrierRecordVo -> creditCarrierLongDurationListVoList.add(new CreditCarrierLongDurationListVo(saasCreditCarrierRecordVo, creditBmpMatchVos)));
            creditCarrierReportVo.setLongDuration(creditCarrierLongDurationListVoList);
        }

        List<SaasCreditCarrierRecordVo> merchantList = saasCreditCarrierRecordService.listByRecordIdAndRecordTypeEnum(recordId, CreditCarrierRecordTypeEnum.MERCHANT);
        if (CollectionUtils.isNotEmpty(merchantList)) {
            List<CreditCarrierMerchantListVo> creditCarrierMerchantListVoList = new ArrayList<>(merchantList.size());
            merchantList.forEach(saasCreditCarrierRecordVo -> creditCarrierMerchantListVoList.add(new CreditCarrierMerchantListVo(saasCreditCarrierRecordVo)));
            creditCarrierReportVo.setMerchant(creditCarrierMerchantListVoList);
        }

        List<SaasCreditCarrierRecordVo> activeRegionList = saasCreditCarrierRecordService.listByRecordIdAndRecordTypeEnum(recordId, CreditCarrierRecordTypeEnum.ACTIVE_REGION);
        if (CollectionUtils.isNotEmpty(activeRegionList)) {
            List<CreditCarrierActiveRegionListVo> creditCarrierActiveRegionListVoList = new ArrayList<>(activeRegionList.size());
            activeRegionList.forEach(saasCreditCarrierRecordVo -> creditCarrierActiveRegionListVoList.add(new CreditCarrierActiveRegionListVo(saasCreditCarrierRecordVo, saasCreditCarrierExtVo != null ? saasCreditCarrierExtVo.getTotalDuration() : 0)));
            creditCarrierReportVo.setActiveRegion(creditCarrierActiveRegionListVoList);
        }

        List<SaasCreditCarrierRecordVo> contactRegionList = saasCreditCarrierRecordService.listByRecordIdAndRecordTypeEnum(recordId, CreditCarrierRecordTypeEnum.CONTACT_REGION);
        if (CollectionUtils.isNotEmpty(contactRegionList)) {
            List<CreditCarrierContactRegionListVo> creditCarrierContactRegionListVoList = new ArrayList<>(contactRegionList.size());
            contactRegionList.forEach(saasCreditCarrierRecordVo -> creditCarrierContactRegionListVoList.add(new CreditCarrierContactRegionListVo(saasCreditCarrierRecordVo, saasCreditCarrierExtVo != null ? saasCreditCarrierExtVo.getTotalDuration() : 0)));
            creditCarrierReportVo.setContactRegion(creditCarrierContactRegionListVoList);
        }

        List<SaasCreditCarrierBillVo> billVoList = saasCreditCarrierBillService.listByRecordId(recordId);
        if (CollectionUtils.isNotEmpty(billVoList)) {
            List<CreditCarrierBillListVo> creditCarrierBillListVoList = new ArrayList<>(billVoList.size());
            billVoList.forEach(saasCreditCarrierBillVo -> creditCarrierBillListVoList.add(new CreditCarrierBillListVo(saasCreditCarrierBillVo)));
            creditCarrierReportVo.setBillList(creditCarrierBillListVoList);
        }

        return creditCarrierReportVo;
    }

    public void generateCarrierReport(String merchantCode, String borrowerCode) {
        ThreadPoolUtils.getTaskInstance().submit(() -> carrierAsyncApplication.generateCarrierReport(merchantCode, borrowerCode));
    }

    private void getMatchList(List<SaasCreditBmpDetailVo> creditBmp, List<CreditBmpMatchVo> creditBmpMatchVos) {
        List<CreditBmpDetailTagEnum> concernTags = CreditBmpDetailTagEnum.getConcernTags();
        Map<Integer, String> concernTagsMap = new HashMap<>();
        for (CreditBmpDetailTagEnum tagEnum : concernTags) {
            concernTagsMap.put(tagEnum.getType(), tagEnum.getDesc());
        }
        Map<Integer, CreditBmpMatchVo> matchMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(creditBmp)) {
            for (SaasCreditBmpDetailVo creditBmpDetailVo : creditBmp) {
                if (StringUtils.isNotEmpty(creditBmpDetailVo.getItagIds())) {
                    List<Integer> tags = getTags(creditBmpDetailVo.getItagIds().split(","));
                    for (Integer tagId : tags) {
                        if (concernTagsMap.containsKey(tagId)) {
                            String typeName = concernTagsMap.get(tagId);
                            CreditBmpMatchVo matchVo;
                            if (!matchMap.containsKey(tagId)) {
                                matchVo = new CreditBmpMatchVo(typeName);
                                matchMap.put(tagId, matchVo);
                            } else {
                                matchVo = matchMap.get(tagId);
                            }
                            matchVo.getNumberList().add(creditBmpDetailVo.getTelnum());
                        }
                    }
                }
            }
        }
        for (CreditBmpMatchVo matchVo : matchMap.values()) {
            creditBmpMatchVos.add(matchVo);
        }
    }

    private List<Integer> getTags(String[] tags) {
        List<Integer> tagList = new ArrayList<>();
        if (tags.length == 0) {
            return null;
        }
        for (String tag : tags) {
            tagList.add(Integer.valueOf(tag));
        }
        return tagList;
    }

}
