package com.beitu.saas.rest.controller.channel;

import com.beitu.saas.app.application.channel.SaasChannelApplication;
import com.beitu.saas.channel.domain.SaasChannelRiskSettingsVo;
import com.beitu.saas.channel.domain.SaasChannelVo;
import com.beitu.saas.rest.controller.channel.request.ChannelRequestVo;
import com.beitu.saas.rest.controller.channel.request.SaasModuleItemVo;
import com.beitu.saas.rest.controller.channel.request.SaasModuleVo;
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

        if (CollectionUtils.isNotEmpty(channelRequestVo.getSaasModuleItemVoList())) {
            channelRequestVo.getSaasModuleItemVoList().stream().forEach(x -> {
                SaasChannelRiskSettingsVo saasChannelRiskSettingsVo = new SaasChannelRiskSettingsVo();
                saasChannelRiskSettingsVo.setModuleCode(x.getModuleCode())
                        .setItemCode(x.getItemCode())
                        .setRequired(x.getRequired());
                settingsVos.add(saasChannelRiskSettingsVo);
            });
        } else if (CollectionUtils.isNotEmpty(channelRequestVo.getSaasModuleVoList())) {
            channelRequestVo.getSaasModuleVoList().stream().forEach(x -> {
                SaasChannelRiskSettingsVo settingsVo = new SaasChannelRiskSettingsVo();
                settingsVo.setModuleCode(x.getModuleCode())
                        .setRequired(x.getRequired());
                settingsVos.add(settingsVo);
            });
        }

        SaasChannelVo saasChannelVo = new SaasChannelVo();
        BeanUtils.copyProperties(channelRequestVo, saasChannelVo);
        saasChannelApplication.createChannel(saasChannelVo, settingsVos);

        return Response.ok().putData("新建渠道成功");
    }
}
