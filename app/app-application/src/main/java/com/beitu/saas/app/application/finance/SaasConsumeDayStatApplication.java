package com.beitu.saas.app.application.finance;

import com.beitu.saas.auth.dao.SaasMerchantDao;
import com.beitu.saas.finance.client.SaasConsumeDayStatService;
import com.beitu.saas.finance.entity.SaasConsumeDayStatEntity;
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
    private SaasMerchantDao saasMerchantDao;

    /**
     * 每日点券/短信统计
     */

    public void creditAndMsgDayClear() {
        LOGGER.info("== 点券和短信日清算任务开始 ==");
        List<String> merchantCodeList = saasMerchantDao.selectAllMerchantCode();
        merchantCodeList.stream().forEach(x -> {
            this.SyncMerchantConsumeDayStat(x);
        });
        LOGGER.info("== 点券和短信日清算任务结束 ==");
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
            // TODO: 2018/3/23  根据机构号统计消费的点券记录表,统计短信消费记录表

            SaasConsumeDayStatEntity statEntity = new SaasConsumeDayStatEntity()
                    //.setConsumeCredit()
                    //.setConsumeMsg()
                    .setDt(x)
                    .setLastClearDt(DateUtil.addDate(x, 1))
                    .setMerchantCode(merchantCode);
            saasConsumeDayStatService.create(statEntity);

            // TODO: 2018/3/23 查询点券余额表扣除余额,余额不足为负数

            // TODO: 2018/3/23 查询短信余额表扣除余额,余额不足为负数

            // TODO: 2018/3/23 总余额扣除点券和短信统计数

            // TODO: 2018/3/23 记录消费记录流水
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
