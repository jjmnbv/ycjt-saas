package com.beitu.saas.sms.service.sms.impl;

import com.alibaba.fastjson.JSON;
import com.beitu.saas.sms.service.sms.MsgService;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.beitu.saas.sms.dao.SmsBusinessRefDao;
import com.beitu.saas.sms.model.BusinessRefInfo;
import com.beitu.saas.sms.util.CacheKey;
import com.google.common.collect.ArrayListMultimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class MsgServiceImpl implements MsgService {

    private static Log LOGGER = LogFactory.getLog(MsgService.class);

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private SmsBusinessRefDao businessRefDao;

    /**
     * 加载 功能 服务商 模板 缓存信息
     */
    @Override
    @PostConstruct
    public void loadCache() {
        // 获取所有的业务功能
        List<BusinessRefInfo> allConfigs = businessRefDao.getAll();
        if (CollectionUtils.isEmpty(allConfigs)) {
            LOGGER.info("load config info failed!");
            return;
        }
        // 删除失效配置
        ArrayListMultimap<String, BusinessRefInfo> multimapWithBizCode = ArrayListMultimap.create(); // 以bizCode作为Key
        for (BusinessRefInfo info : allConfigs) {
            // 以biz_code作为key
            String bizCodeKey = CacheKey.getKeyOfMessageConfig(info.getBizCode());
            if (info.getSendFlag() == 0) { // 移除失效配置
                redisClient.del(bizCodeKey);
            } else {
                multimapWithBizCode.put(bizCodeKey, info);
            }
        }
        // 缓存有效配置
        for (String bizCode : multimapWithBizCode.keys()) {
            redisClient.del(bizCode);
            redisClient.set(bizCode, JSON.toJSONString(multimapWithBizCode.get(bizCode)));
        }
    }

}
