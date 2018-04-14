package com.beitu.saas.app.application.openapi;

import com.beitu.saas.app.application.openapi.thread.PushOrderUserCreditThread;
import com.beitu.saas.app.application.openapi.vo.OrderPushToSaasDataVo;
import com.beitu.saas.app.application.openapi.vo.SaasBorrowerRelatedDataVo;
import com.beitu.saas.app.application.order.OrderRecommendApplication;
import com.beitu.saas.app.enums.OpenApiOrderPushErrorCodeEnum;
import com.beitu.saas.app.enums.OpenApiOrderPushFromTypeEnum;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.consts.TimeConsts;
import com.beitu.saas.common.handle.oss.OSSService;
import com.beitu.saas.common.utils.NetworkUtil;
import com.beitu.saas.common.utils.ThreadPoolUtils;
import com.beitu.saas.openapi.client.SaasOpenApiOrderInfoLogService;
import com.beitu.saas.openapi.domain.SaasOpenApiOrderInfoLogVo;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.JSONUtils;
import com.fqgj.common.utils.MD5;
import com.fqgj.common.utils.TimeUtils;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class OpenApiApplication {
    
    private static final Log LOGGER = LogFactory.getLog(OpenApiApplication.class);
    
    @Autowired
    private OrderRecommendApplication orderRecommendApplication;
    
    @Autowired
    private OpenApiMerchantApplication openApiMerchantApplication;
    
    @Autowired
    private SaasOpenApiOrderInfoLogService saasOpenApiOrderInfoLogService;
    
    @Autowired
    private RedisClient redisClient;
    
    @Autowired
    private OSSService ossService;
    
    @Autowired
    private ConfigUtil configUtil;
    
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
        String redisMobile = redisClient.get(RedisKeyConsts.SAAS_OPEN_API_ORDER_PUSH, mobile);
        if (StringUtils.isNotEmpty(redisMobile) && Objects.equals(redisMobile, mobile)) {
            OpenApiOrderPushErrorCodeEnum errorCodeEnum = OpenApiOrderPushErrorCodeEnum.ORDER_PUSH_IS_PROCESSING;
            LOGGER.warn("************************* 洋葱借条推单处理失败:{} *************************", errorCodeEnum.getMsg());
            throw new ApplicationException(errorCodeEnum);
        }
        redisClient.set(RedisKeyConsts.SAAS_OPEN_API_ORDER_PUSH, mobile, TimeConsts.THIRTY_SECONDS, mobile);
        
        OpenApiOrderPushFromTypeEnum from = OpenApiOrderPushFromTypeEnum.YCJT_APP;
        if (logExistByMobile(mobile, from)) {
            OpenApiOrderPushErrorCodeEnum errorCodeEnum = OpenApiOrderPushErrorCodeEnum.LOG_MOBILE_EXIST_ERROR;
            LOGGER.warn("************************* 洋葱借条推单处理失败:{} *************************", errorCodeEnum.getMsg());
            throw new ApplicationException(errorCodeEnum);
        }
    
        String identityNo = pushData.getIdentityNo();
        Integer zmScore = pushData.getZmScore();
        Map merchantsInfo = orderRecommendApplication.getRecommendMerchantCode(Long.valueOf(zmScore.toString()), identityNo);
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
        
        String url = uploadOrderPushData(mobile, from, requestString);
        Integer flowType = (Integer) merchantsInfo.get("flowType");
        SaasOpenApiOrderInfoLogVo vo = new SaasOpenApiOrderInfoLogVo(mobile, zmScore, identityNo, url, flowType, from.getType(), Boolean.FALSE);
        if (saasOpenApiOrderInfoLogService.addSaasOpenApiOrderInfoLog(vo)) {
            ThreadPoolUtils.getTaskInstance().execute(new PushOrderUserCreditThread(openApiMerchantApplication, borrowerRelatedDataVos ,pushData, flowType));
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    
    public void ipWhiteListValidation(HttpServletRequest request) {
        String sourceIp = null;
        try {
            sourceIp = NetworkUtil.getIpAddress(request);
        } catch (IOException e) {
        }
        if (StringUtils.isEmpty(sourceIp)) {
            LOGGER.warn("************************* 洋葱借条推单来源IP地址获取失败 *************************");
            throw new ApplicationException(OpenApiOrderPushErrorCodeEnum.SOURCE_IP_ACQUIRE_FAILURE);
        }
        LOGGER.info("************************* OpenAPI洋葱借条推单来源IP地址 : {} *************************", sourceIp);
        String ipWhite = configUtil.getOrderPushIpWhiteList();
        if (StringUtils.isEmpty(ipWhite) || Objects.equals(ipWhite, "null")) {
            LOGGER.warn("************************* 洋葱借条推单功能处于关闭状态 *************************");
            throw new ApplicationException(OpenApiOrderPushErrorCodeEnum.ORDER_PUSH_OFF);
        }
        String[] whiteIps = ipWhite.split(",");
        if (whiteIps.length == 0) {
            LOGGER.warn("************************* 洋葱借条推单功能处于关闭状态 *************************");
            throw new ApplicationException(OpenApiOrderPushErrorCodeEnum.ORDER_PUSH_OFF);
        }
        Set<String> set = new HashSet<String>(Arrays.asList(whiteIps));
        if (!set.contains(sourceIp)) {
            LOGGER.warn("************************* 洋葱借条推单来源IP地址非法 *************************");
            throw new ApplicationException(OpenApiOrderPushErrorCodeEnum.ILLEGAL_SOURCE_IP);
        }
    }
    
    private String uploadOrderPushData(String mobile, OpenApiOrderPushFromTypeEnum from, String data) {
        String dataUrl = "openApiOrderPushData/";
        if (configUtil.isServerTest()) {
            dataUrl += "test/";
        }
        String userTime = mobile + "_" + new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        dataUrl += from.getCode() + "_" + userTime + "_" + MD5.md5(userTime + System.currentTimeMillis()) + ".json";
        return ossService.uploadFile(dataUrl, data);
    }
    
    private Boolean logExistByMobile(String mobile, OpenApiOrderPushFromTypeEnum from) {
        Date startDate = TimeUtils.appointed(-TimeConsts.ONE_MONTH_DAYS);
        return saasOpenApiOrderInfoLogService.getByMobile(mobile, from.getType(), Boolean.TRUE, startDate) != null;
    }
    
}
