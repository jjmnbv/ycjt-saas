package com.beitu.saas.app.application.channel;

import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.channel.client.SaasChannelRiskSettingsService;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.domain.SaasChannelRiskSettingsVo;
import com.beitu.saas.channel.domain.SaasChannelVo;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.beitu.saas.channel.entity.SaasChannelRiskSettingsEntity;
import com.beitu.saas.channel.enums.ChannelStatusEnum;
import com.beitu.saas.common.utils.OrderNoUtil;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private SaasChannelService saasChannelService;
    @Autowired
    private SaasChannelRiskSettingsService saasChannelRiskSettingsService;

    /**
     * 创建渠道
     */
    @Transactional(rollbackFor = Exception.class)
    public void createChannel(SaasChannelVo saasChannelVo, List<SaasChannelRiskSettingsVo> saasChannelRiskSettingsVoList) {
        SaasChannelEntity saasChannelEntity = new SaasChannelEntity()
                .setChannelCode(OrderNoUtil.makeOrderNum())
                .setChannelStatus(ChannelStatusEnum.OPEN.getType())
                .setLinkUrl("阿婆罗配置" + OrderNoUtil.makeOrderNum()) // TODO: 2018/3/22
                .setCreator(RequestLocalInfo.getCurrentAdmin().getName());

        BeanUtils.copyProperties(saasChannelVo, saasChannelEntity);
        saasChannelService.create(saasChannelEntity);

        saasChannelRiskSettingsVoList.stream().forEach(x -> {
            SaasChannelRiskSettingsEntity entity = new SaasChannelRiskSettingsEntity();
            BeanUtils.copyProperties(x, entity);
            saasChannelRiskSettingsService.create(entity);
        });

        LOGGER.info("== 渠道创建成功! ==");
    }
}
