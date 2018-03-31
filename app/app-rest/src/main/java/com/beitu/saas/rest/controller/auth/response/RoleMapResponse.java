package com.beitu.saas.rest.controller.auth.response;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;

/**
 * @author xiaochong
 * @create 2018/3/31 下午2:28
 * @description
 */
@ApiModel
public class RoleMapResponse implements ResponseData {

    private Long roleId;

    private String roleName;

    public RoleMapResponse(Long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
