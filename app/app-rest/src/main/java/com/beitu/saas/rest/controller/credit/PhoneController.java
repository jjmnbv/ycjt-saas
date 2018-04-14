package com.beitu.saas.rest.controller.credit;

import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.application.credit.DhbReportApplication;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by linchengyu on 17/8/3.
 */
@Controller
@RequestMapping("/phone")
public class PhoneController {

    private static final Log LOGGER = LogFactory.getLog(PhoneController.class);

    @Autowired
    private DhbReportApplication dhbReportApplication;

    @SignIgnore
    @RequestMapping(value = "/dhb/bmp/callback", method = RequestMethod.POST)
    @ResponseBody
    public void bmpCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        LOGGER.info("***** 进入电话邦金融标签接收处理 *****");
        String reqStr = IOUtils.toString(request.getInputStream(), "utf-8");
        try {
            dhbReportApplication.handleBmpData(reqStr);
        } catch (Exception e) {
            write(response, "error");
            LOGGER.error("Dianhuabang Error:电话邦金融标签异步通知数据解析异常:", e);
            LOGGER.error("Request String:" + reqStr);
            return;
        }
        write(response, "ok");
        LOGGER.info("Dianhuabang Success:电话邦金融标签异步通知数据接收处理成功");
    }

    private void write(HttpServletResponse response, String responseStr) throws Exception {
        response.getWriter().write(responseStr);
        response.getWriter().flush();
    }

}
