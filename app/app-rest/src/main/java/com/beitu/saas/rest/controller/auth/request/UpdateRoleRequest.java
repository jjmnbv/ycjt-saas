package com.beitu.saas.rest.controller.auth.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author xiaochong
 * @create 2018/3/31 下午3:46
 * @description
 */
@ApiModel
public class UpdateRoleRequest extends ParamsObject {


    @NotNull(message ="角色id不能为空")
    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("角色名")
    @NotBlank(message = "角色名不能为空")
    private String roleName;

    @ApiModelProperty("角色菜单id集合")
    private List<Integer> menusIds;

    @ApiModelProperty("角色按钮id集合")
    private List<Integer> buttonIds;

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

    public List<Integer> getMenusIds() {
        return menusIds;
    }

    public void setMenusIds(List<Integer> menusIds) {
        this.menusIds = menusIds;
    }

    public List<Integer> getButtonIds() {
        return buttonIds;
    }

    public void setButtonIds(List<Integer> buttonIds) {
        this.buttonIds = buttonIds;
    }

    @Override
    public void validate() {

    }
}
