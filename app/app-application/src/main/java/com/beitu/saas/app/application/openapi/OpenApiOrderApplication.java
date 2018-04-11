package com.beitu.saas.app.application.openapi;

import com.beitu.saas.app.application.openapi.vo.OrderPushToSaasDataVo;
import com.beitu.saas.app.application.order.OrderRecommendApplication;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.beitu.saas.channel.enums.ChannelTypeEnum;
import com.beitu.saas.common.utils.StringUtil;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.JSONUtils;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author linanjun
 * @create 2018/3/30 下午4:24
 * @description
 */
@Component
public class OpenApiOrderApplication {

    private static final Log LOGGER = LogFactory.getLog(OpenApiOrderApplication.class);
    
    @Autowired
    private OrderRecommendApplication orderRecommendApplication;
    
    @Autowired
    private SaasChannelService saasChannelService;

    public Boolean ycjtOrderPushProcess(String requestString) {
        OrderPushToSaasDataVo pushData;
        try {
            pushData = JSONUtils.json2pojoAndOffUnknownField(requestString, OrderPushToSaasDataVo.class);
        } catch (Exception e) {
            throw new ApplicationException("数据解析失败");
        }
        List<String> merchantCodes = orderRecommendApplication.getRecommendMerchantCode(Long.valueOf(pushData.getZmScore().toString()));
        if (CollectionUtils.isEmpty(merchantCodes)) {
            throw new ApplicationException("未找到匹配推单对象机构");
        }
        
        
        
        
        
        
        pushData.getZmScore();
        
        return null;
    }
    
    private Boolean orderPushUserRegister(OrderPushToSaasDataVo orderPushToSaasDataVo, List<String> merchantCodes) {
        for (int i = 0; i < merchantCodes.size(); i++) {
            String merchantCode = merchantCodes.get(i);
            SaasChannelEntity channel = saasChannelService.getDefaultSaasChannelByMerchantCode(merchantCode, ChannelTypeEnum.RECOMMEND_DEFINED.getType());
            if (channel == null || StringUtils.isEmpty(channel.getChannelCode())) {
                continue;
            }
            String channelCode = channel.getChannelCode();
            
        }
    }

}
