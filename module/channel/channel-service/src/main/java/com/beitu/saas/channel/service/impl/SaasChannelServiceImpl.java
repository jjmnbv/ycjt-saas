package com.beitu.saas.channel.service.impl;

import com.beitu.saas.channel.consts.ChannelConsts;
import com.beitu.saas.channel.domain.DefaultChannelInfoVo;
import com.beitu.saas.channel.enums.ChannelStatusEnum;
import com.beitu.saas.channel.enums.ChannelTypeEnum;
import com.beitu.saas.channel.param.ChannelStatQueryParam;
import com.beitu.saas.channel.param.SaasChannelParam;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.dao.SaasChannelDao;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.beitu.saas.channel.vo.ChannelStatVo;
import com.beitu.saas.common.utils.OrderNoUtil;
import com.fqgj.common.api.Page;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * User: fenqiguanjia
 * Date: 2018-03-21
 * Time: 20:58:19.277
 */
@Module(value = "渠道信息表服务模块")
@NameSpace("com.beitu.saas.channel.dao.impl.SaasChannelDaoImpl")
@Service
public class SaasChannelServiceImpl extends AbstractBaseService implements SaasChannelService {


    @Autowired
    private SaasChannelDao saasChannelDao;

    @Override
    public List<SaasChannelEntity> getSaasChannelList(SaasChannelParam param, Page page) {
        return saasChannelDao.selectChannelEntityList(param, page);
    }

    @Override
    public void operateSaasChannel(String channelCode, Integer status) {
        SaasChannelEntity channelEntity = saasChannelDao.selectChannelEntityByChannelCode(channelCode);
        channelEntity.setChannelStatus(status);
        saasChannelDao.updateByPrimaryKey(channelEntity);
    }

    @Override
    public SaasChannelEntity getSaasChannelByChannelCode(String channelCode) {
        SaasChannelEntity channelEntity = saasChannelDao.selectChannelEntityByChannelCode(channelCode);
        return channelEntity;
    }

    @Override
    public List<ChannelStatVo> getChannelStatByPage(ChannelStatQueryParam channelStatQueryParam, Page page) {
        List<ChannelStatVo> channelStatVos = saasChannelDao.selectChannelStatList(channelStatQueryParam, page);
        channelStatVos.stream().forEach(x -> {
            SaasChannelEntity entity = saasChannelDao.selectChannelEntityByChannelCode(x.getChannelCode());
            if (entity != null) {
                x.setChannelName(entity.getChannelName());
            }
            this.clearStatRatio(x);
        });
        return channelStatVos;
    }

    /**
     * 清算渠道统计
     */
    private void clearStatRatio(ChannelStatVo channelStatVo) {
        BigDecimal intoPiecesNum = BigDecimal.valueOf(channelStatVo.getIntoPiecesNum());
        BigDecimal primaryReviewerNum = BigDecimal.valueOf(channelStatVo.getPrimaryReviewerNum());
        BigDecimal finalReviewerNum = BigDecimal.valueOf(channelStatVo.getFinalReviewerNum());
        BigDecimal loanLenderNum = BigDecimal.valueOf(channelStatVo.getLoanLenderNum());

        channelStatVo.setPrimaryReviewerRatio(intoPiecesNum.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : primaryReviewerNum.divide(intoPiecesNum, 2, BigDecimal.ROUND_HALF_UP));
        channelStatVo.setFinalReviewerRatio(primaryReviewerNum.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : finalReviewerNum.divide(primaryReviewerNum, 2, BigDecimal.ROUND_HALF_UP));
        channelStatVo.setFinalReviewerBaseRatio(intoPiecesNum.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : finalReviewerNum.divide(intoPiecesNum, 2, BigDecimal.ROUND_HALF_UP));
        channelStatVo.setLoanLenderRatio(finalReviewerNum.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : loanLenderNum.divide(finalReviewerNum, 2, BigDecimal.ROUND_HALF_UP));
        channelStatVo.setLoanLenderBaseRatio(intoPiecesNum.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : loanLenderNum.divide(intoPiecesNum, 2, BigDecimal.ROUND_HALF_UP));
    }

    @Override
    public SaasChannelEntity getDefaultSaasChannelByMerchantCode(String merchantCode, Integer channelType) {
        return saasChannelDao.selectDefaultChannelEntityByMerchantCode(merchantCode, channelType);
    }

    @Override
    public void createMerchantDefaultChannel(String merchantCode) {
        List<DefaultChannelInfoVo> defaultChannelTypeList = ChannelTypeEnum.getDefaultChannelList();

        defaultChannelTypeList.stream().forEach(x -> {
            SaasChannelEntity saasChannelEntity = new SaasChannelEntity();
            String channelCode = OrderNoUtil.makeOrderNum();
            saasChannelEntity.setMerchantCode(merchantCode)
                    .setChannelCode(channelCode)
                    .setChannelName(x.getChanelName())
                    .setChannelStatus(ChannelStatusEnum.OPEN.getType())
                    .setChannelType(x.getChannelType())
                    .setLinkUrl("?channel=" + channelCode)
                    .setChargePersonCode(ChannelConsts.DEFAULT_CHANNEL_CREATOR_CODE)
                    .setCreatorCode(ChannelConsts.DEFAULT_CHANNEL_CREATOR_CODE);
            saasChannelDao.insert(saasChannelEntity);
        });
    }

}


