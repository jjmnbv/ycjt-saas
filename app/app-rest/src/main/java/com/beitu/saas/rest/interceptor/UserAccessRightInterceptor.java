package com.beitu.saas.rest.interceptor;


import com.beitu.saas.app.annotations.HasPermission;
import com.beitu.saas.app.annotations.IgnoreRepeatRequest;
import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.application.auth.RoleApplication;
import com.beitu.saas.app.application.channel.SaasChannelApplication;
import com.beitu.saas.app.common.RequestBasicInfo;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.app.common.RequestUserInfo;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.auth.service.SaasAdminService;
import com.beitu.saas.borrower.client.SaasBorrowerService;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.domain.SaasH5ChannelVo;
import com.beitu.saas.channel.enums.ChannelErrorCodeEnum;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.enums.RestCodeEnum;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.base.services.redis.TimeConsts;
import com.fqgj.common.utils.JSONUtils;
import com.fqgj.common.utils.MD5;
import com.fqgj.exception.common.ApplicationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author xiaochong
 * @Create 2017/9/11 下午7:27
 * @Description
 */
@Component
public class UserAccessRightInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private SaasAdminService saasAdminService;

    @Autowired
    private SaasBorrowerService saasBorrowerService;

    @Autowired
    private RoleApplication roleApplication;

    @Autowired
    private SaasChannelApplication saasChannelApplication;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET,PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Accept,X-Requested-With,remember-me,bid,basicParams");

        if (isWebResources(request)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) o;
        SignIgnore signIgnoreAnnotation = handlerMethod.getMethodAnnotation(SignIgnore.class);
        if (signIgnoreAnnotation != null) {
            return true;
        }
        String basicParams = request.getHeader("basicParams");
        RequestBasicInfo basicVO = JSONUtils.json2pojo(basicParams, RequestBasicInfo.class);
        if (StringUtils.isBlank(basicParams)) {
            throw new ApplicationException(RestCodeEnum.SYSTEM_PARAMTER_ERROR);
        }

        RequestUserInfo requestUserInfo = new RequestUserInfo();
        requestUserInfo.setRequestBasicInfo(basicVO);
        RequestLocalInfo.putCurrentAdmin(requestUserInfo);
        VisitorAccessible annotation = handlerMethod.getMethodAnnotation(VisitorAccessible.class);
        if (annotation != null) {
            return true;
        }
        if (StringUtils.isNotEmpty(basicVO.getToken())) {
            if (!verifyHeader(request)) {
                throw new ApplicationException(RestCodeEnum.TOKEN_NOT_AVAILABLE);
            }
            HasPermission hasPermission = handlerMethod.getMethodAnnotation(HasPermission.class);
            if (hasPermission != null) {
                String permissionKey = hasPermission.permissionKey();
                String adminCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode();
                Boolean hasButtonPermission = roleApplication.hasButtonPermission(permissionKey, adminCode);
                if (!hasButtonPermission) {
                    throw new ApplicationException("无权限操作");
                }
            }
            IgnoreRepeatRequest ignoreRepeatRequest = handlerMethod.getMethodAnnotation(IgnoreRepeatRequest.class);
            if (null != ignoreRepeatRequest) {
                if (!isRepeatSubmit(request, basicVO.getToken())) {
                    throw new ApplicationException(RestCodeEnum.REPEAT_REQUEST);
                }
            }
        } else {
            throw new ApplicationException(RestCodeEnum.TOKEN_NOT_AVAILABLE);
        }
        return true;
    }

    private boolean isWebResources(HttpServletRequest request) {

        String uri = request.getRequestURI();
        uri = uri.replace("//", "/");
        if (uri.startsWith("/web/")) {
            return true;
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
        if (o instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) o;
            SignIgnore signIgnoreAnnotation = handlerMethod.getMethodAnnotation(SignIgnore.class);
            if (signIgnoreAnnotation != null) {
                return;
            }
            VisitorAccessible visitorAccessibleAnnotation = handlerMethod.getMethodAnnotation(VisitorAccessible.class);
            if (visitorAccessibleAnnotation == null) {
                IgnoreRepeatRequest ignoreRepeatRequest = handlerMethod.getMethodAnnotation(IgnoreRepeatRequest.class);
                RequestBasicInfo basicVO = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo();
                if (basicVO.getPlatform().equals("h5")) {
                    String code = RequestLocalInfo.getCurrentAdmin().getSaasBorrower().getBorrowerCode();
                    redisClient.expire(RedisKeyConsts.SAAS_TOKEN_KEY, TimeConsts.TEN_MINUTES, code);
                }
                if (basicVO.getPlatform().equals("web")) {
                    String code = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode();
                    redisClient.expire(RedisKeyConsts.SAAS_TOKEN_KEY, TimeConsts.TEN_MINUTES, code);
                }
//                redisClient.expire(RedisKeyConsts.SAAS_TOKEN_KEY, TimeConsts.TEN_MINUTES, basicVO.getToken());
                // 为了方便测试联调，将token时效设置变长
                redisClient.expire(RedisKeyConsts.SAAS_TOKEN_KEY, TimeConsts.AN_HOUR, basicVO.getToken());
                String key = MD5.md5(httpServletRequest.getRequestURI() + basicVO.getToken());
                String value = redisClient.get(RedisKeyConsts.REPEAT_PREFIX, key);
                if (ignoreRepeatRequest != null && StringUtils.isNotEmpty(value)) {
                    redisClient.del(RedisKeyConsts.REPEAT_PREFIX, key);
                }
            }
        }
    }

    private Boolean hasPermission(RequestBasicInfo basicVO) {
        RequestUserInfo requestUserInfo = RequestLocalInfo.getCurrentAdmin();
        String userCode = redisClient.get(RedisKeyConsts.SAAS_TOKEN_KEY, basicVO.getToken());
        if (StringUtils.isEmpty(userCode)) {
            return false;
        }
        if (basicVO.getPlatform().equals("h5")) {
            String channelCode = requestUserInfo.getRequestBasicInfo().getChannel();
            if (StringUtils.isEmpty(channelCode)) {
                throw new ApplicationException(ChannelErrorCodeEnum.DISABLE_CHANNEL);
            }
            SaasH5ChannelVo saasH5ChannelVo = saasChannelApplication.getSaasChannelBychannelCode(channelCode);
            if (saasH5ChannelVo == null) {
                throw new ApplicationException(ChannelErrorCodeEnum.DISABLE_CHANNEL);
            }
            SaasBorrowerVo saasBorrowerVo = saasBorrowerService.getByBorrowerCodeAndMerchantCode(userCode, saasH5ChannelVo.getMerchantCode());
            if (saasBorrowerVo == null) {
                throw new ApplicationException(RestCodeEnum.TOKEN_NOT_AVAILABLE);
            }
            requestUserInfo.setUser(saasBorrowerVo);
        } else if (basicVO.getPlatform().equals("web")) {
            SaasAdmin saasAdmin = saasAdminService.getSaasAdminByAdminCode(userCode);
            requestUserInfo.setUser(saasAdmin);
        } else {
            throw new ApplicationException("平台非法");
        }
        return true;
    }

    private Boolean verifyHeader(HttpServletRequest request) throws Exception {
        String basicParams = request.getHeader("basicParams");
        if (StringUtils.isBlank(basicParams)) {
            throw new ApplicationException(RestCodeEnum.SYSTEM_PARAMTER_ERROR);
        }

        RequestBasicInfo basicVO = JSONUtils.json2pojo(basicParams, RequestBasicInfo.class);
        return hasPermission(basicVO);
    }

    private boolean isRepeatSubmit(HttpServletRequest request, String accessToken) {
        String str = request.getRequestURI() + accessToken;
        String key = MD5.md5(str);
        String value = redisClient.get(RedisKeyConsts.REPEAT_PREFIX, key);
        if (StringUtils.isEmpty(value)) {
            redisClient.set(RedisKeyConsts.REPEAT_PREFIX, key, TimeConsts.TWO_MINUTE, key);
            return true;
        }
        return false;
    }
}
