package com.beitu.saas.app.task;

import com.beitu.saas.app.application.finance.SaasConsumeDayStatApplication;
import com.beitu.saas.common.config.ConfigUtil;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/27
 * Time: 下午7:56
 */
@Component
@EnableScheduling
public class CreditAndMsgDayClearTaksJob {
    private static final Log LOGGER = LogFactory.getLog(CreditAndMsgDayClearTaksJob.class);

    @Autowired
    private ConfigUtil configUtil;
    @Autowired
    private SaasConsumeDayStatApplication saasConsumeDayStatApplication;

    @Scheduled(cron = "0 30 1 * * ?")
    public void creditAndMsgDayClear() {
        LOGGER.info("== 点券和短信日清算任务开始 ==");
        String taskSwitch = configUtil.getClearDayStatSwith();
        if (StringUtils.isEmpty(taskSwitch) || taskSwitch.equals("false")) {
            LOGGER.info("!====== 点券和短信每日清算任务未开启 ====!");
            return;
        }

        try {
            saasConsumeDayStatApplication.creditAndMsgDayClear();
        } catch (Exception e) {
            LOGGER.info("== 点券和短信日清算任务异常,异常原因:{}  ==", e);
        }
        LOGGER.info("== 点券和短信日清算任务结束 ==");
    }
}
