package com.beitu.saas.app.application.channel;

import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.channel.param.SaasChannelParam;
import com.beitu.saas.channel.client.SaasChannelRiskSettingsService;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.domain.SaasChannelRiskSettingsVo;
import com.beitu.saas.channel.domain.SaasChannelVo;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.beitu.saas.channel.entity.SaasChannelRiskSettingsEntity;
import com.beitu.saas.channel.enums.ChannelStatusEnum;
import com.beitu.saas.common.utils.OrderNoUtil;
import com.beitu.saas.common.utils.ShortUrlUtil;
import com.fqgj.common.api.Page;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        SaasChannelEntity saasChannelEntity = new SaasChannelEntity();
        BeanUtils.copyProperties(saasChannelVo, saasChannelEntity);

        String channelCode = OrderNoUtil.makeOrderNum();
        saasChannelEntity.setChannelCode(channelCode)
                .setChannelStatus(ChannelStatusEnum.OPEN.getType())
                .setLinkUrl("阿婆罗配置" + channelCode) // TODO: 2018/3/22
                .setCreator(RequestLocalInfo.getCurrentAdmin().getName());
        saasChannelService.create(saasChannelEntity);

        saasChannelRiskSettingsVoList.stream().forEach(x -> {
            SaasChannelRiskSettingsEntity entity = new SaasChannelRiskSettingsEntity();
            BeanUtils.copyProperties(x, entity);
            entity.setChannelCode(channelCode);
            saasChannelRiskSettingsService.create(entity);
        });

        LOGGER.info("== 渠道创建成功! ==");
    }

    public List<SaasChannelVo> getSaasChannelList(SaasChannelParam param, Page page) {
        List<SaasChannelVo> saasChannelVoList = new ArrayList<>();
        List<SaasChannelEntity> saasChannelList = saasChannelService.getSaasChannelList(param, page);

        saasChannelList.stream().forEach(x -> {
            SaasChannelVo saasChannelVo = new SaasChannelVo()
                    .setMerchantCode(x.getMerchantCode())
                    .setChannelCode(x.getChannelCode())
                    .setChannelName(x.getChannelName())
                    .setChannelStatus(x.getChannelStatus())
                    .setChargePerson(x.getChargePerson())
                    .setLongLinkUrl(x.getLinkUrl())// TODO: 2018/3/22 加上域名
                    .setShortLinkUrl(ShortUrlUtil.generateShortUrl(x.getLinkUrl()))// TODO: 2018/3/22 加上域名
                    .setCreator(x.getCreator())
                    .setGmtCreate(x.getGmtCreate())
                    .setRemark(x.getRemark());

            saasChannelVoList.add(saasChannelVo);
        });

        return saasChannelVoList;
    }
}
