package com.beitu.saas.intergration.esign.impl;

import com.beitu.saas.common.utils.contract.utils.FileHelper;
import com.beitu.saas.intergration.esign.EsignIntegrationService;
import com.beitu.saas.intergration.esign.dto.AddOrganizeAccountSuccessDto;
import com.beitu.saas.intergration.esign.dto.AddPersonAccountSuccessDto;
import com.beitu.saas.intergration.esign.param.*;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.timevale.esign.sdk.tech.bean.OrganizeBean;
import com.timevale.esign.sdk.tech.bean.PersonBean;
import com.timevale.esign.sdk.tech.bean.PosBean;
import com.timevale.esign.sdk.tech.bean.SignPDFStreamBean;
import com.timevale.esign.sdk.tech.bean.result.AddAccountResult;
import com.timevale.esign.sdk.tech.bean.result.AddSealResult;
import com.timevale.esign.sdk.tech.bean.result.FileDigestSignResult;
import com.timevale.esign.sdk.tech.bean.seal.OrganizeTemplateType;
import com.timevale.esign.sdk.tech.bean.seal.PersonTemplateType;
import com.timevale.esign.sdk.tech.bean.seal.SealColor;
import com.timevale.esign.sdk.tech.impl.constants.LegalAreaType;
import com.timevale.esign.sdk.tech.impl.constants.OrganRegType;
import com.timevale.esign.sdk.tech.impl.constants.SignType;
import com.timevale.esign.sdk.tech.service.AccountService;
import com.timevale.esign.sdk.tech.service.SealService;
import com.timevale.esign.sdk.tech.service.SelfSignService;
import com.timevale.esign.sdk.tech.service.UserSignService;
import com.timevale.esign.sdk.tech.service.factory.AccountServiceFactory;
import com.timevale.esign.sdk.tech.service.factory.SealServiceFactory;
import com.timevale.esign.sdk.tech.service.factory.SelfSignServiceFactory;
import com.timevale.esign.sdk.tech.service.factory.UserSignServiceFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author linanjun
 * @create 2018/3/29 下午5:21
 * @description
 */
@Service
public class EsignIntegrationServiceImpl implements EsignIntegrationService {

    private static final Log LOGGER = LogFactory.getLog(EsignIntegrationServiceImpl.class);

    @Override
    public AddPersonAccountSuccessDto addPersonAccount(PersonAccountParam personAccountParam) {
        PersonBean personBean = new PersonBean();
        personBean.setIdNo(personAccountParam.getIdentityCode());
        personBean.setName(personAccountParam.getUserName());
        // 个人身份证件类型：大陆身份证
        personBean.setPersonArea(LegalAreaType.MAINLAND.type());
        AccountService accountService = AccountServiceFactory.instance();
        AddAccountResult addAccountResult = accountService.addAccount(personBean);
        if (0 != addAccountResult.getErrCode()) {
            LOGGER.error("创建个人账户失败，errCode=" + addAccountResult.getErrCode() + " msg=" + addAccountResult.getMsg() + " --- userCode:" + personAccountParam.getUserCode());
            return null;
        }
        String accountId = addAccountResult.getAccountId();
        // 印章模板类型：华文行楷
        PersonTemplateType personTemplateType = PersonTemplateType.HWXK;
        // 印章颜色：红色
        SealColor sealColor = SealColor.RED;

        SealService sealService = SealServiceFactory.instance();
        AddSealResult addSealResult = sealService.addTemplateSeal(accountId, personTemplateType, sealColor);
        if (0 != addSealResult.getErrCode()) {
            LOGGER.error("创建个人模板印章失败，errCode=" + addSealResult.getErrCode() + " msg=" + addSealResult.getMsg() + " --- userCode:" + personAccountParam.getUserCode());
            return null;
        }
        return new AddPersonAccountSuccessDto(accountId, addSealResult.getSealData());
    }

    @Override
    public AddOrganizeAccountSuccessDto addOrganizeAccount(OrganizeAccountParam organizeAccountParam) {
        OrganizeBean organizeBean = new OrganizeBean();
        organizeBean.setName(organizeAccountParam.getName());
        organizeBean.setOrganCode(organizeAccountParam.getOrganizeCode());
        // 单位类型：0-普通企业，1-社会团体，2-事业单位，3-民办非企业单位，4-党政及国家机构，默认0
        organizeBean.setOrganType(0);
        // 企业注册类型：组织机构代码号
        organizeBean.setRegType(OrganRegType.NORMAL);
        AccountService accountService = AccountServiceFactory.instance();
        AddAccountResult addAccountResult = accountService.addAccount(organizeBean);
        if (0 != addAccountResult.getErrCode()) {
            LOGGER.error("创建企业账户失败，errCode=" + addAccountResult.getErrCode() + " msg=" + addAccountResult.getMsg() + " --- merchantCode:" + organizeAccountParam.getMerchantCode());
            return null;
        }
        String accountId = addAccountResult.getAccountId();
        // 印章模板类型：标准公章
        OrganizeTemplateType organizeTemplateType = OrganizeTemplateType.STAR;
        // 印章颜色：红色
        SealColor sealColor = SealColor.RED;
        // 横向文字
        String hText = "合同专用章";
        String pText = "";
        SealService sealService = SealServiceFactory.instance();
        AddSealResult addSealResult = sealService.addTemplateSeal(accountId, organizeTemplateType, sealColor, hText, pText);
        if (0 != addSealResult.getErrCode()) {
            LOGGER.error("创建企业模板印章失败，errCode=" + addSealResult.getErrCode() + " msg=" + addSealResult.getMsg() + " --- merchantCode:" + organizeAccountParam.getMerchantCode());
            return null;
        }
        return new AddOrganizeAccountSuccessDto(accountId, addSealResult.getSealData());
    }

    @Override
    public byte[] borrowerDoLoanContractSign(BorrowerDoContractSignParam borrowerDoContractSignParam) {
        if (StringUtils.isEmpty(borrowerDoContractSignParam.getBorrowerSealData())) {
            throw new ApplicationException("用户印章数据丢失");
        }
        SignPDFStreamBean signPDFStreamBean = new SignPDFStreamBean();
        signPDFStreamBean.setStream(borrowerDoContractSignParam.getSrcPdfContent());
        SignType signType = SignType.Key;
        PosBean posBean = setKeyPosBean("⼄⽅：", 200, 5, 140);
        UserSignService userSignService = UserSignServiceFactory.instance();
        FileDigestSignResult finalResult = userSignService.localSignPDF(borrowerDoContractSignParam.getBorrowerAccountId(), borrowerDoContractSignParam.getBorrowerSealData(), signPDFStreamBean, posBean, signType);
        if (0 != finalResult.getErrCode()) {
            LOGGER.error("借款协议借款方签章失败：{} --- borrowerCode:{}", finalResult.getMsg(), borrowerDoContractSignParam.getBorrowerCode());
            return null;
        }
        return finalResult.getStream();
    }

    @Override
    public byte[] lenderDoLoanContractSign(LenderDoContractSignParam lenderDoContractSignParam) {
        if (StringUtils.isEmpty(lenderDoContractSignParam.getMerchantSealData())) {
            throw new ApplicationException("机构印章数据丢失");
        }
        SignPDFStreamBean signPDFStreamBean = new SignPDFStreamBean();
        signPDFStreamBean.setStream(lenderDoContractSignParam.getSrcPdfContent());
        SignType signType = SignType.Key;
        PosBean posBean = setKeyPosBean("甲⽅：", 200, 5, 140);
        UserSignService userSignService = UserSignServiceFactory.instance();
        FileDigestSignResult finalResult = userSignService.localSignPDF(lenderDoContractSignParam.getMerchantAccountId(), lenderDoContractSignParam.getMerchantSealData(), signPDFStreamBean, posBean, signType);
        if (0 != finalResult.getErrCode()) {
            LOGGER.error("借款协议出借方签章失败：{} --- merchantCode:{}", finalResult.getMsg(), lenderDoContractSignParam.getMerchantCode());
            return null;
        }
        return finalResult.getStream();
    }

    @Override
    public byte[] borrowerDoExpendContractSign(BorrowerDoContractSignParam borrowerDoContractSignParam) {
        if (StringUtils.isEmpty(borrowerDoContractSignParam.getBorrowerSealData())) {
            throw new ApplicationException("用户印章数据丢失");
        }
        SignPDFStreamBean signPDFStreamBean = new SignPDFStreamBean();
        signPDFStreamBean.setStream(borrowerDoContractSignParam.getSrcPdfContent());
        SignType signType = SignType.Key;
        PosBean posBean = setKeyPosBean("⼄⽅：", 200, 5, 140);
        UserSignService userSignService = UserSignServiceFactory.instance();
        FileDigestSignResult finalResult = userSignService.localSignPDF(borrowerDoContractSignParam.getBorrowerAccountId(), borrowerDoContractSignParam.getBorrowerSealData(), signPDFStreamBean, posBean, signType);
        if (0 != finalResult.getErrCode()) {
            LOGGER.error("展期协议借款方签章失败：{} --- borrowerCode:{}", finalResult.getMsg(), borrowerDoContractSignParam.getBorrowerCode());
            return null;
        }
        return finalResult.getStream();
    }

    @Override
    public byte[] lenderDoExpendContractSign(LenderDoContractSignParam lenderDoContractSignParam) {
        if (StringUtils.isEmpty(lenderDoContractSignParam.getMerchantSealData())) {
            throw new ApplicationException("机构印章数据丢失");
        }
        SignPDFStreamBean signPDFStreamBean = new SignPDFStreamBean();
        signPDFStreamBean.setStream(lenderDoContractSignParam.getSrcPdfContent());
        SignType signType = SignType.Key;
        PosBean posBean = setKeyPosBean("甲⽅：", 200, 5, 140);
        UserSignService userSignService = UserSignServiceFactory.instance();
        FileDigestSignResult finalResult = userSignService.localSignPDF(lenderDoContractSignParam.getMerchantAccountId(), lenderDoContractSignParam.getMerchantSealData(), signPDFStreamBean, posBean, signType);
        if (0 != finalResult.getErrCode()) {
            LOGGER.error("展期协议出借方签章失败：{} --- merchantCode:{}", finalResult.getMsg(), lenderDoContractSignParam.getMerchantCode());
            return null;
        }
        return finalResult.getStream();
    }

    @Override
    public byte[] doLicenseContractSign(LicenseContractSignParam licenseContractSignParam) {
        if (StringUtils.isEmpty(licenseContractSignParam.getUserSealData())) {
            throw new ApplicationException("授权方印章数据丢失");
        }
        SignPDFStreamBean signPDFStreamBean = new SignPDFStreamBean();
        signPDFStreamBean.setStream(licenseContractSignParam.getSrcPdfContent());
        SignType signType = SignType.Single;
        PosBean posBean = setXYPosBean("1", 360, 170, 140);
        UserSignService userSignService = UserSignServiceFactory.instance();
        FileDigestSignResult finalResult = userSignService.localSignPDF(licenseContractSignParam.getUserAccountId(), licenseContractSignParam.getUserSealData(), signPDFStreamBean, posBean, signType);
        LOGGER.error("授权协议授权方签章返回结果:{}", finalResult);
        if (0 != finalResult.getErrCode()) {
            LOGGER.error("授权协议授权方签章失败:" + finalResult.getMsg() + " --- userCode:" + licenseContractSignParam.getUserCode());
            return null;
        }
        return finalResult.getStream();
    }

    public static PosBean setKeyPosBean(String key, int x, int y, int width) {
        PosBean posBean = new PosBean();
        // 定位类型，0-坐标定位，1-关键字定位，默认0，若选择关键字定位，签署类型(signType)必须指定为关键字签署才会生效。
        posBean.setPosType(1);
        // 关键字签署时不可空 */
        posBean.setKey(key);
        // 关键字签署时会对整体pdf文档进行搜索，故设置签署页码无效
        // posBean.setPosPage("1");
        // 签署位置X坐标，以关键字所在位置为原点进行偏移，默认值为0，控制横向移动距离，单位为px
        posBean.setPosX(x);
        // 签署位置Y坐标，以关键字所在位置为原点进行偏移，默认值为0，控制纵向移动距离，单位为px
        posBean.setPosY(y);
        // 印章展现宽度，将以此宽度对印章图片做同比缩放。详细查阅接口文档的15 PosBean描述
        posBean.setWidth(width);
        return posBean;
    }

    public static PosBean setXYPosBean(String page, int x, int y, int width) {
        PosBean posBean = new PosBean();
        // 定位类型，0-坐标定位，1-关键字定位，默认0，若选择关键字定位，签署类型(signType)必须指定为关键字签署才会生效。
        posBean.setPosType(0);
        // 签署页码，若为多页签章，支持页码格式“1-3,5,8“，若为坐标定位时，不可空
        posBean.setPosPage(page);
        // 签署位置X坐标，默认值为0，以pdf页面的左下角作为原点，控制横向移动距离，单位为px
        posBean.setPosX(x);
        // 签署位置Y坐标，默认值为0，以pdf页面的左下角作为原点，控制纵向移动距离，单位为px
        posBean.setPosY(y);
        // 印章展现宽度，将以此宽度对印章图片做同比缩放。详细查阅接口文档的15 PosBean描述
        posBean.setWidth(width);
        return posBean;
    }

}