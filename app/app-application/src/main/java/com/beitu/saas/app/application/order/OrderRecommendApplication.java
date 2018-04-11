package com.beitu.saas.app.application.order;

import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.common.utils.SaaSRedisClient;
import com.beitu.saas.common.utils.SortUtil;
import com.beitu.saas.merchant.client.SaasMerchantFlowConfigService;
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

    public Map getRecommendMerchantCode(Long zmScore) {
        Long modulo = 10L;
        Long totalNum = saaSRedisClient.getNumber(RedisKeyConsts.SAAS_RECOMMEND_NUM_DAY);
        Map<String, String> map = saaSRedisClient.hNumGetAll(RedisKeyConsts.SAAS_MERCHANT_RECOMMEND_NUM_DAY);

        int flowRatio = configUtil.getRecommendFlowRatio();
        Integer flowType = 1;//1共享2买断
        Integer zmType = 1;
        if ((totalNum % modulo) > flowRatio) {
            flowType = 2;
        }
        if (zmScore >= 610) {
            zmType = 2;
        }
        Map<String, Integer> flowNumMap = saasMerchantFlowConfigService.getMerchantCodeFlowNumForMap(zmType, flowType);
        if (null == flowNumMap) {
            if (flowType == 1) {
                flowNumMap = saasMerchantFlowConfigService.getMerchantCodeFlowNumForMap(zmType, 2);
            } else {
                flowNumMap = saasMerchantFlowConfigService.getMerchantCodeFlowNumForMap(zmType, 1);
            }
        }
        Map<String, Double> unSortMap = new HashMap<>();
        flowNumMap.forEach((k, v) -> {
            double ratio = map.containsKey(k) ? (Double.valueOf(map.get(k)) / v) : 0;
            if (ratio < 1) {
                unSortMap.put(k, ratio);
            }
        });
        Map mapASC = SortUtil.sortMapByValueASC(unSortMap);
        Iterator it = mapASC.entrySet().iterator();
        List list = new ArrayList();
        int i = 1;
        while (it.hasNext()) {
            i++;
            Map.Entry entry = (Map.Entry) it.next();
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
        saaSRedisClient.incrBy(RedisKeyConsts.SAAS_RECOMMEND_NUM_DAY, 1L);
        saaSRedisClient.expireAt(RedisKeyConsts.SAAS_RECOMMEND_NUM_DAY, DateUtil.getNextDayBeginTime());
        saaSRedisClient.expireAt(RedisKeyConsts.SAAS_MERCHANT_RECOMMEND_NUM_DAY, DateUtil.getNextDayBeginTime());
        Integer finalFlowType = flowType;
        return new HashMap(2) {{
            put("flowType", finalFlowType);
            put("list", list);
        }};
    }
}
