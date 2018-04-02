package com.beitu.saas.app.application.channel;

import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.auth.service.SaasAdminService;
import com.beitu.saas.channel.domain.SaasChannelDetailVo;
import com.beitu.saas.channel.domain.SaasChannelRiskSettingsVo;
import com.beitu.saas.channel.domain.SaasH5ChannelVo;
import com.beitu.saas.channel.enums.ChannelOperateTypeEnum;
import com.beitu.saas.channel.param.SaasChannelParam;
import com.beitu.saas.channel.client.SaasChannelRiskSettingsService;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.domain.SaasChannelVo;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.beitu.saas.channel.entity.SaasChannelRiskSettingsEntity;
import com.beitu.saas.channel.enums.ChannelStatusEnum;
import com.beitu.saas.channel.param.SaasChannelRiskSettingsParam;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.utils.OrderNoUtil;
import com.beitu.saas.common.utils.ShortUrlUtil;
import com.fqgj.common.api.Page;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    @Autowired
    private SaasAdminService saasAdminService;
    @Resource
    private ConfigUtil configUtil;


    /**
     * 获取机构下的所有管理员
     */
    public List<SaasAdmin> getSaasAdminListByMerchantCode(String merchantCode) {
        return saasAdminService.getAllAdminListByMerchantCode(merchantCode);
    }

    /**
     * 创建/编辑渠道
     */
    @Transactional(rollbackFor = Exception.class)
    public void addOrUpdateChannel(SaasChannelParam saasChannelParam, List<SaasChannelRiskSettingsParam> saasChannelRiskSettingsVoList) {
        if (saasChannelParam.getOpType() == ChannelOperateTypeEnum.ADD.getType()) {
            SaasChannelEntity saasChannelEntity = new SaasChannelEntity();
            BeanUtils.copyProperties(saasChannelParam, saasChannelEntity);
            String channelCode = OrderNoUtil.makeOrderNum();
            saasChannelEntity.setChannelCode(channelCode)
                    .setChannelStatus(ChannelStatusEnum.OPEN.getType())
                    .setLinkUrl("?channel=" + channelCode)
                    .setCreatorCode(RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode());
            saasChannelService.create(saasChannelEntity);

            saasChannelRiskSettingsVoList.stream().forEach(x -> {
                SaasChannelRiskSettingsEntity entity = new SaasChannelRiskSettingsEntity();
                BeanUtils.copyProperties(x, entity);
                entity.setChannelCode(channelCode);
                saasChannelRiskSettingsService.create(entity);
            });
            LOGGER.info("== 渠道创建成功! ==");
        }


        if (saasChannelParam.getOpType() == ChannelOperateTypeEnum.UPDATE.getType()) {
            SaasChannelEntity saasChannelEntity = saasChannelService.getSaasChannelByChannelCode(saasChannelParam.getChannelCode());
            BeanUtils.copyProperties(saasChannelParam, saasChannelEntity);
            saasChannelService.updateById(saasChannelEntity);

            saasChannelRiskSettingsService.deleteRiskSettingsByChannelCode(saasChannelEntity.getChannelCode());
            saasChannelRiskSettingsVoList.stream().forEach(x -> {
                SaasChannelRiskSettingsEntity entity = new SaasChannelRiskSettingsEntity();
                BeanUtils.copyProperties(x, entity);
                entity.setChannelCode(saasChannelEntity.getChannelCode());
                saasChannelRiskSettingsService.create(entity);
            });
            LOGGER.info("== 渠道修改成功! ==");
        }
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
                .setChargePersonName(this.getAdminNameByAdminCode(saasChannel.getChargePersonCode()))
                .setChargePersonCode(saasChannel.getChargePersonCode())
                .setCreatorName(this.getAdminNameByAdminCode(saasChannel.getCreatorCode()))
                .setCreatorCode(saasChannel.getCreatorCode())
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
                    .setChargePersonCode(x.getChargePersonCode())
                    .setChargePersonName(this.getAdminNameByAdminCode(x.getChargePersonCode()))
                    .setLinkUrl(configUtil.getAddressURLPrefix() + configUtil.getH5AddressURLPrefix() + x.getLinkUrl())
                    .setLongLinkUrl(configUtil.getAddressURLPrefix() + configUtil.getH5AddressURLPrefix() + x.getLinkUrl())
                    .setShortLinkUrl(ShortUrlUtil.generateShortUrl(configUtil.getAddressURLPrefix() + x.getLinkUrl()))
                    .setCreatorName(this.getAdminNameByAdminCode(x.getCreatorCode()))
                    .setCreatorCode(x.getCreatorCode())
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


    /**
     * 根据用户code获取管理员名称
     *
     * @param adminCode
     * @return
     */
    private String getAdminNameByAdminCode(String adminCode) {
        SaasAdmin saasAdminByAdminCode = saasAdminService.getSaasAdminByAdminCode(adminCode);

        if (saasAdminByAdminCode != null) {
            return saasAdminByAdminCode.getName();
        }
        return null;
    }

    public String getDefaultSaasChannelCodeByMerchantCode(String merchantCode) {
        SaasChannelEntity saasChannelEntity = saasChannelService.getDefaultSaasChannelByMerchantCode(merchantCode);
        if (ChannelStatusEnum.CLOSE.getType().equals(saasChannelEntity.getChannelStatus())) {
            return null;
        }
        return saasChannelEntity.getChannelCode();
    }

}
