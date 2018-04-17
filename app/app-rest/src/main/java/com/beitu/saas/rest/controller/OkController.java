package com.beitu.saas.rest.controller;

import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.app.application.auth.MerchantApplication;
import com.beitu.saas.app.application.credit.CarrierReportApplication;
import com.beitu.saas.app.application.credit.DunningReportApplication;
import com.beitu.saas.app.application.finance.SaasConsumeDayStatApplication;
import com.beitu.saas.auth.domain.MerchantContractInfoVo;
import com.beitu.saas.auth.service.SaasMerchantService;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.beitu.saas.channel.enums.ChannelTypeEnum;
import com.beitu.saas.common.enums.RestCodeEnum;
import com.beitu.saas.common.utils.IpChooseUtil;
import com.beitu.saas.common.utils.OrderNoUtil;
import com.beitu.saas.credit.client.SaasCreditCarrierService;
import com.beitu.saas.credit.entity.SaasCreditCarrier;

import com.beitu.saas.intergration.risk.RiskEcommerceService;
import com.beitu.saas.intergration.risk.param.GXBEcommerceCrawlingParam;

import com.beitu.saas.risk.helpers.CollectionUtils;
import com.beitu.saas.risk.helpers.StringUtils;
import com.beitu.saas.user.client.SaasEsignAccountService;
import com.beitu.saas.user.client.SaasEsignUserAuthorizationService;
import com.beitu.saas.user.client.SaasUserEsignAuthorizationService;
import com.beitu.saas.user.domain.SaasEsignAccountVo;
import com.beitu.saas.user.domain.SaasEsignUserAuthorizationVo;
import com.beitu.saas.user.entity.SaasUserEsignAuthorization;

import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author xiaochong
 * @create 2017/11/6 下午4:24
 * @description
 */
@RestController
public class OkController {

    private static final Log LOGGER = LogFactory.getLog(OkController.class);

    @Autowired
    private DunningReportApplication dunningReportApplication;

    @Autowired
    private SaasCreditCarrierService saasCreditCarrierService;

    @Autowired
    private CarrierReportApplication carrierReportApplication;

    @Autowired
    private SaasConsumeDayStatApplication saasConsumeDayStatApplication;

    @Autowired
    private SaasUserEsignAuthorizationService saasUserEsignAuthorizationService;

    @Autowired
    private SaasEsignAccountService saasEsignAccountService;

    @Autowired
    private SaasEsignUserAuthorizationService saasEsignUserAuthorizationService;

    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;

    @Autowired
    private MerchantApplication merchantApplication;

    @Autowired
    private RiskEcommerceService riskEcommerceService;

    @RequestMapping("/ok")
    @ResponseBody
    @VisitorAccessible
    @SignIgnore
    public String ok() {
        GXBEcommerceCrawlingParam param = new GXBEcommerceCrawlingParam();
        param.setIdcard("430224197111173910");
        param.setName("王五");
        param.setPhone("18819036332");
        param.setReturnUrl("http://www.baidu.com");
        param.setUserCode("12e31884bfbqdgey3");
        String ecommerceCrawlingUrl = riskEcommerceService.getEcommerceCrawlingUrl(param);
        LOGGER.info(ecommerceCrawlingUrl);
        return ecommerceCrawlingUrl;
        //return "ok";
    }

    @RequestMapping(value = "/stat", method = RequestMethod.GET)
    @ResponseBody
    @VisitorAccessible
    @SignIgnore
    public String stat(@RequestParam(value = "id") Long id) {
        SaasCreditCarrier saasCreditCarrier = (SaasCreditCarrier) saasCreditCarrierService.selectById(id);
        carrierReportApplication.generateCarrierReport(saasCreditCarrier.getMerchantCode(), saasCreditCarrier.getBorrowerCode());
        dunningReportApplication.generateDunningReport(saasCreditCarrier.getMerchantCode(), saasCreditCarrier.getBorrowerCode());
        return "ok";
    }

    @RequestMapping(value = "/syncTask", method = RequestMethod.POST)
    @ResponseBody
    @VisitorAccessible
    @SignIgnore
    public String syncTask() {
        saasConsumeDayStatApplication.creditAndMsgDayClear();
        return "ok";
    }

    @RequestMapping(value = "/esign/data/sync", method = RequestMethod.GET)
    @ResponseBody
    @VisitorAccessible
    @SignIgnore
    public String syncEsignData() {
        List<SaasUserEsignAuthorization> saasUserEsignAuthorizationList = saasUserEsignAuthorizationService.selectByParams(new HashMap<String, Object>(2) {{
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasUserEsignAuthorizationList)) {
            return "null";
        }
        saasUserEsignAuthorizationList.forEach(saasUserEsignAuthorization -> {
            String saasEsignCode = OrderNoUtil.makeOrderNum();
            SaasEsignAccountVo saasEsignAccountVo = new SaasEsignAccountVo();
            saasEsignAccountVo.setSaasEsignCode(saasEsignCode);
            SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(saasUserEsignAuthorization.getUserCode());
            if (saasBorrowerRealInfoVo != null) {
                saasEsignAccountVo.setName(saasBorrowerRealInfoVo.getName());
                saasEsignAccountVo.setCode(saasBorrowerRealInfoVo.getIdentityCode());
            } else {
                MerchantContractInfoVo merchantContractInfoVo = merchantApplication.getMerchantContractInfo(saasUserEsignAuthorization.getUserCode());
                if (merchantContractInfoVo == null) {
                    return;
                }
                saasEsignAccountVo.setName(merchantContractInfoVo.getName());
                saasEsignAccountVo.setCode(merchantContractInfoVo.getCode());
            }
            SaasEsignAccountVo saasEsignAccountVo1 = saasEsignAccountService.getByCode(saasEsignAccountVo.getCode());
            if (saasEsignAccountVo1 == null) {
                saasEsignAccountVo.setAccountId(saasUserEsignAuthorization.getAccountId());
                saasEsignAccountVo.setSealUrl(saasUserEsignAuthorization.getSealUrl());
                saasEsignAccountService.addSaasEsignAccountVo(saasEsignAccountVo);
            } else {
                saasEsignCode = saasEsignAccountVo1.getSaasEsignCode();
            }
            if (StringUtils.isNotEmpty(saasUserEsignAuthorization.getAuthorizationUrl())) {
                SaasEsignUserAuthorizationVo saasEsignUserAuthorizationVo1 = saasEsignUserAuthorizationService.getByUserCode(saasUserEsignAuthorization.getUserCode());
                if (saasEsignUserAuthorizationVo1 == null) {
                    SaasEsignUserAuthorizationVo saasEsignUserAuthorizationVo = new SaasEsignUserAuthorizationVo();
                    saasEsignUserAuthorizationVo.setUserCode(saasUserEsignAuthorization.getUserCode());
                    saasEsignUserAuthorizationVo.setSaasEsignCode(saasEsignCode);
                    saasEsignUserAuthorizationVo.setAuthorizationUrl(saasUserEsignAuthorization.getAuthorizationUrl());
                    saasEsignUserAuthorizationService.addSaasEsignUserAuthorizationVo(saasEsignUserAuthorizationVo);
                }
            }
            saasUserEsignAuthorizationService.deleteById(saasUserEsignAuthorization.getId());
        });
        return "ok";
    }


}
