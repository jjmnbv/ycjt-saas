package com.beitu.saas.channel.service.impl;

import com.beitu.saas.channel.consts.ChannelConsts;
import com.beitu.saas.channel.enums.ChannelStatusEnum;
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
        BigDecimal intoPiecesNum = new BigDecimal(channelStatVo.getIntoPiecesNum());
        BigDecimal primaryReviewerNum = new BigDecimal(channelStatVo.getPrimaryReviewerNum());
        BigDecimal finalReviewerNum = new BigDecimal(channelStatVo.getFinalReviewerNum());
        BigDecimal loanLenderNum = new BigDecimal(channelStatVo.getLoanLenderNum());

        channelStatVo.setPrimaryReviewerRatio(primaryReviewerNum.divide(intoPiecesNum, 2, BigDecimal.ROUND_HALF_UP));
        channelStatVo.setFinalReviewerRatio(finalReviewerNum.divide(primaryReviewerNum, 2, BigDecimal.ROUND_HALF_UP));
        channelStatVo.setFinalReviewerBaseRatio(finalReviewerNum.divide(intoPiecesNum, 2, BigDecimal.ROUND_HALF_UP));
        channelStatVo.setLoanLenderRatio(loanLenderNum.divide(finalReviewerNum, 2, BigDecimal.ROUND_HALF_UP));
        channelStatVo.setLoanLenderBaseRatio(loanLenderNum.divide(intoPiecesNum, 2, BigDecimal.ROUND_HALF_UP));
    }

    @Override
    public SaasChannelEntity getDefaultSaasChannelByMerchantCode(String merchantCode) {
        return saasChannelDao.selectChannelEntityByMerchantCodeAndCreatorCode(merchantCode, ChannelConsts.DEFAULT_CHANNEL_CREATOR_CODE);
    }

    @Override
    public SaasChannelEntity createMerchantDefaultChannel(String merchantCode) {
        SaasChannelEntity saasChannelEntity = new SaasChannelEntity();
        String channelCode = ChannelConsts.DEFAULT_CHANNEL_CREATOR_CODE;
        saasChannelEntity.setMerchantCode(merchantCode)
                .setChannelCode(channelCode)
                .setChannelName("SAAS进件渠道")
                .setChannelStatus(ChannelStatusEnum.OPEN.getType())
                .setLinkUrl("?channelCode=" + channelCode)
                .setChargePersonCode(ChannelConsts.DEFAULT_CHANNEL_CREATOR_CODE)
                .setCreatorCode(ChannelConsts.DEFAULT_CHANNEL_CREATOR_CODE);
        saasChannelDao.insert(saasChannelEntity);
        return saasChannelEntity;
    }

}


