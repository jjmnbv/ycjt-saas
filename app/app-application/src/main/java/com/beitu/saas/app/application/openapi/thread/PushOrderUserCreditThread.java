package com.beitu.saas.app.application.openapi.thread;

import com.beitu.saas.app.application.openapi.OpenApiMerchantApplication;
import com.beitu.saas.app.application.openapi.vo.OrderPushToSaasDataVo;
import com.beitu.saas.app.application.openapi.vo.OrderPushToSaasVo;
import com.beitu.saas.app.application.openapi.vo.SaasBorrowerRelatedDataVo;
import com.beitu.saas.app.enums.OpenApiOrderPushErrorCodeEnum;
import com.beitu.saas.finance.client.enums.CreditConsumeEnum;
import com.fqgj.common.utils.JSONUtils;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;

import java.util.List;
import java.util.Objects;

public class PushOrderUserCreditThread implements Runnable {
    
    private static final Log LOGGER = LogFactory.getLog(PushOrderUserCreditThread.class);
    
    private OpenApiMerchantApplication openApiMerchantApplication;
    
    private List<SaasBorrowerRelatedDataVo> borrowerRelatedDataVos;
    
    private OrderPushToSaasDataVo pushData;
    
    private Integer flowType;
    
    public PushOrderUserCreditThread(OpenApiMerchantApplication openApiMerchantApplication, List<SaasBorrowerRelatedDataVo> borrowerRelatedDataVos, OrderPushToSaasDataVo pushData, Integer flowType) {
        this.openApiMerchantApplication = openApiMerchantApplication;
        this.borrowerRelatedDataVos = borrowerRelatedDataVos;
        this.pushData = pushData;
        this.flowType = flowType;
    }
    
    @Override
    public void run() {
        String mobile = pushData.getMobile();
        String identityNo = pushData.getIdentityNo();
        Integer zmScore = pushData.getZmScore();
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
        
        CreditConsumeEnum consumeEnum = getConsumeTypeBy(flowType, zmScore);
        for (int i = 0; i < borrowerRelatedDataVos.size(); i++) {
            SaasBorrowerRelatedDataVo vo = borrowerRelatedDataVos.get(i);
            try {
                openApiMerchantApplication.merchantOrderUserProcess(vo, userAndOrderData, mobile, identityNo, consumeEnum);
            } catch (Exception e) {
                LOGGER.warn("************************* 商户推单处理失败 Mobile:{} IdentityNo:{} Merchant:{} Borrower:{} CAUSE:{} *************************", mobile, identityNo, vo.getMerchantCode(), vo.getBorrowerCode(), e);
            }
        }
    }
    
    private CreditConsumeEnum getConsumeTypeBy(Integer flowType, Integer zmScore) {
        if (Objects.equals(flowType, 1)) {
            if (zmScore < 610) {
                return CreditConsumeEnum.FLOW_SHARED_610_DOWN;
            } else {
                return CreditConsumeEnum.FLOW_SHARED_610_UP;
            }
        }
        if (zmScore < 610) {
            return CreditConsumeEnum.FLOW_ALONE_610_DOWN;
        }
        return CreditConsumeEnum.FLOW_ALONE_610_UP;
    }
}
