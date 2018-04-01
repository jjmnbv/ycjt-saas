package com.beitu.saas.rest.controller.auth;

import com.beitu.saas.app.application.auth.AdminInfoApplication;
import com.beitu.saas.app.application.auth.RoleApplication;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.auth.entity.SaasMenu;
import com.beitu.saas.auth.entity.SaasOperationButton;
import com.beitu.saas.auth.entity.SaasRole;
import com.beitu.saas.auth.service.SaasMenuService;
import com.beitu.saas.auth.service.SaasOperationButtonService;
import com.beitu.saas.auth.service.SaasRolePermissionService;
import com.beitu.saas.auth.service.SaasRoleService;
import com.beitu.saas.auth.vo.FormedMenuVO;
import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.rest.controller.auth.request.AddRoleRequest;
import com.beitu.saas.rest.controller.auth.request.UpdateRoleRequest;
import com.beitu.saas.rest.controller.auth.response.RoleDetailResponse;
import com.beitu.saas.rest.controller.auth.response.RoleListResponse;
import com.beitu.saas.rest.controller.auth.response.RoleMapResponse;
import com.beitu.saas.rest.controller.auth.response.UserButtonResponse;
import com.fqgj.common.api.Page;
import com.fqgj.common.api.Response;
import com.fqgj.common.api.annotations.ParamsValidate;
import com.fqgj.common.api.exception.ApiErrorException;
import com.fqgj.exception.common.ApplicationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
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
@Api(description = "角色相关接口")
public class RoleController {

    @Autowired
    private SaasRoleService saasRoleService;

    @Autowired
    private RoleApplication roleApplication;


    @Autowired
    private SaasMenuService saasMenuService;

    @Autowired
    private SaasOperationButtonService saasOperationButtonService;

    @Autowired
    private AdminInfoApplication adminInfoApplication;

    @Autowired
    private SaasRolePermissionService saasRolePermissionService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ParamsValidate
    @ApiOperation(value = "添加角色")
    public Response add(@RequestBody AddRoleRequest addRoleRequest) {
        SaasAdmin saasAdmin = RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
        roleApplication.addRoleAndEmpower(addRoleRequest.getRoleName(), saasAdmin.getName(), saasAdmin.getCode(), addRoleRequest.getMenusIds(), addRoleRequest.getButtonIds());
        return Response.ok();
    }

    @RequestMapping(value = "/list/{currentPage}/{pageSize}", method = RequestMethod.GET)
    @ParamsValidate
    @ApiOperation(value = "角色列表", response = RoleListResponse.class)
    public Response list(@PathVariable(value = "currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {
        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        SaasAdmin saasAdmin = RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
        List<SaasRole> list = saasRoleService.getRoleListByMerchantCode(saasAdmin.getMerchantCode(), page);
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
    @ApiOperation(value = "启动和禁用角色")
    public Response enable(@PathVariable Long roleId, @PathVariable("enable") boolean enable) {
        SaasRole saasRole = new SaasRole();
        saasRole.setEnabled(enable);
        saasRole.setId(roleId);
        boolean success = saasRoleService.updateById(saasRole) > 0;
        if (!success) {
            throw new ApplicationException("角色状态更新失败");
        }
        return Response.ok();
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "角色详情", response = RoleDetailResponse.class)
    public Response detail(@RequestParam(value = "roleId", required = false) Long roleId) {
        String roleName = null;
        SaasAdmin saasAdmin = RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
        String code = saasAdmin.getCode();
        List<Integer> menuIds = adminInfoApplication.getMenuIdsByAdmin(code);
        List<Integer> buttonIds = adminInfoApplication.getButtonIdsByAdmin(code);
        List<Integer> menuCheckedIds = new ArrayList<>();
        List<Integer> buttonCheckedIds = new ArrayList<>();
        if (null != roleId) {
            SaasRole saasRole = (SaasRole) saasRoleService.selectById(roleId);
            if (!saasRole.getMerchantCode().equals(saasAdmin.getMerchantCode())) {
                throw new ApiErrorException("请求角色非法");
            }
            roleName = saasRole.getName();
            menuCheckedIds = adminInfoApplication.getMenuIdsByRoleId(roleId);
            buttonCheckedIds = adminInfoApplication.getButtonIdsByRoleId(roleId);
        }
//        if (null != roleId) {
//            SaasRole saasRole = (SaasRole) saasRoleService.selectById(roleId);
//            if (!saasRole.getMerchantCode().equals(saasAdmin.getMerchantCode())) {
//                throw new ApiErrorException("请求角色非法");
//            }
//            roleName = saasRole.getName();
//            menuIds = adminInfoApplication.getMenuIdsByRoleId(roleId);
//            buttonIds = adminInfoApplication.getButtonIdsByRoleId(roleId);
//        } else {
//            menuIds = adminInfoApplication.getMenuIdsByAdmin(code);
//            buttonIds = adminInfoApplication.getButtonIdsByAdmin(code);
//        }

        List<SaasMenu> menuList = saasMenuService.getListByIds(menuIds);
        List<SaasOperationButton> buttonList = saasOperationButtonService.getListByIds(buttonIds);

        RoleDetailResponse roleDetailResponse = new RoleDetailResponse(roleName, new FormedMenuVO(menuList),
                new UserButtonResponse(buttonList, saasOperationButtonService.getParentButtonForMap()).getList(), buttonCheckedIds, menuCheckedIds);
        return Response.ok().putData(roleDetailResponse);
    }


    @RequestMapping(value = "/map", method = RequestMethod.GET)
    @ParamsValidate
    @ApiOperation(value = "角色枚举", response = RoleMapResponse.class)
    public Response roleMap() {
        SaasAdmin saasAdmin = RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
        List<SaasRole> saasRoleList = saasRoleService.getRoleListByMerchantCode(saasAdmin.getMerchantCode(), null);
        List<RoleMapResponse> list = new ArrayList<>();
        saasRoleList.forEach(saasRole -> list.add(new RoleMapResponse(saasRole.getId(), saasRole.getName())));
        return Response.ok().putData(list);
    }


    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ParamsValidate
    @ApiOperation(value = "更新角色")
    public Response update(@RequestBody UpdateRoleRequest updateRoleRequest) {
        roleApplication.updateRole(updateRoleRequest.getRoleName(), updateRoleRequest.getRoleId(), updateRoleRequest.getMenusIds(), updateRoleRequest.getButtonIds());
        return Response.ok();
    }

}
