package com.beitu.saas.app.application.credit;

import com.beitu.saas.app.application.credit.vo.*;
import com.beitu.saas.app.enums.EducationMsgCodeEnum;
import com.beitu.saas.app.enums.MaritalStatusMsgCodeEnum;
import com.beitu.saas.borrower.client.*;
import com.beitu.saas.borrower.domain.*;
import com.beitu.saas.borrower.entity.SaasBorrowerEmergentContact;
import com.beitu.saas.borrower.entity.SaasBorrowerRealInfo;
import com.beitu.saas.borrower.entity.SaasBorrowerWorkInfo;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.utils.identityNumber.vo.IdcardInfoExtractor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @author linanjun
 * @create 2018/3/21 下午4:02
 * @description
 */
@Service
public class BorrowerBaseInfoApplication {

    @Autowired
    private SaasBorrowerPersonalInfoService saasBorrowerPersonalInfoService;

    @Autowired
    private SaasBorrowerService saasBorrowerService;

    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;

    @Autowired
    private SaasBorrowerIdentityInfoService saasBorrowerIdentityInfoService;

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private SaasBorrowerWorkInfoService saasBorrowerWorkInfoService;

    @Autowired
    private SaasBorrowerEmergentContactService saasBorrowerEmergentContactService;

    public BorrowerPersonalInfoVo getUserPersonalInfoVo(String borrowerCode, String orderNumb) {
        BorrowerPersonalInfoVo borrowerPersonalInfoVo = new BorrowerPersonalInfoVo();
        SaasBorrowerVo saasBorrowerVo = saasBorrowerService.getByBorrowerCode(borrowerCode);
        if (saasBorrowerVo == null) {

        }
        borrowerPersonalInfoVo.setMobile(saasBorrowerVo.getMobile());

        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(borrowerCode);
        if (saasBorrowerRealInfoVo != null) {
            borrowerPersonalInfoVo.setUserName(saasBorrowerRealInfoVo.getName());
            borrowerPersonalInfoVo.setIdentityCode(saasBorrowerRealInfoVo.getIdentityCode());
            borrowerPersonalInfoVo.setNativePlace(saasBorrowerRealInfoVo.getNativePlace());
            IdcardInfoExtractor idcardInfoExtractor = new IdcardInfoExtractor(saasBorrowerRealInfoVo.getIdentityCode());
            borrowerPersonalInfoVo.setGender(idcardInfoExtractor.getGender());
            borrowerPersonalInfoVo.setAge(idcardInfoExtractor.getAge());
        }

        SaasBorrowerPersonalInfoVo saasBorrowerPersonalInfoVo = saasBorrowerPersonalInfoService.getByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);
        if (saasBorrowerPersonalInfoVo != null) {
            borrowerPersonalInfoVo.setQq(saasBorrowerPersonalInfoVo.getQq());
            borrowerPersonalInfoVo.setEducation(EducationMsgCodeEnum.getByCode(saasBorrowerPersonalInfoVo.getEducation()).getMsg());
            borrowerPersonalInfoVo.setMaritalStatus(MaritalStatusMsgCodeEnum.getByCode(saasBorrowerPersonalInfoVo.getMaritalStatus()).getMsg());
            borrowerPersonalInfoVo.setZmCreditScore(saasBorrowerPersonalInfoVo.getZmCreditScore());
        }

        return borrowerPersonalInfoVo;
    }

    public BorrowerIdentityInfoVo getUserIdentityInfoVo(String borrowerCode, String orderNumb) {
        SaasBorrowerIdentityInfoVo saasBorrowerIdentityInfoVo = saasBorrowerIdentityInfoService.getByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);
        if (saasBorrowerIdentityInfoVo == null) {
            return null;
        }
        BorrowerIdentityInfoVo borrowerIdentityInfoVo = new BorrowerIdentityInfoVo();
        borrowerIdentityInfoVo.setFrontUrl(configUtil.getAddressURLPrefix() + saasBorrowerIdentityInfoVo.getFrontUrl());
        borrowerIdentityInfoVo.setBackUrl(configUtil.getAddressURLPrefix() + saasBorrowerIdentityInfoVo.getBackUrl());
        borrowerIdentityInfoVo.setHoldUrl(configUtil.getAddressURLPrefix() + saasBorrowerIdentityInfoVo.getHoldUrl());
        return borrowerIdentityInfoVo;
    }

    public BorrowerWorkInfoVo getUserWorkInfoVo(String borrowerCode, String orderNumb) {
        SaasBorrowerWorkInfoVo saasBorrowerWorkInfoVo = saasBorrowerWorkInfoService.getByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);
        if (saasBorrowerWorkInfoVo == null) {
            return null;
        }
        BorrowerWorkInfoVo borrowerWorkInfoVo = new BorrowerWorkInfoVo();
        BeanUtils.copyProperties(saasBorrowerWorkInfoVo, borrowerWorkInfoVo);
        return borrowerWorkInfoVo;
    }

    public BorrowerEmergentContactVo getUserEmergentContactVo(String borrowerCode, String orderNumb) {
        SaasBorrowerEmergentContactVo saasBorrowerEmergentContactVo = saasBorrowerEmergentContactService.getByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);
        if (saasBorrowerEmergentContactVo == null) {
            return null;
        }
        BorrowerEmergentContactVo borrowerEmergentContactVo = new BorrowerEmergentContactVo();
        BeanUtils.copyProperties(saasBorrowerEmergentContactVo, borrowerEmergentContactVo);
        return borrowerEmergentContactVo;
    }

    public BorrowerLivingAreaVo getUserLivingAreaVo(String borrowerCode, String orderNumb) {
        return null;
    }

}
