package com.beitu.saas.app.application.contract;

import com.beitu.saas.app.application.auth.MerchantApplication;
import com.beitu.saas.app.application.contract.consts.ContractConsts;
import com.beitu.saas.auth.domain.MerchantContractInfoVo;
import com.beitu.saas.auth.enums.ContractConfigTypeEnum;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.enums.RestCodeEnum;
import com.beitu.saas.common.handle.oss.OSSService;
import com.beitu.saas.common.utils.OrderNoUtil;
import com.beitu.saas.finance.client.SaasCreditHistoryService;
import com.beitu.saas.finance.client.enums.CreditConsumeEnum;
import com.beitu.saas.intergration.esign.EsignIntegrationService;
import com.beitu.saas.intergration.esign.dto.AddOrganizeAccountSuccessDto;
import com.beitu.saas.intergration.esign.dto.AddPersonAccountSuccessDto;
import com.beitu.saas.intergration.esign.param.LicenseContractSignParam;
import com.beitu.saas.intergration.esign.param.OrganizeAccountParam;
import com.beitu.saas.intergration.esign.param.PersonAccountParam;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.entity.SaasOrder;
import com.beitu.saas.user.client.SaasUserEsignAuthorizationService;
import com.beitu.saas.user.domain.SaasUserEsignAuthorizationVo;
import com.fqgj.common.utils.MD5;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.exception.common.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

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

    @Autowired
    private SaasCreditHistoryService saasCreditHistoryService;

    public Boolean needDoLicenseContractSign(String userCode) {
        if (saasUserEsignAuthorizationService.isSuccessAuthorization(userCode)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public String getUserSealUrl(String userCode) {
        SaasUserEsignAuthorizationVo saasUserEsignAuthorizationVo = saasUserEsignAuthorizationService.getSuccessEsignAuthorizationByUserCode(userCode);
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
        doEsignAuthorization(saasBorrowerRealInfoVo.getMerchantCode(), borrowerCode, saasBorrowerRealInfoVo.getName(), accountId, null, sealData);
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
        doEsignAuthorization(merchantCode, merchantCode, ContractConsts.DEFAULT_DO_LICENSE_CONTRACT_SIGN_OPERATOR_NAME, accountId, sealUrl, sealData);
    }

    private void doEsignAuthorization(String merchantCode, String userCode, String name, String accountId, String sealUrl, String sealData) {
        if (StringUtils.isEmpty(sealUrl)) {
            sealUrl = getSealUrl(userCode);
            ossService.uploadFile(sealUrl, sealData);
        }
        String url = getAuthorizationUrl(userCode);
        String createFilePath = configUtil.getPdfPath() + MD5.md5(OrderNoUtil.makeOrderNum()) + ".pdf";
        contractCreateApplication.createAuthorizationPdf(userCode, createFilePath);
        SaasUserEsignAuthorizationVo saasUserEsignAuthorizationVo = saasUserEsignAuthorizationService.getByUserCode(userCode);
        if (saasUserEsignAuthorizationVo == null) {
            saasUserEsignAuthorizationVo = new SaasUserEsignAuthorizationVo();
            saasUserEsignAuthorizationVo.setUserCode(userCode);
            saasUserEsignAuthorizationVo.setAccountId(accountId);
            saasUserEsignAuthorizationVo.setSealUrl(sealUrl);
            saasUserEsignAuthorizationVo.setSuccess(Boolean.FALSE);
            saasUserEsignAuthorizationVo.setSaasUserEsignAuthorizationId(saasUserEsignAuthorizationService.create(saasUserEsignAuthorizationVo).getId());
        }
        File file = new File(createFilePath);
        byte[] pdfContent = null;
        if (file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                pdfContent = new byte[(int) file.length()];
                fis.read(pdfContent);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            LicenseContractSignParam licenseContractSignParam = new LicenseContractSignParam();
//            licenseContractSignParam.setUserCode(merchantCode);
//            licenseContractSignParam.setUserAccountId(accountId);
//            licenseContractSignParam.setUserSealData(sealData);
//            licenseContractSignParam.setSrcPdfContent(pdfContent);
//            byte[] content = esignIntegrationService.doLicenseContractSign(licenseContractSignParam);
            byte[] content = pdfContent;
            if (content == null) {
                throw new ApplicationException("esign盖章失败");
            }
            InputStream inputStream = null;
            try {
                inputStream = new ByteArrayInputStream(content);
                url = ossService.uploadFile(inputStream, inputStream.available(), url);
                file.delete();
            } catch (Exception e) {
                throw new ApplicationException("上传文件失败");
            } finally {
                try {
                    inputStream.close();
                } catch (Exception e) {

                }
            }
            saasUserEsignAuthorizationService.updateSaasUserEsignAuthorization(saasUserEsignAuthorizationVo.getSaasUserEsignAuthorizationId(), url);
            saasCreditHistoryService.addExpenditureCreditHistory(merchantCode, name, CreditConsumeEnum.RISK_ESIGN);
        }
    }


    /**
     * 借款人 签署 借款合同
     *
     * @param borrowerCode 借款人CODE
     * @param orderId      可空
     */
    public String borrowerDoLoanContractSign(String borrowerCode, Long orderId) {
        SaasUserEsignAuthorizationVo saasUserEsignAuthorizationVo = saasUserEsignAuthorizationService.getSuccessEsignAuthorizationByUserCode(borrowerCode);
        if (saasUserEsignAuthorizationVo == null) {
            throw new ApplicationException("请先进行签署授权");
        }
        SaasOrder saasOrder = saasOrderService.selectById(orderId);
        if (saasOrder == null || StringUtils.isEmpty(saasOrder.getTermUrl())) {
            String url = getLoanContractUrl(orderId);
            String loanContractUrl = configUtil.getPdfPath() + MD5.md5(OrderNoUtil.makeOrderNum()) + ".pdf";
            contractCreateApplication.createLoanPdf(orderId, loanContractUrl);
            File file = new File(loanContractUrl);
            try {
                InputStream inputStream = new FileInputStream(file);
                url = ossService.uploadFile(inputStream, inputStream.available(), url);
                file.delete();
                return url;
            } catch (Exception e) {
                throw new ApplicationException("上传文件失败");
            }
        } else {
            return saasOrder.getTermUrl();
        }
    }

    public String lenderDoLoanContractSign(String merchantCode, Long orderId) {
        SaasUserEsignAuthorizationVo saasUserEsignAuthorizationVo = saasUserEsignAuthorizationService.getSuccessEsignAuthorizationByUserCode(merchantCode);
        if (saasUserEsignAuthorizationVo == null) {
            throw new ApplicationException("请先进行签署授权");
        }
        SaasOrder saasOrder = saasOrderService.selectById(orderId);
        if (saasOrder == null || StringUtils.isEmpty(saasOrder.getTermUrl())) {
            String url = getLoanContractUrl(orderId);
            String loanContractUrl = configUtil.getPdfPath() + MD5.md5(OrderNoUtil.makeOrderNum()) + ".pdf";
            contractCreateApplication.createLoanPdf(orderId, loanContractUrl);
            File file = new File(loanContractUrl);
            try {
                InputStream inputStream = new FileInputStream(file);
                url = ossService.uploadFile(inputStream, inputStream.available(), url);
                file.delete();
                return url;
            } catch (Exception e) {
                throw new ApplicationException("上传文件失败");
            }
        } else {
            return saasOrder.getTermUrl();
        }
    }

    public String borrowerDoExtendContractSign(String borrowerCode, Long orderId) {
        SaasUserEsignAuthorizationVo saasUserEsignAuthorizationVo = saasUserEsignAuthorizationService.getSuccessEsignAuthorizationByUserCode(borrowerCode);
        if (saasUserEsignAuthorizationVo == null) {
            throw new ApplicationException("请先进行签署授权");
        }
        SaasOrder saasOrder = saasOrderService.selectById(orderId);
        if (saasOrder == null || StringUtils.isEmpty(saasOrder.getTermUrl())) {
            String url = getExtendContractUrl(orderId);
            String loanContractUrl = configUtil.getPdfPath() + MD5.md5(OrderNoUtil.makeOrderNum()) + ".pdf";
            contractCreateApplication.createExtendPdf(orderId, loanContractUrl);
            File file = new File(loanContractUrl);
            try {
                InputStream inputStream = new FileInputStream(file);
                url = ossService.uploadFile(inputStream, inputStream.available(), url);
                file.delete();
                return url;
            } catch (Exception e) {
                throw new ApplicationException("上传文件失败");
            }
        } else {
            return saasOrder.getTermUrl();
        }
    }

    public String lenderDoExtendContractSign(String merchantCode, Long orderId) {
        SaasUserEsignAuthorizationVo saasUserEsignAuthorizationVo = saasUserEsignAuthorizationService.getSuccessEsignAuthorizationByUserCode(merchantCode);
        if (saasUserEsignAuthorizationVo == null) {
            throw new ApplicationException("请先进行签署授权");
        }
        SaasOrder saasOrder = saasOrderService.selectById(orderId);
        if (saasOrder == null || StringUtils.isEmpty(saasOrder.getTermUrl())) {
            String url = getExtendContractUrl(orderId);
            String loanContractUrl = configUtil.getPdfPath() + MD5.md5(OrderNoUtil.makeOrderNum()) + ".pdf";
            contractCreateApplication.createExtendPdf(orderId, loanContractUrl);
            File file = new File(loanContractUrl);
            try {
                InputStream inputStream = new FileInputStream(file);
                url = ossService.uploadFile(inputStream, inputStream.available(), url);
                file.delete();
                return url;
            } catch (Exception e) {
                throw new ApplicationException("上传文件失败");
            }
        } else {
            return saasOrder.getTermUrl();
        }
    }


    private String getSealUrl(String userCode) {
        StringBuilder filePath = new StringBuilder("contract/seal/");
        if (configUtil.isServerTest()) {
            filePath.append("test/");
        }
        filePath.append(userCode).append("/").append(MD5.md5(OrderNoUtil.makeOrderNum())).append(".jpg");
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

    private String getLoanContractUrl(Long orderId) {
        StringBuilder filePath = new StringBuilder("contract/loan/");
        if (configUtil.isServerTest()) {
            filePath.append("test/");
        }
        filePath.append(orderId).append("_").append(MD5.md5(OrderNoUtil.makeOrderNum())).append(".pdf");
        return filePath.toString();
    }

    private String getExtendContractUrl(Long orderId) {
        StringBuilder filePath = new StringBuilder("contract/extend/");
        if (configUtil.isServerTest()) {
            filePath.append("test/");
        }
        filePath.append(orderId).append("_").append(MD5.md5(OrderNoUtil.makeOrderNum())).append(".pdf");
        return filePath.toString();

    }

}