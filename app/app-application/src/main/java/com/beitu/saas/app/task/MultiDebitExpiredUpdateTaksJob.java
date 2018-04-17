package com.beitu.saas.app.task;

import com.beitu.saas.app.application.finance.SaasConsumeDayStatApplication;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.credit.client.SaasMultiDebitService;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/27
 * Time: 下午7:56
 */
@Component
public class MultiDebitExpiredUpdateTaksJob {
    private static final Log LOGGER = LogFactory.getLog(MultiDebitExpiredUpdateTaksJob.class);

    @Autowired
    private ConfigUtil configUtil;
    @Autowired
    private SaasMultiDebitService saasMultiDebitService;

    @Scheduled(cron = "0 30 2 * * ?")
    public void creditAndMsgDayClear() {
        LOGGER.info("== 过期的借贷统计清算任务开始 ==");

        String ipWhite = configUtil.getClearDayStatIpWhite();
        String[] whiteIps = ipWhite.split(",");
        Set<String> set = new HashSet<String>(Arrays.asList(whiteIps));
        String hostIp = null;
        try {
            hostIp = InetAddress.getLocalHost().getHostAddress();
            LOGGER.info(" 白名单IP ;{}, 当前执行任务的主机IP: {}", ipWhite, hostIp);
        } catch (Exception e) {
            LOGGER.error("!====== 获取机器地址发生异常：{} ====!", e);
        }
        if (whiteIps.length == 0 || !set.contains(hostIp)) {
            LOGGER.info("!====== 过期的借贷统计清算任务机器白名单限制 ====!");
            return;
        }

        saasMultiDebitService.updateExpiredMultiDebitEntity();
        LOGGER.info("== 过期的借贷统计清算任务结束 ==");
    }

}
