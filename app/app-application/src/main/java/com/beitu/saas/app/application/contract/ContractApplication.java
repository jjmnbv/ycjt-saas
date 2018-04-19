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
import com.beitu.saas.intergration.esign.param.*;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.entity.SaasOrder;
import com.beitu.saas.user.client.SaasEsignAccountService;
import com.beitu.saas.user.client.SaasEsignUserAuthorizationService;
import com.beitu.saas.user.domain.SaasEsignAccountVo;
import com.beitu.saas.user.domain.SaasEsignUserAuthorizationVo;
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
    private SaasEsignUserAuthorizationService saasEsignUserAuthorizationService;

    @Autowired
    private SaasEsignAccountService saasEsignAccountService;

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
        if (saasEsignUserAuthorizationService.isSuccessAuthorization(userCode)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public String getUserSealUrl(String userCode) {
        SaasEsignUserAuthorizationVo saasEsignUserAuthorizationVo = saasEsignUserAuthorizationService.getByUserCode(userCode);
        if (saasEsignUserAuthorizationVo == null) {
            return null;
        }
        SaasEsignAccountVo saasEsignAccountVo = saasEsignAccountService.getBySaasEsignCode(saasEsignUserAuthorizationVo.getSaasEsignCode());
        if (saasEsignAccountVo == null) {
            return null;
        }
        return saasEsignAccountVo.getSealUrl();
    }

    /**
     * 借款人 签署 esign 授权协议
     *
     * @param borrowerCode 借款人
     */
    public void borrowerDoLicenseContractSign(String borrowerCode) {
        if (!needDoLicenseContractSign(borrowerCode)) {
            return;
        }
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(borrowerCode);
        if (saasBorrowerRealInfoVo == null) {
            throw new ApplicationException(RestCodeEnum.BORROWER_NOT_EXIST_ERROR);
        }
        SaasEsignAccountVo saasEsignAccountVo = saasEsignAccountService.getByCode(saasBorrowerRealInfoVo.getIdentityCode());
        String saasEsignCode;
        String accountId;
        String sealData;
        if (saasEsignAccountVo != null) {
            accountId = saasEsignAccountVo.getAccountId();
            sealData = ossService.getFileContent(saasEsignAccountVo.getSealUrl());
            saasEsignCode = saasEsignAccountVo.getSaasEsignCode();
        } else {
            PersonAccountParam personAccountParam = new PersonAccountParam();
            personAccountParam.setUserCode(borrowerCode);
            personAccountParam.setUserName(saasBorrowerRealInfoVo.getName());
            personAccountParam.setIdentityCode(saasBorrowerRealInfoVo.getIdentityCode());
            AddPersonAccountSuccessDto addPersonAccountSuccessDto = esignIntegrationService.addPersonAccount(personAccountParam);
            if (addPersonAccountSuccessDto == null) {
                throw new ApplicationException("新增个人账户失败");
            }
            accountId = addPersonAccountSuccessDto.getPersonAccountId();
            sealData = addPersonAccountSuccessDto.getSealData();
            if (StringUtils.isEmpty(sealData)) {
                throw new ApplicationException("ESIGN印章生成失败");
            }
            saasEsignCode = OrderNoUtil.makeOrderNum();
            SaasEsignAccountVo addSaasEsignAccountVo = new SaasEsignAccountVo();
            addSaasEsignAccountVo.setName(saasBorrowerRealInfoVo.getName());
            addSaasEsignAccountVo.setCode(saasBorrowerRealInfoVo.getIdentityCode());
            addSaasEsignAccountVo.setAccountId(accountId);
            String sealUrl = getSealUrl(borrowerCode);
            ossService.uploadFile(sealUrl, sealData);
            addSaasEsignAccountVo.setSealUrl(sealUrl);
            addSaasEsignAccountVo.setSaasEsignCode(saasEsignCode);
            saasEsignAccountService.addSaasEsignAccountVo(addSaasEsignAccountVo);
        }
        doEsignAuthorization(saasBorrowerRealInfoVo.getMerchantCode(), borrowerCode, saasBorrowerRealInfoVo.getName(), saasEsignCode, accountId, sealData);
    }

    /**
     * 出借机构 签署 esign 授权协议
     *
     * @param merchantCode 出借机构
     */
    public void lenderDoLicenseContractSign(String merchantCode) {
        if (!needDoLicenseContractSign(merchantCode)) {
            return;
        }
        MerchantContractInfoVo merchantContractInfoVo = merchantApplication.getMerchantContractInfo(merchantCode);
        if (merchantContractInfoVo == null) {
            throw new ApplicationException(RestCodeEnum.MERCHANT_NOT_EXIST_ERROR);
        }
        SaasEsignAccountVo saasEsignAccountVo = saasEsignAccountService.getByCode(merchantContractInfoVo.getCode());
        String saasEsignCode;
        String accountId;
        String sealData;
        if (saasEsignAccountVo != null) {
            accountId = saasEsignAccountVo.getAccountId();
            sealData = ossService.getFileContent(saasEsignAccountVo.getSealUrl());
            saasEsignCode = saasEsignAccountVo.getSaasEsignCode();
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
            saasEsignCode = OrderNoUtil.makeOrderNum();
            SaasEsignAccountVo addSaasEsignAccountVo = new SaasEsignAccountVo();
            addSaasEsignAccountVo.setName(merchantContractInfoVo.getName());
            addSaasEsignAccountVo.setCode(merchantContractInfoVo.getCode());
            addSaasEsignAccountVo.setAccountId(accountId);
            String sealUrl = getSealUrl(merchantCode);
            ossService.uploadFile(sealUrl, sealData);
            addSaasEsignAccountVo.setSealUrl(sealUrl);
            addSaasEsignAccountVo.setSaasEsignCode(saasEsignCode);
            saasEsignAccountService.addSaasEsignAccountVo(addSaasEsignAccountVo);
        }
        doEsignAuthorization(merchantCode, merchantCode, ContractConsts.DEFAULT_DO_LICENSE_CONTRACT_SIGN_OPERATOR_NAME, saasEsignCode, accountId, sealData);
    }

    private void doEsignAuthorization(String merchantCode, String userCode, String name, String saasEsignCode, String accountId, String sealData) {
        new DoSign() {

            @Override
            String getLocalContractFilePath() {
                return configUtil.getPdfPath() + userCode + "_" + MD5.md5(OrderNoUtil.makeOrderNum()) + ".pdf";
            }

            @Override
            void createLocalContract(String localContractFilePath) {
                contractCreateApplication.createAuthorizationPdf(userCode, localContractFilePath);
            }

            @Override
            byte[] doEsign(byte[] pdfContent) {
                LicenseContractSignParam licenseContractSignParam = new LicenseContractSignParam();
                licenseContractSignParam.setUserCode(merchantCode);
                licenseContractSignParam.setUserAccountId(accountId);
                licenseContractSignParam.setUserSealData(sealData);
                licenseContractSignParam.setSrcPdfContent(pdfContent);
                byte[] content = esignIntegrationService.doLicenseContractSign(licenseContractSignParam);
                if (content == null) {
                    throw new ApplicationException("esign盖章失败");
                }
                saasCreditHistoryService.addExpenditureCreditHistory(merchantCode, name, CreditConsumeEnum.RISK_ESIGN);
                return content;
            }

            @Override
            String getContractUrl() {
                return getAuthorizationUrl(userCode);
            }

            @Override
            void afterCompletion(String url) {
                SaasEsignUserAuthorizationVo addSaasEsignUserAuthorizationVo = new SaasEsignUserAuthorizationVo();
                addSaasEsignUserAuthorizationVo.setUserCode(userCode);
                addSaasEsignUserAuthorizationVo.setAuthorizationUrl(url);
                addSaasEsignUserAuthorizationVo.setSaasEsignCode(saasEsignCode);
                saasEsignUserAuthorizationService.addSaasEsignUserAuthorizationVo(addSaasEsignUserAuthorizationVo);
            }
        }.doSign();
    }


    /**
     * 签署 借款合同
     *
     * @param orderId 订单ID
     */
    public void doLoanContractSign(Long orderId) {
        final SaasOrder saasOrder = saasOrderService.selectById(orderId);
        SaasEsignAccountVo borrowerEsignAccountVo = getSaasEsignAccountVo(saasOrder.getBorrowerCode());
        if (borrowerEsignAccountVo == null) {
            throw new ApplicationException("借款方未签署电子协议授权书");
        }
        final String borrowerSealData = ossService.getFileContent(borrowerEsignAccountVo.getSealUrl());
        if (StringUtils.isEmpty(borrowerSealData)) {
            throw new ApplicationException("借款方印章数据丢失");
        }
        SaasEsignAccountVo lenderEsignAccountVo = getSaasEsignAccountVo(saasOrder.getMerchantCode());
        if (lenderEsignAccountVo == null) {
            throw new ApplicationException("出借方未签署电子协议授权书");
        }
        final String lenderSealData = ossService.getFileContent(lenderEsignAccountVo.getSealUrl());
        if (StringUtils.isEmpty(lenderSealData)) {
            throw new ApplicationException("出借方印章数据丢失");
        }
        new DoSign() {

            @Override
            String getLocalContractFilePath() {
                return configUtil.getPdfPath() + orderId + "_" + MD5.md5(OrderNoUtil.makeOrderNum()) + ".pdf";
            }

            @Override
            void createLocalContract(String localContractFilePath) {
                contractCreateApplication.createLoanPdf(orderId, localContractFilePath);
            }

            @Override
            String getContractUrl() {
                return getLoanContractUrl(orderId);
            }

            @Override
            byte[] doEsign(byte[] pdfContent) {
                LenderDoContractSignParam lenderDoContractSignParam = new LenderDoContractSignParam();
                lenderDoContractSignParam.setMerchantCode(saasOrder.getMerchantCode());
                lenderDoContractSignParam.setMerchantAccountId(lenderEsignAccountVo.getAccountId());
                lenderDoContractSignParam.setMerchantSealData(lenderSealData);
                lenderDoContractSignParam.setSrcPdfContent(pdfContent);
                byte[] content = esignIntegrationService.lenderDoLoanContractSign(lenderDoContractSignParam);
                if (content == null) {
                    throw new ApplicationException("esign盖章失败");
                }
                saasCreditHistoryService.addExpenditureCreditHistory(saasOrder.getMerchantCode(), "借款合同" + orderId + "出借方", CreditConsumeEnum.RISK_ESIGN);
                BorrowerDoContractSignParam borrowerDoContractSignParam = new BorrowerDoContractSignParam();
                borrowerDoContractSignParam.setBorrowerCode(saasOrder.getBorrowerCode());
                borrowerDoContractSignParam.setBorrowerAccountId(borrowerEsignAccountVo.getAccountId());
                borrowerDoContractSignParam.setBorrowerSealData(borrowerSealData);
                borrowerDoContractSignParam.setSrcPdfContent(content);
                byte[] finalContent = esignIntegrationService.borrowerDoLoanContractSign(borrowerDoContractSignParam);
                if (content == null) {
                    throw new ApplicationException("esign盖章失败");
                }
                saasCreditHistoryService.addExpenditureCreditHistory(saasOrder.getMerchantCode(), "借款合同" + orderId + "借款方", CreditConsumeEnum.RISK_ESIGN);
                return finalContent;
            }

            @Override
            void afterCompletion(String url) {
                SaasOrder updateSaasOrder = new SaasOrder();
                updateSaasOrder.setId(saasOrder.getId());
                updateSaasOrder.setTermUrl(url);
                saasOrderService.updateById(updateSaasOrder);
            }

        }.doSign();
    }

    /**
     * 签署 展期合同
     *
     * @param orderId 订单ID
     */
    public void doExtendContractSign(Long orderId) {
        final SaasOrder saasOrder = saasOrderService.selectById(orderId);
        SaasEsignAccountVo borrowerEsignAccountVo = getSaasEsignAccountVo(saasOrder.getBorrowerCode());
        if (borrowerEsignAccountVo == null) {
            throw new ApplicationException("借款方未签署电子协议授权书");
        }
        final String borrowerSealData = ossService.getFileContent(borrowerEsignAccountVo.getSealUrl());
        if (StringUtils.isEmpty(borrowerSealData)) {
            throw new ApplicationException("借款方印章数据丢失");
        }
        SaasEsignAccountVo lenderEsignAccountVo = getSaasEsignAccountVo(saasOrder.getMerchantCode());
        if (lenderEsignAccountVo == null) {
            throw new ApplicationException("出借方未签署电子协议授权书");
        }
        final String lenderSealData = ossService.getFileContent(lenderEsignAccountVo.getSealUrl());
        if (StringUtils.isEmpty(lenderSealData)) {
            throw new ApplicationException("出借方印章数据丢失");
        }
        new DoSign() {

            @Override
            String getLocalContractFilePath() {
                return configUtil.getPdfPath() + orderId + "_" + MD5.md5(OrderNoUtil.makeOrderNum()) + ".pdf";
            }

            @Override
            void createLocalContract(String localContractFilePath) {
                contractCreateApplication.createExtendPdf(orderId, localContractFilePath);
            }

            @Override
            String getContractUrl() {
                return getExtendContractUrl(orderId);
            }

            @Override
            byte[] doEsign(byte[] pdfContent) {
                return pdfContent;
            }

            @Override
            void afterCompletion(String url) {
                SaasOrder updateSaasOrder = new SaasOrder();
                updateSaasOrder.setId(saasOrder.getId());
                updateSaasOrder.setTermUrl(url);
                saasOrderService.updateById(updateSaasOrder);
            }

        }.doSign();
    }

    private SaasEsignAccountVo getSaasEsignAccountVo(String userCode) {
        SaasEsignUserAuthorizationVo saasEsignUserAuthorizationVo = saasEsignUserAuthorizationService.getByUserCode(userCode);
        if (saasEsignUserAuthorizationVo == null) {
            return null;
        }
        return saasEsignAccountService.getBySaasEsignCode(saasEsignUserAuthorizationVo.getSaasEsignCode());
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

    private abstract class DoSign {

        abstract String getLocalContractFilePath();

        abstract void createLocalContract(String localContractFilePath);

        abstract byte[] doEsign(byte[] localPdfContent);

        abstract String getContractUrl();

        abstract void afterCompletion(String url);

        public void doSign() {
            String createFilePath = getLocalContractFilePath();
            createLocalContract(createFilePath);
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
                if (pdfContent == null) {
                    throw new ApplicationException("本地合同生成失败");
                }
                byte[] content = doEsign(pdfContent);
                if (content == null) {
                    throw new ApplicationException("esign盖章失败");
                }
                InputStream inputStream = null;
                String url = getContractUrl();
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
                afterCompletion(url);
            }
        }
    }

}