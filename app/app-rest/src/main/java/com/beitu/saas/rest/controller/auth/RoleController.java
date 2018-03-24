package com.beitu.saas.rest.controller.auth;

import com.beitu.saas.app.application.auth.RoleApplication;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.auth.service.SaasRolePermissionService;
import com.beitu.saas.auth.service.SaasRoleService;
import com.beitu.saas.rest.controller.auth.request.AddRoleRequest;
import com.fqgj.common.api.Response;
import com.fqgj.common.api.annotations.ParamsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ParamsValidate
    public Response add(@RequestBody AddRoleRequest addRoleRequest) {
        SaasAdmin saasAdmin = RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
        roleApplication.addRoleAndEmpower(addRoleRequest.getRoleName(),saasAdmin.getName(),saasAdmin.getCode(),addRoleRequest.getMenusIds(),addRoleRequest.getButtonIds());
        return Response.ok();
    }
}
