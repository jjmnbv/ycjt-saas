package com.beitu.saas.rest.controller.merchant;

import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.auth.domain.MerchantContractInfoVo;
import com.beitu.saas.merchant.client.SaasMerchantFlowConfigService;
import com.beitu.saas.merchant.client.domain.SaasMerchantFlowConfigVo;
import com.beitu.saas.merchant.client.enums.MerchantFlowNumEnum;
import com.beitu.saas.rest.controller.merchant.request.SaveConfigRequest;
import com.beitu.saas.rest.controller.merchant.response.FlowConfigInfoResponse;
import com.fqgj.common.api.Response;
import com.fqgj.common.api.annotations.ParamsValidate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaochong
 * @create 2018/4/9 下午12:19
 * @description
 */
@RestController("/merchant/config")
@Api(description = "机构流量设置相关接口")
public class MerchantConfigController {

    @Autowired
    private SaasMerchantFlowConfigService saasMerchantFlowConfigService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ApiOperation(value = "获取机构推荐流量配置", response = FlowConfigInfoResponse.class)
    public Response configInfo() {
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        SaasMerchantFlowConfigVo saasMerchantFlowConfigVo = saasMerchantFlowConfigService.getMerchantFlowConfigByMerchantCode(merchantCode);
        FlowConfigInfoResponse flowConfigInfoResponse = new FlowConfigInfoResponse();
        if (null != saasMerchantFlowConfigVo) {
            BeanUtils.copyProperties(saasMerchantFlowConfigVo, flowConfigInfoResponse);
        }
        return Response.ok().putData(flowConfigInfoResponse);
    }
    @RequestMapping(value = "/flow-num/map", method = RequestMethod.GET)
    @ApiOperation(value = "每日最大推送量枚举")
    public Response flowNum() {
        return Response.ok().putData(MerchantFlowNumEnum.getEnumMap());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ParamsValidate
    @ApiOperation(value = "保存流量设置")
    public Response saveConfig(@RequestBody SaveConfigRequest request) {
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        SaasMerchantFlowConfigVo vo = new SaasMerchantFlowConfigVo();
        BeanUtils.copyProperties(request, vo);
        vo.setMerchantCode(merchantCode);
        saasMerchantFlowConfigService.replace(vo);
        return Response.ok();
    }

}
