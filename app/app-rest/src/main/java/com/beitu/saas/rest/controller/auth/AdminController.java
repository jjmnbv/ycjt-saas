package com.beitu.saas.rest.controller.auth;

import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.application.SendApplication;
import com.beitu.saas.app.application.auth.AdminInfoApplication;
import com.beitu.saas.app.application.auth.MerchantApplication;
import com.beitu.saas.app.application.auth.RoleApplication;
import com.beitu.saas.app.common.RequestBasicInfo;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.app.enums.SaasSmsTypeEnum;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.auth.entity.SaasAdminRole;
import com.beitu.saas.auth.entity.SaasRole;
import com.beitu.saas.auth.enums.AdminErrorEnum;
import com.beitu.saas.auth.service.*;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.consts.TimeConsts;
import com.beitu.saas.common.utils.NetworkUtil;
import com.beitu.saas.rest.controller.auth.request.AddAdminRequest;
import com.beitu.saas.rest.controller.auth.request.AdminLoginRequest;
import com.beitu.saas.rest.controller.auth.request.ResetPasswordRequest;
import com.beitu.saas.rest.controller.auth.request.UpdateAdminRequest;
import com.beitu.saas.rest.controller.auth.response.AdminDetailResponse;
import com.beitu.saas.rest.controller.auth.response.AdminListResponse;
import com.beitu.saas.rest.controller.auth.response.AdminLoginResponse;
import com.beitu.saas.rest.controller.auth.response.RoleListResponse;
import com.beitu.saas.sms.enums.SmsErrorCodeEnum;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.common.api.Page;
import com.fqgj.common.api.Response;
import com.fqgj.common.api.annotations.ParamsValidate;
import com.fqgj.common.api.exception.ApiErrorException;
import com.fqgj.common.entity.BaseEntity;
import com.fqgj.common.utils.*;
import com.fqgj.exception.common.ApplicationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

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

    @Autowired
    private SaasMerchantService saasMerchantService;

    @Autowired
    private RoleApplication roleApplication;

    @Autowired
    private SendApplication sendApplication;

    @Autowired
    private MerchantApplication merchantApplication;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ParamsValidate
    @VisitorAccessible
    @ApiOperation(value = "登录")
    public Response login(@RequestBody AdminLoginRequest adminLoginRequest, HttpServletRequest request) throws IOException {
        SaasAdmin saasAdmin = saasAdminService.login(adminLoginRequest.getMobile(), adminLoginRequest.getPassword());
        if (!roleApplication.enableAdminRole(saasAdmin.getCode())){
            throw new ApiErrorException("角色被禁用");
        }
        if (StringUtils.isEmpty(adminLoginRequest.getVerifyCode())) {
            if (!saasAdminLoginLogService.equalLoginIp(saasAdmin.getCode(), NetworkUtil.getIpAddress(request))) {
                throw new ApiErrorException(AdminErrorEnum.SHOW_VERIFYCODE);
            }
        }
        String oldToken = redisClient.get(RedisKeyConsts.SAAS_TOKEN_KEY, saasAdmin.getCode());
        if (StringUtils.isNotEmpty(oldToken)) {
            Map map = new HashMap(2) {{
                put("account_phone", saasAdmin.getMobile());
            }};
            sendApplication.sendNotifyMessage(saasAdmin.getMerchantCode(), saasAdmin.getMobile(), map, SaasSmsTypeEnum.SAAS_0019);
            redisClient.del(RedisKeyConsts.SAAS_TOKEN_KEY, oldToken);
        }
        String verifyCode = redisClient.get(RedisKeyConsts.H5_SAVE_LOGIN_VERIFYCODE_KEY, adminLoginRequest.getMobile());
        if (StringUtils.isNotEmpty(verifyCode)) {
            if (!verifyCode.equals(adminLoginRequest.getVerifyCode())) {
                throw new ApiErrorException(SmsErrorCodeEnum.INPUT_WRONG_VERIFY_CODE);
            }
        }
        saasAdminLoginLogService.addAdminLoginLog(request, saasAdmin.getCode());
        String token = MD5.md5(UUID.randomUUID().toString());
        redisClient.set(RedisKeyConsts.SAAS_TOKEN_KEY, saasAdmin.getCode(), TimeConsts.TEN_MINUTES, token);
        redisClient.set(RedisKeyConsts.SAAS_TOKEN_KEY, token, TimeConsts.TEN_MINUTES, saasAdmin.getCode());
        AdminLoginResponse response = new AdminLoginResponse();
        response.setToken(token);
        response.setAdminName(saasAdmin.getName());
        response.setMerchantName(saasMerchantService.getByMerchantCode(saasAdmin.getMerchantCode()).getCompanyName());
        return Response.ok().putData(response);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    @ParamsValidate
    @ApiOperation(value = "退出登录")
    public Response logout() throws IOException {
        RequestBasicInfo requestBasicInfo = RequestLocalInfo.getCurrentAdmin().getRequestBasicInfo();
        redisClient.del(RedisKeyConsts.SAAS_TOKEN_KEY, requestBasicInfo.getToken());
        redisClient.del(RedisKeyConsts.SAAS_TOKEN_KEY, RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode());
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
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        if (!merchantApplication.canAddAccount(merchantCode)) {
            throw new ApiErrorException("创建账户个数到底上限,请联系软件服务商");
        }
        SaasAdmin saasAdmin = new SaasAdmin();
        BeanUtils.copyProperties(addAdminRequest, saasAdmin);
        saasAdmin.setPassword(MD5.md5(addAdminRequest.getPassword())).setMerchantCode(GenerOrderNoUtil.generateOrderNo());
        saasAdmin.setEnable(true).setMerchantCode(merchantCode);
        saasAdmin.setDefault(false);
        saasAdmin.setCreateName(RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getName());
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
        if (enable == false) {
            SaasAdmin entity = (SaasAdmin) saasAdminService.selectById(adminId);
            String oldToken = redisClient.get(RedisKeyConsts.SAAS_TOKEN_KEY, entity.getCode());
            if (StringUtils.isNotEmpty(oldToken)) {
                redisClient.del(RedisKeyConsts.SAAS_TOKEN_KEY, oldToken);
                redisClient.del(RedisKeyConsts.SAAS_TOKEN_KEY, entity.getCode());
            }
        }
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
    @ApiOperation("编辑子账户")
    public Response updateAdmin(@RequestBody UpdateAdminRequest updateAdminRequest) {
        SaasAdmin saasAdmin = new SaasAdmin();
        saasAdmin.setId(updateAdminRequest.getAdminId());
        if (StringUtils.isNotEmpty(updateAdminRequest.getJob()) || StringUtils.isNotEmpty(updateAdminRequest.getName())) {
            saasAdmin.setJob(updateAdminRequest.getJob());
            saasAdmin.setName(updateAdminRequest.getName());
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

    @RequestMapping(value = "/list/{currentPage}/{pageSize}", method = RequestMethod.GET)
    @ParamsValidate
    @ApiOperation(value = "子账户列表", response = AdminListResponse.class)
    public Response list(@PathVariable(value = "currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {
        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        SaasAdmin saasAdmin = RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
        List<SaasAdmin> list = saasAdminService.getAdminListByMerchantCode(saasAdmin.getMerchantCode(), page);
        List<AdminListResponse> listResponses = new ArrayList<>();
        list.forEach(admin -> {
            AdminListResponse response = new AdminListResponse();
            BeanUtils.copyProperties(admin, response);
            response.setAdminId(admin.getId());
            response.setCreateTime(DateUtil.convertDateToString(admin.getGmtCreate()));

            List list1 = saasAdminRoleService.selectByParams(new HashMap<String, Object>() {{
                put("adminCode", admin.getCode());
                put("deleted", false);
            }});
            if (CollectionUtils.isNotEmpty(list1)) {
                SaasAdminRole saasAdminRole = (SaasAdminRole) list1.get(0);
                response.setRoleName(((SaasRole) saasRoleService.selectById(saasAdminRole.getRoleId())).getName());
            }
            listResponses.add(response);
        });
        return Response.ok().putData(new HashMap<String, Object>(2) {{
            put("roleList", listResponses);
            put("page", page);
        }});
    }


    @RequestMapping(value = "/detail/{adminId}", method = RequestMethod.GET)
    @ParamsValidate
    @ApiOperation(value = "账户详情", response = AdminDetailResponse.class)
    public Response list(@PathVariable("adminId") Long adminId) {
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        AdminDetailResponse response = new AdminDetailResponse();
        SaasAdmin saasAdmin = (SaasAdmin) saasAdminService.selectById(adminId);
        if (!merchantCode.equals(saasAdmin.getMerchantCode())) {
            throw new ApiErrorException("请求账户详情非法");
        }
        BeanUtils.copyProperties(saasAdmin, response);
        response.setAdminId(saasAdmin.getId());
        SaasRole saasRole = roleApplication.getRoleByAdminCode(saasAdmin.getCode());
        response.setRoleId(saasRole.getId());
        response.setRoleName(saasRole.getName());
        return Response.ok().putData(response);
    }

}
