package com.beitu.saas.intergration.risk.impl;

import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.utils.HttpClientUtil;
import com.beitu.saas.common.utils.UrlUtils;
import com.beitu.saas.intergration.risk.RiskEcommerceService;
import com.beitu.saas.intergration.risk.param.GXBEcommerceCrawlingParam;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.JSONUtils;
import com.fqgj.exception.common.ApplicationException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author xiaochong
 * @create 2018/4/16 下午4:35
 * @description
 */
@Service
public class RiskEcommerceServiceImpl implements RiskEcommerceService {

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private ConfigUtil configUtil;

    private final String authItem = "ecommerce";


    @Override
    public String getEcommerceCrawlingUrl(GXBEcommerceCrawlingParam param) {

        String timestamp = String.valueOf(System.currentTimeMillis());
        String sequenceNo = param.getUserCode();
        String gxbSign = getGXBSign(configUtil.getGXBAppId(), configUtil.getGXBAppSecret(), authItem, timestamp, sequenceNo);
        Map map = new HashMap(10) {{
            put("sequenceNo", sequenceNo);
            put("phone", param.getPhone());
            put("authItem", authItem);
            put("name", param.getName());
            put("idcard", param.getIdcard());
            put("appId", configUtil.getGXBAppId());
            put("timestamp", timestamp);
            put("sign", gxbSign);
        }};
        Map<String, Object> responseMap = null;
        try {
            String response = HttpClientUtil.doPostJson(configUtil.getGXBAuthPath(), JSONUtils.obj2json(map));
            responseMap = JSONUtils.json2map(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (CollectionUtils.isEmpty(responseMap)) {
            throw new ApplicationException("授权失败");
        }
        if (!responseMap.get("retCode").equals(1)) {
            throw new ApplicationException("授权失败");
        }
        String token = (String) ((Map) responseMap.get("data")).get("token");
        Map urlMap = new HashMap() {{
            put("token", token);
            put("returnUrl", param.getReturnUrl());
            put("title", "银柳智能风控系统");
        }};
        //redisClient.set(RedisKeyConsts.SAAS_GXB_ECOMMERCE_TOKE,token,);
        return UrlUtils.appendParams(configUtil.getGXBCrawlingPath(), urlMap);
    }


    private String getGXBSign(String appId, String appSecurity, String authItem, String timestamp, String sequenceNo) {
        return DigestUtils.md5Hex(String.format("%s%s%s%s%s", appId, appSecurity, authItem, timestamp, sequenceNo));
    }

}
