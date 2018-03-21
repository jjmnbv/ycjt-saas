package com.beitu.saas.rest.filter;

import com.alibaba.druid.util.PatternMatcher;
import com.alibaba.druid.util.ServletPathMatcher;
import com.fqgj.common.utils.DateUtil;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xiaochong
 * @Create 2017/9/11 下午7:27
 * @Description
 */
public class RequestFilter implements Filter {
    
    private static final Log timeLogger = LogFactory.getLog("request_time_log");

    private static final String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
    
    private static final String PARAM_NAME_EXCLUSIONS = "exclusions";
    
    private static Set<String> excludesPattern;
    
    private static String contextPath;
    
    private static PatternMatcher pathMatcher = new ServletPathMatcher();
    
    @Override
    public void destroy() {
        
    }
    
    private String getContextPath(ServletContext context) {
        if (context.getMajorVersion() == 2 && context.getMinorVersion() < 5) {
            return null;
        }
        
        try {
            return getContextPath_2_5(context);
        } catch (NoSuchMethodError error) {
            return null;
        }
    }
    
    private String getContextPath_2_5(ServletContext context) {
        String contextPath = context.getContextPath();
        
        if (contextPath == null || contextPath.length() == 0) {
            contextPath = "/";
        }
        
        return contextPath;
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        
        RequestTimeVO requestTimeVO = new RequestTimeVO();
        requestTimeVO.setEventName("requestTime");
        Date beginDate = new Date();
        requestTimeVO.setRequestUri(((HttpServletRequest) request).getRequestURI());
        requestTimeVO.setBeginDate(DateUtil.getDate(beginDate, dateTimePattern));
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        if (isExclusion(requestURI)) {
            chain.doFilter(request, response);
            return;
        }
        ServletRequest requestWrapper = null;
        if (request instanceof HttpServletRequest) {
            requestWrapper = new CacheRequestBodyWrapper((HttpServletRequest) request);
        }
        if (requestWrapper == null) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(requestWrapper, response);
        }
        
        Date endDate = new Date();
        requestTimeVO.setEndDate(DateUtil.getDate(endDate, dateTimePattern));
        requestTimeVO.setDuration(DateUtil.diffDateToMillis(endDate, beginDate));
        
        //timeLogger.info(JSON.toJSONString(requestTimeVO));
    }
    
    public boolean isExclusion(String requestURI) {
        if (excludesPattern == null) {
            return false;
        }
        
        if (contextPath != null && requestURI.startsWith(contextPath)) {
            requestURI = requestURI.substring(contextPath.length());
            if (!requestURI.startsWith("/")) {
                requestURI = "/" + requestURI;
            }
        }
        
        for (String pattern : excludesPattern) {
            if (pathMatcher.matches(pattern, requestURI)) {
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public void init(FilterConfig config) throws ServletException {
        {
            String exclusions = config.getInitParameter(PARAM_NAME_EXCLUSIONS);
            if (exclusions != null && exclusions.trim().length() != 0) {
                excludesPattern = new HashSet<String>(Arrays.asList(exclusions.split("\\s*,\\s*")));
            }
            contextPath = getContextPath(config.getServletContext());
        }
    }
    
    public static class RequestTimeVO implements Serializable {
        
        private String eventName;
        private String requestUri;
        private Long duration;
        private String beginDate;
        private String endDate;
        
        public String getEventName() {
            return eventName;
        }
        
        public void setEventName(String eventName) {
            this.eventName = eventName;
        }
        
        public String getRequestUri() {
            return requestUri;
        }
        
        public void setRequestUri(String requestUri) {
            this.requestUri = requestUri;
        }
        
        public Long getDuration() {
            return duration;
        }
        
        public void setDuration(Long duration) {
            this.duration = duration;
        }
        
        public String getBeginDate() {
            return beginDate;
        }
        
        public void setBeginDate(String beginDate) {
            this.beginDate = beginDate;
        }
        
        public String getEndDate() {
            return endDate;
        }
        
        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }
    }
}

class CacheRequestBodyWrapper extends HttpServletRequestWrapper {
    
    private final String body; // 报文
    
    public CacheRequestBodyWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = IOUtils.toString(request.getInputStream());
    }
    
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
    
    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body.getBytes());
        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }
    
}