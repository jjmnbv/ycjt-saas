package com.beitu.saas.app.application.credit;

import com.beitu.saas.app.application.credit.vo.CreditDunningDetailVo;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.enums.CreditDunningDetailTypeEnum;
import com.beitu.saas.common.handle.dianhua.DianhuaHandler;
import com.beitu.saas.common.handle.dianhua.domain.DunningDataVo;
import com.beitu.saas.common.handle.dianhua.domain.DunningStatisticVo;
import com.beitu.saas.common.handle.oss.OSSService;
import com.fqgj.common.utils.JSONUtils;
import com.fqgj.common.utils.MD5;
import com.fqgj.exception.common.ApplicationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by linchengyu on 17/8/2.
 */
@Component
public class DunningReportApplication {

    @Autowired
    private DianhuaHandler dianhuaHandler;

    @Autowired
    private CreditDunningService creditDunningService;

    @Autowired
    private CreditDunningDetailService creditDunningDetailService;

    @Autowired
    private UserCarrierService userCarrierService;

    @Autowired
    private OSSService ossService;

    @Autowired
    private ConfigUtil configUtil;

    @Async
    @Transactional
    public void generateDunningReport(Long queryId, Long userId) {
        UserCarrierVo userCarrierVo = userCarrierService.getUserCarrierByUserId(userId);
        String result = dianhuaHandler.getDunningVo(userCarrierVo.getUrl());
        DunningResultVo resultVo;
        try {
            resultVo = JSONUtils.json2pojoAndOffUnknownField(result, DunningResultVo.class);
        } catch (Exception e) {
            throw new ApplicationException(CreditErrorCodeEnum.CREDIT_DUNNING_GENERATE_ERROR, "电话邦结果JSON序列化失败");
        }
        String url = uploadDunningData(userId, result);
        CreditDunningVo creditDunningVo = new CreditDunningVo(userId, queryId, userCarrierVo.getUserCarrierId(), url);
        if (resultVo != null && resultVo.getData() != null) {
            creditDunningVo.setSid(resultVo.getData().getSid());
            creditDunningVo.setMobile(resultVo.getData().getTel());
            creditDunningVo.setTotalNum(resultVo.getData().getTotalNum());
            creditDunningVo.setEffectiveNum(resultVo.getData().getEffectiveNum());
        }
        Long recordId = creditDunningService.addCreditDunning(creditDunningVo);
        if (recordId == null) {
            throw new ApplicationException(CreditErrorCodeEnum.CREDIT_DUNNING_GENERATE_ERROR, "credit_dunning插入失败");
        }

        if (resultVo != null && resultVo.getData() != null) {
            List<CreditDunningDetailVo> list = convertDunningData(resultVo.getData());
            for (CreditDunningDetailVo detailVo : list) {
                detailVo.setUserId(userId);
                detailVo.setRecordId(recordId);
            }
            creditDunningDetailService.addList(list);
        }
        creditDunningService.creditDunningSuccess(recordId);
    }

    private String uploadDunningData(Long userId, String content) {
        String userIdTime = userId + "_" + (new Date().getTime());

        String filePath = "dunningData/";
        if (configUtil.isServerTest()) {
            filePath += "test/";
        }
        filePath += (userIdTime + "_" + MD5.md5(userIdTime) + ".json");

        return ossService.uploadFile(filePath, content);
    }

    private List<CreditDunningDetailVo> convertDunningData(DunningDataVo dunningDataVo) {
        List<CreditDunningDetailVo> creditDunningDetailVos = new ArrayList<>();
        if (dunningDataVo != null) {
            if (dunningDataVo.getOverview() != null) {
                CreditDunningDetailVo dunning = getCreditDunningDetailVo(dunningDataVo.getOverview().getDunning(), CreditDunningDetailTypeEnum.OVER_VIEW);
                creditDunningDetailVos.add(dunning);
                CreditDunningDetailVo notSure = getCreditDunningDetailVo(dunningDataVo.getOverview().getNotSureDunning(), CreditDunningDetailTypeEnum.OVER_VIEW_NOT_SURE);
                creditDunningDetailVos.add(notSure);
            }
            if (dunningDataVo.getLastWeek() != null) {
                CreditDunningDetailVo dunning = getCreditDunningDetailVo(dunningDataVo.getLastWeek().getDunning(), CreditDunningDetailTypeEnum.LAST_WEEK);
                creditDunningDetailVos.add(dunning);
                CreditDunningDetailVo notSure = getCreditDunningDetailVo(dunningDataVo.getLastWeek().getNotSureDunning(), CreditDunningDetailTypeEnum.LAST_WEEK_NOT_SURE);
                creditDunningDetailVos.add(notSure);
            }
            if (dunningDataVo.getLastTwoWeeks() != null) {
                CreditDunningDetailVo dunning = getCreditDunningDetailVo(dunningDataVo.getLastTwoWeeks().getDunning(), CreditDunningDetailTypeEnum.LAST_TWO_WEEK);
                creditDunningDetailVos.add(dunning);
                CreditDunningDetailVo notSure = getCreditDunningDetailVo(dunningDataVo.getLastTwoWeeks().getNotSureDunning(), CreditDunningDetailTypeEnum.LAST_TWO_WEEK_NOT_SURE);
                creditDunningDetailVos.add(notSure);
            }
            if (dunningDataVo.getLastThreeWeeks() != null) {
                CreditDunningDetailVo dunning = getCreditDunningDetailVo(dunningDataVo.getLastThreeWeeks().getDunning(), CreditDunningDetailTypeEnum.LAST_THREE_WEEK);
                creditDunningDetailVos.add(dunning);
                CreditDunningDetailVo notSure = getCreditDunningDetailVo(dunningDataVo.getLastThreeWeeks().getNotSureDunning(), CreditDunningDetailTypeEnum.LAST_THREE_WEEK_NOT_SURE);
                creditDunningDetailVos.add(notSure);
            }
            if (dunningDataVo.getLast30Days() != null) {
                CreditDunningDetailVo dunning = getCreditDunningDetailVo(dunningDataVo.getLast30Days().getDunning(), CreditDunningDetailTypeEnum.DAYS_30);
                creditDunningDetailVos.add(dunning);
                CreditDunningDetailVo notSure = getCreditDunningDetailVo(dunningDataVo.getLast30Days().getNotSureDunning(), CreditDunningDetailTypeEnum.DAYS_30_NOT_SURE);
                creditDunningDetailVos.add(notSure);
            }
            if (dunningDataVo.getLast30And60Days() != null) {
                CreditDunningDetailVo dunning = getCreditDunningDetailVo(dunningDataVo.getLast30And60Days().getDunning(), CreditDunningDetailTypeEnum.DAYS_30_60);
                creditDunningDetailVos.add(dunning);
                CreditDunningDetailVo notSure = getCreditDunningDetailVo(dunningDataVo.getLast30And60Days().getNotSureDunning(), CreditDunningDetailTypeEnum.DAYS_30_60_NOT_SURE);
                creditDunningDetailVos.add(notSure);
            }
            if (dunningDataVo.getLast60And90Days() != null) {
                CreditDunningDetailVo dunning = getCreditDunningDetailVo(dunningDataVo.getLast60And90Days().getDunning(), CreditDunningDetailTypeEnum.DAYS_60_90);
                creditDunningDetailVos.add(dunning);
                CreditDunningDetailVo notSure = getCreditDunningDetailVo(dunningDataVo.getLast60And90Days().getNotSureDunning(), CreditDunningDetailTypeEnum.DAYS_60_90_NOT_SURE);
                creditDunningDetailVos.add(notSure);
            }
        }
        return creditDunningDetailVos;
    }

    public CreditDunningDetailVo getCreditDunningDetailVo(DunningStatisticVo dunningStatisticVo, CreditDunningDetailTypeEnum typeEnum) {
        CreditDunningDetailVo creditDunningDetailVo = new CreditDunningDetailVo();
        if (dunningStatisticVo != null) {
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
        }

        if (typeEnum != null) {
            creditDunningDetailVo.setType(typeEnum.getType());
        }

        return creditDunningDetailVo;
    }


}
