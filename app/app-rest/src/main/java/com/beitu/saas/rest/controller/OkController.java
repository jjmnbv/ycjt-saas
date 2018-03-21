package com.beitu.saas.rest.controller;

import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.annotations.VisitorAccessible;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author xiaochong
 * @create 2017/11/6 下午4:24
 * @description
 */
@RestController
public class OkController {

    private static final Log LOGGER = LogFactory.getLog(OkController.class);

    @RequestMapping("/ok")
    @ResponseBody
    @VisitorAccessible
    @SignIgnore
    public String ok() {
        return "ok";
    }
}
