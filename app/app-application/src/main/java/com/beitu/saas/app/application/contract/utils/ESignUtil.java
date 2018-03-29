package com.beitu.saas.app.application.contract.utils;

import com.beitu.saas.app.application.contract.eSign.SignHelper;
import com.beitu.saas.app.application.contract.vo.AuthorizationLawTermVo;
import com.beitu.saas.common.handle.oss.OSSHandler;
import com.beitu.saas.common.handle.oss.OSSService;
import com.fqgj.common.utils.MD5;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.timevale.esign.sdk.tech.bean.PersonBean;
import com.timevale.esign.sdk.tech.bean.PosBean;
import com.timevale.esign.sdk.tech.bean.result.AddSealResult;
import com.timevale.esign.sdk.tech.bean.result.FileDigestSignResult;
import com.timevale.esign.sdk.tech.impl.constants.SignType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@Component
public class ESignUtil {
    
    private static final Log LOGGER = LogFactory.getLog(ESignUtil.class);
    
    @Autowired
    private UserEsignAuthorizationService userEsignAuthorizationService;
    
    @Autowired
    private OSSService ossService;
    
    @Autowired
    private OSSHandler ossHandler;
    
    public InputStream doAuthorizationSign(Object lawTerm, Boolean isCurrentEnvIsTest) {
        Long userId = ((AuthorizationLawTermVo) lawTerm).getUserId();
        String userCode = ((AuthorizationLawTermVo) lawTerm).getUserCode();
        String signUrl = ((AuthorizationLawTermVo) lawTerm).getSignUrl();
        String userSignSealData = null;
        if (StringUtils.isNotEmpty(signUrl)) {
            String bucketName = "yangcongjietiao";
            userSignSealData = ossHandler.getFileContent(bucketName, signUrl);
        }
        if (StringUtils.isEmpty(userSignSealData)) {
            throw new ApplicationException("用户签字数据为空 --- userId:" + userId);
        }
        
        PersonBean personBean = new PersonBean();
        personBean.setName(((AuthorizationLawTermVo) lawTerm).getUserName());
        personBean.setIdNo(((AuthorizationLawTermVo) lawTerm).getIdentityNo());
        personBean.setPersonArea(0);
        
        String accountId = getOrCreateUserAccount(userId, userCode,signUrl, personBean);
        getOrCreateUserSeal(userId,userCode, accountId, isCurrentEnvIsTest);
        
        String srcPdf = ((AuthorizationLawTermVo) lawTerm).getPdfPath();
        PosBean posBean = SignHelper.setXYPosBean("1", 360, 250, 140);
        SignType signType = SignType.Single;
        FileDigestSignResult result = SignHelper.userPersonSignByStream(FileHelper.getBytes(srcPdf), accountId, userSignSealData, posBean, signType);
        if (0 != result.getErrCode()) {
            throw new ApplicationException("授权协议签章失败" + (result.isErrShow() ? (":" + result.getMsg()) : "") + " --- userId:" + userId);
        }
        
        return new ByteArrayInputStream(result.getStream());
    }
    
    public InputStream doContractSign(Object lawTerm, Boolean isCurrentEnvIsTest) {
        Long userId = ((OrderLawTermVo) lawTerm).getUserId();
        Long lenderId = ((OrderLawTermVo) lawTerm).getLenderId();
        String userCode = ((OrderLawTermVo) lawTerm).getUserCode();
        String lenderCode = ((OrderLawTermVo) lawTerm).getLenderCode();
        if (!userAuthorizationSuccess(userId)) {
            throw new ApplicationException("借款用户未授权，无法签署借款协议 --- userId:" + userId);
        }
        if (!userAuthorizationSuccess(lenderId)) {
            throw new ApplicationException("出借用户未授权，无法签署借款协议 --- userId:" + lenderId);
        }
        
        PersonBean borrowerBean = new PersonBean();
        borrowerBean.setName(((OrderLawTermVo) lawTerm).getBorrowUserName());
        borrowerBean.setIdNo(((OrderLawTermVo) lawTerm).getBorrowIdentityNo());
        borrowerBean.setPersonArea(0);
    
        PersonBean lenderBean = new PersonBean();
        lenderBean.setName(((OrderLawTermVo) lawTerm).getLenderUserName());
        lenderBean.setIdNo(((OrderLawTermVo) lawTerm).getLenderIdentityNo());
        lenderBean.setPersonArea(0);
        
        String borrowerAccountId = getOrCreateUserAccount(userId,userCode, null, borrowerBean);
        String borrowerSealUrl = getOrCreateUserSeal(userId,userCode, borrowerAccountId, isCurrentEnvIsTest);
        
        String lenderAccountId = getOrCreateUserAccount(lenderId,lenderCode, null, lenderBean);
        String lenderSealUrl = getOrCreateUserSeal(lenderId,lenderCode, lenderAccountId, isCurrentEnvIsTest);
        
        String lenderSealData = null;
        String borrowerSealData = null;
        
        if (StringUtils.isNotEmpty(borrowerSealUrl)) {
            String bucketName = "yangcongjietiao";
            borrowerSealData = ossHandler.getFileContent(bucketName, borrowerSealUrl);
        }
        if (StringUtils.isNotEmpty(lenderSealUrl)) {
            String bucketName = "yangcongjietiao";
            lenderSealData = ossHandler.getFileContent(bucketName, lenderSealUrl);
        }
        
        if (StringUtils.isEmpty(borrowerSealData)) {
            throw new ApplicationException("借款用户印章数据为空 --- userId:" + userId);
        }
        if (StringUtils.isEmpty(lenderSealData)) {
            throw new ApplicationException("出借用户印章数据为空 --- userId:" + lenderId);
        }
        
        String srcPdf = ((OrderLawTermVo) lawTerm).getPdfPath();
        SignType signType = SignType.Key;
        PosBean posBean = SignHelper.setKeyPosBean("丙方：", 260, -35, 160);
        FileDigestSignResult platformSignResult = SignHelper.platformSignByStreamm(FileHelper.getBytes(srcPdf), posBean, signType);
        if (0 != platformSignResult.getErrCode()) {
            throw new ApplicationException("借款协议公司签章失败" + (platformSignResult.isErrShow() ? (":" + platformSignResult.getMsg()) : ""));
        }
        
        posBean = SignHelper.setKeyPosBean("乙方：", 200, 5, 60);
        FileDigestSignResult borrowerResult = SignHelper.userPersonSignByStream(platformSignResult.getStream(), borrowerAccountId, borrowerSealData, posBean, signType);
        if (0 != borrowerResult.getErrCode()) {
            throw new ApplicationException("借款协议借款方签章失败" + (borrowerResult.isErrShow() ? (":" + borrowerResult.getMsg()) : "") + " --- userId:" + userId);
        }
        
        posBean = SignHelper.setKeyPosBean("甲方：", 200, 5, 60);
        FileDigestSignResult finalResult = SignHelper.userPersonSignByStream(borrowerResult.getStream(), lenderAccountId, lenderSealData, posBean, signType);
        if (0 != finalResult.getErrCode()) {
            throw new ApplicationException("借款协议出借方签章失败" + (finalResult.isErrShow() ? (":" + finalResult.getMsg()) : "") + " --- userId:" + lenderId);
        }
        
        return new ByteArrayInputStream(finalResult.getStream());
    }
    
    public String getOrCreateUserAccount(Long userId,String userCode, String signUrl, PersonBean personBean) {
        UserEsignAuthorizationVo vo = getValidAuthorization(userId);
        if (vo != null) {
            return vo.getAccountId();
        }
        String userPersonAccountId = SignHelper.addPersonAccount(personBean);
        if (userPersonAccountId == null) {
            throw new ApplicationException("e签宝账户创建失败");
        }
        
        UserEsignAuthorizationVo userEsignAuthorizationVo = new UserEsignAuthorizationVo();
        userEsignAuthorizationVo.setUserId(userId);
        userEsignAuthorizationVo.setUserCode(userCode);
        userEsignAuthorizationVo.setSignUrl(signUrl);
        userEsignAuthorizationVo.setAccountId(userPersonAccountId);
        userEsignAuthorizationVo.setAuthorizationTime(new Date());
        userEsignAuthorizationService.addOrUpdate(userEsignAuthorizationVo);
        return userPersonAccountId;
    }
    
    private String getOrCreateUserSeal(Long userId,String userCode, String accountId, Boolean isCurrentEnvIsTest) {
        UserEsignAuthorizationVo vo = getValidAuthorization(userId);
        if (vo != null && StringUtils.isNotBlank(vo.getSealUrl())) {
            return vo.getSealUrl();
        }
        AddSealResult userPersonSealData = SignHelper.addPersonTemplateSeal(accountId);
        if (userPersonSealData == null || StringUtils.isEmpty(userPersonSealData.getSealData())) {
            throw new ApplicationException("用户印章创建失败");
        }
        
        String sealUrl = "clientSpace/";
        if (isCurrentEnvIsTest) {
            sealUrl += "test/";
        }
        sealUrl += (userId + "/" + MD5.md5(UUID.randomUUID().toString()));
        LOGGER.info("用户印章上传开始=====file " + sealUrl);
        sealUrl = ossService.uploadFile(sealUrl, userPersonSealData.getSealData());
        LOGGER.info("用户印章上传结束=====file " + sealUrl);
        
        UserEsignAuthorizationVo userEsignAuthorizationVo = new UserEsignAuthorizationVo();
        userEsignAuthorizationVo.setUserId(userId);
        userEsignAuthorizationVo.setSealUrl(sealUrl);
        userEsignAuthorizationVo.setUserCode(userCode);
        userEsignAuthorizationService.addOrUpdate(userEsignAuthorizationVo);
        return sealUrl;
    }
    
    public void createAuthorizationUrl(Long userId,String userCode, String url) {
        UserEsignAuthorizationVo vo = getValidAuthorization(userId);
        if (vo != null && StringUtils.isNotBlank(vo.getAuthorizationUrl())) {
            return;
        }
        UserEsignAuthorizationVo userEsignAuthorizationVo = new UserEsignAuthorizationVo();
        userEsignAuthorizationVo.setUserId(userId);
        userEsignAuthorizationVo.setUserCode(userCode);
        userEsignAuthorizationVo.setAuthorizationUrl(url);
        userEsignAuthorizationVo.setSuccess(Boolean.TRUE);
        userEsignAuthorizationService.addOrUpdate(userEsignAuthorizationVo);
    }
    
    private UserEsignAuthorizationVo getValidAuthorization(Long userId) {
        UserEsignAuthorizationVo vo = userEsignAuthorizationService.getByUserId(userId);
        if (vo != null && StringUtils.isNotBlank(vo.getAccountId()) && StringUtils.isNotBlank(vo.getSignUrl())) {
            return vo;
        }
        return null;
    }
    
    public Boolean userAuthorizationSuccess(Long userId) {
        UserEsignAuthorizationVo vo = getValidAuthorization(userId);
        if (vo == null) {
            return Boolean.FALSE;
        }
        return vo.getSuccess();
    }
    
}
