package com.beitu.saas.app.application.contract;

import com.beitu.saas.app.application.auth.MerchantApplication;
import com.beitu.saas.auth.domain.MerchantContractInfoVo;
import com.beitu.saas.auth.enums.ContractConfigTypeEnum;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.enums.RestCodeEnum;
import com.beitu.saas.common.handle.oss.OSSService;
import com.beitu.saas.common.utils.OrderNoUtil;
import com.beitu.saas.intergration.esign.EsignIntegrationService;
import com.beitu.saas.intergration.esign.dto.AddOrganizeAccountSuccessDto;
import com.beitu.saas.intergration.esign.dto.AddPersonAccountSuccessDto;
import com.beitu.saas.intergration.esign.param.*;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.entity.SaasOrder;
import com.beitu.saas.user.client.SaasUserEsignAuthorizationService;
import com.beitu.saas.user.domain.SaasUserEsignAuthorizationVo;
import com.fqgj.common.utils.MD5;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.exception.common.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Autowired
    private SaasOrderService<SaasOrder> saasOrderService;

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private ContractCreateApplication contractCreateApplication;

    public Boolean needDoLicenseContractSign(String userCode) {
        if (saasUserEsignAuthorizationService.isSuccessAuthorization(userCode)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public String getUserSealUrl(String userCode) {
        SaasUserEsignAuthorizationVo saasUserEsignAuthorizationVo = saasUserEsignAuthorizationService.getByUserCode(userCode);
        if (saasUserEsignAuthorizationVo == null) {
            return null;
        }
        return saasUserEsignAuthorizationVo.getSealUrl();
    }

    /**
     * 借款人 签署 esign 授权协议
     *
     * @param borrowerCode 借款人
     */
    public void borrowerDoLicenseContractSign(String borrowerCode) {
        if (StringUtils.isNotEmpty(getUserSealUrl(borrowerCode))) {
            return;
        }
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(borrowerCode);
        if (saasBorrowerRealInfoVo == null) {
            throw new ApplicationException(RestCodeEnum.BORROWER_NOT_EXIST_ERROR);
        }
        PersonAccountParam personAccountParam = new PersonAccountParam();
        personAccountParam.setUserCode(borrowerCode);
        personAccountParam.setUserName(saasBorrowerRealInfoVo.getName());
        personAccountParam.setIdentityCode(saasBorrowerRealInfoVo.getIdentityCode());
        AddPersonAccountSuccessDto addPersonAccountSuccessDto = esignIntegrationService.addPersonAccount(personAccountParam);
        if (addPersonAccountSuccessDto == null) {
            throw new ApplicationException("新增个人账户失败");
        }
        String accountId = addPersonAccountSuccessDto.getPersonAccountId();
        String sealData = addPersonAccountSuccessDto.getSealData();
        String sealUrl = getSealUrl(borrowerCode);
        ossService.uploadFile(sealUrl, sealData);
        LicenseContractSignParam licenseContractSignParam = new LicenseContractSignParam();
        licenseContractSignParam.setUserCode(borrowerCode);
        licenseContractSignParam.setUserAccountId(accountId);
        licenseContractSignParam.setUserSealData(sealData);
        licenseContractSignParam.setSrcPdfContent(contractCreateApplication.createAuthorizationPdf(borrowerCode));
        String content = esignIntegrationService.doLicenseContractSign(licenseContractSignParam);
        SaasUserEsignAuthorizationVo saasUserEsignAuthorizationVo = new SaasUserEsignAuthorizationVo();
        saasUserEsignAuthorizationVo.setUserCode(borrowerCode);
        saasUserEsignAuthorizationVo.setAccountId(accountId);
        saasUserEsignAuthorizationVo.setSealUrl(sealUrl);
        saasUserEsignAuthorizationVo.setSuccess(Boolean.FALSE);
        if (StringUtils.isNotEmpty(content)) {
            String authorizationUrl = ossService.uploadFile(getAuthorizationUrl(borrowerCode), content);
            saasUserEsignAuthorizationVo.setAuthorizationUrl(authorizationUrl);
            saasUserEsignAuthorizationVo.setAuthorizationTime(new Date());
            saasUserEsignAuthorizationVo.setSuccess(Boolean.TRUE);
        }
        saasUserEsignAuthorizationService.create(saasUserEsignAuthorizationVo);
    }

    /**
     * 出借机构 签署 esign 授权协议
     *
     * @param merchantCode 出借机构
     */
    public void lenderDoLicenseContractSign(String merchantCode) {
        if (StringUtils.isNotEmpty(getUserSealUrl(merchantCode))) {
            return;
        }
        String accountId = null;
        String sealData;
        String sealUrl = null;
        MerchantContractInfoVo merchantContractInfoVo = merchantApplication.getMerchantContractInfo(merchantCode);
        if (merchantContractInfoVo == null) {
            throw new ApplicationException(RestCodeEnum.MERCHANT_NOT_EXIST_ERROR);
        }
        if (StringUtils.isNotEmpty(merchantContractInfoVo.getContractUrl())) {
            sealUrl = merchantContractInfoVo.getContractUrl();
            sealData = ossService.getFileContent(sealUrl);
        } else {
            if (ContractConfigTypeEnum.PERSONAL_CONTRACT.getKey().equals(merchantContractInfoVo.getContractType())) {
                PersonAccountParam personAccountParam = new PersonAccountParam();
                personAccountParam.setUserCode(merchantCode);
                personAccountParam.setUserName(merchantContractInfoVo.getName());
                personAccountParam.setIdentityCode(merchantContractInfoVo.getCode());
                AddPersonAccountSuccessDto addPersonAccountSuccessDto = esignIntegrationService.addPersonAccount(personAccountParam);
                if (addPersonAccountSuccessDto == null) {
                    throw new ApplicationException("新增机构个人账户失败");
                }
                accountId = addPersonAccountSuccessDto.getPersonAccountId();
                sealData = addPersonAccountSuccessDto.getSealData();
            } else {
                OrganizeAccountParam organizeAccountParam = new OrganizeAccountParam();
                organizeAccountParam.setMerchantCode(merchantCode);
                organizeAccountParam.setName(merchantContractInfoVo.getName());
                organizeAccountParam.setOrganizeCode(merchantContractInfoVo.getCode());
                AddOrganizeAccountSuccessDto successDto = esignIntegrationService.addOrganizeAccount(organizeAccountParam);
                if (successDto == null) {
                    throw new ApplicationException("新增机构账户失败");
                }
                accountId = successDto.getOrganizeAccountId();
                sealData = successDto.getSealData();
            }
        }
        if (StringUtils.isEmpty(sealUrl)) {
            sealUrl = getSealUrl(merchantCode);
            ossService.uploadFile(sealUrl, sealData);
        }
        LicenseContractSignParam licenseContractSignParam = new LicenseContractSignParam();
        licenseContractSignParam.setUserCode(merchantCode);
        licenseContractSignParam.setUserAccountId(accountId);
        licenseContractSignParam.setUserSealData(sealData);
        licenseContractSignParam.setSrcPdfContent(contractCreateApplication.createAuthorizationPdf(merchantCode));
        String content = esignIntegrationService.doLicenseContractSign(licenseContractSignParam);
        SaasUserEsignAuthorizationVo saasUserEsignAuthorizationVo = new SaasUserEsignAuthorizationVo();
        saasUserEsignAuthorizationVo.setUserCode(merchantCode);
        saasUserEsignAuthorizationVo.setAccountId(accountId);
        saasUserEsignAuthorizationVo.setSealUrl(sealUrl);
        saasUserEsignAuthorizationVo.setSuccess(Boolean.FALSE);
        if (StringUtils.isNotEmpty(content)) {
            String authorizationUrl = ossService.uploadFile(getAuthorizationUrl(merchantCode), content);
            saasUserEsignAuthorizationVo.setAuthorizationUrl(authorizationUrl);
            saasUserEsignAuthorizationVo.setAuthorizationTime(new Date());
            saasUserEsignAuthorizationVo.setSuccess(Boolean.TRUE);
        }
        saasUserEsignAuthorizationService.create(saasUserEsignAuthorizationVo);
    }

    /**
     * 借款人 签署 借款合同
     *
     * @param borrowerCode 借款人CODE
     * @param orderId      可空
     */
    public String borrowerDoLoanContractSign(String borrowerCode, Long orderId) {
        SaasUserEsignAuthorizationVo saasUserEsignAuthorizationVo = saasUserEsignAuthorizationService.getByUserCode(borrowerCode);
        if (saasUserEsignAuthorizationVo == null) {
            throw new ApplicationException("请先进行签署授权");
        }
        SaasOrder saasOrder = saasOrderService.selectById(orderId);
        if (saasOrder == null || StringUtils.isEmpty(saasOrder.getTermUrl())) {
            String loanContractUrl = getLoanContractUrl(borrowerCode);
            return ossService.uploadFile(loanContractUrl, new String(contractCreateApplication.createLoanPdf(orderId)));
        } else {
            return saasOrder.getTermUrl();
        }
    }

    public String lenderDoLoanContractSign(String merchantCode, Long orderId) {
        SaasUserEsignAuthorizationVo saasUserEsignAuthorizationVo = saasUserEsignAuthorizationService.getByUserCode(merchantCode);
        if (saasUserEsignAuthorizationVo == null) {
            throw new ApplicationException("请先进行签署授权");
        }
        SaasOrder saasOrder = saasOrderService.selectById(orderId);
        if (saasOrder == null || StringUtils.isEmpty(saasOrder.getTermUrl())) {
            String loanContractUrl = getLoanContractUrl(merchantCode);
            return ossService.uploadFile(loanContractUrl, new String(contractCreateApplication.createLoanPdf(orderId)));
        } else {
            return saasOrder.getTermUrl();
        }
    }

    public String borrowerDoExtendContractSign(String borrowerCode, Long orderId) {
        SaasUserEsignAuthorizationVo saasUserEsignAuthorizationVo = saasUserEsignAuthorizationService.getByUserCode(borrowerCode);
        if (saasUserEsignAuthorizationVo == null) {
            throw new ApplicationException("请先进行签署授权");
        }
        SaasOrder saasOrder = saasOrderService.selectById(orderId);
        if (saasOrder == null || StringUtils.isEmpty(saasOrder.getTermUrl())) {
            String loanContractUrl = getLoanContractUrl(borrowerCode);
            return ossService.uploadFile(loanContractUrl, new String(contractCreateApplication.createExtendPdf(orderId)));
        } else {
            return saasOrder.getTermUrl();
        }
    }

    public String lenderDoExtendContractSign(String merchantCode, Long orderId) {
        SaasUserEsignAuthorizationVo saasUserEsignAuthorizationVo = saasUserEsignAuthorizationService.getByUserCode(merchantCode);
        if (saasUserEsignAuthorizationVo == null) {
            throw new ApplicationException("请先进行签署授权");
        }
        SaasOrder saasOrder = saasOrderService.selectById(orderId);
        if (saasOrder == null || StringUtils.isEmpty(saasOrder.getTermUrl())) {
            String loanContractUrl = getLoanContractUrl(merchantCode);
            return ossService.uploadFile(loanContractUrl, new String(contractCreateApplication.createExtendPdf(orderId)));
        } else {
            return saasOrder.getTermUrl();
        }
    }


    private String getSealUrl(String userCode) {
        StringBuilder filePath = new StringBuilder("contract/seal/");
        if (configUtil.isServerTest()) {
            filePath.append("test/");
        }
        filePath.append(userCode).append("/").append(MD5.md5(OrderNoUtil.makeOrderNum()));
        return filePath.toString();
    }

    private String getAuthorizationUrl(String userCode) {
        StringBuilder filePath = new StringBuilder("contract/authorization/");
        if (configUtil.isServerTest()) {
            filePath.append("test/");
        }
        filePath.append(userCode).append("_").append(MD5.md5(OrderNoUtil.makeOrderNum())).append(".pdf");
        return filePath.toString();
    }

    private String getLoanContractUrl(String userCode) {
        StringBuilder filePath = new StringBuilder("contract/loan/");
        if (configUtil.isServerTest()) {
            filePath.append("test/");
        }
        filePath.append(userCode).append("_").append(MD5.md5(OrderNoUtil.makeOrderNum())).append(".pdf");
        return filePath.toString();
    }

    private String getExtendContractUrl(String userCode) {
        StringBuilder filePath = new StringBuilder("contract/extend/");
        if (configUtil.isServerTest()) {
            filePath.append("test/");
        }
        filePath.append(userCode).append("_").append(MD5.md5(OrderNoUtil.makeOrderNum())).append(".pdf");
        return filePath.toString();

    }

}