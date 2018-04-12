package com.beitu.saas.app.application.openapi;

import com.beitu.saas.app.application.openapi.vo.*;
import com.beitu.saas.app.application.order.OrderRecommendApplication;
import com.beitu.saas.app.enums.OpenApiOrderPushErrorCodeEnum;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.JSONUtils;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class OpenApiApplication {
    
    private static final Log LOGGER = LogFactory.getLog(OpenApiApplication.class);
    
    @Autowired
    private OrderRecommendApplication orderRecommendApplication;
    
    @Autowired
    private OpenApiMerchantApplication openApiMerchantApplication;
    
    public Boolean ycjtOrderPushProcess(String requestString) {
        OrderPushToSaasDataVo pushData;
        try {
            pushData = JSONUtils.json2pojoAndOffUnknownField(requestString, OrderPushToSaasDataVo.class);
        } catch (Exception e) {
            LOGGER.warn("************************* 洋葱借条推单处理失败:{} *************************", e);
            throw new ApplicationException(OpenApiOrderPushErrorCodeEnum.DATA_PARSE_ERROR);
        }
        if (pushData == null) {
            OpenApiOrderPushErrorCodeEnum errorCodeEnum = OpenApiOrderPushErrorCodeEnum.DATA_PARSE_ERROR;
            LOGGER.warn("************************* 洋葱借条推单处理失败:{} *************************", errorCodeEnum.getMsg());
            throw new ApplicationException(errorCodeEnum);
        }
        String mobile = pushData.getMobile();
        String identityNo = pushData.getIdentityNo();
        
        // TODO: 2018/4/11 根据推单记录数据判断是否继续进行推单操作
        
        Map merchantsInfo = orderRecommendApplication.getRecommendMerchantCode(Long.valueOf(pushData.getZmScore().toString()), identityNo);
        List<String> merchantCodes = (List<String>) merchantsInfo.get("list");
        if (CollectionUtils.isEmpty(merchantCodes)) {
            OpenApiOrderPushErrorCodeEnum errorCodeEnum = OpenApiOrderPushErrorCodeEnum.NO_MATCHED_MERCHANT;
            LOGGER.warn("************************* 洋葱借条推单处理失败:{} Mobile:{} IdentityNo:{} *************************", errorCodeEnum.getMsg(), mobile, identityNo);
            throw new ApplicationException(errorCodeEnum);
        }
        
        List<SaasBorrowerRelatedDataVo> borrowerRelatedDataVos = openApiMerchantApplication.merchantChannelRegister(pushData, merchantCodes);
        if (CollectionUtils.isEmpty(borrowerRelatedDataVos)) {
            OpenApiOrderPushErrorCodeEnum errorCodeEnum = OpenApiOrderPushErrorCodeEnum.NO_MATCHED_MERCHANT;
            LOGGER.warn("************************* 洋葱借条推单处理失败:{} Mobile:{} IdentityNo:{} *************************", errorCodeEnum.getMsg(), mobile, identityNo);
            throw new ApplicationException(errorCodeEnum);
        }
        
        
        // TODO: 2018/4/12 子线程来处理数据
        /////////////////////////////////////////////////////////////////////////
        
        OrderPushToSaasVo userAndOrderData;
        try {
            userAndOrderData = JSONUtils.json2pojoAndOffUnknownField(pushData.getData(), OrderPushToSaasVo.class);
        } catch (Exception e) {
            LOGGER.warn("************************* 洋葱借条推单处理失败 Mobile:{} IdentityNo:{} CAUSE:{} *************************", mobile, identityNo, e);
            throw new ApplicationException(OpenApiOrderPushErrorCodeEnum.DATA_PARSE_ERROR);
        }
        if (userAndOrderData == null) {
            OpenApiOrderPushErrorCodeEnum errorCodeEnum = OpenApiOrderPushErrorCodeEnum.DATA_PARSE_ERROR;
            LOGGER.warn("************************* 洋葱借条推单处理失败:{} Mobile:{} IdentityNo:{} *************************", errorCodeEnum.getMsg(), mobile, identityNo);
            throw new ApplicationException(errorCodeEnum);
        }
        
        Integer count = 0;
        for (int i = 0; i < borrowerRelatedDataVos.size(); i++) {
            SaasBorrowerRelatedDataVo vo = borrowerRelatedDataVos.get(i);
            try {
                openApiMerchantApplication.merchantOrderUserProcess(vo, userAndOrderData, mobile, identityNo);
                count++;
            } catch (Exception e) {
                LOGGER.warn("************************* 商户推单处理失败 Mobile:{} IdentityNo:{} Merchant:{} Borrower:{} CAUSE:{} *************************", mobile, identityNo, vo.getMerchantCode(), vo.getBorrowerCode(), e);
            }
        }
        
        /////////////////////////////////////////////////////////////////////////
        
        
        // TODO: 2018/4/11 根据商户吸量类型flowType记录推单数据
        Integer flowType = (Integer) merchantsInfo.get("flowType");
        
        return borrowerRelatedDataVos.size() > 0;
    }
    
}
