package com.beitu.saas.rest.controller.merchant;

import com.beitu.saas.app.annotations.HasPermission;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.common.consts.ButtonPermissionConsts;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.merchant.client.SaasMerchantFlowConfigService;
import com.beitu.saas.merchant.client.domain.SaasMerchantFlowConfigVo;
import com.beitu.saas.merchant.client.enums.MerchantFlowNumEnum;
import com.beitu.saas.merchant.client.enums.MerchantFlowZMEnum;
import com.beitu.saas.rest.controller.merchant.request.SaveConfigRequest;
import com.beitu.saas.rest.controller.merchant.response.FlowConfigInfoResponse;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.common.api.Response;
import com.fqgj.common.api.annotations.ParamsValidate;
import com.fqgj.common.api.exception.ApiErrorException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiaochong
 * @create 2018/4/9 下午12:19
 * @description
 */
@RestController
@RequestMapping("/merchant/config")
@Api(description = "机构流量设置相关接口")
public class MerchantConfigController {

    @Autowired
    private SaasMerchantFlowConfigService saasMerchantFlowConfigService;

    @Autowired
    private RedisClient redisClient;

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

    @RequestMapping(value = "/flow-zm/map/{flowType}", method = RequestMethod.GET)
    @ApiOperation(value = "芝麻分枚举")
    public Response flowZm(@PathVariable("flowType") Long flowType) {
        return Response.ok().putData(MerchantFlowZMEnum.getEnumMapByFlowType(flowType));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ParamsValidate
    @ApiOperation(value = "保存流量设置")
    @HasPermission(permissionKey = ButtonPermissionConsts.SET_FLOW_CONDITIONS)
    public Response saveConfig(@RequestBody SaveConfigRequest request) {
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        Object o = redisClient.get(RedisKeyConsts.SAAS_MERCHANT_FLOW_CONFIG, merchantCode);
        if (null != o) {
            throw new ApiErrorException("流量一天只能设置一次");
        }
        redisClient.set(RedisKeyConsts.SAAS_MERCHANT_FLOW_CONFIG, merchantCode, merchantCode);
        redisClient.expireAt(RedisKeyConsts.SAAS_MERCHANT_FLOW_CONFIG, DateUtil.getNextDayBeginTime(), merchantCode);
        SaasMerchantFlowConfigVo vo = new SaasMerchantFlowConfigVo();
        BeanUtils.copyProperties(request, vo);
        vo.setMerchantCode(merchantCode);
        saasMerchantFlowConfigService.replace(vo);
        return Response.ok();
    }

}
