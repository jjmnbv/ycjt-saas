package com.beitu.saas.app.application.contract;

import com.beitu.saas.app.application.auth.MerchantApplication;
import com.beitu.saas.app.application.contract.consts.ContractConsts;
import com.beitu.saas.auth.domain.MerchantContractInfoVo;
import com.beitu.saas.auth.enums.ContractConfigTypeEnum;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.common.config.ConfigUtil;
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

    @Autowired
    private SaasOrderService<SaasOrder> saasOrderService;

    @Autowired
    private ConfigUtil configUtil;

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
     * 签署 esign 授权协议
     *
     * @param userCode 借款人传 borrowerCode；机构传 merchantCode
     */
    public void doLicenseContractSign(String userCode) {
//        if (StringUtils.isNotEmpty(getUserSealUrl(userCode))) {
//            return;
//        }
//        String accountId = null;
//        String sealData;
//        String sealUrl = null;
//        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(userCode);
//        if (saasBorrowerRealInfoVo != null) {
//            PersonAccountParam personAccountParam = new PersonAccountParam();
//            personAccountParam.setUserCode(userCode);
//            personAccountParam.setUserName(saasBorrowerRealInfoVo.getName());
//            personAccountParam.setIdentityCode(saasBorrowerRealInfoVo.getIdentityCode());
//            AddPersonAccountSuccessDto addPersonAccountSuccessDto = esignIntegrationService.addPersonAccount(personAccountParam);
//            if (addPersonAccountSuccessDto == null) {
//                throw new ApplicationException("新增个人账户失败");
//            }
//            accountId = addPersonAccountSuccessDto.getPersonAccountId();
//            sealData = addPersonAccountSuccessDto.getSealData();
//        } else {
//            MerchantContractInfoVo merchantContractInfoVo = merchantApplication.getMerchantContractInfo(userCode);
//            if (merchantContractInfoVo == null) {
//                throw new ApplicationException("机构信息不完善");
//            }
//            if (StringUtils.isNotEmpty(merchantContractInfoVo.getContractUrl())) {
//                sealUrl = merchantContractInfoVo.getContractUrl();
//                sealData = ossService.getFileContent(sealUrl);
//            } else {
//                if (ContractConfigTypeEnum.PERSONAL_CONTRACT.getKey().equals(merchantContractInfoVo.getContractType())) {
//                    PersonAccountParam personAccountParam = new PersonAccountParam();
//                    personAccountParam.setUserCode(userCode);
//                    personAccountParam.setUserName(merchantContractInfoVo.getName());
//                    personAccountParam.setIdentityCode(merchantContractInfoVo.getCode());
//                    AddPersonAccountSuccessDto addPersonAccountSuccessDto = esignIntegrationService.addPersonAccount(personAccountParam);
//                    if (addPersonAccountSuccessDto == null) {
//                        throw new ApplicationException("新增机构个人账户失败");
//                    }
//                    accountId = addPersonAccountSuccessDto.getPersonAccountId();
//                    sealData = addPersonAccountSuccessDto.getSealData();
//                } else {
//                    OrganizeAccountParam organizeAccountParam = new OrganizeAccountParam();
//                    organizeAccountParam.setMerchantCode(userCode);
//                    organizeAccountParam.setName(merchantContractInfoVo.getName());
//                    organizeAccountParam.setOrganizeCode(merchantContractInfoVo.getCode());
//                    AddOrganizeAccountSuccessDto successDto = esignIntegrationService.addOrganizeAccount(organizeAccountParam);
//                    if (successDto == null) {
//                        throw new ApplicationException("新增机构账户失败");
//                    }
//                    accountId = successDto.getOrganizeAccountId();
//                    sealData = successDto.getSealData();
//                }
//            }
//        }
//        if (StringUtils.isEmpty(sealUrl)) {
//            sealUrl = getSealUrl(userCode);
//            ossService.uploadFile(sealUrl, sealData);
//        }
//        LicenseContractSignParam licenseContractSignParam = new LicenseContractSignParam();
//        licenseContractSignParam.setUserCode(userCode);
//        licenseContractSignParam.setUserAccountId(accountId);
//        licenseContractSignParam.setUserSealData(sealData);
//        licenseContractSignParam.setSrcPdf(ContractConsts.AUTHORIZATION_PDF_PATH);
//        String content = esignIntegrationService.doLicenseContractSign(licenseContractSignParam);
//        SaasUserEsignAuthorizationVo saasUserEsignAuthorizationVo = new SaasUserEsignAuthorizationVo();
//        saasUserEsignAuthorizationVo.setUserCode(userCode);
//        saasUserEsignAuthorizationVo.setAccountId(accountId);
//        saasUserEsignAuthorizationVo.setSealUrl(sealUrl);
//        saasUserEsignAuthorizationVo.setSuccess(Boolean.FALSE);
//        if (StringUtils.isNotEmpty(content)) {
//            String authorizationUrl = ossService.uploadFile(getAuthorizationUrl(userCode), content);
//            saasUserEsignAuthorizationVo.setAuthorizationUrl(authorizationUrl);
//            saasUserEsignAuthorizationVo.setAuthorizationTime(new Date());
//            saasUserEsignAuthorizationVo.setSuccess(Boolean.TRUE);
//        }
//        saasUserEsignAuthorizationService.create(saasUserEsignAuthorizationVo);
    }

    /**
     * 借款人 签署 借款合同
     *
     * @param borrowerCode 借款人CODE
     * @param orderId      可空
     */
    public String borrowerDoLoanContractSign(String borrowerCode, Long orderId) {
        return null;
//        SaasUserEsignAuthorizationVo saasUserEsignAuthorizationVo = saasUserEsignAuthorizationService.getByUserCode(borrowerCode);
//        if (saasUserEsignAuthorizationVo == null) {
//            throw new ApplicationException("请先进行签署授权");
//        }
//        SaasOrder saasOrder = saasOrderService.selectById(orderId);
//        BorrowerDoContractSignParam borrowerDoContractSignParam = new BorrowerDoContractSignParam();
//        if (saasOrder == null || StringUtils.isEmpty(saasOrder.getTermUrl())) {
//            borrowerDoContractSignParam.setSrcPdf(ContractConsts.LOAN_PDF_PATH);
//        } else {
//            borrowerDoContractSignParam.setSrcContent(ossService.getFileContent(saasOrder.getTermUrl()).getBytes());
//        }
//        borrowerDoContractSignParam.setBorrowerAccountId(saasUserEsignAuthorizationVo.getAccountId());
//        borrowerDoContractSignParam.setBorrowerSealData(ossService.getFileContent(saasUserEsignAuthorizationVo.getSealUrl()));
//        borrowerDoContractSignParam.setBorrowerCode(borrowerCode);
//        String loanContractUrl = getLoanContractUrl(borrowerCode);
//        return ossService.uploadFile(loanContractUrl, esignIntegrationService.borrowerDoContractSign(borrowerDoContractSignParam));
    }

    public String lenderDoLoanContractSign(String merchantCode, Long orderId) {
        return null;
//        SaasUserEsignAuthorizationVo saasUserEsignAuthorizationVo = saasUserEsignAuthorizationService.getByUserCode(merchantCode);
//        if (saasUserEsignAuthorizationVo == null) {
//            throw new ApplicationException("请先进行签署授权");
//        }
//        SaasOrder saasOrder = saasOrderService.selectById(orderId);
//        LenderDoContractSignParam lenderDoContractSignParam = new LenderDoContractSignParam();
//        if (saasOrder == null || StringUtils.isEmpty(saasOrder.getTermUrl())) {
//            lenderDoContractSignParam.setSrcPdf(ContractConsts.LOAN_PDF_PATH);
//        } else {
//            lenderDoContractSignParam.setSrcContent(ossService.getFileContent(saasOrder.getTermUrl()).getBytes());
//        }
//        lenderDoContractSignParam.setMerchantAccountId(saasUserEsignAuthorizationVo.getAccountId());
//        lenderDoContractSignParam.setMerchantSealData(ossService.getFileContent(saasUserEsignAuthorizationVo.getSealUrl()));
//        lenderDoContractSignParam.setMerchantCode(merchantCode);
//        String loanContractUrl = getLoanContractUrl(merchantCode);
//        return ossService.uploadFile(loanContractUrl, esignIntegrationService.lenderDoContractSign(lenderDoContractSignParam));
    }

    public String borrowerDoExtendContractSign(String borrowerCode, Long orderId) {
        return null;
//        SaasUserEsignAuthorizationVo saasUserEsignAuthorizationVo = saasUserEsignAuthorizationService.getByUserCode(borrowerCode);
//        if (saasUserEsignAuthorizationVo == null) {
//            throw new ApplicationException("请先进行签署授权");
//        }
//        SaasOrder saasOrder = saasOrderService.selectById(orderId);
//        BorrowerDoContractSignParam borrowerDoContractSignParam = new BorrowerDoContractSignParam();
//        if (saasOrder == null || StringUtils.isEmpty(saasOrder.getTermUrl())) {
//            borrowerDoContractSignParam.setSrcPdf(ContractConsts.EXTEND_PDF_PATH);
//        } else {
//            borrowerDoContractSignParam.setSrcContent(ossService.getFileContent(saasOrder.getTermUrl()).getBytes());
//        }
//        borrowerDoContractSignParam.setBorrowerAccountId(saasUserEsignAuthorizationVo.getAccountId());
//        borrowerDoContractSignParam.setBorrowerSealData(ossService.getFileContent(saasUserEsignAuthorizationVo.getSealUrl()));
//        borrowerDoContractSignParam.setBorrowerCode(borrowerCode);
//        String loanContractUrl = getLoanContractUrl(borrowerCode);
//        return ossService.uploadFile(loanContractUrl, esignIntegrationService.borrowerDoContractSign(borrowerDoContractSignParam));
    }

    public String lenderDoExtendContractSign(String merchantCode, Long orderId) {
        return null;
//        SaasUserEsignAuthorizationVo saasUserEsignAuthorizationVo = saasUserEsignAuthorizationService.getByUserCode(merchantCode);
//        if (saasUserEsignAuthorizationVo == null) {
//            throw new ApplicationException("请先进行签署授权");
//        }
//        SaasOrder saasOrder = saasOrderService.selectById(orderId);
//        LenderDoContractSignParam lenderDoContractSignParam = new LenderDoContractSignParam();
//        if (saasOrder == null || StringUtils.isEmpty(saasOrder.getTermUrl())) {
//            lenderDoContractSignParam.setSrcPdf(ContractConsts.EXTEND_PDF_PATH);
//        } else {
//            lenderDoContractSignParam.setSrcContent(ossService.getFileContent(saasOrder.getTermUrl()).getBytes());
//        }
//        lenderDoContractSignParam.setMerchantAccountId(saasUserEsignAuthorizationVo.getAccountId());
//        lenderDoContractSignParam.setMerchantSealData(ossService.getFileContent(saasUserEsignAuthorizationVo.getSealUrl()));
//        lenderDoContractSignParam.setMerchantCode(merchantCode);
//        String loanContractUrl = getLoanContractUrl(merchantCode);
//        return ossService.uploadFile(loanContractUrl, esignIntegrationService.lenderDoContractSign(lenderDoContractSignParam));
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