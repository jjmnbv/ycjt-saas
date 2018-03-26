package com.beitu.saas.rest.controller.auth;

import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.app.application.auth.AdminInfoApplication;
import com.beitu.saas.app.common.RequestBasicInfo;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.auth.entity.SaasAdminRole;
import com.beitu.saas.auth.entity.SaasRole;
import com.beitu.saas.auth.service.SaasAdminLoginLogService;
import com.beitu.saas.auth.service.SaasAdminRoleService;
import com.beitu.saas.auth.service.SaasAdminService;
import com.beitu.saas.auth.service.SaasRoleService;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.consts.TimeConsts;
import com.beitu.saas.rest.controller.auth.request.AddAdminRequest;
import com.beitu.saas.rest.controller.auth.request.AdminLoginRequest;
import com.beitu.saas.rest.controller.auth.request.ResetPasswordRequest;
import com.beitu.saas.rest.controller.auth.request.UpdateAdminRequest;
import com.beitu.saas.rest.controller.auth.response.AdminListResponse;
import com.beitu.saas.rest.controller.auth.response.RoleListResponse;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.common.api.Page;
import com.fqgj.common.api.Response;
import com.fqgj.common.api.annotations.ParamsValidate;
import com.fqgj.common.entity.BaseEntity;
import com.fqgj.common.utils.DateUtil;
import com.fqgj.common.utils.GenerOrderNoUtil;
import com.fqgj.common.utils.MD5;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.exception.common.ApplicationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private AdminInfoApplication adminInfoApplication;

    @Autowired
    private SaasAdminRoleService saasAdminRoleService;

    @Autowired
    private SaasRoleService saasRoleService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ParamsValidate
    @VisitorAccessible
    @ApiOperation(value = "登录")
    public Response login(@RequestBody AdminLoginRequest adminLoginRequest, HttpServletRequest request) throws IOException {
        //TODO 校验验证码
        SaasAdmin saasAdmin = saasAdminService.login(adminLoginRequest.getMobile(), adminLoginRequest.getPassword());
        String oldToken = redisClient.get(RedisKeyConsts.SAAS_TOKEN_KEY, saasAdmin.getCode());
        if (StringUtils.isNotEmpty(oldToken)){
            redisClient.del(RedisKeyConsts.SAAS_TOKEN_KEY,oldToken);
        }
        saasAdminLoginLogService.addAdminLoginLog(request, saasAdmin.getCode());
        String token = MD5.md5(UUID.randomUUID().toString());
        redisClient.set(RedisKeyConsts.SAAS_TOKEN_KEY, saasAdmin.getCode(), TimeConsts.TEN_SECONDS, token);
        redisClient.set(RedisKeyConsts.SAAS_TOKEN_KEY,token, TimeConsts.TEN_SECONDS,saasAdmin.getCode());
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

    /**
     * 新增管理员
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ParamsValidate
    @ApiOperation("新增用户")
    public Response addAdmin(@RequestBody AddAdminRequest addAdminRequest) {
        SaasAdmin saasAdmin = new SaasAdmin();
        BeanUtils.copyProperties(addAdminRequest, saasAdmin);
        saasAdmin.setPassword(MD5.md5(addAdminRequest.getPassword())).setMerchantCode(GenerOrderNoUtil.generateOrderNo());
        saasAdmin.setEnable(true).setMerchantCode(RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode());
        adminInfoApplication.addAdminAndRole(saasAdmin, addAdminRequest.getRoleId());
        return Response.ok();
    }

    @RequestMapping(value = "/status/{adminId}/{enable}", method = RequestMethod.PUT)
    @ApiOperation("禁用账户")
    public Response enable(@PathVariable Long adminId, @PathVariable("enable") Boolean enable) {
        SaasAdmin saasAdmin = new SaasAdmin();
        saasAdmin.setEnable(enable);
        saasAdmin.setId(adminId);
        boolean success = saasAdminService.updateById(saasAdmin) > 0;
        if (!success) {
            throw new ApplicationException("账户状态更新失败");
        }

        return Response.ok();
    }

    @RequestMapping(value = "/reset/password", method = RequestMethod.PUT)
    @ApiOperation("重置密码")
    public Response reset(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        SaasAdmin saasAdmin = new SaasAdmin();
        saasAdmin.setPassword(MD5.md5(resetPasswordRequest.getPassword()));
        saasAdmin.setId(resetPasswordRequest.getAdminId());
        boolean success = saasAdminService.updateById(saasAdmin) > 0;
        if (!success) {
            throw new ApplicationException("重置密码失败");
        }
        return Response.ok();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ApiOperation("重置密码")
    public Response updateAdmin(@RequestBody UpdateAdminRequest updateAdminRequest) {
        SaasAdmin saasAdmin = new SaasAdmin();
        saasAdmin.setId(updateAdminRequest.getAdminId());
        if (StringUtils.isNotEmpty(updateAdminRequest.getJob()) || StringUtils.isNotEmpty(updateAdminRequest.getName())) {
            saasAdmin.setJob(updateAdminRequest.getJob());
            saasAdmin.setJob(updateAdminRequest.getName());
            saasAdminService.updateById(saasAdmin);
        }
        if (updateAdminRequest.getRoleId() != null) {
            SaasAdminRole saasAdminRole = new SaasAdminRole();
            SaasAdmin entity = (SaasAdmin) saasAdminService.selectById(updateAdminRequest.getAdminId());
            saasAdminRole.setAdminCode(entity.getCode());
            saasAdminRole.setRoleId(updateAdminRequest.getRoleId());
            saasAdminRoleService.updateByAdminCode(saasAdminRole);
        }
        return Response.ok();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ParamsValidate
    public Response list(@RequestBody Page page) {
        SaasAdmin saasAdmin = RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
        List<SaasAdmin> list = saasAdminService.getAdminListByMerchantCode(saasAdmin.getCode(), page);
        List<AdminListResponse> listResponses = new ArrayList<>();
        list.forEach(admin -> {
            AdminListResponse response = new AdminListResponse();
            BeanUtils.copyProperties(admin, response);
            response.setAdminId(admin.getId());
            response.setCreateTime(DateUtil.convertDateToString(admin.getGmtCreate()));
            SaasAdminRole saasAdminRole = (SaasAdminRole) saasAdminRoleService.selectByParams(new HashMap<String, Object>() {{
                put("adminCode", admin.getCode());
                put("deleted", false);
            }}).get(0);
            response.setRoleName(((SaasRole) saasRoleService.selectById(saasAdminRole.getRoleId())).getName());
        });
        return Response.ok().putData(new HashMap<String, Object>(2) {{
            put("roleList", listResponses);
            put("page", page);
        }});
    }

}
