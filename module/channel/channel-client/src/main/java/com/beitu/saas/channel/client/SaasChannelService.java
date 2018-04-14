package com.beitu.saas.channel.client;

import com.beitu.saas.channel.param.ChannelStatQueryParam;
import com.beitu.saas.channel.param.SaasChannelParam;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.beitu.saas.channel.vo.ChannelStatVo;
import com.fqgj.common.api.Page;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;

/**
 * User: fenqiguanjia
 * Date: 2018-03-21
 * Time: 20:58:19.270
 */
public interface SaasChannelService<T extends BaseEntity> extends BaseService<T> {

    /**
     * 渠道列表-不包括默认渠道
     */
    List<SaasChannelEntity> getSaasChannelList(SaasChannelParam param, Page page);

    /**
     * 渠道列表
     */
    List<SaasChannelEntity> getAllSaasChannelList(String merchantCode);

    /**
     * 操作渠道功能
     */
    void operateSaasChannel(String channelCode, Integer status);


    /**
     * 根据渠道号渠道信息
     */
    SaasChannelEntity getSaasChannelByChannelCode(String channelCode);

    /**
     * 渠道统计列表
     *
     * @param channelStatQueryParam
     * @param page
     * @return
     */
    List<ChannelStatVo> getChannelStatByPage(ChannelStatQueryParam channelStatQueryParam, Page page);

    /**
     * 根据 机构CODE 得到 机构SAAS进件渠道
     */
    SaasChannelEntity getDefaultSaasChannelByMerchantCode(String merchantCode, Integer channelType);

    /**
     * 创建 机构默认SAAS进件渠道
     *
     * @param merchantCode 机构CODE
     * @return
     */
    void createMerchantDefaultChannel(String merchantCode);

}