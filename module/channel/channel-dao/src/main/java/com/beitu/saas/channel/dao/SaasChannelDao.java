package com.beitu.saas.channel.dao;

import com.beitu.saas.channel.param.ChannelStatQueryParam;
import com.beitu.saas.channel.param.SaasChannelParam;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.beitu.saas.channel.vo.ChannelStatVo;
import com.fqgj.common.api.Page;
import com.fqgj.common.base.BaseMapper;

import java.util.List;

/**
 * User: fenqiguanjia
 * Date: 2018-03-21
 * Time: 20:58:19.262
 */

public interface SaasChannelDao extends BaseMapper<SaasChannelEntity> {

    List<SaasChannelEntity> selectChannelEntityList(SaasChannelParam saasChannelParam, Page page);

    List<SaasChannelEntity> selectAllChannelEntityList(String merchantCode);

    Integer countChannelEntityList(SaasChannelParam saasChannelParam);

    SaasChannelEntity selectChannelEntityByChannelCode(String channelCode);

    List<ChannelStatVo> selectChannelStatList(ChannelStatQueryParam channelStatQueryParam, Page page);

    Integer queryTotalChannelStatCount(ChannelStatQueryParam channelStatQueryParam);

    SaasChannelEntity selectDefaultChannelEntityByMerchantCode(String merchantCode, Integer channelType);

}