package com.beitu.saas.rest.controller.channel;

import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.app.application.channel.SaasChannelApplication;
import com.beitu.saas.channel.param.SaasChannelParam;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.domain.SaasChannelVo;
import com.beitu.saas.channel.enums.ChannelErrorCodeEnum;
import com.beitu.saas.channel.param.SaasChannelRiskSettingsParam;
import com.beitu.saas.rest.controller.channel.request.SaasChannelQueryRequestParam;
import com.beitu.saas.rest.controller.channel.request.SaasChannelRequestParam;
import com.beitu.saas.rest.controller.channel.request.SaasOperateChannelRequestParam;
import com.beitu.saas.rest.controller.channel.response.SaasChannelListResponse;
import com.fqgj.common.api.Page;
import com.fqgj.common.api.Response;
import com.fqgj.common.response.ModuleResponse;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.StringUtils;
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

import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 上午11:03
 */
@Api(description = "渠道相关接口")
@RestController
@RequestMapping("/channel")
public class SaasChannelController {
    private static final Log LOGGER = LogFactory.getLog(SaasChannelController.class);


    @Autowired
    private SaasChannelApplication saasChannelApplication;
    @Autowired
    private SaasChannelService saasChannelService;

    /**
     * 新建渠道
     *
     * @param saasChannelRequestParam
     * @return
     */
    @ApiOperation(value = "新建渠道", response = Response.class)
    @RequestMapping(value = "/addChannel", method = RequestMethod.POST)
    @VisitorAccessible
    @SignIgnore
    public Response addChannel(@RequestBody SaasChannelRequestParam saasChannelRequestParam) {
        List<SaasChannelRiskSettingsParam> settingsVos = new ArrayList<>();
        boolean setModule = CollectionUtils.isNotEmpty(saasChannelRequestParam.getSaasModuleRequestParams());
        boolean setModuleItem = saasChannelRequestParam.getSaasModuleItemRequestParams().size() == 1 && !StringUtils.isEmpty(saasChannelRequestParam.getSaasModuleItemRequestParams().get(0).getItemCode());

        if (setModuleItem) {
            saasChannelRequestParam.getSaasModuleItemRequestParams().stream().forEach(x -> {
                SaasChannelRiskSettingsParam saasChannelRiskSettingsVo = new SaasChannelRiskSettingsParam();
                saasChannelRiskSettingsVo.setModuleCode(x.getModuleCode())
                        .setItemCode(x.getItemCode())
                        .setRequired(x.getRequired());
                settingsVos.add(saasChannelRiskSettingsVo);
            });
        } else if (setModule) {
            saasChannelRequestParam.getSaasModuleRequestParams().stream().forEach(x -> {
                SaasChannelRiskSettingsParam settingsVo = new SaasChannelRiskSettingsParam();
                settingsVo.setModuleCode(x.getModuleCode())
                        .setRequired(x.getRequired());
                settingsVos.add(settingsVo);
            });
        }

        SaasChannelParam saasChannelParam = new SaasChannelParam();
        BeanUtils.copyProperties(saasChannelRequestParam, saasChannelParam);

        try {
            saasChannelApplication.createChannel(saasChannelParam, settingsVos);
        } catch (Exception e) {
            LOGGER.error("==  创建渠道失败, 机构号:{}, 渠道名称:{} ,失败原因:{}  ==", saasChannelRequestParam.getMerchantCode(), saasChannelRequestParam.getChannelName(), e);
            return Response.error(null, ChannelErrorCodeEnum.CHANNEL_PARAM_INVALID.getMsg());
        }
        return Response.ok().putData("操作成功");
    }


    /**
     * 获取渠道列表
     *
     * @return
     */
    @RequestMapping(value = "/saasChannelList", method = RequestMethod.POST)
    @ApiOperation(value = "渠道列表", response = SaasChannelListResponse.class)
    @VisitorAccessible
    @SignIgnore
    public ModuleResponse getSaasChannelList(@RequestBody SaasChannelQueryRequestParam saasChannelQueryRequestParam, Page page) {

        SaasChannelParam saasChannelParam = new SaasChannelParam();
        BeanUtils.copyProperties(saasChannelQueryRequestParam, saasChannelParam);
        List<SaasChannelVo> saasChannelList = saasChannelApplication.getSaasChannelList(saasChannelParam, page);

        SaasChannelListResponse saasChannelListResponse = new SaasChannelListResponse(saasChannelList);
        return new ModuleResponse<>(saasChannelListResponse);
    }


    /**
     * 禁用/启用 渠道操作
     */
    @ApiOperation(value = "禁用/启用", response = Response.class)
    @VisitorAccessible
    @SignIgnore
    @RequestMapping(value = "/operateSaasChannel", method = RequestMethod.POST)
    public Response operateSaasChannel(@RequestBody SaasOperateChannelRequestParam saasOperateChannelRequestParam) {
        saasChannelService.operateSaasChannel(saasOperateChannelRequestParam.getChannelCode(), saasOperateChannelRequestParam.getStatus());
        return Response.ok().putData("操作成功");
    }
}
