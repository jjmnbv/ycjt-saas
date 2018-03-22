package com.beitu.saas.channel.dao.impl;

import com.beitu.saas.channel.param.SaasChannelParam;
import com.beitu.saas.channel.dao.SaasChannelDao;
import com.beitu.saas.channel.entity.SaasChannelEntity;
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
    public Integer countChannelEntityList(SaasChannelParam saasChannelParam) {
        Map<String, Object> map = new HashMap<>();
        map.put("saasChannelParam", saasChannelParam);
        return getSqlSession().selectOne(this.getStatement("countChannelEntityList"), map);
    }

    @Override
    public SaasChannelEntity selectChannelEntityByChannelCode(String channelCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("channelCode", channelCode);
        return getSqlSession().selectOne(this.getStatement("selectChannelEntityList"), map);
    }
}