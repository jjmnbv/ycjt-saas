package com.beitu.saas.app.application.contract;

import com.beitu.saas.app.application.auth.MerchantApplication;
import com.beitu.saas.auth.domain.MerchantContractInfoVo;
import com.beitu.saas.auth.domain.SaasMerchantVo;
import com.beitu.saas.auth.enums.ContractConfigTypeEnum;
import com.beitu.saas.auth.service.SaasMerchantService;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.borrower.entity.SaasBorrowerRealInfo;
import com.beitu.saas.common.handle.oss.OSSService;
import com.beitu.saas.intergration.esign.EsignIntegrationService;
import com.beitu.saas.intergration.esign.dto.AddPersonAccountSuccessDto;
import com.beitu.saas.intergration.esign.param.LicenseContractSignParam;
import com.beitu.saas.intergration.esign.param.PersonAccountParam;
import com.beitu.saas.user.client.SaasUserEsignAuthorizationService;
import com.beitu.saas.user.domain.SaasUserEsignAuthorizationVo;
import com.fqgj.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Date;

/**
 * @Author linanjun
 * @Create 2017/10/11 下午8:19
 * @Description
 */
@Component
public class ContractApplication {

    @Autowired
    private EsignIntegrationService esignIntegrationService;

    @Autowired
    private SaasUserEsignAuthorizationService saasUserEsignAuthorizationService;

    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;

    @Autowired
    private MerchantApplication merchantApplication;

    @Autowired
    private OSSService ossService;

    public Boolean needDoLicenseContractSign(String userCode) {
        if (saasUserEsignAuthorizationService.isSuccessAuthorization(userCode)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public String getUserSealUrl(String userCode) {
        return saasUserEsignAuthorizationService.getSealUrlByUserCode(userCode);
    }

    /**
     * 签署 esign 授权协议
     *
     * @param userCode 借款人传 borrowerCode；机构传 merchantCode
     */
    public void doLicenseContractSign(String userCode) {
        if (StringUtils.isNotEmpty(getUserSealUrl(userCode))) {
            return;
        }
        String accountId = null;
        String sealData = null;
        String sealUrl = null;
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(userCode);
        if (saasBorrowerRealInfoVo != null) {
            PersonAccountParam personAccountParam = new PersonAccountParam();
            personAccountParam.setUserCode(userCode);
            personAccountParam.setUserName(saasBorrowerRealInfoVo.getName());
            personAccountParam.setIdentityCode(saasBorrowerRealInfoVo.getIdentityCode());
            AddPersonAccountSuccessDto addPersonAccountSuccessDto = esignIntegrationService.addPersonAccount(personAccountParam);
            if (addPersonAccountSuccessDto == null) {

            }
            accountId = addPersonAccountSuccessDto.getPersonAccountId();
            sealData = addPersonAccountSuccessDto.getSealData();
        } else {
            MerchantContractInfoVo merchantContractInfoVo = merchantApplication.getMerchantContractInfo(userCode);
            if (merchantContractInfoVo == null) {

            }
            if (StringUtils.isNotEmpty(merchantContractInfoVo.getContractUrl())) {
                sealUrl = merchantContractInfoVo.getContractUrl();
                sealData = ossService.getFileContent(sealUrl);
            } else {
                if (ContractConfigTypeEnum.PERSONAL_CONTRACT.getKey().equals(merchantContractInfoVo.getContractType())) {

                } else {

                }
            }
        }
        if (StringUtils.isEmpty(sealUrl)) {
            sealUrl = getSealUrl(userCode);
            ossService.uploadFile(sealUrl, sealData);
        }
        // TODO 签署 授权合同
        LicenseContractSignParam licenseContractSignParam = new LicenseContractSignParam();
        licenseContractSignParam.setUserCode(userCode);
        licenseContractSignParam.setUserAccountId(accountId);
        licenseContractSignParam.setUserSealData(sealData);
        licenseContractSignParam.setSrcPdf("");
        InputStream inputStream = esignIntegrationService.doLicenseContractSign(licenseContractSignParam);
        SaasUserEsignAuthorizationVo saasUserEsignAuthorizationVo = new SaasUserEsignAuthorizationVo();
        saasUserEsignAuthorizationVo.setUserCode(userCode);
        saasUserEsignAuthorizationVo.setAccountId(accountId);
        saasUserEsignAuthorizationVo.setSealUrl(sealUrl);
        saasUserEsignAuthorizationVo.setSuccess(Boolean.FALSE);
        try {
            String authorizationUrl = ossService.uploadFile(inputStream, inputStream.available(), getAuthorizationUrl(userCode));
            saasUserEsignAuthorizationVo.setAuthorizationUrl(authorizationUrl);
            saasUserEsignAuthorizationVo.setAuthorizationTime(new Date());
            saasUserEsignAuthorizationVo.setSuccess(Boolean.TRUE);
        } catch (Exception e) {

        }
        saasUserEsignAuthorizationService.create(saasUserEsignAuthorizationVo);
    }

    public void borrowerDoLoanContractSign(String borrowerCode, Long orderId) {

    }

    public void lenderDoLoanContractSign(String merchantCode, Long orderId) {

    }

    public void borrowerDoExtendContractSign(String borrowerCode, Long orderId) {

    }

    public void lenderDoExtendContractSign(String borrowerCode, Long orderId) {

    }

    private String getSealUrl(String userCode) {
        return "";
    }

    private String getAuthorizationUrl(String userCode) {
        return "";
    }

}
