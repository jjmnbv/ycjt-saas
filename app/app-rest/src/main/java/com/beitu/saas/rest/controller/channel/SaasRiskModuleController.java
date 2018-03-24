package com.beitu.saas.rest.controller.channel;

import com.beitu.saas.app.application.channel.SaasRiskModuleApplication;
import com.beitu.saas.channel.domain.SaasModuleItemVo;
import com.beitu.saas.channel.domain.SaasModuleVo;
import com.beitu.saas.channel.enums.ChannelErrorCodeEnum;
import com.beitu.saas.rest.controller.channel.request.SaasRiskModuleItemRequestParam;
import com.beitu.saas.rest.controller.channel.request.SaasRiskModuleRequestParam;
import com.beitu.saas.rest.controller.channel.response.SaasModuleItemResponse;
import com.beitu.saas.rest.controller.channel.response.SaasModuleResponse;
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
@Api(description = "渠道风控配置相关接口")
@RestController
@RequestMapping("/module")
public class SaasRiskModuleController {
    private static final Log LOGGER = LogFactory.getLog(SaasRiskModuleController.class);

    @Autowired
    private SaasRiskModuleApplication saasRiskModuleApplication;


    /**
     * 风控模块列表查询
     *
     * @return
     */
    @RequestMapping(value = "/moduleList", method = RequestMethod.POST)
    @ApiOperation(value = "渠道列表", response = SaasModuleResponse.class)
    public Response getModuleList() {
        List<SaasModuleVo> moduleList = saasRiskModuleApplication.getModuleList();
        return Response.ok().putData(new SaasModuleResponse(moduleList));
    }


    /**
     * 风控模块详情表查询
     */
    @RequestMapping(value = "/moduleItemlList/{moduleCode}", method = RequestMethod.POST)
    @ApiOperation(value = "渠道列表", response = SaasModuleItemResponse.class)
    public Response getModuleItemList(@PathVariable(value = "moduleCode") String moduleCode) {
        List<SaasModuleItemVo> moduleItemVos = saasRiskModuleApplication.getModuleItemList(moduleCode);
        return Response.ok().putData(new SaasModuleItemResponse(moduleItemVos));
    }


    /**
     * 新建风控模块
     *
     * @param saasRiskModuleRequestParam
     * @return
     */
    @ApiOperation(value = "新建风控模块", response = Response.class)
    @RequestMapping(value = "/addModule", method = RequestMethod.POST)
    public Response addModule(@RequestBody SaasRiskModuleRequestParam saasRiskModuleRequestParam) {
        SaasModuleVo moduleVo = new SaasModuleVo();
        BeanUtils.copyProperties(saasRiskModuleRequestParam, moduleVo);
        try {
            saasRiskModuleApplication.createModule(moduleVo);
        } catch (Exception e) {
            LOGGER.error("==  创建风控模块失败, 模块号:{}, 失败原因:{}  ==", saasRiskModuleRequestParam.getModuleCode(), e);
            return Response.error(null, ChannelErrorCodeEnum.MODULE_PARAM_INVALID.getMsg());
        }
        return Response.ok().putData("操作成功");
    }


    /**
     * 新建风控模块字段
     *
     * @param saasRiskModuleItemRequestParam
     * @return
     */
    @ApiOperation(value = "新建风控模块字段", response = Response.class)
    @RequestMapping(value = "/addModuleItem", method = RequestMethod.POST)
    public Response addModuleItem(@RequestBody SaasRiskModuleItemRequestParam saasRiskModuleItemRequestParam) {
        SaasModuleItemVo saasModuleItemVo = new SaasModuleItemVo();
        BeanUtils.copyProperties(saasRiskModuleItemRequestParam, saasModuleItemVo);
        try {
            saasRiskModuleApplication.createModuleItem(saasModuleItemVo);
        } catch (Exception e) {
            LOGGER.error("==  创建模块字段失败, 模块号:{},字段号:{} 失败原因:{}  ==", saasRiskModuleItemRequestParam.getModuleCode(), saasRiskModuleItemRequestParam.getItemCode(), e);
            return Response.error(null, ChannelErrorCodeEnum.MODULE_ITEM_PARAM_INVALID.getMsg());
        }
        return Response.ok().putData("操作成功");
    }
}
