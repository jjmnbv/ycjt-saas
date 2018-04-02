package com.beitu.saas.rest.controller.credit;

import com.beitu.saas.app.annotations.SignIgnore;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/loan/platform")
public class LoanPlatformController {
    
    private static final Log LOGGER = LogFactory.getLog(LoanPlatformController.class);
    
    @SignIgnore
    @ResponseBody
    @RequestMapping(value = "/juxinli/callback", consumes = "application/json", method = RequestMethod.POST)
    public void carrierCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("************************* 聚信立回调开始 *************************");
        request.setCharacterEncoding("UTF-8");
        String reqStr = IOUtils.toString(request.getInputStream(), "utf-8");
        LOGGER.info(reqStr);
        write(response, "success");
    
    
        // TODO: 2018/4/2 查询token 入库
        
        
        LOGGER.info("************************* 聚信立回调结束 *************************");
    }
    
    private void write(HttpServletResponse response, String responseStr) throws Exception {
        response.getWriter().write(responseStr);
        response.getWriter().flush();
    }
    
}
