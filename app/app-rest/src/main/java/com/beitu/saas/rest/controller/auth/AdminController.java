package com.beitu.saas.rest.controller.auth;

import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.app.common.RequestBasicInfo;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.auth.service.SaasAdminLoginLogService;
import com.beitu.saas.auth.service.SaasAdminService;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.consts.TimeConsts;
import com.beitu.saas.rest.controller.auth.request.AdminLoginRequest;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.common.api.Response;
import com.fqgj.common.api.annotations.ParamsValidate;
import com.fqgj.common.utils.MD5;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author xiaochong
 * @create 2018/3/22 下午4:00
 * @description
 */
@RestController
@RequestMapping("/admin")
@Api(description = "saas用户模块")
public class AdminController {

    @Autowired
    private SaasAdminService saasAdminService;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private SaasAdminLoginLogService saasAdminLoginLogService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ParamsValidate
    @VisitorAccessible
    @ApiOperation(value = "登录")
    public Response login(@RequestBody AdminLoginRequest adminLoginRequest, HttpServletRequest request) throws IOException {
        //TODO 校验验证码
        SaasAdmin saasAdmin = saasAdminService.login(adminLoginRequest.getMobile(), adminLoginRequest.getPassword());
        saasAdminLoginLogService.addAdminLoginLog(request, saasAdmin.getCode());
        String token = MD5.md5(UUID.randomUUID().toString());
        redisClient.set(RedisKeyConsts.SAAS_TOKEN_KEY, saasAdmin.getCode(), TimeConsts.TEN_SECONDS, token);
        return Response.ok().putData(new HashMap<String, Object>(2) {{
            put("token", token);
        }});
    }

    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    @ParamsValidate
    @ApiOperation(value = "退出登录")
    public Response logout() throws IOException {
        RequestBasicInfo requestBasicInfo = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo();
        redisClient.del(RedisKeyConsts.SAAS_TOKEN_KEY, requestBasicInfo.getToken());
        return Response.ok();
    }
}
