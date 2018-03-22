package com.beitu.saas.app.application.channel;

import com.beitu.saas.channel.domain.SaasChannelRiskSettingsVo;
import com.beitu.saas.channel.domain.SaasChannelVo;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 上午11:50
 */
@Component
public class SaasChannelApplication {
    private static final Log LOGGER = LogFactory.getLog(SaasChannelApplication.class);

    /**
     * 创建渠道
     */
    public void createChannel(SaasChannelVo saasChannelVo, List<SaasChannelRiskSettingsVo> saasChannelRiskSettingsVoList) {

    }
}
