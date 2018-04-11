package com.beitu.saas.app.application.order;

import com.beitu.saas.app.application.openapi.OpenApiOrderApplication;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.common.utils.SaaSRedisClient;
import com.beitu.saas.common.utils.SortUtil;
import com.beitu.saas.merchant.client.SaasMerchantFlowConfigService;
import com.fqgj.common.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author xiaochong
 * @create 2018/4/9 下午2:57
 * @description
 */
@Component
public class OrderRecommendApplication {

    @Autowired
    private SaaSRedisClient saaSRedisClient;

    @Autowired
    private SaasMerchantFlowConfigService saasMerchantFlowConfigService;

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private OpenApiOrderApplication openApiOrderApplication;

    public Map getRecommendMerchantCode(Long zmScore, String identityNo) {
        Map map = getRecommendMerchantCode(zmScore, identityNo, null);
        List list = (List) map.get("list");
        if (CollectionUtils.isEmpty(list)) {
            Integer flowType = (Integer) map.get("flowType");
            return getRecommendMerchantCode(zmScore, identityNo, 3 - flowType);
        }
        return map;
    }

    public Map getRecommendMerchantCode(Long zmScore, String identityNo, Integer type) {
        Long modulo = 10L;
        List list = new ArrayList();
        Long totalNum = saaSRedisClient.getNumber(RedisKeyConsts.SAAS_RECOMMEND_NUM_DAY);
        Map<String, String> map = saaSRedisClient.hNumGetAll(RedisKeyConsts.SAAS_MERCHANT_RECOMMEND_NUM_DAY);
        Integer flowType;//1共享2买断
        if (type == null) {
            flowType = 1;
            int flowRatio = configUtil.getRecommendFlowRatio();
            if ((totalNum % modulo) > flowRatio) {
                flowType = 2;
            }
        } else {
            flowType = type;
        }
        Integer zmType = 1;
        if (zmScore >= 610) {
            zmType = 2;
        }
        Map<String, Integer> flowNumMap = saasMerchantFlowConfigService.getMerchantCodeFlowNumForMap(zmType, flowType);
        if (CollectionUtils.isEmpty(flowNumMap)) {
            Integer finalType = flowType;
            return new HashMap(2) {{
                put("list", list);
                put("flowType", finalType);
            }};
        }
        Map<String, Double> unSortMap = new HashMap<>();
        flowNumMap.forEach((k, v) -> {
            double ratio = map.containsKey(k) ? (Double.valueOf(map.get(k)) / v) : 0;
            if (ratio < 1) {
                unSortMap.put(k, ratio);
            }
        });
        if (CollectionUtils.isEmpty(unSortMap)) {
            Integer finalType = flowType;
            return new HashMap(2) {{
                put("flowType", finalType);
                put("list", list);
            }};
        }
        Map mapASC = SortUtil.sortMapByValueASC(unSortMap);
        Iterator it = mapASC.entrySet().iterator();

        int i = 1;
        while (it.hasNext()) {
            i++;
            Map.Entry entry = (Map.Entry) it.next();
            if (!openApiOrderApplication.canMatchMerchant(identityNo, entry.getKey().toString())) {
                continue;
            }
            saaSRedisClient.hIncrBy(RedisKeyConsts.SAAS_MERCHANT_RECOMMEND_NUM_DAY, entry.getKey().toString(), 1L);
            if (flowType == 2) {
                list.add(entry.getKey());
                break;
            }
            if (flowType == 1) {
                list.add(entry.getKey());
                if (i == configUtil.getShareNum()) {
                    break;
                }
            }
        }
        if (CollectionUtils.isNotEmpty(list)) {
            saaSRedisClient.incrBy(RedisKeyConsts.SAAS_RECOMMEND_NUM_DAY, 1L);
            saaSRedisClient.expireAt(RedisKeyConsts.SAAS_RECOMMEND_NUM_DAY, DateUtil.getNextDayBeginTime());
            saaSRedisClient.expireAt(RedisKeyConsts.SAAS_MERCHANT_RECOMMEND_NUM_DAY, DateUtil.getNextDayBeginTime());
        }
        Integer finalFlowType = flowType;
        return new HashMap(2) {{
            put("flowType", finalFlowType);
            put("list", list);
        }};
    }
}
