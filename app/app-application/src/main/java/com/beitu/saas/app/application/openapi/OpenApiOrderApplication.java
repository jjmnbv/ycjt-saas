package com.beitu.saas.app.application.openapi;

import com.beitu.saas.app.application.openapi.vo.*;
import com.beitu.saas.app.application.order.OrderRecommendApplication;
import com.beitu.saas.app.enums.OpenApiOrderPushErrorCodeEnum;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.client.SaasBorrowerService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.borrower.entity.SaasBorrower;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.beitu.saas.channel.enums.ChannelTypeEnum;
import com.beitu.saas.common.utils.OrderNoUtil;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.entity.SaasOrder;
import com.beitu.saas.order.enums.OrderStatusEnum;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.JSONUtils;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    
    @Autowired
    private SaasBorrowerService saasBorrowerService;
    
    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;
    
    @Autowired
    private SaasOrderService saasOrderService;
    
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
        List<String> merchantCodes = (List<String>)merchantsInfo.get("list");
        if (CollectionUtils.isEmpty(merchantCodes)) {
            OpenApiOrderPushErrorCodeEnum errorCodeEnum = OpenApiOrderPushErrorCodeEnum.NO_MATCHED_MERCHANT;
            LOGGER.warn("************************* 洋葱借条推单处理失败:{} Mobile:{} IdentityNo:{} *************************", errorCodeEnum.getMsg(), mobile, identityNo);
            throw new ApplicationException(errorCodeEnum);
        }
        
        List<SaasBorrowerRelatedDataVo> borrowerRelatedDataVos = orderPushUserRegister(pushData, merchantCodes);
        if (CollectionUtils.isEmpty(borrowerRelatedDataVos)) {
            OpenApiOrderPushErrorCodeEnum errorCodeEnum = OpenApiOrderPushErrorCodeEnum.NO_MATCHED_MERCHANT;
            LOGGER.warn("************************* 洋葱借条推单处理失败:{} Mobile:{} IdentityNo:{} *************************", errorCodeEnum.getMsg(), mobile, identityNo);
            throw new ApplicationException(errorCodeEnum);
        }
        
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
        
        for (int i = 0; i < borrowerRelatedDataVos.size(); i++) {
            SaasBorrowerRelatedDataVo vo = borrowerRelatedDataVos.get(i);
            try {
                userInfoAndOrderInfoProcess(vo, userAndOrderData, mobile, identityNo);
            } catch (Exception e) {
                LOGGER.warn("************************* 洋葱借条推单处理失败 Mobile:{} IdentityNo:{} Merchant:{} Borrower:{} CAUSE:{} *************************", mobile, identityNo, vo.getMerchantCode(), vo.getBorrowerCode(), e);
            }
        }
        
        // TODO: 2018/4/11 根据商户吸量类型flowType记录推单数据
        Integer flowType = (Integer)merchantsInfo.get("flowType");
        
        return null;
    }
    
    @Transactional
    public void userInfoAndOrderInfoProcess(SaasBorrowerRelatedDataVo borrowerRelatedDataVo, OrderPushToSaasVo userAndOrderData, String mobile, String identityNo) {
        OrderPushUserBasicInfoVo basicInfo = userAndOrderData.getBasicInfo();
        if (basicInfo == null) {
            OpenApiOrderPushErrorCodeEnum errorCodeEnum = OpenApiOrderPushErrorCodeEnum.USER_BASIC_INFO_MISS;
            LOGGER.warn("************************* 洋葱借条推单处理失败:{} Mobile:{} IdentityNo:{} *************************", errorCodeEnum.getMsg(), mobile, identityNo);
        }
        OrderPushUserOrderInfoVo orderInfo = userAndOrderData.getOrderInfo();
        if (orderInfo == null) {
            OpenApiOrderPushErrorCodeEnum errorCodeEnum = OpenApiOrderPushErrorCodeEnum.USER_ORDER_INFO_MISS;
            LOGGER.warn("************************* 洋葱借条推单处理失败:{} Mobile:{} IdentityNo:{} *************************", errorCodeEnum.getMsg(), mobile, identityNo);
        }
        String merchantCode = borrowerRelatedDataVo.getMerchantCode();
        String borrowerCode = borrowerRelatedDataVo.getBorrowerCode();
        String name = basicInfo.getName();
        saasBorrowerRealInfoService.create(merchantCode, borrowerCode, name, identityNo);
        
        String orderNumb = OrderNoUtil.makeOrderNum();
        String channelCode = borrowerRelatedDataVo.getChannelCode();
        SaasOrder saasOrder = new SaasOrder();
        BeanUtils.copyProperties(orderInfo, saasOrder);
        saasOrder.setOrderNumb(orderNumb);
        saasOrder.setMerchantCode(merchantCode);
        saasOrder.setChannelCode(channelCode);
        saasOrder.setBorrowerCode(borrowerCode);
        saasOrder.setOrderStatus(OrderStatusEnum.SUBMIT_PRELIMINARY_REVIEW.getCode());
        saasOrderService.create(saasOrder);
        
        
        
        
        
        
    }
    
    private List<SaasBorrowerRelatedDataVo> orderPushUserRegister(OrderPushToSaasDataVo data, List<String> merchantCodes) {
        List<SaasBorrowerRelatedDataVo> borrowerCodes = new ArrayList<>();
        for (int i = 0; i < merchantCodes.size(); i++) {
            String merchantCode = merchantCodes.get(i);
            String identityNo = data.getIdentityNo();
            if (!canMatchMerchant(identityNo, merchantCode)) {
                continue;
            }
            SaasChannelEntity channel = saasChannelService.getDefaultSaasChannelByMerchantCode(merchantCode, ChannelTypeEnum.RECOMMEND_DEFINED.getType());
            if (channel == null || StringUtils.isEmpty(channel.getChannelCode())) {
                continue;
            }
            String channelCode = channel.getChannelCode();
            String mobile = data.getMobile();
            String borrowerCode = getBorrowerCodeByInfo(mobile, identityNo, merchantCode);
            if (borrowerCode == null) {
                borrowerCode = registerUser(mobile, merchantCode, channelCode);
            }
            borrowerCodes.add(new SaasBorrowerRelatedDataVo(borrowerCode, merchantCode, channelCode));
        }
        return borrowerCodes;
    }
    
    public Boolean canMatchMerchant(String identityNo, String merchantCode) {
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByIdentityCodeAndMerchantCode(identityNo, merchantCode);
        if (saasBorrowerRealInfoVo != null) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    
    private String getBorrowerCodeByInfo(String mobile, String identityNo, String merchantCode) {
        SaasBorrowerVo borrowerVo = saasBorrowerService.getByMobileAndMerchantCode(mobile, merchantCode);
        if (borrowerVo != null) {
            return borrowerVo.getBorrowerCode();
        }
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByIdentityCodeAndMerchantCode(identityNo, merchantCode);
        if (saasBorrowerRealInfoVo != null) {
            return saasBorrowerRealInfoVo.getBorrowerCode();
        }
        return null;
    }
    
    private String registerUser(String mobile, String merchantCode, String channelCode) {
        SaasBorrowerVo saasBorrowerVo = new SaasBorrowerVo();
        saasBorrowerVo.setMobile(mobile);
        saasBorrowerVo.setMerchantCode(merchantCode);
        saasBorrowerVo.setChannelCode(channelCode);
        SaasBorrower saasBorrower = saasBorrowerService.create(saasBorrowerVo);
        return saasBorrower.getBorrowerCode();
    }

}
