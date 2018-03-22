package com.beitu.saas.rest.controller.auth;

import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.auth.service.SaasAdminService;
import com.beitu.saas.rest.controller.auth.request.AdminLoginRequest;
import com.fqgj.common.api.Response;
import com.fqgj.common.api.annotations.ParamsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author xiaochong
 * @create 2018/3/22 下午4:00
 * @description
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private SaasAdminService saasAdminService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ParamsValidate
    @VisitorAccessible
    public Response login(@RequestBody AdminLoginRequest adminLoginRequest, HttpServletRequest request) throws IOException {
        //TODO 校验验证码
        String token = saasAdminService.login(adminLoginRequest.getMobile(), adminLoginRequest.getPassword());
        //插入登录记录
        return Response.ok();
    }
}
