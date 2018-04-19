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
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.enums.RestCodeEnum;
import com.beitu.saas.common.handle.oss.OSSService;
import com.beitu.saas.common.utils.IpChooseUtil;
import com.beitu.saas.common.utils.OrderNoUtil;
import com.beitu.saas.credit.client.SaasCreditCarrierService;
import com.beitu.saas.credit.entity.SaasCreditCarrier;
import com.beitu.saas.intergration.esign.EsignIntegrationService;
import com.beitu.saas.intergration.esign.dto.AddPersonAccountSuccessDto;
import com.beitu.saas.intergration.esign.param.PersonAccountParam;
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
import java.util.concurrent.CountDownLatch;

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
    private ConfigUtil configUtil;

    @Autowired
    private EsignIntegrationService esignIntegrationService;

    @Autowired
    private OSSService ossService;

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

    @RequestMapping(value = "/esign/data/sync", method = RequestMethod.GET)
    @ResponseBody
    @VisitorAccessible
    @SignIgnore
    @Transactional(rollbackFor = Exception.class)
    public String syncEsignData() {
        List<SaasUserEsignAuthorization> saasUserEsignAuthorizationList = saasUserEsignAuthorizationService.selectByParams(null);
        if (CollectionUtils.isEmpty(saasUserEsignAuthorizationList)) {
            return "null";
        }
        StringBuilder results = new StringBuilder();
        saasUserEsignAuthorizationList.forEach(saasUserEsignAuthorization -> {
            String saasEsignCode = OrderNoUtil.makeOrderNum();
            SaasEsignAccountVo saasEsignAccountVo = new SaasEsignAccountVo();
            saasEsignAccountVo.setSaasEsignCode(saasEsignCode);
            SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(saasUserEsignAuthorization.getUserCode());
            if (saasBorrowerRealInfoVo != null) {
                return;
            } else {
                MerchantContractInfoVo merchantContractInfoVo = merchantApplication.getMerchantContractInfo(saasUserEsignAuthorization.getUserCode());
                results.append(saasUserEsignAuthorization.getUserCode()).append(",");
                if (merchantContractInfoVo == null) {
                    return;
                }
                saasEsignAccountVo.setName(merchantContractInfoVo.getName());
                saasEsignAccountVo.setCode(merchantContractInfoVo.getCode());
            }
            SaasEsignAccountVo saasEsignAccountVo1 = saasEsignAccountService.getByCode(saasEsignAccountVo.getCode());
            if (saasEsignAccountVo1 == null) {
                PersonAccountParam personAccountParam = new PersonAccountParam();
                personAccountParam.setUserCode(saasUserEsignAuthorization.getUserCode());
                personAccountParam.setUserName(saasEsignAccountVo.getName());
                personAccountParam.setIdentityCode(saasEsignAccountVo.getCode());
                AddPersonAccountSuccessDto addPersonAccountSuccessDto = esignIntegrationService.addPersonAccount(personAccountParam);
                if (addPersonAccountSuccessDto == null) {
                    throw new ApplicationException("新增机构个人账户失败");
                }
                saasEsignAccountVo.setAccountId(addPersonAccountSuccessDto.getPersonAccountId());
                String url = getAuthorizationUrl(saasUserEsignAuthorization.getUserCode());
                ossService.uploadFile(url, addPersonAccountSuccessDto.getSealData());
                saasEsignAccountVo.setSealUrl(url);
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
        return results.toString();
    }

    private String getAuthorizationUrl(String userCode) {
        StringBuilder filePath = new StringBuilder("contract/authorization/");
        if (configUtil.isServerTest()) {
            filePath.append("test/");
        }
        filePath.append(userCode).append("_").append(MD5.md5(OrderNoUtil.makeOrderNum())).append(".pdf");
        return filePath.toString();
    }

}
