package com.beitu.saas.app.application.finance;

import com.beitu.saas.finance.client.SaasConsumeDayStatService;
import com.beitu.saas.finance.entity.SaasConsumeDayStatEntity;
import com.fqgj.common.utils.DateUtil;
import com.fqgj.common.utils.JSONUtils;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

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

    /**
     * 每日点券/短信统计
     */

    public void clearCreditAndMsg() {
        // TODO: 2018/3/23 查询机构表的所有机构号 ,遍历
        this.SyncMerchantConsumeDayStat("机构号");
    }


    /**
     * 清算点券/短信
     */
    //查询今日清算的消费记录

    //总余额扣除点券和短信统计数

    //记录消费记录流水

    //清算当前剩余总数

    //余额不足为负数


    /**
     * 同步机构日消费统计
     *
     * @param merchantCode
     */
    @Transactional(rollbackFor = Exception.class)
    private void SyncMerchantConsumeDayStat(String merchantCode) {
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

        try {
            List<String> dateStringList = new ArrayList<>();
            dates.stream().forEach(x -> {
                dateStringList.add(DateUtil.getDate(x, "yyyy-MM-dd"));
            });
            LOGGER.info("当前日期: {} ,机构号: {},待清算的日期: {} ", DateUtil.getPatternDate(new Date(), "yyyy-MM-dd"), merchantCode, JSONUtils.obj2json(dateStringList));
        } catch (Exception e) {
            LOGGER.error("日志打印异常!");
        }

        dates.stream().forEach(x -> {
            // TODO: 2018/3/23  根据机构号统计消费的点券记录表,统计短信消费记录表

            // TODO: 2018/3/23  入库
            SaasConsumeDayStatEntity statEntity = new SaasConsumeDayStatEntity()
                    //.setConsumeCredit()
                    //.setConsumeMsg()
                    .setDt(x)
                    .setLastClearDt(DateUtil.addDate(x, 1))
                    .setMerchantCode(merchantCode);
            saasConsumeDayStatService.create(statEntity);
        });
    }

    private Date getYesterday() {
        return DateUtil.addDate(DateUtil.getPatternDate(new Date(), "yyyy-MM-dd"), -1);
    }

}
