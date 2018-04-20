package com.beitu.saas.rest.controller;

import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.app.application.auth.MerchantApplication;
import com.beitu.saas.app.application.contract.ContractApplication;
import com.beitu.saas.app.application.credit.CarrierReportApplication;
import com.beitu.saas.app.application.credit.DunningReportApplication;
import com.beitu.saas.app.application.finance.SaasConsumeDayStatApplication;
import com.beitu.saas.auth.domain.MerchantContractInfoVo;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.handle.oss.OSSService;
import com.beitu.saas.common.utils.OrderNoUtil;
import com.beitu.saas.credit.client.SaasCreditCarrierService;
import com.beitu.saas.credit.entity.SaasCreditCarrier;
import com.beitu.saas.intergration.esign.EsignIntegrationService;
import com.beitu.saas.intergration.esign.dto.AddPersonAccountSuccessDto;
import com.beitu.saas.intergration.esign.param.PersonAccountParam;
import com.beitu.saas.order.client.SaasOrderApplicationService;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.domain.SaasOrderApplicationVo;
import com.beitu.saas.order.entity.SaasOrder;
import com.beitu.saas.order.entity.SaasOrderApplication;
import com.beitu.saas.risk.helpers.CollectionUtils;
import com.beitu.saas.risk.helpers.StringUtils;
import com.beitu.saas.user.client.SaasEsignAccountService;
import com.beitu.saas.user.client.SaasEsignUserAuthorizationService;
import com.beitu.saas.user.client.SaasUserEsignAuthorizationService;
import com.beitu.saas.user.domain.SaasEsignAccountVo;
import com.beitu.saas.user.domain.SaasEsignUserAuthorizationVo;
import com.beitu.saas.user.entity.SaasUserEsignAuthorization;
import com.fqgj.common.utils.MD5;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    private SaasOrderService saasOrderService;

    @Autowired
    private SaasEsignUserAuthorizationService saasEsignUserAuthorizationService;

    @Autowired
    private SaasOrderApplicationService saasOrderApplicationService;

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private ContractApplication contractApplication;

    @RequestMapping("/ok")
    @ResponseBody
    @VisitorAccessible
    @SignIgnore
    public String ok() {
        return "ok";
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

    @RequestMapping(value = "/order/data/sync", method = RequestMethod.GET)
    @ResponseBody
    @VisitorAccessible
    @SignIgnore
    @Transactional(rollbackFor = Exception.class)
    public String syncOrderData() {
        List<SaasOrder> saasOrderList = saasOrderService.selectByParams(new HashMap<String, Object>(4) {{
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasOrderList)) {
            return "null";
        }
        saasOrderList.forEach(saasOrder -> {
            String borrowerCode = saasOrder.getBorrowerCode();
            SaasEsignUserAuthorizationVo saasEsignUserAuthorizationVo = saasEsignUserAuthorizationService.getByUserCode(borrowerCode);
            if (saasEsignUserAuthorizationVo == null || StringUtils.isEmpty(saasEsignUserAuthorizationVo.getAuthorizationUrl())) {
                return;
            }
            SaasOrder updateSaasOrder = new SaasOrder();
            updateSaasOrder.setId(saasOrder.getId());
            updateSaasOrder.setBorrowerAuthorizedSignLoan(Boolean.TRUE);
            saasOrderService.updateById(updateSaasOrder);
            SaasOrderApplicationVo saasOrderApplicationVo = saasOrderApplicationService.getByBorrowerCodeAndOrderNumb(saasOrder.getBorrowerCode(), saasOrder.getOrderNumb());
            if (saasOrderApplicationVo != null) {
                SaasOrderApplication updateSaasOrderApplication = new SaasOrderApplication();
                updateSaasOrderApplication.setId(saasOrderApplicationVo.getSaasOrderApplicationId());
                updateSaasOrderApplication.setBorrowerAuthorizedSignLoan(Boolean.TRUE);
                saasOrderApplicationService.updateById(updateSaasOrderApplication);
            }
        });
        return "ok";
    }

    @RequestMapping(value = "/contract/create", method = RequestMethod.GET)
    @ResponseBody
    @VisitorAccessible
    @SignIgnore
    public String createContract(@RequestParam(value = "orderId") Long orderId, @RequestParam(value = "type") Integer type) {
        if (1 == type) {
            contractApplication.doExtendContractSign(orderId);
        } else {
            contractApplication.doLoanContractSign(orderId);
        }
        return "ok";
    }

}
