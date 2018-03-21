package com.beitu.saas.rest.interceptor;


import com.beitu.saas.app.annotations.IgnoreRepeatRequest;
import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.enums.RestCodeEnum;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.base.services.redis.TimeConsts;
import com.fqgj.common.api.enums.BasicErrorCodeEnum;
import com.fqgj.common.utils.MD5;
import com.fqgj.exception.common.ApplicationException;


import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;


/**
 * @author xiaochong
 * @Create 2017/9/11 下午7:27
 * @Description
 */
@Component
public class UserAccessRightInterceptor implements HandlerInterceptor {

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private RedisClient redisClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Accept,X-Requested-With,remember-me,bid,accessToken");

        String accessToken = request.getHeader("accessToken");
        if (isWebResources(request)) {
            return true;
        }

        if (org.apache.commons.lang3.StringUtils.isNotEmpty(accessToken)) {
            if (!hasPermission(accessToken)) {
                throw new ApplicationException("无效的token");
            }
        } else {
            HandlerMethod handlerMethod = (HandlerMethod) o;
            VisitorAccessible annotation = handlerMethod.getMethodAnnotation(VisitorAccessible.class);
            if (annotation != null) {
                return true;
            }
            if (!request.getMethod().equals("OPTIONS")) {
                if (!handlerMethod.getMethod().getName().toUpperCase().equals("LOGIN")) {
                    throw new ApplicationException("需要登陆才能访问");
                }
            }
            IgnoreRepeatRequest ignoreRepeatRequest = handlerMethod.getMethodAnnotation(IgnoreRepeatRequest.class);
            if (null != ignoreRepeatRequest) {
                if (!isRepeatSubmit(request,accessToken)) {
                    throw new ApplicationException(RestCodeEnum.REPEAT_REQUEST);
                }
            }
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
            if (signIgnoreAnnotation == null) {
                IgnoreRepeatRequest ignoreRepeatRequest = handlerMethod.getMethodAnnotation(IgnoreRepeatRequest.class);
                String accessToken = httpServletRequest.getHeader("accessToken");
                String key = MD5.md5(httpServletRequest.getRequestURI() + accessToken);
                String value = redisClient.get(RedisKeyConsts.REPEAT_PREFIX, key);
                if (ignoreRepeatRequest != null && StringUtils.isNotEmpty(value)) {
                    redisClient.del(RedisKeyConsts.REPEAT_PREFIX, key);
                }
            }
        }
    }

    private Boolean hasPermission(String accessToken) {
    //TODO 查询tokn
    //        Admin admin = adminService.getByAccessToken(accessToken);
    //        if (admin == null) {
    //            return false;
    //        }

        RequestLocalInfo.putCurrentAdmin(null);
        return true;
    }

    private String getRequestBody(HttpServletRequest request) {

        String businessParams = "";

        try {
            InputStream inputStream = request.getInputStream();
            businessParams = IOUtils.toString(inputStream);
            if (StringUtils.isNotEmpty(businessParams)) {
                return businessParams;
            }
        } catch (Exception e) {
            throw new ApplicationException(BasicErrorCodeEnum.PARAM_RESOLVE_ERROR);
        }


        return businessParams;

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
