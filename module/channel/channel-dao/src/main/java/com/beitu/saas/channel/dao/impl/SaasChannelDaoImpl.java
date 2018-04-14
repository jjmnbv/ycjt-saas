package com.beitu.saas.channel.dao.impl;

import com.beitu.saas.channel.param.ChannelStatQueryParam;
import com.beitu.saas.channel.param.SaasChannelParam;
import com.beitu.saas.channel.dao.SaasChannelDao;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.beitu.saas.channel.vo.ChannelStatVo;
import com.fqgj.common.api.Page;
import com.fqgj.common.base.AbstractBaseMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: fenqiguanjia
 * Date: 2018-03-21
 * Time: 20:58:19.266
 */

@Repository
public class SaasChannelDaoImpl extends AbstractBaseMapper<SaasChannelEntity> implements SaasChannelDao {
    @Override
    public List<SaasChannelEntity> selectChannelEntityList(SaasChannelParam saasChannelParam, Page page) {
        Map<String, Object> map = new HashMap<>();
        map.put("saasChannelParam", saasChannelParam);
        if (page != null) {
            page.setTotalCount(this.countChannelEntityList(saasChannelParam));
            map.put("page", page);
        }
        return getSqlSession().selectList(this.getStatement("selectChannelEntityList"), map);
    }

    @Override
    public List<SaasChannelEntity> selectAllChannelEntityList(String merchantCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("merchantCode", merchantCode);
        return getSqlSession().selectList(this.getStatement("selectAllChannelEntityList"), map);    }

    @Override
    public Integer countChannelEntityList(SaasChannelParam saasChannelParam) {
        Map<String, Object> map = new HashMap<>();
        map.put("saasChannelParam", saasChannelParam);
        return getSqlSession().selectOne(this.getStatement("countChannelEntityList"), map);
    }

    @Override
    public SaasChannelEntity selectChannelEntityByChannelCode(String channelCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("channelCode", channelCode);
        return getSqlSession().selectOne(this.getStatement("selectChannelEntityByChannelCode"), map);
    }

    @Override
    public List<ChannelStatVo> selectChannelStatList(ChannelStatQueryParam channelStatQueryParam, Page page) {
        Map<String, Object> map = new HashMap<>();
        map.put("channelStatQueryParam", channelStatQueryParam);
        if (page != null) {
            page.setTotalCount(this.queryTotalChannelStatCount(channelStatQueryParam));
            map.put("page", page);
        }
        return getSqlSession().selectList(this.getStatement("selectChannelStatList"), map);
    }

    @Override
    public Integer queryTotalChannelStatCount(ChannelStatQueryParam channelStatQueryParam) {
        Map<String, Object> paramMap = new HashMap(2);
        paramMap.put("channelStatQueryParam", channelStatQueryParam);
        return getSqlSession().selectOne(this.getStatement("queryTotalChannelStatCount"), paramMap);
    }

    @Override
    public SaasChannelEntity selectDefaultChannelEntityByMerchantCode(String merchantCode, Integer channelType) {
        Map<String, Object> paramMap = new HashMap(4);
        paramMap.put("merchantCode", merchantCode);
        paramMap.put("channelType", channelType);
        return getSqlSession().selectOne(this.getStatement("selectDefaultChannelEntityByMerchantCode"), paramMap);
    }

}