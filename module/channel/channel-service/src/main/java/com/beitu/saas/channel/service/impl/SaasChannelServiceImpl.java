package com.beitu.saas.channel.service.impl;

import com.beitu.saas.channel.param.ChannelStatQueryParam;
import com.beitu.saas.channel.param.SaasChannelParam;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.dao.SaasChannelDao;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.beitu.saas.channel.vo.ChannelStatVo;
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
            x.setChannelName(entity.getChannelName());
            this.clearStatRatio(x);
        });
        return channelStatVos;
    }

    /**
     * 清算渠道统计
     */
    private void clearStatRatio(ChannelStatVo channelStatVo) {
        channelStatVo.setPrimaryReviewerRatio(new BigDecimal(channelStatVo.getPrimaryReviewerNum() / channelStatVo.getIntoPiecesNum()).setScale(2, BigDecimal.ROUND_HALF_UP));
        channelStatVo.setFinalReviewerRatio(new BigDecimal(channelStatVo.getFinalReviewerNum() / channelStatVo.getPrimaryReviewerNum()).setScale(2, BigDecimal.ROUND_HALF_UP));
        channelStatVo.setFinalReviewerBaseRatio(new BigDecimal(channelStatVo.getFinalReviewerNum() / channelStatVo.getIntoPiecesNum()).setScale(2, BigDecimal.ROUND_HALF_UP));
        channelStatVo.setLoanLenderRatio(new BigDecimal(channelStatVo.getLoanLenderNum() / channelStatVo.getFinalReviewerNum()).setScale(2, BigDecimal.ROUND_HALF_UP));
        channelStatVo.setLoanLenderBaseRatio(new BigDecimal(channelStatVo.getLoanLenderNum() / channelStatVo.getIntoPiecesNum()).setScale(2, BigDecimal.ROUND_HALF_UP));
    }
}


