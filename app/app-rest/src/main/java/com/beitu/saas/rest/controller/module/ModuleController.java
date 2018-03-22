package com.beitu.saas.rest.controller.module;

import com.beitu.saas.app.application.module.SaasModuleApplication;
import com.beitu.saas.channel.domain.SaasModuleItemVo;
import com.beitu.saas.channel.domain.SaasModuleVo;
import com.beitu.saas.channel.enums.ChannelErrorCodeEnum;
import com.beitu.saas.rest.controller.module.request.ModuleItemRequestVo;
import com.beitu.saas.rest.controller.module.request.ModuleRequestVo;
import com.fqgj.common.api.Response;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 下午4:28
 */
@Api(description = "渠道相关接口")
@RestController
@RequestMapping("/module")
public class ModuleController {
    private static final Log LOGGER = LogFactory.getLog(ModuleController.class);

    @Autowired
    private SaasModuleApplication saasModuleApplication;

    /**
     * 新建渠道
     *
     * @param moduleRequestVo
     * @return
     */
    @ApiOperation(value = "新建风控模块", response = Response.class)
    @RequestMapping(value = "/addModule", method = RequestMethod.POST)
    public Response addModule(@RequestBody ModuleRequestVo moduleRequestVo) {
        SaasModuleVo moduleVo = new SaasModuleVo();
        BeanUtils.copyProperties(moduleRequestVo, moduleVo);
        try {
            saasModuleApplication.createModule(moduleVo);
        } catch (Exception e) {
            LOGGER.error("==  创建风控模块失败, 模块号:{}, 失败原因:{}  ==", moduleRequestVo.getModuleCode(), e);
            return Response.error(null, ChannelErrorCodeEnum.MODULE_PARAM_INVALID.getMsg());
        }
        return Response.ok().putData("操作成功");
    }


    /**
     * 新建渠道
     *
     * @param moduleItemRequestVo
     * @return
     */
    @ApiOperation(value = "新建风控模块字段", response = Response.class)
    @RequestMapping(value = "/addModuleItem", method = RequestMethod.POST)
    public Response addModuleItem(@RequestBody ModuleItemRequestVo moduleItemRequestVo) {
        SaasModuleItemVo saasModuleItemVo = new SaasModuleItemVo();
        BeanUtils.copyProperties(moduleItemRequestVo, saasModuleItemVo);
        try {
            saasModuleApplication.createModuleItem(saasModuleItemVo);
        } catch (Exception e) {
            LOGGER.error("==  创建模块字段失败, 模块号:{},字段号:{} 失败原因:{}  ==", moduleItemRequestVo.getModuleCode(), moduleItemRequestVo.getItemCode(), e);
            return Response.error(null, ChannelErrorCodeEnum.MODULE_ITEM_PARAM_INVALID.getMsg());
        }
        return Response.ok().putData("操作成功");
    }
}
