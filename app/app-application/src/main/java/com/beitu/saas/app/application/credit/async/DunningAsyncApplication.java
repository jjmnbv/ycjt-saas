package com.beitu.saas.app.application.credit.async;

import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.handle.dianhua.DianhuaHandler;
import com.beitu.saas.common.handle.dianhua.domain.DunningDataVo;
import com.beitu.saas.common.handle.dianhua.domain.DunningResultVo;
import com.beitu.saas.common.handle.dianhua.domain.DunningStatisticVo;
import com.beitu.saas.common.handle.oss.OSSService;
import com.beitu.saas.credit.client.SaasCreditCarrierService;
import com.beitu.saas.credit.client.SaasCreditDunningDetailService;
import com.beitu.saas.credit.client.SaasCreditDunningService;
import com.beitu.saas.credit.domain.SaasCreditCarrierVo;
import com.beitu.saas.credit.domain.SaasCreditDunningDetailVo;
import com.beitu.saas.credit.domain.SaasCreditDunningVo;
import com.beitu.saas.credit.enums.CreditDunningDetailTypeEnum;
import com.beitu.saas.credit.enums.CreditErrorCodeEnum;
import com.fqgj.common.utils.JSONUtils;
import com.fqgj.common.utils.MD5;
import com.fqgj.exception.common.ApplicationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jungle
 */
@Service
public class DunningAsyncApplication {

    @Autowired
    private DianhuaHandler dianhuaHandler;

    @Autowired
    private SaasCreditDunningService saasCreditDunningService;

    @Autowired
    private SaasCreditDunningDetailService saasCreditDunningDetailService;

    @Autowired
    private SaasCreditCarrierService saasCreditCarrierService;

    @Autowired
    private OSSService ossService;

    @Autowired
    private ConfigUtil configUtil;

    public void generateDunningReport(String merchantCode, String borrowerCode) {
        SaasCreditCarrierVo saasCreditCarrierVo = saasCreditCarrierService.getByMerchantCodeAndBorrowerCode(merchantCode, borrowerCode);
        if (saasCreditCarrierVo == null) {
            throw new ApplicationException(CreditErrorCodeEnum.CREDIT_CARRIER_DATA_LACK);
        }
        Long recordId = null;
        DunningDataVo dunningDataVo = null;
        SaasCreditDunningVo saasCreditDunningVo = saasCreditDunningService.getByMerchantCodeAndBorrowerCode(merchantCode, borrowerCode);
        if (saasCreditDunningVo == null) {
            String result = dianhuaHandler.getDunningVo(saasCreditCarrierVo.getUrl());
            DunningResultVo resultVo;
            try {
                resultVo = JSONUtils.json2pojoAndOffUnknownField(result, DunningResultVo.class);
            } catch (Exception e) {
                throw new ApplicationException(CreditErrorCodeEnum.CREDIT_DUNNING_GENERATE_ERROR, "电话邦结果JSON序列化失败");
            }
            saasCreditDunningVo = new SaasCreditDunningVo();
            if (resultVo != null && resultVo.getData() != null) {
                saasCreditDunningVo.setMerchantCode(merchantCode);
                saasCreditDunningVo.setBorrowerCode(borrowerCode);
                saasCreditDunningVo.setCarrierId(saasCreditCarrierVo.getSaasCreditCarrierId());
                dunningDataVo = resultVo.getData();
                saasCreditDunningVo.setSid(dunningDataVo.getSid());
                saasCreditDunningVo.setMobile(dunningDataVo.getTel());
                saasCreditDunningVo.setTotalNum(dunningDataVo.getTotalNum());
                saasCreditDunningVo.setEffectiveNum(dunningDataVo.getEffectiveNum());
                saasCreditDunningVo.setSuccess(Boolean.FALSE);
                String url = uploadDunningData(borrowerCode, result);
                saasCreditDunningVo.setUrl(url);
                recordId = saasCreditDunningService.addSaasCreditDunning(saasCreditDunningVo).getId();
            }
        } else if (saasCreditDunningVo.getSuccess()) {
            return;
        } else {
            recordId = saasCreditDunningVo.getSaasCreditDunningId();
            String result = ossService.getFileContent(saasCreditDunningVo.getUrl());
            try {
                DunningResultVo resultVo = JSONUtils.json2pojoAndOffUnknownField(result, DunningResultVo.class);
                dunningDataVo = resultVo.getData();
            } catch (Exception e) {
                throw new ApplicationException(CreditErrorCodeEnum.CREDIT_DUNNING_GENERATE_ERROR, "电话邦结果JSON序列化失败");
            }
        }

        if (recordId == null) {
            throw new ApplicationException(CreditErrorCodeEnum.CREDIT_DUNNING_GENERATE_ERROR, "credit_dunning插入失败");
        }
        if (dunningDataVo != null) {
            List<SaasCreditDunningDetailVo> list = convertDunningData(dunningDataVo, recordId, dunningDataVo.getSid());
            saasCreditDunningDetailService.batchAddSaasCreditDunningDetail(list);
        }
        if (!saasCreditDunningService.updateSuccess(recordId)) {
            throw new ApplicationException(CreditErrorCodeEnum.UPDATE_FAILURE);
        }
    }

    private String uploadDunningData(String borrowerCode, String content) {
        StringBuilder filePath = new StringBuilder("dunningData/");
        if (configUtil.isServerTest()) {
            filePath.append("test/");
        }
        filePath.append(borrowerCode).append("_").append(System.currentTimeMillis())
                .append("_").append(MD5.md5(System.currentTimeMillis() + "")).append(".json");
        return ossService.uploadFile(filePath.toString(), content);
    }

    private List<SaasCreditDunningDetailVo> convertDunningData(DunningDataVo dunningDataVo, Long recordId, String sid) {
        List<SaasCreditDunningDetailVo> saasCreditDunningDetailVoList = new ArrayList<>();
        if (dunningDataVo != null) {
            if (dunningDataVo.getOverview() != null) {
                SaasCreditDunningDetailVo dunning = getSaasCreditDunningDetailVo(dunningDataVo.getOverview().getDunning(), CreditDunningDetailTypeEnum.OVER_VIEW, recordId, sid);
                saasCreditDunningDetailVoList.add(dunning);
                SaasCreditDunningDetailVo notSure = getSaasCreditDunningDetailVo(dunningDataVo.getOverview().getNotSureDunning(), CreditDunningDetailTypeEnum.OVER_VIEW_NOT_SURE, recordId, sid);
                saasCreditDunningDetailVoList.add(notSure);
            }
            if (dunningDataVo.getLastWeek() != null) {
                SaasCreditDunningDetailVo dunning = getSaasCreditDunningDetailVo(dunningDataVo.getLastWeek().getDunning(), CreditDunningDetailTypeEnum.LAST_WEEK, recordId, sid);
                saasCreditDunningDetailVoList.add(dunning);
                SaasCreditDunningDetailVo notSure = getSaasCreditDunningDetailVo(dunningDataVo.getLastWeek().getNotSureDunning(), CreditDunningDetailTypeEnum.LAST_WEEK_NOT_SURE, recordId, sid);
                saasCreditDunningDetailVoList.add(notSure);
            }
            if (dunningDataVo.getLastTwoWeeks() != null) {
                SaasCreditDunningDetailVo dunning = getSaasCreditDunningDetailVo(dunningDataVo.getLastTwoWeeks().getDunning(), CreditDunningDetailTypeEnum.LAST_TWO_WEEK, recordId, sid);
                saasCreditDunningDetailVoList.add(dunning);
                SaasCreditDunningDetailVo notSure = getSaasCreditDunningDetailVo(dunningDataVo.getLastTwoWeeks().getNotSureDunning(), CreditDunningDetailTypeEnum.LAST_TWO_WEEK_NOT_SURE, recordId, sid);
                saasCreditDunningDetailVoList.add(notSure);
            }
            if (dunningDataVo.getLastThreeWeeks() != null) {
                SaasCreditDunningDetailVo dunning = getSaasCreditDunningDetailVo(dunningDataVo.getLastThreeWeeks().getDunning(), CreditDunningDetailTypeEnum.LAST_THREE_WEEK, recordId, sid);
                saasCreditDunningDetailVoList.add(dunning);
                SaasCreditDunningDetailVo notSure = getSaasCreditDunningDetailVo(dunningDataVo.getLastThreeWeeks().getNotSureDunning(), CreditDunningDetailTypeEnum.LAST_THREE_WEEK_NOT_SURE, recordId, sid);
                saasCreditDunningDetailVoList.add(notSure);
            }
            if (dunningDataVo.getLast30Days() != null) {
                SaasCreditDunningDetailVo dunning = getSaasCreditDunningDetailVo(dunningDataVo.getLast30Days().getDunning(), CreditDunningDetailTypeEnum.DAYS_30, recordId, sid);
                saasCreditDunningDetailVoList.add(dunning);
                SaasCreditDunningDetailVo notSure = getSaasCreditDunningDetailVo(dunningDataVo.getLast30Days().getNotSureDunning(), CreditDunningDetailTypeEnum.DAYS_30_NOT_SURE, recordId, sid);
                saasCreditDunningDetailVoList.add(notSure);
            }
            if (dunningDataVo.getLast30And60Days() != null) {
                SaasCreditDunningDetailVo dunning = getSaasCreditDunningDetailVo(dunningDataVo.getLast30And60Days().getDunning(), CreditDunningDetailTypeEnum.DAYS_30_60, recordId, sid);
                saasCreditDunningDetailVoList.add(dunning);
                SaasCreditDunningDetailVo notSure = getSaasCreditDunningDetailVo(dunningDataVo.getLast30And60Days().getNotSureDunning(), CreditDunningDetailTypeEnum.DAYS_30_60_NOT_SURE, recordId, sid);
                saasCreditDunningDetailVoList.add(notSure);
            }
            if (dunningDataVo.getLast60And90Days() != null) {
                SaasCreditDunningDetailVo dunning = getSaasCreditDunningDetailVo(dunningDataVo.getLast60And90Days().getDunning(), CreditDunningDetailTypeEnum.DAYS_60_90, recordId, sid);
                saasCreditDunningDetailVoList.add(dunning);
                SaasCreditDunningDetailVo notSure = getSaasCreditDunningDetailVo(dunningDataVo.getLast60And90Days().getNotSureDunning(), CreditDunningDetailTypeEnum.DAYS_60_90_NOT_SURE, recordId, sid);
                saasCreditDunningDetailVoList.add(notSure);
            }
        }
        return saasCreditDunningDetailVoList;
    }

    private SaasCreditDunningDetailVo getSaasCreditDunningDetailVo(DunningStatisticVo dunningStatisticVo, CreditDunningDetailTypeEnum typeEnum, Long recordId, String sid) {
        if (dunningStatisticVo == null) {
            return null;
        }
        SaasCreditDunningDetailVo creditDunningDetailVo = new SaasCreditDunningDetailVo();
        if (StringUtils.isNotEmpty(dunningStatisticVo.getCallTelTotalNums())) {
            creditDunningDetailVo.setCallTelTotalNums(Integer.valueOf(dunningStatisticVo.getCallTelTotalNums()));
        }

        if (StringUtils.isNotEmpty(dunningStatisticVo.getCallTotalTimes())) {
            creditDunningDetailVo.setCallTotalTimes(Integer.valueOf(dunningStatisticVo.getCallTotalTimes()));
        }

        if (StringUtils.isNotEmpty(dunningStatisticVo.getCallOutTimes())) {
            creditDunningDetailVo.setCallOutTimes(Integer.valueOf(dunningStatisticVo.getCallOutTimes()));
        }

        if (StringUtils.isNotEmpty(dunningStatisticVo.getCallInTimes())) {
            creditDunningDetailVo.setCallInTimes(Integer.valueOf(dunningStatisticVo.getCallInTimes()));
        }

        if (StringUtils.isNotEmpty(dunningStatisticVo.getCallTotalDuration())) {
            creditDunningDetailVo.setCallTotalDuration(Integer.valueOf(dunningStatisticVo.getCallTotalDuration()));
        }

        if (StringUtils.isNotEmpty(dunningStatisticVo.getCallAvgDuration())) {
            creditDunningDetailVo.setCallAvgDuration(BigDecimal.valueOf(Double.valueOf(dunningStatisticVo.getCallAvgDuration()).doubleValue()));
        }

        if (StringUtils.isNotEmpty(dunningStatisticVo.getCallOutDuration())) {
            creditDunningDetailVo.setCallOutDuration(Integer.valueOf(dunningStatisticVo.getCallOutDuration()));
        }

        if (StringUtils.isNotEmpty(dunningStatisticVo.getCallInDuration())) {
            creditDunningDetailVo.setCallInDuration(Integer.valueOf(dunningStatisticVo.getCallInDuration()));
        }

        if (StringUtils.isNotEmpty(dunningStatisticVo.getCallDurationBelow15())) {
            creditDunningDetailVo.setCallDurationBelow15(Integer.valueOf(dunningStatisticVo.getCallDurationBelow15()));
        }

        if (StringUtils.isNotEmpty(dunningStatisticVo.getCallDurationBetween15And30())) {
            creditDunningDetailVo.setCallDurationBetween15and30(Integer.valueOf(dunningStatisticVo.getCallDurationBetween15And30()));
        }

        if (StringUtils.isNotEmpty(dunningStatisticVo.getCallDurationAbove60())) {
            creditDunningDetailVo.setCallDurationAbove60(Integer.valueOf(dunningStatisticVo.getCallDurationAbove60()));
        }

        creditDunningDetailVo.setFirstCallTime(dunningStatisticVo.getFirstCallTime());
        creditDunningDetailVo.setLastCallTime(dunningStatisticVo.getLastCallTime());
        if (typeEnum != null) {
            creditDunningDetailVo.setType(typeEnum.getType());
        }
        creditDunningDetailVo.setSid(sid);
        creditDunningDetailVo.setRecordId(recordId);
        return creditDunningDetailVo;
    }


}
