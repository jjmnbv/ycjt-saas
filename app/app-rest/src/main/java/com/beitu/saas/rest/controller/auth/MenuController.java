package com.beitu.saas.rest.controller.auth;

import com.beitu.saas.app.application.auth.AdminInfoApplication;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.auth.entity.SaasMenu;
import com.beitu.saas.auth.entity.SaasOperationButton;
import com.beitu.saas.auth.service.SaasMenuService;
import com.beitu.saas.auth.service.SaasOperationButtonService;
import com.beitu.saas.auth.vo.FormedMenuVO;
import com.beitu.saas.common.aop.TraceAnno;
import com.beitu.saas.rest.controller.auth.response.MenusResponse;
import com.beitu.saas.rest.controller.auth.response.OperationButtonResponse;
import com.beitu.saas.rest.controller.auth.response.UserButtonResponse;
import com.beitu.saas.rest.controller.auth.response.UserMenuResponse;
import com.fqgj.common.api.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * @author xiaochong
 * @create 2018/3/23 上午10:30
 * @description
 */
@RestController
@RequestMapping("/menu")
@Api(description = "菜单接口")
public class MenuController {

    @Autowired
    private SaasMenuService saasMenuService;

    @Autowired
    private SaasOperationButtonService saasOperationButtonService;

    @Autowired
    private AdminInfoApplication adminInfoApplication;

    @RequestMapping(value = "/role/list", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户菜单", response = UserMenuResponse.class)
    public Response adminMenuList() {
        List<Integer> menuIds = adminInfoApplication.getMenuIdsByAdmin(RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode());
        List<SaasMenu> menuList = saasMenuService.getListByIds(menuIds);
        return Response.ok().putData(new UserMenuResponse(new FormedMenuVO(menuList)));
    }

    @RequestMapping(value = "/role/button/list", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户按钮", response = SaasOperationButton.class)
    public Response adminButtonList() {
        List<Integer> buttonIds = adminInfoApplication.getButtonIdsByAdmin(RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getCode());
        List<SaasOperationButton> buttonList = saasOperationButtonService.getListByIds(buttonIds);
        return Response.ok().putData(new UserButtonResponse(buttonList, saasOperationButtonService.getParentButtonForMap()));
    }

//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public Response allMenus() {
//        List<SaasMenu> menuList = saasMenuService.getAllMenu();
//        List<MenusResponse> responses = new ArrayList<>();
//        menuList.forEach(saasMenu -> {
//            MenusResponse menusResponse = new MenusResponse();
//            BeanUtils.copyProperties(saasMenu, menusResponse);
//            responses.add(menusResponse);
//        });
//        return Response.ok().putData(responses);
//    }
//
//    @RequestMapping(value = "/operation/list", method = RequestMethod.GET)
//    public Response allOperation() {
//        List<SaasOperationButton> menuList = saasOperationButtonService.getAllButton();
//        List<OperationButtonResponse> responses = new ArrayList<>();
//        menuList.forEach(saasOperationButton -> {
//            OperationButtonResponse operationButtonResponse = new OperationButtonResponse();
//            operationButtonResponse.setButtonId(saasOperationButton.getId());
//            BeanUtils.copyProperties(saasOperationButton, operationButtonResponse);
//            responses.add(operationButtonResponse);
//        });
//        return Response.ok().putData(responses);
//    }
}
