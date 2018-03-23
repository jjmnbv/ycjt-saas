package com.beitu.saas.app.application.finance;

import com.beitu.saas.finance.client.SaasConsumeDayStatService;
import com.beitu.saas.finance.entity.SaasConsumeDayStatEntity;
import com.fqgj.common.utils.DateUtil;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    /**
     * 每日点券/短信统计
     */

    public void clearCreditAndMsg() {
        SaasConsumeDayStatEntity saasConsumeDayStatEntity = saasConsumeDayStatService.getLastConsumeDayStatEntity();
        List<Date> dates = new ArrayList<>();//待清算的日期集合

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

        dates.stream().forEach(x -> {
            //根据机构号统计消费的点券记录表,统计短信消费记录表

            //入库,事物控制

            //余额不足为负数
        });
    }


    /**
     * 清算点券/短信
     */
    //查询今日清算的消费记录

    //总余额扣除点券和短信统计数

    //记录消费记录流水

    //清算当前剩余总数
    private Date getYesterday() {
        return DateUtil.addDate(DateUtil.getPatternDate(new Date(), "yyyy-MM-dd"), -1);
    }
}
