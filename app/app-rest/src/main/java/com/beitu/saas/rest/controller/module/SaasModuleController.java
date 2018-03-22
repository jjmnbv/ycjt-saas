package com.beitu.saas.rest.controller.module;

import com.beitu.saas.app.application.module.SaasModuleApplication;
import com.beitu.saas.channel.domain.SaasModuleItemVo;
import com.beitu.saas.channel.domain.SaasModuleVo;
import com.beitu.saas.channel.enums.ChannelErrorCodeEnum;
import com.beitu.saas.rest.controller.module.request.SaasModuleItemRequestVo;
import com.beitu.saas.rest.controller.module.request.SaasModuleRequestVo;
import com.beitu.saas.rest.controller.module.response.SaasModuleItemResponse;
import com.beitu.saas.rest.controller.module.response.SaasModuleResponse;
import com.fqgj.common.api.Response;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
public class SaasModuleController {
    private static final Log LOGGER = LogFactory.getLog(SaasModuleController.class);

    @Autowired
    private SaasModuleApplication saasModuleApplication;


    /**
     * 风控模块列表查询
     *
     * @return
     */
    @RequestMapping(value = "/moduleList", method = RequestMethod.POST)
    @ApiOperation(value = "渠道列表", response = SaasModuleResponse.class)
    public Response getModuleList() {
        List<SaasModuleVo> moduleList = saasModuleApplication.getModuleList();
        return Response.ok().putData(new SaasModuleResponse(moduleList));
    }


    /**
     * 风控模块详情表查询
     */
    @RequestMapping(value = "/moduleItemlList/{moduleCode}", method = RequestMethod.POST)
    @ApiOperation(value = "渠道列表", response = SaasModuleItemResponse.class)
    public Response getModuleItemList(@PathVariable(value = "moduleCode") String moduleCode) {
        List<SaasModuleItemVo> moduleItemVos = saasModuleApplication.getModuleItemList(moduleCode);
        return Response.ok().putData(new SaasModuleItemResponse(moduleItemVos));
    }


    /**
     * 新建风控模块
     *
     * @param saasModuleRequestVo
     * @return
     */
    @ApiOperation(value = "新建风控模块", response = Response.class)
    @RequestMapping(value = "/addModule", method = RequestMethod.POST)
    public Response addModule(@RequestBody SaasModuleRequestVo saasModuleRequestVo) {
        SaasModuleVo moduleVo = new SaasModuleVo();
        BeanUtils.copyProperties(saasModuleRequestVo, moduleVo);
        try {
            saasModuleApplication.createModule(moduleVo);
        } catch (Exception e) {
            LOGGER.error("==  创建风控模块失败, 模块号:{}, 失败原因:{}  ==", saasModuleRequestVo.getModuleCode(), e);
            return Response.error(null, ChannelErrorCodeEnum.MODULE_PARAM_INVALID.getMsg());
        }
        return Response.ok().putData("操作成功");
    }


    /**
     * 新建风控模块字段
     *
     * @param saasModuleItemRequestVo
     * @return
     */
    @ApiOperation(value = "新建风控模块字段", response = Response.class)
    @RequestMapping(value = "/addModuleItem", method = RequestMethod.POST)
    public Response addModuleItem(@RequestBody SaasModuleItemRequestVo saasModuleItemRequestVo) {
        SaasModuleItemVo saasModuleItemVo = new SaasModuleItemVo();
        BeanUtils.copyProperties(saasModuleItemRequestVo, saasModuleItemVo);
        try {
            saasModuleApplication.createModuleItem(saasModuleItemVo);
        } catch (Exception e) {
            LOGGER.error("==  创建模块字段失败, 模块号:{},字段号:{} 失败原因:{}  ==", saasModuleItemRequestVo.getModuleCode(), saasModuleItemRequestVo.getItemCode(), e);
            return Response.error(null, ChannelErrorCodeEnum.MODULE_ITEM_PARAM_INVALID.getMsg());
        }
        return Response.ok().putData("操作成功");
    }
}
