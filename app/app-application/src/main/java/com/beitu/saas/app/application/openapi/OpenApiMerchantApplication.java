package com.beitu.saas.app.application.openapi;

import com.beitu.saas.app.application.openapi.vo.*;
import com.beitu.saas.app.enums.OpenApiOrderPushErrorCodeEnum;
import com.beitu.saas.app.enums.OpenApiOrderPushFromTypeEnum;
import com.beitu.saas.borrower.client.*;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.beitu.saas.channel.enums.ChannelTypeEnum;
import com.beitu.saas.common.consts.TimeConsts;
import com.beitu.saas.finance.client.SaasCreditHistoryService;
import com.beitu.saas.finance.client.enums.CreditConsumeEnum;
import com.beitu.saas.openapi.client.SaasOpenApiOrderInfoLogService;
import com.beitu.saas.openapi.domain.SaasOpenApiOrderInfoLogVo;
import com.beitu.saas.openapi.entity.SaasOpenApiOrderInfoLog;
import com.fqgj.common.utils.TimeUtils;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
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
    
    @Autowired
    private SaasOpenApiOrderInfoLogService saasOpenApiOrderInfoLogService;
    
    @Autowired
    private SaasCreditHistoryService saasCreditHistoryService;
    
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
    
    public void merchantOrderUserProcess(SaasBorrowerRelatedDataVo borrowerRelatedDataVo, OrderPushToSaasVo userAndOrderData, String mobile, String identityNo, CreditConsumeEnum flowConsumeEnum) {
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
        logSuccessByMobile(mobile, OpenApiOrderPushFromTypeEnum.YCJT_APP);
        
        String merchantCode = borrowerRelatedDataVo.getMerchantCode();
        saasCreditHistoryService.addExpenditureCreditHistory(merchantCode, "system", flowConsumeEnum);
        
        OrderPushUserPersonalInfoVo personalInfo = userAndOrderData.getPersonalInfo();
        OrderPushUserWorkInfoVo workInfo = userAndOrderData.getWorkInfo();
        OrderPushUserEmergentContactVo emergentContact = userAndOrderData.getEmergentContact();
        OrderPushUserIdentityInfoVo identityInfo = userAndOrderData.getIdentityInfo();
        OrderPushUserCarrierInfoVo carrierInfo = userAndOrderData.getCarrierInfo();
        OrderPushUserDunningInfoVo dunningInfo = userAndOrderData.getDunningInfo();
        OrderPushUserBmpInfoVo bmpInfo = userAndOrderData.getBmpInfo();
        OrderPushUserTongdunInfoVo tongdunInfo = userAndOrderData.getTongdunInfo();
        List<OrderPushUserLocationInfoVo> locationsInfo = userAndOrderData.getLocationsInfo();
        OrderPushUserContactsInfoVo contactsInfo = userAndOrderData.getContactsInfo();
        
        String borrowerCode = borrowerRelatedDataVo.getBorrowerCode();
        
        openApiUserInfoApplication.addUserPersonalInfo(personalInfo, basicInfo, borrowerCode, orderNumb);
        openApiUserInfoApplication.addUserWorkInfo(workInfo, borrowerCode, orderNumb);
        openApiUserInfoApplication.addUserEmergentContact(emergentContact, borrowerCode, orderNumb);
        openApiUserInfoApplication.addUserIdentityInfo(identityInfo, borrowerCode, orderNumb);
        Long carrierId = openApiCreditInfoApplication.addCreditCarrierInfo(carrierInfo, borrowerCode, merchantCode);
        openApiCreditInfoApplication.addCreditDunningInfo(dunningInfo, borrowerCode, merchantCode, carrierId);
        openApiCreditInfoApplication.addCreditBmpInfo(bmpInfo, borrowerCode, merchantCode);
        openApiCreditInfoApplication.addCreditTongdunInfo(tongdunInfo, borrowerCode, merchantCode);
        openApiCreditInfoApplication.addLocationsInfo(locationsInfo, borrowerCode, merchantCode);
        
        saasCreditHistoryService.addExpenditureCreditHistory(merchantCode, "system", CreditConsumeEnum.FLOW_REPORT);
    }
    
    public Boolean canMatchMerchant(String identityNo, String merchantCode) {
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByIdentityCodeAndMerchantCode(identityNo, merchantCode);
        if (saasBorrowerRealInfoVo != null) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    
    private Boolean logSuccessByMobile(String mobile, OpenApiOrderPushFromTypeEnum from) {
        Date startDate = TimeUtils.appointed(-TimeConsts.ONE_MONTH_DAYS);
        SaasOpenApiOrderInfoLogVo vo = saasOpenApiOrderInfoLogService.getByMobile(mobile, from.getType(), Boolean.FALSE, startDate);
        if (vo == null) {
            return Boolean.FALSE;
        }
        SaasOpenApiOrderInfoLog entity = new SaasOpenApiOrderInfoLog();
        BeanUtils.copyProperties(vo, entity);
        entity.setId(vo.getSaasOpenApiOrderInfoLogId());
        entity.setSuccess(Boolean.TRUE);
        return saasOpenApiOrderInfoLogService.updateById(entity) > 0;
    }
    
}
