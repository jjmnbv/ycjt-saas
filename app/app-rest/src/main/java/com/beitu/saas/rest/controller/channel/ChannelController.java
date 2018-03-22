package com.beitu.saas.rest.controller.channel;

import com.beitu.saas.app.application.channel.SaasChannelApplication;
import com.beitu.saas.channel.SaasChannelParam;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.domain.SaasChannelRiskSettingsVo;
import com.beitu.saas.channel.domain.SaasChannelVo;
import com.beitu.saas.channel.enums.ChannelErrorCodeEnum;
import com.beitu.saas.common.consts.ConstantNum;
import com.beitu.saas.rest.controller.channel.request.ChannelQueryRequestVo;
import com.beitu.saas.rest.controller.channel.request.ChannelRequestVo;
import com.beitu.saas.rest.controller.channel.response.SaasChannelListResponse;
import com.fqgj.common.api.Page;
import com.fqgj.common.api.Response;
import com.fqgj.common.utils.CollectionUtils;
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
public class ChannelController {
    private static final Log LOGGER = LogFactory.getLog(ChannelController.class);


    @Autowired
    private SaasChannelApplication saasChannelApplication;

    /**
     * 新建渠道
     *
     * @param channelRequestVo
     * @return
     */
    @ApiOperation(value = "新建渠道", response = Response.class)
    @RequestMapping(value = "/addChannel", method = RequestMethod.POST)
    public Response addAgency(@RequestBody ChannelRequestVo channelRequestVo) {
        List<SaasChannelRiskSettingsVo> settingsVos = new ArrayList<>();
        boolean setModule = CollectionUtils.isNotEmpty(channelRequestVo.getSaasModuleVoList()) && CollectionUtils.isEmpty(channelRequestVo.getSaasModuleItemVoList());
        boolean setModuleItem = CollectionUtils.isNotEmpty(channelRequestVo.getSaasModuleItemVoList());

        if (setModuleItem) {
            channelRequestVo.getSaasModuleItemVoList().stream().forEach(x -> {
                SaasChannelRiskSettingsVo saasChannelRiskSettingsVo = new SaasChannelRiskSettingsVo();
                saasChannelRiskSettingsVo.setModuleCode(x.getModuleCode())
                        .setItemCode(x.getItemCode())
                        .setRequired(x.getRequired());
                settingsVos.add(saasChannelRiskSettingsVo);
            });
        } else if (setModule) {
            channelRequestVo.getSaasModuleVoList().stream().forEach(x -> {
                SaasChannelRiskSettingsVo settingsVo = new SaasChannelRiskSettingsVo();
                settingsVo.setModuleCode(x.getModuleCode())
                        .setRequired(x.getRequired());
                settingsVos.add(settingsVo);
            });
        }

        SaasChannelVo saasChannelVo = new SaasChannelVo();
        BeanUtils.copyProperties(channelRequestVo, saasChannelVo);

        try {
            saasChannelApplication.createChannel(saasChannelVo, settingsVos);
        } catch (Exception e) {
            LOGGER.error("==  创建渠道失败, 机构号:{}, 渠道名称:{} ,失败原因:{}  ==", channelRequestVo.getMerchantCode(), channelRequestVo.getChannelName(), e);
            return Response.error(null, ChannelErrorCodeEnum.PARAM_INVALID.getMsg());
        }
        return Response.ok().putData("创建渠道成功");
    }


    /**
     * 获取渠道列表
     *
     * @return
     */
    @RequestMapping(value = "/saasChannelList", method = RequestMethod.POST)
    @ApiOperation(value = "渠道列表", response = SaasChannelListResponse.class)
    public Response getAgencyList(@RequestBody ChannelQueryRequestVo channelQueryRequestVo) {
        Integer pageNo = 1;
        if (null != channelQueryRequestVo.getPageNo()) {
            pageNo = channelQueryRequestVo.getPageNo();
        }
        Page page = new Page();
        page.setCurrentPage(pageNo);

        SaasChannelParam saasChannelParam = new SaasChannelParam();
        BeanUtils.copyProperties(channelQueryRequestVo, saasChannelParam);
        List<SaasChannelVo> saasChannelList = saasChannelApplication.getSaasChannelList(saasChannelParam, page);

        Boolean hasNextPage = (page.getTotalCount() + page.getPageSize() - 1) / page.getPageSize() > page.getCurrentPage();
        SaasChannelListResponse saasChannelListResponse = new SaasChannelListResponse(hasNextPage, pageNo + ConstantNum.ONE, saasChannelList);

        return Response.ok().putData(saasChannelListResponse);
    }
}
