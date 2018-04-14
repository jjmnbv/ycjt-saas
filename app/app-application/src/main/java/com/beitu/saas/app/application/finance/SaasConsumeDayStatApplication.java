package com.beitu.saas.app.application.finance;

import com.beitu.saas.app.application.SendApplication;
import com.beitu.saas.app.enums.SaasConsumeMsgTypeEnum;
import com.beitu.saas.app.enums.SaasSmsTypeEnum;
import com.beitu.saas.auth.domain.SaasMerchantVo;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.auth.service.SaasAdminService;
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

import java.util.*;

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
    @Autowired
    private SendApplication sendApplication;
    @Autowired
    private SaasAdminService saasAdminService;

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
            Long totalConsumeCreditCount = saasCreditHistoryService.getYesterdayCreditStatCredit(merchantCode, x);
            Long totalConsumeSmsCount = saasSmsHistoryService.getYesterdaySmsStatCredit(merchantCode, x);

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
                saasMerchantCreditInfoService.decrease(merchantCode, totalConsumeCreditCount);
                if (creditEntity.getValue() < totalConsumeCreditCount) {
                    this.sendNotifyMessage(merchantCode, totalConsumeCreditCount, SaasConsumeMsgTypeEnum.CREDIT.getType());
                }
            }
            if (smsEntity != null) {
                saasMerchantSmsInfoService.decrease(merchantCode, totalConsumeSmsCount);
                if (smsEntity.getValue() < totalConsumeSmsCount) {
                    this.sendNotifyMessage(merchantCode, totalConsumeSmsCount, SaasConsumeMsgTypeEnum.MESSAGE.getType());
                }
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

    public void sendNotifyMessage(String merchantCode, Long num, Integer type) {
        try {
            SaasAdmin admin = saasAdminService.getDefaultAdminByMerchantCode(merchantCode);
            SaasMerchantVo merchantVo = saasMerchantService.getByMerchantCode(merchantCode);
            Map<String, Object> map = new HashMap<>(4);
            map.put("company", merchantVo.getCompanyName());
            map.put("num", num);

            if (type == SaasConsumeMsgTypeEnum.CREDIT.getType()) {
                sendApplication.sendNotifyMessage(merchantCode, admin.getMobile(), map, SaasSmsTypeEnum.SAAS_0020);
            }
            if (type == SaasConsumeMsgTypeEnum.MESSAGE.getType()) {
                sendApplication.sendNotifyMessage(merchantCode, admin.getMobile(), map, SaasSmsTypeEnum.SAAS_0021);
            }
        } catch (Exception e) {
            LOGGER.error("结构号: {}  {}余额不足,短信通知失败, 异常原因: {}", merchantCode, SaasConsumeMsgTypeEnum.getMsgByType(type), e);
        }
    }

    private Date getYesterday() {
        return DateUtil.addDate(DateUtil.getPatternDate(new Date(), "yyyy-MM-dd"), -1);
    }

}
