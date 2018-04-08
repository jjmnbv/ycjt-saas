package com.beitu.saas.app.application.finance;

import com.beitu.saas.auth.service.SaasMerchantService;
import com.beitu.saas.finance.client.*;
import com.beitu.saas.finance.entity.SaasConsumeDayStatEntity;
import com.beitu.saas.finance.entity.SaasMerchantCreditInfoEntity;
import com.beitu.saas.finance.entity.SaasMerchantSmsInfoEntity;
import com.fqgj.common.utils.DateUtil;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/23
 * Time: 下午3:33
 */
@Component
public class SaasConsumeDayStatApplication {
    private static final Log LOGGER = LogFactory.getLog(SaasConsumeDayStatApplication.class);

    @Autowired
    private SaasConsumeDayStatService saasConsumeDayStatService;
    @Autowired
    private SaasMerchantService saasMerchantService;
    @Autowired
    private SaasCreditHistoryService saasCreditHistoryService;
    @Autowired
    private SaasSmsHistoryService saasSmsHistoryService;
    @Autowired
    private SaasMerchantCreditInfoService saasMerchantCreditInfoService;
    @Autowired
    private SaasMerchantSmsInfoService saasMerchantSmsInfoService;

    /**
     * 每日点券/短信统计
     */
    public void creditAndMsgDayClear() {
        List<String> merchantCodeList = saasMerchantService.getMerchantList();
        merchantCodeList.stream().forEach(x -> {
            this.SyncMerchantConsumeDayStat(x);
        });
    }


    /**
     * 同步点券/短信消费统计
     * 考虑多天情况
     *
     * @param merchantCode
     */
    @Transactional(rollbackFor = Exception.class)
    private void SyncMerchantConsumeDayStat(String merchantCode) {
        LOGGER.info("== 机构号为: {}的日清算开始 ==", merchantCode);
        List<Date> dates = this.getNeedStatDateList(merchantCode);

        dates.stream().forEach(x -> {
            Long totalConsumeCreditCount = saasCreditHistoryService.getYesterdayCreditStatCredit(merchantCode, this.getYesterday());
            Long totalConsumeSmsCount = saasSmsHistoryService.getYesterdaySmsStatCredit(merchantCode, this.getYesterday());

            SaasConsumeDayStatEntity statEntity = new SaasConsumeDayStatEntity()
                    .setConsumeCredit(totalConsumeCreditCount)
                    .setConsumeSms(totalConsumeSmsCount)
                    .setDt(x)
                    .setLastClearDt(DateUtil.addDate(x, 1))
                    .setMerchantCode(merchantCode);
            saasConsumeDayStatService.create(statEntity);

            SaasMerchantCreditInfoEntity creditEntity = saasMerchantCreditInfoService.getCreditInfoByMerchantCode(merchantCode);
            SaasMerchantSmsInfoEntity smsEntity = saasMerchantSmsInfoService.getSmsInfoByMerchantCode(merchantCode);
            if (creditEntity != null) {
                Long clearCredit = creditEntity.getValue() - totalConsumeCreditCount;
                saasMerchantCreditInfoService.update(creditEntity.setValue(clearCredit));
            }
            if (smsEntity != null) {
                Long clearSms = smsEntity.getValue() - totalConsumeSmsCount;
                saasMerchantSmsInfoService.update(smsEntity.setValue(clearSms));
            }
        });
        LOGGER.info("== 机构号为: {}的日清算完成 ==", merchantCode);
    }

    /**
     * 获取需要同步统计的日期
     *
     * @return
     */
    private List<Date> getNeedStatDateList(String merchantCode) {
        SaasConsumeDayStatEntity saasConsumeDayStatEntity = saasConsumeDayStatService.getLastConsumeDayStatEntity(merchantCode);
        List<Date> dates = new ArrayList<>();
        if (saasConsumeDayStatEntity == null) {
            Date yesterday = this.getYesterday();
            dates.add(yesterday);
        } else {
            Date lastClearDate = saasConsumeDayStatEntity.getLastClearDt();
            Integer countDays = DateUtil.countDay(new Date(), lastClearDate);
            for (int i = 1; i <= countDays; i++) {
                dates.add(DateUtil.addDate(saasConsumeDayStatEntity.getDt(), i));
            }
        }
        return dates;
    }

    private Date getYesterday() {
        return DateUtil.addDate(DateUtil.getPatternDate(new Date(), "yyyy-MM-dd"), -1);
    }

}
