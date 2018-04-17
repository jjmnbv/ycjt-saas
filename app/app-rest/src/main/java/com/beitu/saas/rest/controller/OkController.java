package com.beitu.saas.rest.controller;

import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.app.application.credit.CarrierReportApplication;
import com.beitu.saas.app.application.credit.DunningReportApplication;
import com.beitu.saas.app.application.finance.SaasConsumeDayStatApplication;
import com.beitu.saas.auth.service.SaasMerchantService;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.beitu.saas.channel.enums.ChannelTypeEnum;
import com.beitu.saas.common.utils.IpChooseUtil;
import com.beitu.saas.credit.client.SaasCreditCarrierService;
import com.beitu.saas.credit.entity.SaasCreditCarrier;
import com.beitu.saas.intergration.risk.RiskEcommerceService;
import com.beitu.saas.intergration.risk.param.GXBEcommerceCrawlingParam;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private SaasChannelService saasChannelService;
    @Autowired
    private SaasMerchantService saasMerchantService;

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

}
