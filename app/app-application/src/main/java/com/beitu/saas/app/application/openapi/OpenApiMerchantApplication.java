package com.beitu.saas.app.application.openapi;

import com.beitu.saas.app.application.openapi.vo.*;
import com.beitu.saas.app.enums.OpenApiOrderPushErrorCodeEnum;
import com.beitu.saas.borrower.client.*;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.beitu.saas.channel.enums.ChannelTypeEnum;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OpenApiMerchantApplication {
    
    private static final Log LOGGER = LogFactory.getLog(OpenApiMerchantApplication.class);
    
    @Autowired
    private SaasChannelService saasChannelService;
    
    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;
    
    @Autowired
    private OpenApiUserInfoApplication openApiUserInfoApplication;
    
    @Autowired
    private OpenApiCreditInfoApplication openApiCreditInfoApplication;
    
    public List<SaasBorrowerRelatedDataVo> merchantChannelRegister(OrderPushToSaasDataVo data, List<String> merchantCodes) {
        List<SaasBorrowerRelatedDataVo> borrowerRelatedDataVos = new ArrayList<>();
        for (int i = 0; i < merchantCodes.size(); i++) {
            String merchantCode = merchantCodes.get(i);
            String identityNo = data.getIdentityNo();
            if (!canMatchMerchant(identityNo, merchantCode)) {
                continue;
            }
            SaasChannelEntity channel = saasChannelService.getDefaultSaasChannelByMerchantCode(merchantCode, ChannelTypeEnum.RECOMMEND_DEFINED.getType());
            if (channel == null || StringUtils.isEmpty(channel.getChannelCode())) {
                LOGGER.warn("************************* 洋葱借条推单处理失败: 机构系统推荐订单渠道缺失 Merchant:{} *************************", merchantCode);
                continue;
            }
            String channelCode = channel.getChannelCode();
            String mobile = data.getMobile();
            String borrowerCode = openApiUserInfoApplication.getBorrowerCodeByInfo(mobile, identityNo, merchantCode);
            if (borrowerCode == null) {
                borrowerCode = openApiUserInfoApplication.registerUser(mobile, merchantCode, channelCode);
            }
            borrowerRelatedDataVos.add(new SaasBorrowerRelatedDataVo(borrowerCode, merchantCode, channelCode));
        }
        return borrowerRelatedDataVos;
    }
    
    public void merchantOrderUserProcess(SaasBorrowerRelatedDataVo borrowerRelatedDataVo, OrderPushToSaasVo userAndOrderData, String mobile, String identityNo, Integer flowType) {
        OrderPushUserBasicInfoVo basicInfo = userAndOrderData.getBasicInfo();
        if (basicInfo == null) {
            OpenApiOrderPushErrorCodeEnum errorCodeEnum = OpenApiOrderPushErrorCodeEnum.USER_BASIC_INFO_MISS;
            LOGGER.warn("************************* 洋葱借条推单处理失败: {} Mobile:{} IdentityNo:{} *************************", errorCodeEnum.getMsg(), mobile, identityNo);
        }
        OrderPushUserOrderInfoVo orderInfo = userAndOrderData.getOrderInfo();
        if (orderInfo == null) {
            OpenApiOrderPushErrorCodeEnum errorCodeEnum = OpenApiOrderPushErrorCodeEnum.USER_ORDER_INFO_MISS;
            LOGGER.warn("************************* 洋葱借条推单处理失败: {} Mobile:{} IdentityNo:{} *************************", errorCodeEnum.getMsg(), mobile, identityNo);
        }
        String orderNumb = openApiUserInfoApplication.userRealNameAndOrderCreate(borrowerRelatedDataVo, basicInfo, orderInfo);
    
        /////////////////////////////////////////////////////////////////////////
        // TODO: 2018/4/13 推送成功/计费点
        /////////////////////////////////////////////////////////////////////////
        
        OrderPushUserPersonalInfoVo personalInfo = userAndOrderData.getPersonalInfo();
        OrderPushUserWorkInfoVo workInfo = userAndOrderData.getWorkInfo();
        OrderPushUserEmergentContactVo emergentContact = userAndOrderData.getEmergentContact();
        OrderPushUserIdentityInfoVo identityInfo = userAndOrderData.getIdentityInfo();
        OrderPushUserCarrierInfoVo carrierInfo = userAndOrderData.getCarrierInfo();
        OrderPushUserDunningInfoVo dunningInfo = userAndOrderData.getDunningInfo();
        OrderPushUserBmpInfoVo bmpInfo = userAndOrderData.getBmpInfo();
        OrderPushUserTongdunInfoVo tongdunInfo = userAndOrderData.getTongdunInfo();
        
        String merchantCode = borrowerRelatedDataVo.getMerchantCode();
        String borrowerCode = borrowerRelatedDataVo.getBorrowerCode();
        
        openApiUserInfoApplication.addUserPersonalInfo(personalInfo, basicInfo, borrowerCode, orderNumb);
        openApiUserInfoApplication.addUserWorkInfo(workInfo, borrowerCode, orderNumb);
        openApiUserInfoApplication.addUserEmergentContact(emergentContact, borrowerCode, orderNumb);
        openApiUserInfoApplication.addUserIdentityInfo(identityInfo, borrowerCode, orderNumb);
        Long carrierId = openApiCreditInfoApplication.addCreditCarrierInfo(carrierInfo, borrowerCode, merchantCode);
        openApiCreditInfoApplication.addCreditDunningInfo(dunningInfo, borrowerCode, merchantCode, carrierId);
        openApiCreditInfoApplication.addCreditBmpInfo(bmpInfo, borrowerCode, merchantCode);
        openApiCreditInfoApplication.addCreditTongdunInfo(tongdunInfo, borrowerCode, merchantCode);
    }
    
    public Boolean canMatchMerchant(String identityNo, String merchantCode) {
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByIdentityCodeAndMerchantCode(identityNo, merchantCode);
        if (saasBorrowerRealInfoVo != null) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    
}
