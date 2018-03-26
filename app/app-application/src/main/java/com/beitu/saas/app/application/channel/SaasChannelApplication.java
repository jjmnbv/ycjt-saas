package com.beitu.saas.app.application.channel;

import com.beitu.saas.channel.domain.SaasChannelDetailVo;
import com.beitu.saas.channel.domain.SaasChannelRiskSettingsVo;
import com.beitu.saas.channel.domain.SaasH5ChannelVo;
import com.beitu.saas.channel.param.SaasChannelParam;
import com.beitu.saas.channel.client.SaasChannelRiskSettingsService;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.domain.SaasChannelVo;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.beitu.saas.channel.entity.SaasChannelRiskSettingsEntity;
import com.beitu.saas.channel.enums.ChannelStatusEnum;
import com.beitu.saas.channel.param.SaasChannelRiskSettingsParam;
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
    public void createChannel(SaasChannelParam saasChannelParam, List<SaasChannelRiskSettingsParam> saasChannelRiskSettingsVoList) {
        SaasChannelEntity saasChannelEntity = new SaasChannelEntity();
        BeanUtils.copyProperties(saasChannelParam, saasChannelEntity);

        String channelCode = OrderNoUtil.makeOrderNum();
        saasChannelEntity.setChannelCode(channelCode)
                .setChannelStatus(ChannelStatusEnum.OPEN.getType())
                .setLinkUrl("channel/" + channelCode) // TODO: 2018/3/22
                .setCreator("admin");// TODO: 2018/3/24 先写死
        saasChannelService.create(saasChannelEntity);

        saasChannelRiskSettingsVoList.stream().forEach(x -> {
            SaasChannelRiskSettingsEntity entity = new SaasChannelRiskSettingsEntity();
            BeanUtils.copyProperties(x, entity);
            entity.setChannelCode(channelCode);
            saasChannelRiskSettingsService.create(entity);
        });

        LOGGER.info("== 渠道创建成功! ==");
    }

    /**
     * 获取渠道详情
     *
     * @param channelCode
     * @return
     */
    public SaasChannelDetailVo getSaasChannelDetail(String channelCode) {
        SaasChannelEntity saasChannel = saasChannelService.getSaasChannelByChannelCode(channelCode);
        List<SaasChannelRiskSettingsEntity> saasChannelRiskSettings = saasChannelRiskSettingsService.getSaasChannelRiskSettingsByChannelCode(channelCode);

        List<SaasChannelRiskSettingsVo> riskSettingsVos = new ArrayList<>();
        saasChannelRiskSettings.stream().forEach(x -> {
            SaasChannelRiskSettingsVo vo = new SaasChannelRiskSettingsVo()
                    .setModuleCode(x.getModuleCode())
                    .setChannelCode(x.getChannelCode())
                    .setItemCode(x.getItemCode())
                    .setRequired(x.getRequired());
            riskSettingsVos.add(vo);
        });

        return new SaasChannelDetailVo().setChannelName(saasChannel.getChannelName())
                .setChannelCode(channelCode)
                .setChargePerson(saasChannel.getChargePerson())
                .setCreator(saasChannel.getCreator())
                .setRemark(saasChannel.getRemark())
                .setSaasChannelRiskSettingsVos(riskSettingsVos);
    }

    /**
     * 分页获取渠道列表
     *
     * @param param
     * @param page
     * @return
     */
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
                    .setLinkUrl(x.getLinkUrl())// TODO: 2018/3/22 加上阿波罗域名
                    .setLongLinkUrl(x.getLinkUrl())// TODO: 2018/3/22 加上域名
                    //.setShortLinkUrl(ShortUrlUtil.generateShortUrl(x.getLinkUrl()))// TODO: 2018/3/22 加上域名
                    .setShortLinkUrl(ShortUrlUtil.generateShortUrl("http://agent.yangcongjietiao.com/agentWebViews/agent/index.html"))
                    .setCreator(x.getCreator())
                    .setGmtCreate(x.getGmtCreate())
                    .setRemark(x.getRemark());

            saasChannelVoList.add(saasChannelVo);
        });

        return saasChannelVoList;
    }

    /**
     * 根据渠道号获取渠道信息
     *
     * @param channelCode
     * @return
     */
    public SaasH5ChannelVo getSaasChannelBychannelCode(String channelCode) {
        SaasChannelEntity saasChannelEntity = saasChannelService.getSaasChannelByChannelCode(channelCode);
        if (saasChannelEntity.getChannelStatus() == ChannelStatusEnum.CLOSE.getType()) {
            return null;
        }
        return new SaasH5ChannelVo().setChannelCode(saasChannelEntity.getChannelCode())
                .setMerchantCode(saasChannelEntity.getMerchantCode())
                .setChannelName(saasChannelEntity.getChannelName());
    }


    /**
     * 根据渠道号获取风控配置信息
     */
    public List<SaasChannelRiskSettingsVo> getSaasChannelRiskSettingsByChannelCode(String channelCode) {
        List<SaasChannelRiskSettingsEntity> entityList = saasChannelRiskSettingsService.getSaasChannelRiskSettingsByChannelCode(channelCode);
        List<SaasChannelRiskSettingsVo> riskSettingsVos = new ArrayList<>();

        entityList.stream().forEach(x -> {
            SaasChannelRiskSettingsVo vo = new SaasChannelRiskSettingsVo()
                    .setChannelCode(x.getChannelCode())
                    .setItemCode(x.getItemCode())
                    .setModuleCode(x.getModuleCode())
                    .setRequired(x.getRequired());
            riskSettingsVos.add(vo);
        });

        return riskSettingsVos;
    }
}
