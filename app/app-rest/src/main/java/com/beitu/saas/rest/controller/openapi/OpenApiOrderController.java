package com.beitu.saas.rest.controller.openapi;

import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.application.openapi.OpenApiOrderApplication;
import com.fqgj.common.api.Response;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/open/api/order")
public class OpenApiOrderController {

    private static final Log LOGGER = LogFactory.getLog(OpenApiOrderController.class);
    
    @Autowired
    private OpenApiOrderApplication openApiOrderApplication;
    
    /**
     * 洋葱借条推单接口
     *
     * @param request
     * @return
     */
    @SignIgnore
    @ResponseBody
    @ApiOperation(value = "洋葱借条推单接口")
    @RequestMapping(value = "/ycjt/push", consumes = "application/json", method = RequestMethod.POST)
    public Response ycjtPush(HttpServletRequest request) throws Exception {
        LOGGER.info("************************* 洋葱借条推单处理开始 *************************");
        String reqStr = IOUtils.toString(request.getInputStream(), "utf-8");
        openApiOrderApplication.ycjtOrderPushProcess(reqStr);
        LOGGER.info("************************* 洋葱借条推单处理成功 *************************");
        return Response.ok();
    }
    
}
