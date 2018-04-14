package com.beitu.saas.rest.controller.auth;

import com.beitu.saas.app.annotations.HasPermission;
import com.beitu.saas.app.annotations.IgnoreRepeatRequest;
import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.app.application.auth.MerchantApplication;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.app.enums.ButtonPermissionEnum;
import com.beitu.saas.auth.domain.SaasMerchantVo;
import com.beitu.saas.auth.entity.SaasMerchant;
import com.beitu.saas.auth.entity.SaasMerchantConfig;
import com.beitu.saas.auth.entity.SaasSmsConfigDictionary;
import com.beitu.saas.auth.enums.ContractConfigTypeEnum;
import com.beitu.saas.auth.service.SaasMerchantConfigService;
import com.beitu.saas.auth.service.SaasMerchantService;
import com.beitu.saas.auth.service.SaasSmsConfigDictionaryService;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.consts.ButtonPermissionConsts;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.consts.TimeConsts;
import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.rest.controller.auth.request.AddMerchantRequest;
import com.beitu.saas.rest.controller.auth.response.MerchantInfoResponse;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.common.api.Response;
import com.fqgj.common.api.annotations.ParamsValidate;
import com.fqgj.common.api.exception.ApiErrorException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author xiaochong
 * @create 2018/3/26 上午11:31
 * @description
 */
@RestController
@RequestMapping("/merchant")
@Api(description = "机构接口")
public class MerchantController {

    @Autowired
    private SaasMerchantService saasMerchantService;

    @Autowired
    private SaasSmsConfigDictionaryService saasSmsConfigDictionaryService;

    @Autowired
    private SaasMerchantConfigService saasMerchantConfigService;

    @Autowired
    private MerchantApplication merchantApplication;

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private RedisClient redisClient;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ApiOperation(value = "机构信息", response = MerchantInfoResponse.class)
    public Response merchantInfo() {
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        SaasMerchantVo saasMerchantVo = saasMerchantService.getByMerchantCode(merchantCode);
        List<SaasSmsConfigDictionary> saasSmsConfigDictionary = saasSmsConfigDictionaryService.getAllSmsConfig();
        List<String> smsConfig = saasMerchantConfigService.getSmsConfigByMerchantCode(merchantCode);
        Boolean isCompanyContract = saasMerchantConfigService.isCompanyContractByMerchantCode(merchantCode);
        MerchantInfoResponse merchantInfoResponse = new MerchantInfoResponse(saasMerchantVo, saasSmsConfigDictionary, smsConfig, isCompanyContract);
        return Response.ok().putData(merchantInfoResponse);
    }

    @RequestMapping(value = "/contractType/{type}", method = RequestMethod.PUT)
    @ApiOperation(value = "合同设置")
    @HasPermission(permissionKey = ButtonPermissionConsts.CONTRACT_SETTING)
    public Response setContractType(@PathVariable Integer type) {
        if (Objects.equals(type, ContractConfigTypeEnum.COMPANY_CONTRACT.getKey())) {
            throw new ApiErrorException("暂不支持公司合同");
        }
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        saasMerchantConfigService.updateContractConfig(merchantCode, type);
        return Response.ok();
    }

    @RequestMapping(value = "/sms/{bizCode}/{enable}", method = RequestMethod.PUT)
    @ApiOperation(value = "短信配置")
    @HasPermission(permissionKey = ButtonPermissionConsts.SMS_SETTING)
    public Response setSmsEnable(@ApiParam("短信id") @PathVariable("bizCode") String bizCode, @PathVariable("enable") Boolean enable) {
        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        saasMerchantConfigService.updateSmsConfig(merchantCode, enable, bizCode);
        return Response.ok();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加机构")
    @SignIgnore
    @VisitorAccessible
    @ParamsValidate
    @IgnoreRepeatRequest
    public Response add(@RequestBody AddMerchantRequest request) {
        if (!configUtil.enableAddMerchant()) {
            return Response.ok("添加机构不可用,请联系管理员");
        }
        Object o = redisClient.get(RedisKeyConsts.SAAS_MERCHANT_ADD, request.getAdminInfo().getAccountPhone());
        if (null != o) {
            throw new ApiErrorException("重复提交");
        }
        redisClient.set(RedisKeyConsts.SAAS_MERCHANT_ADD, request.getAdminInfo().getAccountPhone(), TimeConsts.ONE_MINUTE, request.getAdminInfo().getAccountPhone());
        
        SaasMerchant saasMerchant = new SaasMerchant();
        if (request.getMerchantInfo() != null) {
            BeanUtils.copyProperties(request.getMerchantInfo(), saasMerchant);
        }
        if (request.getLenderInfo() != null) {
            BeanUtils.copyProperties(request.getLenderInfo(), saasMerchant);
        }
        AddMerchantRequest.AdminInfo adminInfo = request.getAdminInfo();
        merchantApplication.addMerchant(saasMerchant, adminInfo.getPassword(), adminInfo.getAccountPhone(), adminInfo.getAccountName());
        return Response.ok();
    }


}
