package com.beitu.saas.app.application.openapi;

import com.beitu.saas.app.application.openapi.vo.OrderPushToSaasDataVo;
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
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.JSONUtils;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    
    public Boolean ycjtOrderPushProcess(String requestString) {
        LOGGER.info("************************* 洋葱借条推单处理开始 *************************");
        OrderPushToSaasDataVo pushData;
        try {
            pushData = JSONUtils.json2pojoAndOffUnknownField(requestString, OrderPushToSaasDataVo.class);
        } catch (Exception e) {
            LOGGER.warn("************************* 洋葱借条推单处理失败:{} *************************", e);
            throw new ApplicationException(OpenApiOrderPushErrorCodeEnum.DATA_PARSE_ERROR);
        }
        Map merchantsInfo = orderRecommendApplication.getRecommendMerchantCode(Long.valueOf(pushData.getZmScore().toString()),pushData.getIdentityNo());
        List<String> merchantCodes = (List<String>)merchantsInfo.get("list");
        if (CollectionUtils.isEmpty(merchantCodes)) {
            OpenApiOrderPushErrorCodeEnum errorCodeEnum = OpenApiOrderPushErrorCodeEnum.NO_MATCHED_MERCHANT;
            LOGGER.warn("************************* 洋葱借条推单处理失败:{} *************************", errorCodeEnum.getMsg());
            throw new ApplicationException(errorCodeEnum);
        }
        List<String> borrowerCodes = orderPushUserRegisterAndRealName(pushData, merchantCodes);
    
    
        
        
        
    
//        String name = data.getName();
//        saasBorrowerRealInfoService.create(merchantCode, borrowerCode, name, identityNo);
        
        
        
        
        // TODO: 2018/4/11 根据商户吸量类型flowType记录推单数据
        Integer flowType = (Integer)merchantsInfo.get("flowType");
        
        
        LOGGER.info("************************* 洋葱借条推单处理成功 *************************");
        return null;
    }
    
    private List<String> orderPushUserRegisterAndRealName(OrderPushToSaasDataVo data, List<String> merchantCodes) {
        List<String> borrowerCodes = new ArrayList<>();
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
            borrowerCodes.add(borrowerCode);
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
