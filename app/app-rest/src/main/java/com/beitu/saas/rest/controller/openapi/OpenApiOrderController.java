package com.beitu.saas.rest.controller.openapi;

import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.application.openapi.OpenApiApplication;
import com.beitu.saas.app.enums.OpenApiOrderPushErrorCodeEnum;
import com.fqgj.common.api.Response;
import com.fqgj.exception.common.ApplicationException;
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
    private OpenApiApplication openApiApplication;
    
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
        openApiApplication.ipWhiteListValidation(request);
        LOGGER.info("************************* 洋葱借条推单处理开始 *************************");
        String reqStr = IOUtils.toString(request.getInputStream(), "utf-8");
        if (!openApiApplication.ycjtOrderPushProcess(reqStr)) {
            LOGGER.warn("************************* 洋葱借条推单处理失败 *************************");
            throw new ApplicationException(OpenApiOrderPushErrorCodeEnum.ORDER_RECEIVE_FAIL);
        }
        LOGGER.info("************************* 洋葱借条推单处理成功 *************************");
        return Response.ok();
    }
    
}
