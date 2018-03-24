package com.beitu.saas.rest.controller.auth.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * @author xiaochong
 * @create 2018/3/23 下午8:39
 * @description
 */
@ApiModel("添加角色参数")
public class AddRoleRequest extends ParamsObject {

    @ApiModelProperty("角色名")
    @NotBlank(message = "角色名不能为空")
    private String roleName;

    @ApiModelProperty("角色菜单id集合")
    private List<Integer> menusIds;

    @ApiModelProperty("角色按钮id集合")
    private List<Integer> buttonIds;

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
