package com.beitu.saas.rest.controller.auth;

import com.beitu.saas.app.application.auth.RoleApplication;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.auth.entity.SaasRole;
import com.beitu.saas.auth.service.SaasRolePermissionService;
import com.beitu.saas.auth.service.SaasRoleService;
import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.rest.controller.auth.request.AddRoleRequest;
import com.beitu.saas.rest.controller.auth.response.RoleListResponse;
import com.fqgj.common.api.Page;
import com.fqgj.common.api.Response;
import com.fqgj.common.api.annotations.ParamsValidate;
import com.fqgj.exception.common.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author xiaochong
 * @create 2018/3/23 下午8:34
 * @description
 */
@RequestMapping("/admin/role")
@RestController
public class RoleController {

    @Autowired
    private SaasRoleService saasRoleService;

    @Autowired
    private SaasRolePermissionService saasRolePermissionService;

    @Autowired
    private RoleApplication roleApplication;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ParamsValidate
    public Response add(@RequestBody AddRoleRequest addRoleRequest) {
        SaasAdmin saasAdmin = RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
        roleApplication.addRoleAndEmpower(addRoleRequest.getRoleName(), saasAdmin.getName(), saasAdmin.getCode(), addRoleRequest.getMenusIds(), addRoleRequest.getButtonIds());
        return Response.ok();
    }

    @RequestMapping(value = "/list/{currentPage}/{pageSize}", method = RequestMethod.GET)
    @ParamsValidate
    public Response list(@PathVariable(value = "currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {
        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        SaasAdmin saasAdmin = RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
        List<SaasRole> list = saasRoleService.getRoleListByMerchantCode(saasAdmin.getCode(), page);
        List<RoleListResponse> listResponses = new ArrayList<>();
        list.forEach(saasRole -> {
            RoleListResponse response = new RoleListResponse();
            BeanUtils.copyProperties(saasRole, response);
            response.setRoleId(saasRole.getId());
            response.setCreateTime(DateUtil.convertDateToString(saasRole.getGmtCreate()));
            listResponses.add(response);
        });
        return Response.ok().putData(new HashMap<String, Object>(2) {{
            put("roleList", listResponses);
            put("page", page);
        }});
    }

    @RequestMapping(value = "/status/{roleId}/{enable}", method = RequestMethod.PUT)
    @ParamsValidate
    public Response enable(@PathVariable Long roleId,  @PathVariable("enable") boolean enable) {
        SaasAdmin saasAdmin = RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
        SaasRole saasRole = new SaasRole();
        saasRole.setMerchantCode(saasAdmin.getCode());
        saasRole.setEnabled(enable);
        saasRole.setId(roleId);
        boolean success = saasRoleService.updateById(saasRole) > 0;
        if (!success) {
            throw new ApplicationException("角色状态更新失败");
        }
        return Response.ok();
    }


}
