package com.beitu.saas.app.application.order;

import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.consts.RedisKeyConsts;
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

    public List getRecommendMerchantCode(Long zmScore) {
        Long modulo = 10L;
        Long totalNum = saaSRedisClient.getNumber(RedisKeyConsts.SAAS_RECOMMEND_NUM_DAY);
        Map<String, Long> map = saaSRedisClient.hNumGetAll(RedisKeyConsts.SAAS_MERCHANT_RECOMMEND_NUM_DAY);
        int flowRatio = configUtil.getRecommendFlowRatio();
        Integer flowType = 1;//1共享2买断
        Integer zmType = 1;
        if ((totalNum % modulo) > flowRatio) {
            flowType = 2;
        }
        if (zmScore > 610) {
            zmType = 2;
        }
        Map<String, Long> flowNumMap = saasMerchantFlowConfigService.getMerchantCodeFlowNumForMap(zmType, flowType);
        flowNumMap.forEach((k, v) -> flowNumMap.replace(k, map.containsKey(k) ? map.get(k) / v : 0));
        Map mapASC = SortUtil.sortMapByValueASC(flowNumMap);
        Iterator it = mapASC.entrySet().iterator();
        List list = new ArrayList();
        int i = 1;
        while (it.hasNext()) {
            i++;
            Map.Entry entry = (Map.Entry) it.next();
            if (flowType == 2) {
                list.add(entry.getKey());
                break;
            }
            if (flowType == 1) {
                list.add(entry.getKey());
                if (i == 3) {
                    break;
                }
            }
        }
        return list;

    }
}
