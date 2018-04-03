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
            LOGGER.info("创建个人模板印章失败，errCode=" + addSealResult.getErrCode() + " msg=" + addSealResult.getMsg() + " --- userCode:" + personAccountParam.getUserCode());
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
            LOGGER.info("创建企业账户失败，errCode=" + addAccountResult.getErrCode() + " msg=" + addAccountResult.getMsg() + " --- merchantCode:" + organizeAccountParam.getMerchantCode());
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
            LOGGER.info("创建企业模板印章失败，errCode=" + addSealResult.getErrCode() + " msg=" + addSealResult.getMsg() + " --- merchantCode:" + organizeAccountParam.getMerchantCode());
            return null;
        }
        return new AddOrganizeAccountSuccessDto(accountId, addSealResult.getSealData());
    }


    @Override
    public String borrowerDoContractSign(BorrowerDoContractSignParam borrowerDoContractSignParam) {
        if (StringUtils.isEmpty(borrowerDoContractSignParam.getBorrowerSealData())) {
            throw new ApplicationException("借款用户印章数据丢失");
        }
        SignPDFStreamBean signPDFStreamBean = new SignPDFStreamBean();
        if (borrowerDoContractSignParam.getSrcContent() == null) {
            platformDoContractSign(borrowerDoContractSignParam.getSrcPdf(), signPDFStreamBean);
        } else {
            signPDFStreamBean.setStream(borrowerDoContractSignParam.getSrcContent());
        }
        SignType signType = SignType.Key;
        PosBean posBean = setKeyPosBean("乙方：", 200, 5, 60);
        UserSignService userSignService = UserSignServiceFactory.instance();
        FileDigestSignResult finalResult = userSignService.localSignPDF(borrowerDoContractSignParam.getBorrowerAccountId(), borrowerDoContractSignParam.getBorrowerSealData(), signPDFStreamBean, posBean, signType);
        if (0 != finalResult.getErrCode()) {
            throw new ApplicationException("借款协议借款方签章失败" + (finalResult.isErrShow() ? (":" + finalResult.getMsg()) : "") + " --- borrowerCode:" + borrowerDoContractSignParam.getBorrowerCode());
        }
        return new String(finalResult.getStream());
    }

    @Override
    public String lenderDoContractSign(LenderDoContractSignParam lenderDoContractSignParam) {
        if (StringUtils.isEmpty(lenderDoContractSignParam.getMerchantSealData())) {
            throw new ApplicationException("出借用户印章数据丢失");
        }
        SignPDFStreamBean signPDFStreamBean = new SignPDFStreamBean();
        if (lenderDoContractSignParam.getSrcContent() == null) {
            platformDoContractSign(lenderDoContractSignParam.getSrcPdf(), signPDFStreamBean);
        } else {
            signPDFStreamBean.setStream(lenderDoContractSignParam.getSrcContent());
        }
        SignType signType = SignType.Key;
        PosBean posBean = setKeyPosBean("甲方：", 200, 5, 60);
        UserSignService userSignService = UserSignServiceFactory.instance();
        FileDigestSignResult finalResult = userSignService.localSignPDF(lenderDoContractSignParam.getMerchantAccountId(), lenderDoContractSignParam.getMerchantSealData(), signPDFStreamBean, posBean, signType);
        if (0 != finalResult.getErrCode()) {
            throw new ApplicationException("借款协议出借方签章失败" + (finalResult.isErrShow() ? (":" + finalResult.getMsg()) : "") + " --- merchantCode:" + lenderDoContractSignParam.getMerchantCode());
        }
        return new String(finalResult.getStream());
    }

    private void platformDoContractSign(String srcPdf, SignPDFStreamBean signPDFStreamBean) {
        // 设置签署印章，www.tsign.cn官网设置的默认签名sealId = 0
        int sealId = 0;
        // 签章类型：关键字签章
        SignType signType = SignType.Key;
        signPDFStreamBean.setStream(FileHelper.getBytes(srcPdf));
        PosBean posBean = setKeyPosBean("丙方：", 260, -35, 160);
        SelfSignService selfSignService = SelfSignServiceFactory.instance();
        FileDigestSignResult platformSignResult = selfSignService.localSignPdf(signPDFStreamBean, posBean, sealId, signType);
        if (0 != platformSignResult.getErrCode()) {
            LOGGER.info("平台自身PDF摘要签署（文件流）失败，errCode=" + platformSignResult.getErrCode() + " msg=" + platformSignResult.getMsg());
        }
        signPDFStreamBean.setStream(platformSignResult.getStream());
    }

    @Override
    public String doLicenseContractSign(LicenseContractSignParam licenseContractSignParam) {
        if (StringUtils.isEmpty(licenseContractSignParam.getUserSealData())) {
            throw new ApplicationException("授权方印章数据丢失");
        }
        // 设置签署印章，www.tsign.cn官网设置的默认签名sealId = 0
        int sealId = 0;
        // 签章类型：关键字签章
        SignPDFStreamBean signPDFStreamBean = new SignPDFStreamBean();
        signPDFStreamBean.setStream(FileHelper.getBytes(licenseContractSignParam.getSrcPdf()));
        SignType signType = SignType.Key;
        PosBean posBean = setKeyPosBean("丙方：", 260, -35, 160);
        SelfSignService selfSignService = SelfSignServiceFactory.instance();
        FileDigestSignResult platformSignResult = selfSignService.localSignPdf(signPDFStreamBean, posBean, sealId, signType);
        if (0 != platformSignResult.getErrCode()) {
            LOGGER.info("平台自身PDF摘要签署（文件流）失败，errCode=" + platformSignResult.getErrCode() + " msg=" + platformSignResult.getMsg());
        }
        posBean = setKeyPosBean("乙方：", 200, 5, 60);
        signPDFStreamBean.setStream(platformSignResult.getStream());
        UserSignService userSignService = UserSignServiceFactory.instance();
        FileDigestSignResult finalResult = userSignService.localSignPDF(licenseContractSignParam.getUserAccountId(), licenseContractSignParam.getUserSealData(), signPDFStreamBean, posBean, signType);
        if (0 != finalResult.getErrCode()) {
            throw new ApplicationException("授权协议授权方签章失败" + (finalResult.isErrShow() ? (":" + finalResult.getMsg()) : "") + " --- userCode:" + licenseContractSignParam.getUserCode());
        }
        return new String(finalResult.getStream());
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

}